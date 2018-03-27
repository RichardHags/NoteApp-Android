package java17.totoro.pumpapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

import java17.totoro.pumpapp.adapter.NoteListAdapter;
import java17.totoro.pumpapp.shared.Note;
import java17.totoro.pumpapp.shared.NoteRepository;


public class NoteActivity extends AppCompatActivity {

    private static final String TAG = "NoteActivity";
    public static final int REQUEST_ADD_NOTE = 9;
    public static final int REQUEST_DELOREDIT_NOTE = 8;
    public static final String TASK_LIST = "task list";
    public static final String SHARED_PREFERENCE = "shared preferences";

    private ListView noteListView;
    private Button noteActivity_btnAdd, noteActivity_btnEdit, noteActivity_btnDelete;

    NoteRepository r = new NoteRepository();
    ArrayList<Note> noteList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        loadData();
        setupUi();
        runAdapter();

        noteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent noteDetailIntent = new Intent(NoteActivity.this, NoteDetailActivity.class);
                //noteDetailIntent.putExtra("noteText", noteList.get(i).getNoteText());
                //startActivity(noteDetailIntent);
                String title = noteList.get(i).getTitle();
                String description = noteList.get(i).getDescription();
                String date = noteList.get(i).getDate();
                String noteText = noteList.get(i).getNoteText();

                Note note = new Note(title, description, date, noteText);

                noteDetailIntent.putExtra("noteText", note);
                NoteActivity.this.startActivityForResult(noteDetailIntent, REQUEST_DELOREDIT_NOTE);

            }
        });

        noteActivity_btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addIntent = new Intent(NoteActivity.this, EditNoteActivity.class);
                NoteActivity.this.startActivityForResult(addIntent, REQUEST_ADD_NOTE);
            }
        });

    }

    public void setupUi() {
        noteListView = (ListView) findViewById(R.id.listView);
        noteActivity_btnAdd = (Button) findViewById(R.id.note_btnAdd);
        noteActivity_btnEdit = (Button) findViewById(R.id.note_btnEdit);
        noteActivity_btnDelete = (Button) findViewById(R.id.note_btnDelete);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_ADD_NOTE:
                if (resultCode == Activity.RESULT_OK) {
                    Note note = (Note) data.getSerializableExtra("newNote");
                    noteList.add(note);
                    r.setNoteList(noteList);
                    saveData();
                    runAdapter();

                    Log.d(TAG, "Orginallistan har 13 items, nya har: " + noteList.size());
                }
                break;
            case REQUEST_DELOREDIT_NOTE:

                break;
        }

    }

    private void runAdapter() {
        NoteListAdapter nlistAdapter = new NoteListAdapter(this, R.layout.adapter_view_layout, noteList);
        noteListView.setAdapter(nlistAdapter);
    }

    private void saveData() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(noteList);
        editor.putString(TASK_LIST, json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sp.getString(TASK_LIST, null);
        Type type = new TypeToken<ArrayList<Note>>() {
        }.getType();
        noteList = gson.fromJson(json, type);

        if (noteList == null) {
            noteList = r.getNoteList();
        }
    }
}




