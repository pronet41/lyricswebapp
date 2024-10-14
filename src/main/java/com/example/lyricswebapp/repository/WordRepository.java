package com.example.lyricswebapp.repository;

import com.example.lyricswebapp.model.SongModel;
import com.example.lyricswebapp.model.WordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<WordModel, Long> {

    // Find words by multiple songs
    List<WordModel> findBySongIn(List<SongModel> songs);

    // Find words by multiple songs and a word group
    List<WordModel> findBySongInAndNameIn(List<SongModel> songs, List<String> wordNames);

    // Find words in a given list
    List<WordModel> findByNameIn(List<String> wordNames);

    WordModel findByName(String wordName);

    List<WordModel> findAllByName(String wordName);
}
