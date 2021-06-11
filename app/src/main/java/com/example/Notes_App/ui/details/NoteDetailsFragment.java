package com.example.Notes_App.ui.details;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import com.example.Notes_App.Observer;
import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;
import com.example.Notes_App.ui.list.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class NoteDetailsFragment extends Fragment{

    private static final String ARG_PARAM1 = "param1";

    DatePickerDialog datePickerDialog;
    NoteRepoImpl notes;
    Note note;

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
            Note note = getArguments().getParcelable(ARG_PARAM1);

            noteName.setText(note.getName());
            noteDescription.setText(note.getDescription());
            noteDate.setText(note.getDate().toString());

        }

        noteDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String sDate = String.format("%d.%d.%d", dayOfMonth, month, year);
                        noteDate.setText(sDate);
                        try {
                            Date dateFormat = new SimpleDateFormat("dd.MM.yyyy").parse(sDate);
                            long dateMilliseconds = 0;
                            if (dateFormat != null) {
                                dateMilliseconds = dateFormat.getTime();
                            }
                            if (getArguments() != null) {
                                Note note = getArguments().getParcelable(ARG_PARAM1);
                                note.setDate(dateMilliseconds);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });


    }
}