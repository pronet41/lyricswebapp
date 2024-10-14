package com.example.lyricswebapp.dto;

import java.util.List;

public class GroupDTO {
    private Long id;
    private String name;
    private List<Long> wordIds;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getWordIds() {
        return wordIds;
    }

    public void setWordIds(List<Long> wordIds) {
        this.wordIds = wordIds;
    }

}
