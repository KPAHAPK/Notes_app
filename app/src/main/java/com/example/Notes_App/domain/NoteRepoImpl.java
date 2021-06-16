package com.example.Notes_App.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NoteRepoImpl implements NoteRepo {

    ArrayList<Note> notes = new ArrayList<>();
    Note note;

    @Override
    public List<Note> getNotes() {

        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("fasfasdfasdf","jlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh", 0));
        notes.add(new Note("14wfwerwerwkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjl","asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh" +
                "asfsafsadkjllkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjlkjljlkjlkjlkjlkjlkjljk;iujoiuojlkjpoiul;jkl;kjpouipoikmpinuoiuyiuyihnhioh"
                , System.currentTimeMillis()));
        return notes;
    }

    @Override
    public void addNotes(Note note) {
        notes.add(note);
    }
}
