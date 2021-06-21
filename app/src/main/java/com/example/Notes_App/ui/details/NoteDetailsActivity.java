package com.example.Notes_App.ui.details;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;

public class NoteDetailsActivity extends AppCompatActivity {

    public static final String KEY = "KEY";

    Note note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_details);

        if (savedInstanceState == null) {

            note = getIntent().getParcelableExtra(KEY);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.note_details_activity, NoteDetailsFragment.newInstance(note))
                    .commit();

        }

    }
}