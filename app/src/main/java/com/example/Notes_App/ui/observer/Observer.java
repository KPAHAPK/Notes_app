package com.example.Notes_App.ui.observer;

import com.example.Notes_App.domain.Note;

public interface Observer {
    void updateNote(Note note);
}
