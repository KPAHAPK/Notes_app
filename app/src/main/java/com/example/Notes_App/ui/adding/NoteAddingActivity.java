package com.example.Notes_App.ui.adding;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.Notes_App.R;

public class NoteAddingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_adding);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.note_adding_activity, NoteAddingFragment.newInstance())
                .commit();
    }
}