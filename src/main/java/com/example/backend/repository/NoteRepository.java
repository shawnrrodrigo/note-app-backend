package com.example.backend.repository;

import com.example.backend.model.Note;
import com.example.backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {
     List<Note> findNoteByUserAndArchivedIsFalse(User user);
     List<Note> findNoteByUserAndArchivedIsTrue(User user);
}
