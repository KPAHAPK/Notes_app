package com.example.Notes_App.ui.adding;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class NoteAddingFragment extends Fragment {

    Note note;
    NoteRepoImpl notes;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NoteAddingFragment() {
    }
    public static NoteAddingFragment newInstance() {
        return new NoteAddingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_adding, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.confirm_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = view.findViewById(R.id.fragment_note_adding_name);
                EditText editText1 = view.findViewById(R.id.fragment_note_adding_description);

                String noteName = editText.getText().toString();
                String noteDescription = editText1.getText().toString();
                long date = System.currentTimeMillis();

                note = new Note(noteName, noteDescription, date);


                notes.addNotes(note);

            }
        });


    }
}