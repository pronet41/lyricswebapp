package com.example.lyricswebapp.controller;

import com.example.lyricswebapp.model.SongModel;
import com.example.lyricswebapp.model.SongsManageModel;
import com.example.lyricswebapp.service.SongsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongsManageController {

    @Autowired
    private SongsManageService uploadService;

    @PostMapping("/upload")
    public ResponseEntity<SongModel> uploadLyrics(@RequestBody SongsManageModel uploadRequestModel) {
        SongModel savedSong = uploadService.saveSongAndWords(uploadRequestModel);
        
        return ResponseEntity.ok(savedSong);
    }

    @GetMapping("")
    public ResponseEntity<List<SongModel>> getAllSongs() {
        List<SongModel> songs = uploadService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/{id}/download")
    public ResponseEntity<SongModel> downloadSong(@PathVariable Long id) {
        SongModel song = uploadService.getSongById(id);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSong(@PathVariable Long id) {
        boolean isDeleted = uploadService.deleteSongById(id);
        if (isDeleted) {
            return ResponseEntity.ok("Song deleted successfully");
        } else {
            return ResponseEntity.status(404).body("Song not found");
        }
    }


}
