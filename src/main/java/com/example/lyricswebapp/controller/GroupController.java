package com.example.lyricswebapp.controller;

import com.example.lyricswebapp.dto.WordToGroupDTO;
import com.example.lyricswebapp.dto.CreateGroupDTO;
import com.example.lyricswebapp.dto.GroupDTO;
import com.example.lyricswebapp.model.GroupModel;
import com.example.lyricswebapp.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAllGroups() {
        List<GroupDTO> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @PostMapping
    public ResponseEntity<Long> createGroup(@RequestBody CreateGroupDTO request) {
        GroupModel createdGroup = groupService.createGroup(request.getName());
        return ResponseEntity.ok(createdGroup.getId());
    }

    @PutMapping("/add-word")
    public ResponseEntity<Void> addWordToGroup(@RequestBody WordToGroupDTO request) {
        groupService.addWordToGroup(request.getGroupId(), request.getWord());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete-word")
    public ResponseEntity<Void> DeleteWordInGroup(@RequestBody WordToGroupDTO request) {
        groupService.deleteWordFromGroup(request.getGroupId(), request.getWord());
        return ResponseEntity.ok().build();
    }
}