package com.example.spring_dz_6;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NoteServiceIntegrationTest {
    @Autowired
    private NoteService noteService;

    @MockBean
    private NoteRepository noteRepository;

    @Test
    public void addNoteCorrectFlow() {
        Note note = new Note();
        note.setTitle("Test Title");
        note.setContent("Test Content");

        when(noteRepository.save(note)).thenReturn(note);

        Note addedNote = noteService.addOrUpdate(note);

        verify(noteRepository).save(note);
    }

    @Test
    public void getNoteByIdFound() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");
        note.setContent("Test Content");

        when(noteRepository.findById(1L)).thenReturn(Optional.of(note));

        Optional<Note> foundNote = noteService.getNoteById(1L);

        verify(noteRepository).findById(1L);
    }

    @Test
    public void deleteNoteById() {
        noteService.deleteNoteById(1L);
        verify(noteRepository).deleteById(1L);
    }
}

