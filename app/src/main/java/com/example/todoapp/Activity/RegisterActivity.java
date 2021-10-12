package com.example.todoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.todoapp.Database.DatabaseHelper;
import com.example.todoapp.R;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    private Button reg;
    private TextView tvLogin;
    private EditText etEmail, etPass;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        reg = (Button)findViewById(R.id.btnReg);
        tvLogin = (TextView)findViewById(R.id.tvLogin);
        etEmail = (EditText)findViewById(R.id.etEmail);
        etPass = (EditText)findViewById(R.id.etPass);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register(){
        String email = etEmail.getText().toString();
        String password = etPass.getText().toString();

        db = new DatabaseHelper(RegisterActivity.this);
        SQLiteDatabase sqlDB = db.getWritableDatabase();

        Cursor cursor = sqlDB.rawQuery("SELECT  * FROM users", null);

        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(1).equals(email)) {
                    Toast.makeText(this, "User Already Exist, Please Login",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
            } while (cursor.moveToNext());
        }
        if (email.isEmpty() && password.isEmpty()) {
            displayToast("Username/password field empty");
        } else {
            db.addUser(email, password);
            displayToast("User registered");
            finish();
        }

//        if(email.isEmpty() && pass.isEmpty()){
//            displayToast("Username/password field empty");
//        }else{
//            db.addUser(email,pass);
//            displayToast("User registered");
//            finish();
//        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void register(View view) {
        switch(view.getId()){
            case R.id.btnReg:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:
        }
    }
}