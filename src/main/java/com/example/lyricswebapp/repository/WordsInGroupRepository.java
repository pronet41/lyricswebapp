package com.example.lyricswebapp.repository;

import com.example.lyricswebapp.model.WordsInGroupModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordsInGroupRepository extends JpaRepository<WordsInGroupModel, Long> {

}
