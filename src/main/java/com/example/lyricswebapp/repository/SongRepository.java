package com.example.lyricswebapp.repository;

import com.example.lyricswebapp.model.SongModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends JpaRepository<SongModel, Long> {

}
