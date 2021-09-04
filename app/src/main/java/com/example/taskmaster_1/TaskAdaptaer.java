package com.example.taskmaster_1;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskmaster_1.database.TaskModel;

import java.util.ArrayList;
import java.util.List;

public class TaskAdaptaer extends RecyclerView.Adapter<TaskAdaptaer.TaskViewHolder>{
    List<TaskModel> allTask = new ArrayList<TaskModel>();

    public TaskAdaptaer(List<TaskModel> allTask){

        this.allTask = allTask;
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TaskModel taskModel;
        View itemView;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }

    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_task,parent,false);
        TaskViewHolder taskViewHolder = new TaskViewHolder(view);
        return taskViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull TaskAdaptaer.TaskViewHolder holder, int position) {

        holder.taskModel = allTask.get(position);

        TextView title = holder.itemView.findViewById(R.id.titleInFragment);
        TextView state = holder.itemView.findViewById(R.id.stateInFragment);
        TextView body = holder.itemView.findViewById(R.id.bodyInFragment);

        title.setText(holder.taskModel.title);
        state.setText(holder.taskModel.getState());
        body.setText(holder.taskModel.getBody());

    }

    @Override
    public int getItemCount() {
        return allTask.size();
    }

}
