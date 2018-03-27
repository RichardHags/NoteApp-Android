package java17.totoro.pumpapp.shared;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Totoro on 2018-03-21.
 */

public class NoteRepository {

    private static final String TAG = "NoteRepository";

    private ArrayList<Note> notes = new ArrayList<>();
    public ArrayList<Note> noteList = new ArrayList<>();


    { //default notes
        String noteText1 = "testar lite text testar lite text testar lite text testar lite text testar lite text testar lite text testar lite text";
        String noteText2 = "lite mer text på en annan note....!!!";
        String noteText3 = "lite text";
        String noteText4 = "testar lite text testar lite text testar lite text testar lite text testar lite text testar lite text testar lite text " +
                "testar lite text testar lite text testar lite text testar lite text testar lite text testar lite text testar lite text " +
                "testar lite text testar lite text testar lite text testar lite text testar lite text testar lite text testar lite text";
        String noteText5 = "lite text2222";
        String noteText6 = "lite text33";

        notes.add(new Note("Bröllop", "Karin och Peters bröllop", "2019-05-11", noteText1));
        notes.add(new Note("Inlämning 3", "Klientutveckling inl3!!", "2018-03-21", noteText2));
        notes.add(new Note("LiA", "Sök LiA", "Senast 24 april", noteText3));
        notes.add(new Note("Blodgivning", "Ge blod", "asap", noteText4));
        notes.add(new Note("Plantera", "Plantera dina tomater", "asap", noteText5));
        notes.add(new Note("Nästa kurs", "Läs på lite inför nästa kurs", "April", noteText6));
        notes.add(new Note("Kigali", "Rwanda resan", "Juni - midsommar", noteText6));
        notes.add(new Note("Födelsedag", "Brorsan fyller år", "2018-11-14", noteText3));
        notes.add(new Note("Title 9", "Description9", "a date9", noteText6));
        notes.add(new Note("Title 10", "Description 10", "a date 10", noteText5));
        notes.add(new Note("Title 11", "Description 11", "a date 11", noteText2));
        notes.add(new Note("Title 12", "Description 12", "a date 12", noteText1));
        notes.add(new Note("Title 13", "Description 13", "a date 13", noteText6));
        noteList = notes;
    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public ArrayList<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(ArrayList<Note> noteList) {
        this.noteList = noteList;
    }

}
