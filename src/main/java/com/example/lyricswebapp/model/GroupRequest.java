package com.example.lyricswebapp.model;
import java.util.List;

public class GroupRequest {

    private String groupName;
    private List<String> wordsInGroup;


    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public List<String> getWordsInGroup() {
        return wordsInGroup;
    }

    public void setWordsInGroup(List<String> wordsInGroup) {
        this.wordsInGroup = wordsInGroup;
    }
}
