package com.example.Notes_App.domain;

import java.util.List;

public interface NoteRepo {
    public List<Note> getNotes();
    public void addNotes(Note note);
}
