package com.example.backend.service;

import com.example.backend.model.Note;
import com.example.backend.repository.NoteRepository;
import com.example.backend.request.NoteRequestDTO;
import com.example.backend.response.NoteResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NoteService {

    List<NoteResponseDTO> getAllUnarchivedNotes();
    List<NoteResponseDTO> getAllArchivedNotes();

    NoteResponseDTO createNote(NoteRequestDTO noteRequestDTO);

    NoteResponseDTO editNote(Long id, NoteRequestDTO noteRequestDTO);

    void editArchiveState(Long id);

    void deleteNote(Long id);

    NoteResponseDTO getNoteById(Long id);
}
