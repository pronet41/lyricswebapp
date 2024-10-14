package com.example.lyricswebapp.repository;

import com.example.lyricswebapp.model.PhraseModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PhraseRepository extends JpaRepository<PhraseModel, Long> {
    Optional<PhraseModel> findByPhrase(String phrase);

    @Query("SELECT p.phrase, COUNT(p.phrase) AS occurrences " +
            "FROM PhraseModel p " +
            "GROUP BY p.phrase")
    List<Object[]> findRecurringPhrases();
}
