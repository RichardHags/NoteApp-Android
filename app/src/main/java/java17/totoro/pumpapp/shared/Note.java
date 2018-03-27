package java17.totoro.pumpapp.shared;

import java.io.Serializable;

/**
 * Created by Totoro on 2018-03-19.
 */

public class Note implements Serializable {

    private String title;
    private String Description;
    private String date;
    private String noteText;


    public Note() {
    }

    public Note(String title, String Description, String date, String noteText) {
        this.title = title;
        this.Description = Description;
        this.date = date;
        this.noteText = noteText;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNoteText() {
        return noteText;
    }

    public void setNoteText(String noteText) {
        this.noteText = noteText;
    }
}
