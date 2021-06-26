package com.example.Notes_App.domain;

import java.util.List;

public interface NoteRepo {

    List<Note> getNotes();

    void getNotes(Callback<List<Note>> callback);

    void addNote(Note note, Callback<Note> callback);

    void removeNote(Note note, Callback<Note> callback);

    boolean addAll(List<Note> list);
}
