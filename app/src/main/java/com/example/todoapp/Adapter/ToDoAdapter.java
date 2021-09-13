//package com.example.todoapp.Adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.todoapp.MainActivity;
//import com.example.todoapp.Model.ToDoModel;
//import com.example.todoapp.R;
//
//import java.util.List;
//
//public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.ViewHolder> {
//
//    private List<ToDoModel> todoList;
//    private MainActivity activity;
//
//    public ToDoAdapter(MainActivity activity){
//        this.activity = activity;
//    }
//
//    public ToDoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.task_layout, parent, false)
//        return new RecyclerView.ViewHolder(itemView);
//    }
//
//    public void onBindViewHolder(ViewHolder holder, int position){
//        ToDoModel item = todoList.get(position);
//        holder.new_task.setText(item.getTask());
//
//    }
//    public static class ViewHolder extends RecyclerView{
//        ViewHolder(View view){
//            super(view);
//        }
//    }
//}
