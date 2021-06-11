package com.example.Notes_App.ui.list;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;
import com.example.Notes_App.ui.adding.NoteAddingActivity;
import com.example.Notes_App.ui.details.NoteDetailsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class NotesListFragment extends Fragment {

    public interface OnNotesClicked{
        void onNotesClicked(Note note);
    }

    private NoteRepoImpl notes;

    private OnNotesClicked onNotesClicked;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public NotesListFragment() {
    }

    public static NotesListFragment newInstance(String param1, String param2) {
        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof OnNotesClicked){
            onNotesClicked = (OnNotesClicked) context;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        notes = new NoteRepoImpl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FloatingActionButton fab = view.findViewById(R.id.add_button);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), NoteAddingActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linearLayout = view.findViewById(R.id.note_list);

        List<Note> noteList = notes.getNotes();

        for (Note note: noteList){

            View itemView = LayoutInflater.from(requireContext()).inflate(R.layout.item_note,linearLayout, false);

            TextView noteName = itemView.findViewById(R.id.item_note_name);
            TextView noteDescription = itemView.findViewById(R.id.item_note_description);
            TextView noteDate = itemView.findViewById(R.id.item_note_date);

            noteName.setText(note.getName());
            noteDescription.setText(note.getDescription());
            noteDate.setText(note.getDate().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onNotesClicked != null) {
                        onNotesClicked.onNotesClicked(note);
                    }
                }
            });

            linearLayout.addView(itemView);
        }

    }
}