package com.example.Notes_App;

import com.example.Notes_App.domain.Note;

public interface AppRouteManger {
   void showNotesList();
   void showNoteDetails(Note note);
   void showAbout();
   void showNoteAddingFragment();
}
