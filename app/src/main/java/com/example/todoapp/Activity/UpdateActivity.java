package com.example.todoapp.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.Database.DatabaseHelper;
import com.example.todoapp.R;

public class UpdateActivity extends AppCompatActivity {

    EditText titleUpdate, descriptionUpdate;
    Button updateButton, deleteButton;
    String id, title, description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);

        titleUpdate = findViewById(R.id.titleUpdate);
        descriptionUpdate = findViewById(R.id.descriptionUpdate);
        updateButton = findViewById(R.id.updateTask);
        deleteButton = findViewById(R.id.deleteTask);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.deleteOneRow(id);
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        getAndSetIntentData();
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDB = new DatabaseHelper(UpdateActivity.this);
                myDB.updateData(id, titleUpdate.getText().toString().trim(),
                        descriptionUpdate.getText().toString().trim());
                Intent intent = new Intent(UpdateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") && getIntent().hasExtra("description")) {
            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            description = getIntent().getStringExtra("description");

            titleUpdate.setText(title);
            descriptionUpdate.setText(description);
        } else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }
}