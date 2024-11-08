package com.example.lyricswebapp.controller;

import com.example.lyricswebapp.model.GroupModel;
import com.example.lyricswebapp.model.GroupRequest;
import com.example.lyricswebapp.model.WordModel;
import com.example.lyricswebapp.service.WordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/words")
public class WordsController {

    private final WordsService wordsService;

    @Autowired
    public WordsController (WordsService wordsService) {
        this.wordsService = wordsService;
    }

    @GetMapping
    public ResponseEntity<List<WordModel>> getWords(
            @RequestParam(value = "songIds", required = false) List<String> songIds,
            @RequestParam(value = "words", required = false) List<String> wordGroup) {

        // Call service to get words with filtering and sorting
        List<WordModel> words = wordsService.getWords(songIds, wordGroup);
        return ResponseEntity.ok(words);
    }

    @PostMapping("/groups")
    public ResponseEntity<Void> createGroup(@RequestBody GroupRequest request) {
        wordsService.createGroup(request);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/groups/{id}")
    public ResponseEntity<GroupModel> updateGroup(
            @PathVariable Long id,
            @RequestBody GroupRequest request) {
        GroupModel updatedGroup = wordsService.updateGroup(id, request.getWordsInGroup());
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/groups/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        wordsService.deleteGroup(id);
        return ResponseEntity.ok().build();
    }

}
