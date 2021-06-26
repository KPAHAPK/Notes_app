package com.example.Notes_App.domain;

import java.util.List;

public interface NoteRepo {

    List<Note> getNotes();

    void getNotes(Callback<List<Note>> callback);

    boolean addNote(Note note);

    boolean removeNote(Note note);

    boolean addAll(List<Note> list);
}
