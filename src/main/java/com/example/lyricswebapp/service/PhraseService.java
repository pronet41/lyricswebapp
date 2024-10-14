package com.example.lyricswebapp.service;

import com.example.lyricswebapp.dto.PhraseOccurrenceDTO;
import org.springframework.data.util.Pair;
import com.example.lyricswebapp.model.PhraseModel;
import com.example.lyricswebapp.model.SongModel;
import com.example.lyricswebapp.model.WordModel;
import com.example.lyricswebapp.repository.PhraseRepository;
import com.example.lyricswebapp.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PhraseService {

    private final PhraseRepository phraseRepository;
    private final SongRepository songRepository;

    @Autowired
    public PhraseService(PhraseRepository phraseRepository, SongRepository songRepository) {
        this.phraseRepository = phraseRepository;
        this.songRepository = songRepository;
    }

    public void savePhrasesFromSong(SongModel song) {
        // Retrieve the words for the song
        List<WordModel> words = song.getWords();
        Set<Pair<String, Integer>> detectedPhrases = detectPhrases(words);

        // Save the detected phrases
        for (Pair<String, Integer> phraseWithLocation : detectedPhrases) {
            String phrase = phraseWithLocation.getFirst();
            Integer location = phraseWithLocation.getSecond();

            PhraseModel phraseModel = new PhraseModel();
            phraseModel.setPhrase(phrase);
            phraseModel.setStartIndex(location);
            phraseModel.setSong(song);
            phraseModel.setLength(phrase.length());
            phraseRepository.save(phraseModel);
        }
    }

    public List<PhraseOccurrenceDTO> getRecurringPhrases() {
        // This query counts occurrences of each phrase and groups them
        List<Object[]> results = phraseRepository.findRecurringPhrases();

        // Convert the result into a list of PhraseOccurrenceDTOs
        List<PhraseOccurrenceDTO> occurrences = new ArrayList<>();
        for (Object[] result : results) {
            String phrase = (String) result[0];
            Long occurrenceCount = (Long) result[1];
            occurrences.add(new PhraseOccurrenceDTO(phrase, occurrenceCount));
        }

        return occurrences;
    }

    private Set<Pair<String, Integer>> detectPhrases(List<WordModel> words) {
        Set<Pair<String, Integer>> detectedPhrases = new HashSet<>();

        // Logic to automatically detect repeating sequences of words
        for (int i = 0; i < words.size(); i++) {
            for (int j = i + 1; j < words.size(); j++) {
                // Compare words and detect recurring phrases
                StringBuilder phrase = new StringBuilder();
                for (int k = i; k <= j; k++) {
                    phrase.append(words.get(k).getName()).append(" ");
                }
                String finalPhrase = phrase.toString().trim();

                // Add the phrase and its starting location to the set
                detectedPhrases.add(Pair.of(finalPhrase, words.get(i).getLocation()));
            }
        }
        return detectedPhrases;
    }
}
