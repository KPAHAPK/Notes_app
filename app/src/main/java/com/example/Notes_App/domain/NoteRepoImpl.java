package com.example.Notes_App.domain;

import java.util.ArrayList;
import java.util.List;

public class NoteRepoImpl implements NoteRepo {

    public List<Note> notes = new ArrayList<>();


    @Override
    public List<Note> getNotes() {
        notes.add(new Note("24124124fasfawrwefwewfew","fsfsdsaeasdsaeew",System.currentTimeMillis()));
        return notes;
    }

    @Override
    public boolean addNote(Note note) {
        notes.add(note);
        return true;
    }

    @Override
    public boolean removeNote(Note note) {
        return notes.remove(note);
    }
}
