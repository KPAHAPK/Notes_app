package com.example.Notes_App.ui.details;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.AppRouteManger;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;
import com.example.Notes_App.domain.NotesStorage;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;


public class NoteDetailsFragment extends Fragment {

    public final static String TAG = "NoteDetailsFragment";

    private static final String ARG_PARAM1 = "param1";

    DatePickerDialog datePickerDialog;
    NoteRepoImpl noteRepo;
    Note note;
    AppRouteManger appRouteManger;
    NotesStorage notesStorage;

    public NoteDetailsFragment() {
    }

    public static NoteDetailsFragment newInstance(Note note) {
        NoteDetailsFragment fragment = new NoteDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (requireActivity() instanceof AppRouteManger) {
            appRouteManger = (AppRouteManger) requireActivity();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        appRouteManger = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        notesStorage = new NotesStorage(requireContext());
        noteRepo = new NoteRepoImpl();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        TextView noteName = view.findViewById(R.id.fragment_note_details_name);
        TextView noteDescription = view.findViewById(R.id.fragment_note_details_description);
        TextView noteDate = view.findViewById(R.id.fragment_note_details_date);

        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_PARAM1);

            noteName.setText(note.getName());
            noteDescription.setText(note.getDescription());
            noteDate.setText(note.getFromatedDate());

        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_details_fragment, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.edit_note_option) {
            Toast.makeText(getContext(), "Edit note", Toast.LENGTH_SHORT).show();
            appRouteManger.showNoteEditor(note);

        }
        if (item.getItemId() == R.id.delete_option) {
            Toast.makeText(getContext(), "Delete note", Toast.LENGTH_SHORT).show();
            new MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setTitle("Are you sure about your decision?")
                    .setMessage("This note will be deleted.\nProceed?")
                    .setPositiveButton("yes", (dialog, which) -> {
                        noteRepo.removeNote(note);
                        notesStorage.setList("notes", noteRepo.getNotes());
                        appRouteManger.back();
                    })
                    .setNegativeButton("No", null).show();

        }
        return super.onOptionsItemSelected(item);
    }
}