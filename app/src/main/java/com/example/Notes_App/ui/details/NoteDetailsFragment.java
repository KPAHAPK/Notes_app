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

import com.example.Notes_App.domain.AppRouteManger;
import com.example.Notes_App.domain.NotesStorage;
import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


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
            noteDate.setText(note.getDate().toString());

        }
//TODO: create DatePicker in Editor
//        noteDate.setOnClickListener(v -> {
//            final Calendar cldr = Calendar.getInstance();
//            int day = cldr.get(Calendar.DAY_OF_MONTH);
//            int month = cldr.get(Calendar.MONTH);
//            int year = cldr.get(Calendar.YEAR);
//            datePickerDialog = new DatePickerDialog(getContext(), (view1, year1, month1, dayOfMonth) -> {
//                String sDate = String.format(Locale.ENGLISH, "%d.%d.%d", dayOfMonth, month1, year1);
//                noteDate.setText(sDate);
//                try {
//                    Date dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH).parse(sDate);
//                    long dateMilliseconds = 0;
//                    if (dateFormat != null) {
//                        dateMilliseconds = dateFormat.getTime();
//                    }
//                    if (getArguments() != null) {
//                        Note note = getArguments().getParcelable(ARG_PARAM1);
//                        note.setDate(dateMilliseconds);
//                    }
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }, year, month, day);
//            datePickerDialog.show();
//        });
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
            //TODO: edit note option

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
                        getParentFragmentManager().popBackStack();
                    })
                    .setNegativeButton("No", null).show();

        }
        return super.onOptionsItemSelected(item);
    }
}