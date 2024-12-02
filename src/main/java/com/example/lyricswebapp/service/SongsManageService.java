package com.example.lyricswebapp.service;


import com.example.lyricswebapp.dto.SongStatisticsDTO;
import com.example.lyricswebapp.model.SongModel;
import com.example.lyricswebapp.model.SongsManageModel;
import com.example.lyricswebapp.model.WordModel;
import com.example.lyricswebapp.repository.SongRepository;
import com.example.lyricswebapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class SongsManageService {

    private final SongRepository songRepository;

    private final WordRepository wordRepository;

    @Autowired
    public SongsManageService (SongRepository songRepository, WordRepository wordRepository) {
        this.songRepository = songRepository;
        this.wordRepository = wordRepository;
    }

    public SongModel saveSongAndWords(SongsManageModel uploadRequestModel) {
        SongModel songModel = uploadRequestModel.getSong();
        SongModel song = new SongModel();
        song.setName(songModel.getName());
        song.setArtist(songModel.getArtist());
        song.setGenre(songModel.getGenre());

        song = songRepository.save(song);

        List<WordModel> wordModels = uploadRequestModel.getWords();
        for (WordModel wordModel : wordModels) {
            WordModel word = new WordModel();
            word.setName(wordModel.getName());
            word.setLocation(wordModel.getLocation());
            word.setVerse(wordModel.getVerse());
            word.setLine(wordModel.getLine());
            word.setLineLocation(wordModel.getLineLocation());
            word.setSong(song);
            wordRepository.save(word);
        }
        song.setWords(wordModels);

        return song;
    }

    public List<SongModel> getAllSongs() {
        return songRepository.findAll();
    }

    public SongModel getSongById(long id) {
        return  songRepository.getReferenceById(id);
    }

    public boolean deleteSongById(Long id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return true;  // Song deleted successfully
        }
        return false;  // Song not found
    }


    public List<SongStatisticsDTO> calculateStatistics(List<Long> songIds) {
        // Fetch the songs based on the provided song IDs
        // This retrieves the SongModel objects including their associated words
        List<SongModel> songs = songRepository.findAllById(songIds);

        // Process each song to calculate its statistics
        return songs.stream().map(song -> {
            // Retrieve the list of words for the current song
            List<WordModel> words = song.getWords();

            // Calculate the average number of words per verse
            // Step 1: Group words by their verse number
            // Step 2: Get the size of each group (number of words in each verse)
            // Step 3: Calculate the average size of these groups
            double avgWordsPerVerse = words.stream()
                    .collect(Collectors.groupingBy(WordModel::getVerse)) // Group by verse
                    .values() // Get the grouped lists
                    .stream()
                    .mapToInt(List::size) // Get the size of each list (number of words in the verse)
                    .average() // Calculate the average
                    .orElse(0);

            // Calculate the average number of words per line
            // Step 1: Group words by verse
            // Step 2: For each verse, group words by line and calculate the average number of words per line
            // Step 3: Calculate the overall average of the per-verse averages
            double avgWordsPerLine = words.stream()
                    .collect(Collectors.groupingBy(WordModel::getVerse)) // Group by verse
                    .values() // Get the grouped lists of words per verse
                    .stream()
                    .mapToDouble(verseWords -> verseWords.stream()
                            .collect(Collectors.groupingBy(WordModel::getLine)) // Group by line within each verse
                            .values()
                            .stream()
                            .mapToInt(List::size) // Get the size of each line group (number of words in the line)
                            .average() // Calculate the average number of words per line within the verse
                            .orElse(0))
                    .average() // Calculate the overall average across all verses
                    .orElse(0);

            // Calculate the average length of words in the song
            // Step 1: Get the length of each word (number of characters in its name)
            // Step 2: Calculate the average length
            double avgWordLength = words.stream()
                    .mapToInt(word -> word.getName().length()) // Get the length of each word
                    .average() // Calculate the average
                    .orElse(0);

            // Create and return a SongStatisticsDTO for the current song
            // This includes the song ID, total number of words, and the calculated averages
            return new SongStatisticsDTO(
                    song.getId(), // Song ID
                    words.size(), // Total number of words in the song
                    avgWordsPerVerse, // Average words per verse
                    avgWordsPerLine, // Average words per line (calculated per verse)
                    avgWordLength // Average word length
            );
        }).collect(Collectors.toList()); // Collect results into a list
    }

}


