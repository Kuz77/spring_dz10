package com.example.spring_dz_6;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class NoteServiceUnitTest {
    @InjectMocks
    private NoteService noteService;

    @Mock
    private NoteRepository noteRepository;

    @Test
    public void addNoteCorrectFlow() {
        Note note = new Note();
        note.setTitle("Test Title");
        note.setContent("Test Content");

        given(noteRepository.save(note)).willReturn(note);

        Note addedNote = noteService.addOrUpdate(note);

        verify(noteRepository).save(note);
    }

    @Test
    public void getNoteByIdFound() {
        Note note = new Note();
        note.setId(1L);
        note.setTitle("Test Title");
        note.setContent("Test Content");

        given(noteRepository.findById(1L)).willReturn(Optional.of(note));

        Optional<Note> foundNote = noteService.getNoteById(1L);

        verify(noteRepository).findById(1L);
    }

    @Test
    public void getNoteByIdNotFound() {
        given(noteRepository.findById(1L)).willReturn(Optional.empty());

        assertThrows(NoteNotFoundException.class, () -> noteService.getNoteById(1L));

        verify(noteRepository).findById(1L);
    }

    @Test
    public void deleteNoteById() {
        noteService.deleteNoteById(1L);
        verify(noteRepository).deleteById(1L);
    }
}
