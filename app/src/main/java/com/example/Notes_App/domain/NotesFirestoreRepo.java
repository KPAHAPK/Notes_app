package com.example.Notes_App.domain;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class NotesFirestoreRepo implements NoteRepo {

    public static final NoteRepo INSTANCE = new NotesFirestoreRepo();

    private FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();

    private static final String NOTES = "notesList";
    private static final String DATE = "date";
    private static final String NAME = "name";
    private static final String DESCRIPTION = "description";

    @Override
    public List<Note> getNotes() {
        return null;
    }

    @Override
    public void getNotes(Callback<List<Note>> callback) {
        firebaseFirestore.collection(NOTES)
                .orderBy(DATE, Query.Direction.ASCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isComplete()) {

                            ArrayList<Note> result = new ArrayList<>();
                            String noteName;
                            String noteDescription;
                            Date noteDate;

                            for (QueryDocumentSnapshot queryDocumentSnapshot : Objects.requireNonNull(task.getResult())) {
                                noteName = (String) queryDocumentSnapshot.get(NAME);
                                noteDescription = (String) queryDocumentSnapshot.get(DESCRIPTION);
                                noteDate = ((Timestamp) queryDocumentSnapshot.get(DATE)).toDate();
                                result.add(new Note(queryDocumentSnapshot.getId(),
                                        noteName,
                                        noteDescription,
                                        noteDate));
                            }

                            callback.onSuccess(result);

                        } else {
                            task.getException();
                        }
                    }
                });
    }

    @Override
    public boolean addNote(Note note) {
        return false;
    }

    @Override
    public boolean removeNote(Note note) {
        return false;
    }

    @Override
    public boolean addAll(List<Note> list) {
        return false;
    }
}
