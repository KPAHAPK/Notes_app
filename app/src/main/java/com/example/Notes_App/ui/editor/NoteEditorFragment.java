package com.example.Notes_App.ui.editor;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.google.android.material.textview.MaterialTextView;

public class NoteEditorFragment extends Fragment {

    public static final String TAG = "NoteEditorFragment";

    private static final String ARG_PARAM1 = "param1";

    Note note;
    AppCompatEditText noteName;
    AppCompatEditText noteDescription;
    MaterialTextView noteDate;

    public static NoteEditorFragment newInstance(Note note) {
        NoteEditorFragment fragment = new NoteEditorFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_editor, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        noteName = view.findViewById(R.id.fragment_note_editor_name);
        noteDescription = view.findViewById(R.id.fragment_note_editor_description);
        noteDate = view.findViewById(R.id.fragment_note_editor_date);

        noteName.setText(note.getName());
        noteDescription.setText(note.getDescription());
        noteDate.setText(note.getDate());

    }
}