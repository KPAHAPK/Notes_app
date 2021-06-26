package com.example.Notes_App.ui.editor;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.AppRouteManger;
import com.example.Notes_App.domain.AppRouter;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepo;
import com.example.Notes_App.domain.NotesFirestoreRepo;
import com.google.android.material.textview.MaterialTextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NoteEditorFragment extends Fragment {

    public static final String EDITED = "EDITED";
    public static final String TAG = "NoteEditorFragment";
    public static final String ARG_PARAM1 = "param1";
    public static final String ARG_RESULT = "RESULT";

    Note note;
    AppCompatEditText noteName;
    AppCompatEditText noteDescription;
    MaterialTextView noteDate;
    AppRouter appRouter;
    long dateMilliseconds;
    //    NotesStorage notesStorage;
    NoteRepo noteRepo = NotesFirestoreRepo.INSTANCE;

    public static NoteEditorFragment newInstance(Note note) {
        NoteEditorFragment fragment = new NoteEditorFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (requireActivity() instanceof AppRouteManger) {
            appRouter = ((AppRouteManger) getActivity()).getAppRouter();

        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            note = getArguments().getParcelable(ARG_PARAM1);
        }
//        notesStorage = new NotesStorage(requireContext());
//        noteRepo = new NoteRepoImpl();
        setHasOptionsMenu(true);
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
        noteDate.setText(note.getFromatedDate());
        dateMilliseconds = note.getDate().getTime();


        noteDate.setOnClickListener(v -> {
            final Calendar cldr = Calendar.getInstance();
            int day = cldr.get(Calendar.DAY_OF_MONTH);
            int month = cldr.get(Calendar.MONTH);
            int year = cldr.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), (view1, year1, month1, dayOfMonth) -> {
                String sDate = String.format(Locale.ENGLISH, "%d/%d/%d", dayOfMonth, month1, year1);
                noteDate.setText(sDate);
                try {
                    Date dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(sDate);
                    if (dateFormat != null) {
                        dateMilliseconds = dateFormat.getTime();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }, year, month, day);
            datePickerDialog.show();
        });

    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_note_editor, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.confirm_edit_option) {
            note.updateNote(String.valueOf(noteName.getText()), String.valueOf(noteDescription.getText()), new Date(dateMilliseconds));

//            notesStorage.setList("notes", noteRepo.getNotes());

            Bundle arg = new Bundle();
            arg.putParcelable(ARG_RESULT, note);
            getParentFragmentManager().setFragmentResult(EDITED, arg);
            appRouter.back();
        }
        return super.onOptionsItemSelected(item);
    }
}