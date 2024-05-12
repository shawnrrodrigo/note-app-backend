package com.example.backend.controller;

import com.example.backend.model.Note;
import com.example.backend.request.NoteRequestDTO;
import com.example.backend.response.NoteResponseDTO;
import com.example.backend.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173/")
@RequestMapping("/note")
public class NoteController {
    @Autowired
    private NoteService noteService;
    @GetMapping
    public ResponseEntity<List<NoteResponseDTO>> getAll(){
       List<NoteResponseDTO> noteResponseDTOS = noteService.getAllUnarchivedNotes();
       return ResponseEntity.ok(noteResponseDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> getNoteById(@PathVariable("id") Long id){
        NoteResponseDTO noteResponseDTO = noteService.getNoteById(id);
        return ResponseEntity.ok(noteResponseDTO);
    }

    @GetMapping("/archived")
    public ResponseEntity<List<NoteResponseDTO>> getAllArchived(){
        List<NoteResponseDTO> noteResponseDTOS = noteService.getAllArchivedNotes();
        return ResponseEntity.ok(noteResponseDTOS);
    }

    @PostMapping
    public ResponseEntity<NoteResponseDTO> createNote(@RequestBody NoteRequestDTO noteRequestDTO){
        NoteResponseDTO noteResponseDTO = noteService.createNote(noteRequestDTO);
        return ResponseEntity.ok(noteResponseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NoteResponseDTO> editNote(@PathVariable("id") Long id, @RequestBody NoteRequestDTO noteRequestDTO){
        NoteResponseDTO noteResponseDTO = noteService.editNote(id, noteRequestDTO);
        return ResponseEntity.ok(noteResponseDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> editArchiveState(@PathVariable("id") Long id){
        noteService.editArchiveState(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable("id") Long id){
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

}
