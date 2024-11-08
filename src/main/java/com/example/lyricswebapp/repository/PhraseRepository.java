package com.example.lyricswebapp.repository;

import com.example.lyricswebapp.model.PhraseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhraseRepository extends JpaRepository<PhraseModel, Long> {

    int deleteByPhraseAndSongId(String phrase, Long songId);

    List<PhraseModel> findBySongId(Long songId);
}
