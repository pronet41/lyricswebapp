package com.example.lyricswebapp.model;

import java.util.List;

public class SongsManageModel {

    private SongModel song;
    private List<WordModel> words;

    public SongModel getSong() {
        return song;
    }

    public void setSong(SongModel song) {
        this.song = song;
    }

    public List<WordModel> getWords() {
        return words;
    }

    public void setWords(List<WordModel> words) {
        this.words = words;
    }
}
