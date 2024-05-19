package com.example.spring_dz_6;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(Long id) {
        super("Note not found with id: " + id);
    }
}

