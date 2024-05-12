package com.example.backend.service;

import com.example.backend.exception.custom.ResourceNotFoundException;
import com.example.backend.model.Category;
import com.example.backend.model.Note;
import com.example.backend.model.NoteCategory;
import com.example.backend.model.User;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.repository.NoteCategoryRepository;
import com.example.backend.repository.NoteRepository;
import com.example.backend.repository.UserRepository;
import com.example.backend.request.NoteRequestDTO;
import com.example.backend.response.NoteResponseDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NoteServiceImpl implements NoteService {
    @Autowired
    private NoteRepository noteRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private NoteCategoryRepository noteCategoryRepository;

    @Autowired
    private UserRepository userRepository;
    @Override
    public List<NoteResponseDTO> getAllUnarchivedNotes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found", HttpStatus.NOT_FOUND.value()));

        List<Note> noteList  = noteRepository.findNoteByUserAndArchivedIsFalse(currentUser);
        return getNoteResponseDTOS(noteList);
    }

    @Override
    public List<NoteResponseDTO> getAllArchivedNotes() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User currentUser = userRepository.findByUsername(username).orElseThrow(() -> new ResourceNotFoundException("User not found", HttpStatus.NOT_FOUND.value()));

        List<Note> noteList = noteRepository.findNoteByUserAndArchivedIsTrue(currentUser);
        return getNoteResponseDTOS(noteList);

    }

    @Override
    public NoteResponseDTO createNote(NoteRequestDTO noteRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        User user = userRepository.findByUsername(username).orElseThrow();
        Note note = new Note();
        note.setTitle(noteRequestDTO.getTitle());
        note.setContent(noteRequestDTO.getContent());
        note.setArchived(false);
        note.setUser(user);

        NoteCategory noteCategory = new NoteCategory();
        Optional<Category> existingCategory = categoryRepository.findByName(noteRequestDTO.getCategory());
        Category category = existingCategory.orElseThrow(()-> new ResourceNotFoundException("Category not found", HttpStatus.NOT_FOUND.value()));
        category.setName(noteRequestDTO.getCategory());
        noteCategory.setNote(note);
        noteCategory.setCategory(category);
        noteRepository.save(note);
        noteCategoryRepository.save(noteCategory);

        NoteResponseDTO noteResponseDTO = new NoteResponseDTO();
        BeanUtils.copyProperties(note, noteResponseDTO);
        noteResponseDTO.setCategory(noteRequestDTO.getCategory());
        return noteResponseDTO;
    }

    @Override
    public NoteResponseDTO editNote(Long id, NoteRequestDTO noteRequestDTO) {
        Optional<Note> existingNote = noteRepository.findById(id);
        Note note = existingNote.orElseThrow(()-> new ResourceNotFoundException("Invalid note", HttpStatus.NOT_FOUND.value()));
        BeanUtils.copyProperties(noteRequestDTO, note);
        note.setId(id);
        Optional<NoteCategory> optionalNoteCategory = noteCategoryRepository.findByNote(note);
        noteRepository.save(note);
        if(optionalNoteCategory.isPresent() && !optionalNoteCategory.get().getCategory().getName().equals(noteRequestDTO.getCategory())){
            Optional<Category> existingCategory = categoryRepository.findByName(noteRequestDTO.getCategory());
            Category category = existingCategory.orElseThrow(()-> new ResourceNotFoundException("Category not found", HttpStatus.NOT_FOUND.value()));
            category.setName(noteRequestDTO.getCategory());

            NoteCategory noteCategory = optionalNoteCategory.orElseThrow(()->new ResourceNotFoundException("Invalid NoteCategory", HttpStatus.NOT_FOUND.value()));
            noteCategory.setNote(note);
            noteCategory.setCategory(category);
            noteCategoryRepository.save(noteCategory);
        }

        NoteResponseDTO noteResponseDTO = new NoteResponseDTO();
        BeanUtils.copyProperties(note, noteResponseDTO);
        noteResponseDTO.setCategory(noteRequestDTO.getCategory());
        return noteResponseDTO;
    }

    @Override
    public void editArchiveState(Long id) {
        Optional<Note> existingNote = noteRepository.findById(id);
        Note note = existingNote.orElseThrow(() -> new ResourceNotFoundException("Invalid note", HttpStatus.NOT_FOUND.value()));
        note.setArchived(!existingNote.get().isArchived());
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) {
        Optional<Note> existingNote = noteRepository.findById(id);
        Note note = existingNote.orElseThrow(() -> new ResourceNotFoundException("Invalid note", HttpStatus.NOT_FOUND.value()));
        Optional<NoteCategory> noteCategory = noteCategoryRepository.findByNote(note);
        if(noteCategory.isPresent()){
            noteCategoryRepository.delete(noteCategory.get());
        }
        noteRepository.delete(note);
    }

    @Override
    public NoteResponseDTO getNoteById(Long id) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        Note note = optionalNote.orElseThrow(()-> new ResourceNotFoundException("Invalid note", HttpStatus.NOT_FOUND.value()));

        Optional<NoteCategory> optionalNoteCategory = noteCategoryRepository.findByNote(note);
        NoteCategory noteCategory = optionalNoteCategory.orElseThrow(()->new ResourceNotFoundException("Invalid NoteCategory", HttpStatus.NOT_FOUND.value()));

        Optional<Category> existingCategory = categoryRepository.findByName(noteCategory.getCategory().getName());
        Category category = existingCategory.orElseThrow(()-> new ResourceNotFoundException("Category not found", HttpStatus.NOT_FOUND.value()));

        NoteResponseDTO noteResponseDTO = new NoteResponseDTO();
        BeanUtils.copyProperties(note, noteResponseDTO);
        noteResponseDTO.setCategory(category.getName());
        return noteResponseDTO;
    }

    private List<NoteResponseDTO> getNoteResponseDTOS(List<Note> noteList) {
        return noteList.stream()
                .map(note -> {
                    NoteResponseDTO noteResponseDTO = new NoteResponseDTO();
                    Optional<NoteCategory> optionalNoteCategory = noteCategoryRepository.findByNote(note);
                    NoteCategory noteCategory = optionalNoteCategory.orElseThrow(()->new ResourceNotFoundException("Invalid NoteCategory", HttpStatus.NOT_FOUND.value()));

                    noteResponseDTO.setId(note.getId());
                    noteResponseDTO.setTitle(note.getTitle());
                    noteResponseDTO.setContent(note.getContent());
                    noteResponseDTO.setCategory(noteCategory.getCategory().getName());
                    return noteResponseDTO;
                }).collect(Collectors.toList());
    }
}
