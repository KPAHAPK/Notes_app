package com.example.Notes_App.domain;

public interface AppRouteManger {
    void showNotesList();

    void showNoteDetails(Note note);

    void showAbout();

    void showNoteCreator();

    void showNoteEditor(Note note);

    void back();
}
