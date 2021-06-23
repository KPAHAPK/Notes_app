package com.example.Notes_App.domain;

import java.util.ArrayList;
import java.util.List;

public class NoteRepoImpl implements NoteRepo {

    private static final List<Note> notes = new ArrayList<>();

    @Override
    public List<Note> getNotes() {

        return notes;
    }

    @Override
    public boolean addNote(Note note) {
        notes.add(note);
        return true;
    }

    @Override
    public boolean removeNote(Note note) {
        notes.remove(note);
        return true;
    }

    @Override
    public boolean addAll(List<Note> list) {
        if (list == null) {
            return false;
        }
        notes.clear();
        notes.addAll(list);
        return true;
    }
}
