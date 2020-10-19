package com.example.wifi.RoleDatabaseRoom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wifi.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Role> todoList;
    private RecyclerViewAdapter.ClickListener clickListener;
    MyDatabase myDatabase;
    RecyclerViewAdapter recyclerViewAdapter;
    Role todo;


    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        todoList = new ArrayList<>();


    }


    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                             int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item_layout, parent, false);
        RecyclerViewAdapter.ViewHolder viewHolder = new RecyclerViewAdapter.ViewHolder(view);

        return viewHolder;

    }



    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Role todo = todoList.get(position);
        holder.txtName.setText(todo.name);
//   holder.txtNo.setText("#" + String.valueOf(todo.todo_id));
        holder.txtDesc.setText(todo.ViewSiteList);
        holder.txtCategory.setText(todo.category);


    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

public void updateTodoList(List<Role> data) {
        todoList.clear();
        todoList.addAll(data);
        notifyDataSetChanged();
    }

    public void addRow(Role data) {
        todoList.add(data);
        notifyDataSetChanged();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txtName;
        public TextView txtNo;
        public TextView txtDesc;
        public TextView txtCategory;
        public Button delete;
        public CardView cardView;
        public Button editButton;

        public ViewHolder(final View view) {
            super(view);

         //   txtNo = view.findViewById(R.id.txtNo);
            txtName = view.findViewById(R.id.rolename);
            txtDesc = view.findViewById(R.id.expirydate);
            txtCategory = view.findViewById(R.id.status);
            delete=view.findViewById(R.id.deletebutton2);
          //  cardView = view.findViewById(R.id.cardView);
            editButton=view.findViewById(R.id.editbutton);
          editButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(todoList.get(getAdapterPosition()).todo_id);
                }
            });
    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            myDatabase.daoAccess().deleteTodo(todoList.get(getAdapterPosition()));
            todoList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());
            /*notifyItemRemoved(getAdapterPosition());
            notifyItemRangeChanged(getAdapterPosition(),todoList.size());*/
        }
    });
        }
    }

    public interface ClickListener {
        void launchIntent(int id);
    }


}