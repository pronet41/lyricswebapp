package com.example.lyricswebapp.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "words")
public class WordModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int location;
    private int verse;
    private int line;
    private int lineLocation;

    @ManyToOne
    @JoinColumn(name = "song_id")
    @JsonBackReference
    private SongModel song;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "words_in_groups",
            joinColumns = @JoinColumn(name = "word_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private Set<GroupModel> groups = new HashSet<>();;

    public Set<GroupModel> getGroups() {
        return groups;
    }

    public void setGroups(Set<GroupModel> groups) {
        this.groups = groups;
    }

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

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getVerse() {
        return verse;
    }

    public void setVerse(int verse) {
        this.verse = verse;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getLineLocation() {
        return lineLocation;
    }

    public void setLineLocation(int lineLocation) {
        this.lineLocation = lineLocation;
    }

    public SongModel getSong() {
        return song;
    }

    public void setSong(SongModel song) {
        this.song = song;
    }

    public Long getSongId() {
        return song != null ? song.getId() : null;
    }
}