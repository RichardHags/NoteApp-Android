package java17.totoro.pumpapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java17.totoro.pumpapp.database.DatabaseHelper;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    DatabaseHelper myDb = new DatabaseHelper(this);

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText eLoginUsername, etLoginPassword;
    private Button btnLogin;
    private TextView tvRegisterLink;
    private CheckBox mCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        // mPreferences = getSharedPreferences("ett namn här", Context.MODE_PRIVATE);
        mEditor = mPreferences.edit();

        setupUi();
        checkSharedPreferences();


        tvRegisterLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "User logged in.");
                if(mCheckBox.isChecked()){
                    mEditor.putString(getString(R.string.checkbox), "True");
                    mEditor.commit();

                    String username = eLoginUsername.getText().toString();
                    mEditor.putString(getString(R.string.username), username);
                    mEditor.commit();

                    String password = etLoginPassword.getText().toString();
                    mEditor.putString(getString(R.string.password), password);
                    mEditor.commit();

                } else {
                    mEditor.putString(getString(R.string.checkbox), "False");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.username), "");
                    mEditor.commit();

                    mEditor.putString(getString(R.string.password), "");
                    mEditor.commit();
                }

                String username = eLoginUsername.getText().toString();
                String pass = etLoginPassword.getText().toString();

                String password = myDb.searchPassword(username);

                if (password.equals(pass)){

                    // Sätt en koll om lösenord stämmer innan den här startas
                    Intent loginIntent = new Intent(LoginActivity.this, MenuActivity.class);
                    LoginActivity.this.startActivity(loginIntent);

                } else {
                    toastMessage("Incorrect login credentials");
                }

            }
        });

    }

    private void setupUi(){

        eLoginUsername = (EditText) findViewById(R.id.etLoginUsername);
        etLoginPassword = (EditText) findViewById(R.id.etLoginPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        tvRegisterLink = (TextView) findViewById(R.id.tvRegister);
        mCheckBox = (CheckBox) findViewById(R.id.loginCheckbox);
    }

    /************************************************
     * Checkar shared preferences för inloggningen och sätter dem som det ska vara.
     ************************************************/
    private void checkSharedPreferences(){
        String checkbox = mPreferences.getString(getString(R.string.checkbox), "False");
        String username = mPreferences.getString(getString(R.string.username), "");
        String password = mPreferences.getString(getString(R.string.password), "");

        eLoginUsername.setText(username);
        etLoginPassword.setText(password);

        if (checkbox.equals("True")){
            mCheckBox.setChecked(true);
        } else {
            mCheckBox.setChecked(false);
        }
    }
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
