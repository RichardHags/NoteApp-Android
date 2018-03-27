package java17.totoro.pumpapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java17.totoro.pumpapp.database.DatabaseHelper;

public class RegisterActivity extends AppCompatActivity {

    DatabaseHelper myDb;

    private EditText regName, regAge, regEmail, regUsername, regPassword;
    private Button btnRegister, regViewDb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = new DatabaseHelper(this);
        setupUI();
        addData();
        viewAll();
    }

    public void addData() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(regName.getText().toString(),
                        Integer.parseInt(regAge.getText().toString()),
                        regEmail.getText().toString(),
                        regUsername.getText().toString(),
                        regPassword.getText().toString());
                if (isInserted)
                    toastMessage("Data is inserted");
                else
                    toastMessage("Data is not inserted");

            }
        });
    }

    public void viewAll(){
        regViewDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0){
                    //show message
                    showMessage("Error", "No data found!");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Id: " + res.getString(0) + "\n");
                    buffer.append("Name: " + res.getString(1) + "\n");
                    buffer.append("Age: " + res.getInt(2) + "\n");
                    buffer.append("Email: " + res.getString(3) + "\n");
                    buffer.append("Username: " + res.getString(4) + "\n");
                    buffer.append("Password: " + res.getString(5) + "\n\n");
                }

                //Show all data
                showMessage("Data", buffer.toString());

            }
        });
    }

    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();

    }

    private void setupUI() {
        regName = (EditText) findViewById(R.id.etName);
        regAge = (EditText) findViewById(R.id.etAge);
        regEmail = (EditText) findViewById(R.id.etEmail);
        regUsername = (EditText) findViewById(R.id.etRegUsername);
        regPassword = (EditText) findViewById(R.id.etRegPassword);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        regViewDb = (Button) findViewById(R.id.btnViewDb);

    }

    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /*private boolean validate() {
        boolean result = false;

        String name = regName.getNoteText().toString();
        int age = Integer.parseInt(regAge.getNoteText().toString());
        String email = regEmail.getNoteText().toString();
        String userName = regUsername.getNoteText().toString();
        String password = regPassword.getNoteText().toString();


    }*/

}
