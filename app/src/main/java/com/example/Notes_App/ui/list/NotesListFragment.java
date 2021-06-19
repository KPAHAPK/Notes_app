package com.example.Notes_App.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;
import com.example.Notes_App.ui.NotesAdapter;
import com.example.Notes_App.ui.adding.NoteAddingFragment;

import java.util.List;

public class NotesListFragment extends Fragment {

    NoteRepoImpl noteRepo;

    public interface OnNotesClicked {
        void onNotesClicked(Note note);
    }

    private OnNotesClicked onNotesClicked;

    public NotesListFragment() {
    }

    public static NotesListFragment newInstance() {
        NotesListFragment fragment = new NotesListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        noteRepo = new NoteRepoImpl();

        if (context instanceof OnNotesClicked) {
            onNotesClicked = (OnNotesClicked) context;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.notes_list);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));



        List<Note> noteList = noteRepo.getNotes();

        NotesAdapter notesAdapter = new NotesAdapter();
        notesAdapter.setData(noteList);



        noteRepo = new NoteRepoImpl();

        for (Note note : noteList) {


            TextView noteName = itemView.findViewById(R.id.item_note_name);
            TextView noteDescription = itemView.findViewById(R.id.item_note_description);
            TextView noteDate = itemView.findViewById(R.id.item_note_date);

            noteName.setText(note.getName());
            noteDescription.setText(note.getDescription());
            noteDate.setText(note.getDate().toString());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.setSelected(true);
                    if (onNotesClicked != null) {
                        onNotesClicked.onNotesClicked(note);
                    }
                }
            });

            recyclerView.addView(itemView);
        }
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_note_option) {
            Toast.makeText(getContext(), "Add note", Toast.LENGTH_SHORT).show();
            getChildFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_note_list_container, NoteAddingFragment.newInstance(), "NoteAddingFragment")
                    .commit();
        }
        return super.onOptionsItemSelected(item);
    }
}