package com.example.lyricswebapp.repository;

import com.example.lyricswebapp.model.SongModel;
import com.example.lyricswebapp.model.WordModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<WordModel, Long> {

    List<WordModel> findAllByName(String wordName);
}
