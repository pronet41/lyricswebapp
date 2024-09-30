package com.example.lyricswebapp.model;

import jakarta.persistence.*;

@Entity
@Table(name = "words_in_groups")
public class WordsInGroupModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private GroupModel group;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private WordModel word;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public GroupModel getGroup() {
        return group;
    }

    public void setGroup(GroupModel group) {
        this.group = group;
    }

    public WordModel getWord() {
        return word;
    }

    public void setWord(WordModel word) {
        this.word = word;
    }
}