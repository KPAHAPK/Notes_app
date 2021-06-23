package com.example.Notes_App.ui.creator;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;
import com.example.Notes_App.domain.NotesStorage;

import java.util.UUID;

public class NoteCreatorFragment extends Fragment {

    public static final String TAG = "NoteAddingFragment";

    Note note;
    NoteRepoImpl noteRepo;
    NotesStorage notesStorage;

    EditText editText;
    EditText editText1;


    public NoteCreatorFragment() {
    }

    public static NoteCreatorFragment newInstance() {
        NoteCreatorFragment fragment = new NoteCreatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        noteRepo = new NoteRepoImpl();
        notesStorage = new NotesStorage(requireContext());
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
        editText = view.findViewById(R.id.fragment_note_adding_name);
        editText1 = view.findViewById(R.id.fragment_note_adding_description);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add_note_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.confirm_option) {
            String noteName = editText.getText().toString();
            String noteDescription = editText1.getText().toString();
            long noteDate = System.currentTimeMillis();

            note = new Note(UUID.randomUUID(), noteName, noteDescription, noteDate);
            noteRepo.addNote(note);
            notesStorage.setList("notes", noteRepo.getNotes());

            getParentFragmentManager().popBackStack();


        }
        return super.onOptionsItemSelected(item);
    }
}