package com.example.lyricswebapp.service;


import com.example.lyricswebapp.model.SongModel;
import com.example.lyricswebapp.model.SongsManageModel;
import com.example.lyricswebapp.model.WordModel;
import com.example.lyricswebapp.repository.SongRepository;
import com.example.lyricswebapp.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongsManageService {

    private final SongRepository songRepository;

    private final WordRepository wordRepository;

    @Autowired
    public SongsManageService (SongRepository songRepository, WordRepository wordRepository) {
        this.songRepository = songRepository;
        this.wordRepository = wordRepository;
    }

    public SongModel saveSongAndWords(SongsManageModel uploadRequestModel) {
        SongModel songModel = uploadRequestModel.getSong();
        SongModel song = new SongModel();
        song.setName(songModel.getName());
        song.setArtist(songModel.getArtist());
        song.setGenre(songModel.getGenre());

        song = songRepository.save(song);

        List<WordModel> wordModels = uploadRequestModel.getWords();
        for (WordModel wordModel : wordModels) {
            WordModel word = new WordModel();
            word.setName(wordModel.getName());
            word.setLocation(wordModel.getLocation());
            word.setVerse(wordModel.getVerse());
            word.setLine(wordModel.getLine());
            word.setLineLocation(wordModel.getLineLocation());
            word.setSong(song);
            wordRepository.save(word);
        }
        song.setWords(wordModels);

        return song;
    }

    public List<SongModel> getAllSongs() {
        return songRepository.findAll();
    }

    public SongModel getSongById(long id) {
        return  songRepository.getReferenceById(id);
    }

    public boolean deleteSongById(Long id) {
        if (songRepository.existsById(id)) {
            songRepository.deleteById(id);
            return true;  // Song deleted successfully
        }
        return false;  // Song not found
    }
}


