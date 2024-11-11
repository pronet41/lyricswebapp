package com.example.lyricswebapp.dto;

public class SongStatisticsDTO {
    private Long songId;
    private double avgWordsPerSong;
    private double avgWordsPerVerse;
    private double avgWordsPerLine;
    private double avgWordLength;

    public SongStatisticsDTO(Long songId, double avgWordsPerSong, double avgWordsPerVerse, double avgWordsPerLine, double avgWordLength) {
        this.songId = songId;
        this.avgWordsPerSong = avgWordsPerSong;
        this.avgWordsPerVerse = avgWordsPerVerse;
        this.avgWordsPerLine = avgWordsPerLine;
        this.avgWordLength = avgWordLength;
    }

    // Getters and setters
    public Long getSongId() {
        return songId;
    }

    public void setSongId(Long songId) {
        this.songId = songId;
    }

    public double getAvgWordsPerSong() {
        return avgWordsPerSong;
    }

    public void setAvgWordsPerSong(double avgWordsPerSong) {
        this.avgWordsPerSong = avgWordsPerSong;
    }

    public double getAvgWordsPerVerse() {
        return avgWordsPerVerse;
    }

    public void setAvgWordsPerVerse(double avgWordsPerVerse) {
        this.avgWordsPerVerse = avgWordsPerVerse;
    }

    public double getAvgWordsPerLine() {
        return avgWordsPerLine;
    }

    public void setAvgWordsPerLine(double avgWordsPerLine) {
        this.avgWordsPerLine = avgWordsPerLine;
    }

    public double getAvgWordLength() {
        return avgWordLength;
    }

    public void setAvgWordLength(double avgWordLength) {
        this.avgWordLength = avgWordLength;
    }
}
