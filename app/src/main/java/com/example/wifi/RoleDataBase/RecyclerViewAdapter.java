package com.example.wifi.RoleDataBase;

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

    private List<DBRole> dbRoleList;
    private RecyclerViewAdapter.ClickListener clickListener;
    MyDatabase myDatabase;


    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        dbRoleList = new ArrayList<>();


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
       DBRole dbRole = dbRoleList.get(position);
        holder.txtName.setText(dbRole.RoleName);
//   holder.txtNo.setText("#" + String.valueOf(todo.todo_id));
        holder.txtDesc.setText(dbRole.ViewSiteList);
       // holder.txtCategory.setText(dbRole.category);


    }

    @Override
    public int getItemCount() {
        return dbRoleList.size();
    }

public void updateTodoList(List<DBRole> data) {
        dbRoleList.clear();
        dbRoleList.addAll(data);
        notifyDataSetChanged();
    }

    public void addRow(DBRole data) {
        dbRoleList.add(data);
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
                    clickListener.launchIntent(dbRoleList.get(getAdapterPosition()).role_id);
                }
            });
    delete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            myDatabase.daoAccess().deleteDBRole(dbRoleList.get(getAdapterPosition()));
            dbRoleList.remove(getAdapterPosition());
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