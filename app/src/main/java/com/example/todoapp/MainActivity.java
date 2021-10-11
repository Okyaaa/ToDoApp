package com.example.todoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.todoapp.Adapter.MainAdapter;
import com.example.todoapp.Database.DatabaseHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button addTaskButton;
    private Toolbar toolbar;

    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> db_id, db_title, db_description;
    MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = findViewById(R.id.mainAppBar);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        addTaskButton = findViewById(R.id.addTaskButton);
        addTaskButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });

        myDB = new DatabaseHelper(MainActivity.this);
        db_id = new ArrayList<>();
        db_title = new ArrayList<>();
        db_description = new ArrayList<>();

        storeDataInArrays();

        mainAdapter = new MainAdapter(MainActivity.this, this, db_id, db_title,
                db_description);
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                db_id.add(cursor.getString(0));
                db_title.add(cursor.getString(1));
                db_description.add(cursor.getString(2));
            }
        }
    }

}