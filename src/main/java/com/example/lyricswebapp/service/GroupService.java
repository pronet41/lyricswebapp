package com.example.lyricswebapp.service;


import com.example.lyricswebapp.dto.GroupDTO;
import com.example.lyricswebapp.model.GroupModel;
import com.example.lyricswebapp.model.WordModel;
import com.example.lyricswebapp.repository.GroupRepository;
import com.example.lyricswebapp.repository.WordRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    private final GroupRepository groupRepository;

    private final WordRepository wordRepository;

    @Autowired
    public GroupService (WordRepository wordRepository, GroupRepository groupRepository) {
        this.wordRepository = wordRepository;
        this.groupRepository = groupRepository;
    }

    public List<GroupDTO> getAllGroups() {
        List<GroupModel> groups = groupRepository.findAll();

        return groups.stream().map(group -> {
            GroupDTO dto = new GroupDTO();
            dto.setId(group.getId());
            dto.setName(group.getName());
            dto.setWordIds(group.getWords().stream()
                    .map(word -> word.getId())
                    .collect(Collectors.toList()));
            return dto;
        }).collect(Collectors.toList());
    }

    public GroupModel createGroup(String name) {
        GroupModel group = new GroupModel();
        group.setName(name);
        return groupRepository.save(group);
    }

    @Transactional
    public void addWordToGroup(Long groupId, String word) {
        // Find the group by ID
        GroupModel group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Find all occurrences of the word in the words table
        List<WordModel> words = wordRepository.findAllByName(word);

        // Save the words in the table
        for (WordModel wordModel : words) {
            wordModel.getGroups().add(group);
            wordRepository.save(wordModel);
        }
    }

    @Transactional
    public void deleteWordFromGroup(Long groupId, String word) {
        // Find the group by ID
        GroupModel group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // Find all occurrences of the word in the words table
        List<WordModel> words = wordRepository.findAllByName(word);

        // Remove the word IDs from the group's words
        for (WordModel wordModel : words) {
            wordModel.getGroups().remove(group);
            wordRepository.save(wordModel);
        }
    }
}
