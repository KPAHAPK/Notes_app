package com.example.Notes_App.ui.list;

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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Notes_App.domain.AppRouteManger;
import com.example.Notes_App.MainActivity;
import com.example.Notes_App.domain.NotesStorage;
import com.example.Notes_App.R;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;

import java.util.List;

public class NotesListFragment extends Fragment {

    public final static String TAG = "NotesListFragment";

    NoteRepoImpl noteRepo;

    AppRouteManger appRouteManger;
    NotesStorage notesStorage;


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
        return inflater.inflate(R.layout.fragment_note_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        noteRepo.addAll(notesStorage.getList("notes"));
        List<Note> noteList = noteRepo.getNotes();

        MainActivity.notesAdapter.setData(noteList);
        MainActivity.notesAdapter.notifyDataSetChanged();
        MainActivity.notesAdapter.setClickListener(note -> appRouteManger.showNoteDetails(note));
        recyclerView.setAdapter(MainActivity.notesAdapter);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_list_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_note_option) {
            appRouteManger.showNoteCreator();
        }
        return super.onOptionsItemSelected(item);
    }
}