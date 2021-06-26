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
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Notes_App.R;
import com.example.Notes_App.domain.AppRouteManger;
import com.example.Notes_App.domain.AppRouter;
import com.example.Notes_App.domain.Callback;
import com.example.Notes_App.domain.Note;
import com.example.Notes_App.domain.NoteRepo;
import com.example.Notes_App.domain.NotesAdapter;
import com.example.Notes_App.domain.NotesFirestoreRepo;
import com.example.Notes_App.ui.creator.NoteCreatorFragment;
import com.example.Notes_App.ui.details.NoteDetailsFragment;
import com.example.Notes_App.ui.editor.NoteEditorFragment;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.List;

public class NotesListFragment extends Fragment {

    public final static String TAG = "NotesListFragment";

    NoteRepo noteRepo = NotesFirestoreRepo.INSTANCE;
    AppRouter appRouter;
    //    NotesStorage notesStorage;
    private int longClickIndex;
    private Note longClickNote;
    public NotesAdapter notesAdapter;
    private boolean isLoading = false;
    private ProgressBar progressBar;

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
            appRouter = ((AppRouteManger) getActivity()).getAppRouter();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        appRouter = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        notesStorage = new NotesStorage(requireContext());
//        noteRepo = new NoteRepoImpl();
        notesAdapter = new NotesAdapter(this);

        isLoading = true;

        noteRepo.getNotes(result -> {
            notesAdapter.setData(result);
            notesAdapter.notifyDataSetChanged();

            isLoading = false;

            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
            }
        });

        notesAdapter.setClickListener(appRouter::showNoteDetails);

        notesAdapter.setLongClickListener((note, index) -> {
            longClickIndex = index;
            longClickNote = note;
        });

        getParentFragmentManager().setFragmentResultListener(NoteEditorFragment.EDITED, this, (requestKey, result) -> {
            if (result.containsKey(NoteEditorFragment.ARG_RESULT)) {
                Note editedNote = result.getParcelable(NoteEditorFragment.ARG_RESULT);
                notesAdapter.updateNote(editedNote);
                notesAdapter.notifyItemChanged(longClickIndex);
            }
        });

        getParentFragmentManager().setFragmentResultListener(NoteCreatorFragment.CREATE, this, (requestKey, result) -> {
            if (result.containsKey(NoteCreatorFragment.NEW_NOTE)) {
                Note createdNote = result.getParcelable(NoteCreatorFragment.NEW_NOTE);
                notesAdapter.add(createdNote);
                int position = notesAdapter.getIndex(createdNote);
                notesAdapter.notifyItemInserted(position);
            }
        });

        getParentFragmentManager().setFragmentResultListener(NoteDetailsFragment.REMOVE, this, (requestKey, result) -> {
            if (result.containsKey(NoteDetailsFragment.REMOVED_NOTES)) {
                Note removedNote = result.getParcelable(NoteDetailsFragment.REMOVED_NOTES);
                int position = notesAdapter.getIndex(removedNote);
                notesAdapter.removeNote(removedNote);
                notesAdapter.notifyItemRemoved(position);
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

        progressBar = view.findViewById(R.id.progress);

        if (isLoading) {
            progressBar.setVisibility(View.VISIBLE);
        }

//        noteRepo.addAll(notesStorage.getList("notes"));
//        List<Note> noteList = noteRepo.getNotes();

//        notesAdapter.setData(noteList);
//        notesAdapter.notifyDataSetChanged();
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
            appRouter.showNoteEditor(longClickNote);
            return true;
        }

        if (item.getItemId() == R.id.action_delete) {
            new MaterialAlertDialogBuilder(requireContext())
                    .setIcon(R.drawable.ic_baseline_warning_24)
                    .setTitle("Are you sure about your decision?")
                    .setMessage("This note will be deleted.\nProceed?")
                    .setPositiveButton("yes", (dialog, which) -> {
                        noteRepo.removeNote(longClickNote, result -> {
                            notesAdapter.removeNote(longClickNote);
                            notesAdapter.notifyItemRemoved(longClickIndex);
                        });
//                        notesStorage.setList("notes", noteRepo.getNotes());
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
            appRouter.showNoteCreator();
        }
        if (item.getItemId() == R.id.clear_all_option) {
        }
        return super.onOptionsItemSelected(item);
    }
}