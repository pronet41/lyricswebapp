package com.example.lyricswebapp.controller;

import com.example.lyricswebapp.dto.PhraseDTO;
import com.example.lyricswebapp.dto.PhraseRequestDTO;
import com.example.lyricswebapp.model.PhraseModel;
import com.example.lyricswebapp.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class PhraseController {

    private final PhraseService phraseService;

    @Autowired
    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @PostMapping("/phrases/create")
    public ResponseEntity<PhraseModel> createPhrase(@RequestBody PhraseRequestDTO createPhraseRequest) {
        PhraseModel savedPhrase = phraseService.createUserDefinedPhrase(createPhraseRequest);
        return ResponseEntity.ok(savedPhrase);
    }

    @DeleteMapping("/phrases/delete")
    public ResponseEntity<String> deletePhrase(@RequestBody PhraseRequestDTO deleteRequestDTO) {
        boolean isDeleted = phraseService.deletePhrase(deleteRequestDTO.getPhrase(), deleteRequestDTO.getSongId());
        if (isDeleted) {
            return ResponseEntity.ok("Phrase deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Phrase not found for the specified song");
        }
    }

    @GetMapping("/phrases/{songId}")
    public ResponseEntity<List<PhraseDTO>> getPhrasesBySongId(@PathVariable Long songId) {
        List<PhraseDTO> phrases = phraseService.getPhrasesBySongId(songId);
        return ResponseEntity.ok(phrases);
    }

}
