package java17.totoro.pumpapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import java17.totoro.pumpapp.shared.Note;

public class NoteDetailActivity extends AppCompatActivity {

    private Button nd_edit, nd_delete;
    private TextView nd_noteText, nd_title, nd_description, nd_date;
    private ArrayList<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_detail);
        setupUi();


//        Intent incomingIntent = getIntent();
//        String incomingNote = incomingIntent.getStringExtra("noteText");
//        nd_noteText.setText(incomingNote);
        Intent incomingIntent = getIntent();
        final Note note = (Note) incomingIntent.getSerializableExtra("noteText");

        nd_noteText.setText(note.getNoteText());
        nd_title.setText(note.getTitle());
        nd_description.setText(note.getDescription());
        nd_date.setText(note.getDate());

    }

    public void setupUi(){
        nd_noteText = (TextView) findViewById(R.id.note_detail_tvNoteText);
        nd_title = (TextView) findViewById(R.id.note_detail_tvTitle);
        nd_description = (TextView) findViewById(R.id.note_detail_tvDescription);
        nd_date = (TextView) findViewById(R.id.note_detail_tvDate);
        nd_edit = (Button) findViewById(R.id.nd_btnEdit);
        nd_delete = (Button) findViewById(R.id.nd_btnDelete);
    }
}
