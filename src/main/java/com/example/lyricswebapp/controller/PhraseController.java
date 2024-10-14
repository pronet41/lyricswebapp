package com.example.lyricswebapp.controller;

import com.example.lyricswebapp.dto.PhraseOccurrenceDTO;
import com.example.lyricswebapp.service.PhraseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/analysis")
public class PhraseController {

    private final PhraseService phraseService;

    @Autowired
    public PhraseController(PhraseService phraseService) {
        this.phraseService = phraseService;
    }

    @GetMapping("/phrases")
    public List<PhraseOccurrenceDTO> getRecurringPhrases() {
        return phraseService.getRecurringPhrases();
    }
}
