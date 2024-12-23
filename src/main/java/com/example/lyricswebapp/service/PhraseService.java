package com.example.lyricswebapp.service;

import com.example.lyricswebapp.dto.PhraseDTO;
import com.example.lyricswebapp.dto.PhraseRequestDTO;
import com.example.lyricswebapp.model.PhraseModel;
import com.example.lyricswebapp.model.SongModel;
import com.example.lyricswebapp.repository.PhraseRepository;
import com.example.lyricswebapp.repository.SongRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhraseService {

    private final PhraseRepository phraseRepository;

    private final SongRepository songRepository;

    @Autowired
    public PhraseService(PhraseRepository phraseRepository, SongRepository songRepository) {
        this.phraseRepository = phraseRepository;
        this.songRepository = songRepository;
    }

    @Transactional
    public PhraseModel createUserDefinedPhrase(PhraseRequestDTO request) {
        // Find the song by ID
        SongModel song = songRepository.findById(request.getSongId())
                .orElseThrow(() -> new RuntimeException("Song not found"));

        // Create and save the PhraseModel
        PhraseModel phrase = new PhraseModel();
        phrase.setPhrase(request.getPhrase());
        phrase.setSong(song);

        return phraseRepository.save(phrase);
    }

    @Transactional
    public boolean deletePhrase(String phrase, Long songId) {
        int deletedCount = phraseRepository.deleteByPhraseAndSongId(phrase, songId);
        return deletedCount > 0;
    }

    public List<PhraseDTO> getPhrasesBySongId(Long songId) {
        List<PhraseModel> phrases = phraseRepository.findBySongId(songId);
        return phrases.stream()
                .map(phrase -> new PhraseDTO(phrase.getId(), phrase.getPhrase()))
                .collect(Collectors.toList());
    }
}
