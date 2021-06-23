package com.example.Notes_App.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.AppRouteManger;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepoImpl;
import com.example.Notes_App.domain.NotesAdapter;
import com.example.Notes_App.domain.NotesStorage;
import com.example.Notes_App.ui.editor.NoteEditorFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class NotesListFragment extends Fragment {

    public final static String TAG = "NotesListFragment";

    NoteRepoImpl noteRepo;
    AppRouteManger appRouteManger;
    NotesStorage notesStorage;
    private int longClickIndex;
    private Note longClickNote;
    public NotesAdapter notesAdapter;

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
        notesAdapter = new NotesAdapter(this);

        notesAdapter.setClickListener(appRouteManger::showNoteDetails);

        notesAdapter.setLongClickListener((note, index) -> {
            longClickIndex = index;
            longClickNote = note;
        });

        getParentFragmentManager().setFragmentResultListener(NoteEditorFragment.EDITED, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                if (result.containsKey(NoteEditorFragment.ARG_RESULT)){
                    notesAdapter.updateNote(result.getParcelable(NoteEditorFragment.ARG_RESULT));
                    notesAdapter.notifyItemChanged(longClickIndex);
                }
            }
        });
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

        DefaultItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(1500L);
        animator.setAddDuration(1500L);
        animator.setChangeDuration(1500L);
        recyclerView.setItemAnimator(animator);

        noteRepo.addAll(notesStorage.getList("notes"));
        List<Note> noteList = noteRepo.getNotes();

        notesAdapter.setData(noteList);
        notesAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(notesAdapter);
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        requireActivity().getMenuInflater().inflate(R.menu.menu_note_context, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.action_edit) {
            appRouteManger.showNoteEditor(longClickNote);
            return true;
        }

        if (item.getItemId() == R.id.action_delete) {
            new MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setTitle("Are you sure about your decision?")
                    .setMessage("This note will be deleted.\nProceed?")
                    .setPositiveButton("yes", (dialog, which) -> {
                        noteRepo.removeNote(longClickNote);
                        notesAdapter.removeNote(longClickNote);
                        notesAdapter.notifyItemRemoved(longClickIndex);
                        notesStorage.setList("notes", noteRepo.getNotes());
                    })
                    .setNegativeButton("No", null).show();
            return true;
        }

        return super.onContextItemSelected(item);
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