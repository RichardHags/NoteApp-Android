package java17.totoro.pumpapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    private Button noteBtn, customToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        noteBtn = (Button) findViewById(R.id.menu_btn_notes);
        customToolbar = (Button) findViewById(R.id.menu_btn_customToolbar);

        noteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent noteIntent = new Intent(MenuActivity.this, NoteActivity.class);
                MenuActivity.this.startActivity(noteIntent);
            }
        });

    }
}
