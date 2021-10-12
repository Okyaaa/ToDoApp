package com.example.todoapp.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapp.R;
import com.example.todoapp.Activity.UpdateActivity;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {

    Context context;
    ArrayList  db_id, db_title, db_description;
    Activity activity;

    int position;

    public MainAdapter(Activity activity, Context context, ArrayList db_id, ArrayList db_title,
                       ArrayList db_description){
        this.activity = activity;
        this.context = context;
        this.db_id = db_id;
        this.db_title = db_title;
        this.db_description = db_description;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card, parent, false);
        return new MainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        this.position = position;
        holder.myText1.setText(String.valueOf(db_title.get(position)));
        holder.myText2.setText(String.valueOf(db_description.get(position)));
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(db_id.get(position)));
                intent.putExtra("title", String.valueOf(db_title.get(position)));
                intent.putExtra("description", String.valueOf(db_description.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return db_id.size();
    }

    public class MainViewHolder extends RecyclerView.ViewHolder{

        TextView myText1, myText2;
        ConstraintLayout card;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);
            myText1 = itemView.findViewById(R.id.title);
            myText2 = itemView.findViewById(R.id.description);
            card = itemView.findViewById(R.id.card_layout);
        }
    }
}
