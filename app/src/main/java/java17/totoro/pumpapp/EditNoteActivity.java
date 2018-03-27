package java17.totoro.pumpapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

import java17.totoro.pumpapp.shared.Note;

public class EditNoteActivity extends AppCompatActivity {

    private Button editNote_btn_add;
    private EditText et_title, et_description, et_date, et_noteText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        setupUi();

        editNote_btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = et_title.getText().toString();
                String description = et_description.getText().toString();
                String date = et_date.getText().toString();
                String noteText = et_noteText.getText().toString();

                Note note = new Note(title, description, date, noteText);

                Intent resultIntent = new Intent();
                resultIntent.putExtra("newNote", note);
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });
    }

    public void setupUi(){
        editNote_btn_add = findViewById(R.id.editNote_btnAdd);
        et_title = findViewById(R.id.edit_note_title);
        et_description = findViewById(R.id.edit_note_description);
        et_date = findViewById(R.id.edit_note_date);
        et_noteText = findViewById(R.id.edit_note_newNote);
    }
}
