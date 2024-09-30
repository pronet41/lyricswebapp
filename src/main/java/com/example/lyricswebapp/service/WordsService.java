package com.example.lyricswebapp.service;

import com.example.lyricswebapp.model.*;
import com.example.lyricswebapp.repository.GroupRepository;
import com.example.lyricswebapp.repository.SongRepository;
import com.example.lyricswebapp.repository.WordRepository;
import com.example.lyricswebapp.repository.WordsInGroupRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WordsService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private SongRepository songRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private WordsInGroupRepository wordsInGroupRepository;

    public List<WordModel> getWords(List<String> songIds, List<String> wordGroup) {
        List<SongModel> songs = new ArrayList<>();
        if (songIds != null && !songIds.isEmpty()) {
            songs = songRepository.findAllById(
                    songIds.stream().map(Long::parseLong).collect(Collectors.toList())
            );
        }

        List<WordModel> words;

        if (!songs.isEmpty() && wordGroup != null && !wordGroup.isEmpty()) {
            words = wordRepository.findBySongInAndNameIn(songs, wordGroup);
        } else if (!songs.isEmpty()) {
            words = wordRepository.findBySongIn(songs);
        } else if (wordGroup != null && !wordGroup.isEmpty()) {
            words = wordRepository.findByNameIn(wordGroup);
        } else {
            words = wordRepository.findAll();
        }

        // Sort alphabetically by word name
        words.sort(Comparator.comparing(WordModel::getName));
        return words;
    }

    public void createGroup(GroupRequest request) {
        GroupModel group = new GroupModel();
        group.setName(request.getGroupName());
        groupRepository.save(group);

        List<WordModel> words = wordRepository.findByNameIn(request.getWordsInGroup());

        for (WordModel word : words) {
            WordsInGroupModel wordsInGroup = new WordsInGroupModel();
            wordsInGroup.setGroup(group);
            wordsInGroup.setWord(word);
            wordsInGroupRepository.save(wordsInGroup);
        }
    }

    @Transactional
    public GroupModel updateGroup(Long groupId, List<String> wordNames) {
        // Find the group by ID
        GroupModel group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Clear the current words in the group
        group.getWords().clear();

        // Add words by their names to the group
        for (String wordName : wordNames) {
            WordModel word = wordRepository.findByName(wordName);
            group.getWords().add(word);
            word.getGroups().add(group);  // Add the group to the word as well
        }

        // Save the group and the words
        groupRepository.save(group);
        wordRepository.saveAll(group.getWords());

        return group;
    }

    public void deleteGroup(Long id) {
        if (groupRepository.existsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new RuntimeException("Group not found");
        }
    }
}
