package com.example.Notes_App.domain;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Notes_App.R;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NoteViewHolder> {

    public interface OnNoteViewClickListener {
        void onNoteClickListener(@NonNull Note note);
    }

    OnNoteViewClickListener clickListener;

    public OnNoteViewClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnNoteViewClickListener clickListener) {
        this.clickListener = clickListener;
    }

    private final List<Note> notesList = new ArrayList<>();

    public void setData(List<Note> list) {
        notesList.clear();
        notesList.addAll(list);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.NoteViewHolder holder, int position) {

        Note note = notesList.get(position);

        holder.noteName.setText(note.getName());
        holder.noteDescription.setText(note.getDescription());
        holder.noteDate.setText(note.getDate());
    }

    @Override
    public int getItemCount() {
        return notesList.size();
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {

        TextView noteName;
        TextView noteDescription;
        TextView noteDate;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                if (getClickListener() != null) {
                    getClickListener().onNoteClickListener(notesList.get(getAdapterPosition()));
                }
            });

            noteName = itemView.findViewById(R.id.item_note_name);
            noteDescription = itemView.findViewById(R.id.item_note_description);
            noteDate = itemView.findViewById(R.id.item_note_date);
        }
    }
}
