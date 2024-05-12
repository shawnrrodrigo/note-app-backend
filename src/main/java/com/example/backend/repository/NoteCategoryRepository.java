package com.example.backend.repository;

import com.example.backend.model.Category;
import com.example.backend.model.Note;
import com.example.backend.model.NoteCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteCategoryRepository extends JpaRepository<NoteCategory, Long> {
    Optional<NoteCategory> findByNote(Note note);
}
