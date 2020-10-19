package com.example.wifi.RoleDatabaseRoom;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import com.example.wifi.R;

import java.util.ArrayList;
import java.util.Arrays;

public class TodoNoteActivity extends AppCompatActivity {

    Spinner spinner;
    EditText inTitle, inDesc;
    Button btnDone, btnDelete;
    boolean isNewTodo = false;
    CheckBox ViewSiteListCB,AddSiteCB,ViewSiteDetailsCB,
    EditSiteCB,DeleteSiteCB,ViewSiteParamsCB,ViewAlarmCB,
    ViewAlarmHistoryCB,ViewSiteSettingsCB,InitializeSettingsCB,
    ViewSiteIDRequestsCB,EditSiteSettingsCB,ControlSectionCB,
    SendOTPCB,EnergyLevelsCB,ViewReportHomepageCB,ViewUserListCB, AddUserCB,EditUserCB,DeleteUserCB,
    ChangeSupervisorCB,ViewRolelistCB,AddRoleCB,EditRoleCB,DeleteRoleCB,ViewSystemSettingsCB,EditSystemSettingsCB;


    private String[] categories = {
            "Android",
            "iOS",
            "Kotlin",
            "Swift"
    };

    public ArrayList<String> spinnerList = new ArrayList<>(Arrays.asList(categories));
    MyDatabase myDatabase;

    Role updateTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newrole2);
    //    Toolbar toolbar = findViewById(R.id.toolbar);
        //getSupportActionBar(toolbar);

        spinner = findViewById(R.id.parentspiner);
        inTitle = findViewById(R.id.rolenameaddrole);
        inDesc = findViewById(R.id.expirydateaddrole);
        btnDone = findViewById(R.id.create);
        ViewSiteListCB=findViewById(R.id.viewsite);
        AddSiteCB=findViewById(R.id.addsite);
        ViewSiteDetailsCB=findViewById(R.id.viewsitedetail);
        EditSiteCB=findViewById(R.id.editsite);
        DeleteSiteCB=findViewById(R.id.deletesite);
        ViewSiteParamsCB=findViewById(R.id.viewsiteparams);
        ViewAlarmCB=findViewById(R.id.viewalarm);
        ViewAlarmHistoryCB=findViewById(R.id.alarmhistory);
        ViewSiteSettingsCB=findViewById(R.id.viewsitesetting);
        InitializeSettingsCB=findViewById(R.id.initializesettings);
        ViewSiteIDRequestsCB=findViewById(R.id.viewSiteidRequest);
        EditSiteSettingsCB=findViewById(R.id.editsitesetting);
        ControlSectionCB=findViewById(R.id.controlsection);
        SendOTPCB=findViewById(R.id.sendotp);
        EnergyLevelsCB=findViewById(R.id.energylevels);
        ViewReportHomepageCB=findViewById(R.id.viewreporthomepage);
        ViewUserListCB=findViewById(R.id.viewuserlist);
        AddUserCB=findViewById(R.id.adduser);
        EditUserCB=findViewById(R.id.edituser);
        DeleteUserCB=findViewById(R.id.deleteuser);
        ChangeSupervisorCB=findViewById(R.id.changesupervisor);
        ViewRolelistCB=findViewById(R.id.viewrolelist);
        AddRoleCB=findViewById(R.id.addrole);
        EditRoleCB=findViewById(R.id.editrole);
        DeleteRoleCB=findViewById(R.id.deleterole);
        ViewSystemSettingsCB=findViewById(R.id.viewsystemsettings);
        EditSystemSettingsCB=findViewById(R.id.editsystemsettings);

      //  btnDelete = findViewById(R.id.btnDelete);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, MyDatabase.DB_NAME).build();

        int todo_id = getIntent().getIntExtra("id", -100);

        if (todo_id == -100)
            isNewTodo = true;

        if (!isNewTodo) {
            fetchTodoById(todo_id);
           // btnDelete.setVisibility(View.VISIBLE);
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewTodo) {
                    String ss="Selected";
                    Role todo = new Role();
                    todo.name = inTitle.getText().toString();
                    todo.description = inDesc.getText().toString();
                    todo.category = spinner.getSelectedItem().toString();
                   // boolean checked =  v.;

                    if(ViewSiteListCB.isChecked()) {
                        Toast.makeText(TodoNoteActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                        todo.ViewSiteList = ss; }
                    if(AddSiteCB.isChecked()){
                            Toast.makeText(TodoNoteActivity.this,"clicked",Toast.LENGTH_SHORT).show();
                    todo.AddSite=ss;}
                    if(ViewSiteDetailsCB.isChecked()){
                        todo.ViewSiteDetails=ss;}
                    if(EditSiteCB.isChecked()){
                        todo.EditSite=ss;}
                    if(DeleteSiteCB.isChecked()){
                        todo.DeleteSite=ss;
                    }
                    if(ViewSiteParamsCB.isChecked()){
                        todo.ViewSiteParams=ss;
                    }
                    if(ViewAlarmCB.isChecked()){
                        todo.ViewAlarm=ss;
                    }
                  if (ViewAlarmHistoryCB.isChecked()) {
                      todo.ViewAlarmHistory =ss;
                  }
                    if(ViewSiteSettingsCB.isChecked()){
                        todo.ViewSiteSettings=ss;
                    }
                    if(InitializeSettingsCB.isChecked()){
                        todo.InitializeSettings=ss;
                    }
                    if(ViewSiteIDRequestsCB.isChecked()){
                        todo.ViewSiteIDRequests=ss;
                    }
                    if(EditSiteSettingsCB.isChecked()){
                        todo.EditSiteSettings=ss;
                    }
                    if(ControlSectionCB.isChecked()){
                        todo.ControlSection=ss;
                    }
                    if(SendOTPCB.isChecked()){
                        todo.SendOTP=ss;
                    }
                    if(EnergyLevelsCB.isChecked()){
                        todo.EnergyLevels=ss;
                    }
                    if(ViewReportHomepageCB.isChecked()){
                        todo.ViewReportHomepage=ss;
                    }
                    if(ViewUserListCB.isChecked()){
                        todo.ViewUserList=ss;
                    }
                    if(AddUserCB.isChecked()){
                        todo.AddUser=ss;
                    }
                    if(EditUserCB.isChecked()){
                        todo.EditUser=ss;
                    }
                    if(DeleteUserCB.isChecked()){
                        todo.DeleteUser=ss;
                    }
                    if(ChangeSupervisorCB.isChecked()){
                        todo.ChangeSupervisor=ss;
                    }
                    if(ViewRolelistCB.isChecked()){
                        todo.ViewRolelist=ss;
                    }
                    if(AddRoleCB.isChecked()){
                        todo.AddRole=ss;
                    }
                    if(EditRoleCB.isChecked()){
                        todo.EditRole=ss;
                    }
                    if(DeleteRoleCB.isChecked()){
                        todo.DeleteRole=ss;
                    }
                    if(ViewSystemSettingsCB.isChecked()){
                        todo.ViewSystemSettings=ss;
                    }
                    if(EditSystemSettingsCB.isChecked()){
                        todo.EditSystemSettings=ss;
                    }

                    insertRow(todo);

                    } else {

                    updateTodo.name = inTitle.getText().toString();
                    updateTodo.description = inDesc.getText().toString();
                    updateTodo.category = spinner.getSelectedItem().toString();
                    updateRow(updateTodo);
                }
            }
        });

/*        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteRow(updateTodo);
            }
        });*/
    }



    private void getSupportActionBar(Toolbar toolbar) {
    }

    @SuppressLint("StaticFieldLeak")
    private void fetchTodoById(final int todo_id) {
        new AsyncTask<Integer, Void, Role>() {
            @Override
            protected Role doInBackground(Integer... params) {
                return myDatabase.daoAccess().fetchTodoListById(params[0]);
            }

            @Override
            protected void onPostExecute(Role todo) {
                super.onPostExecute(todo);
                inTitle.setText(todo.name);
               inDesc.setText(todo.description);
                spinner.setSelection(spinnerList.indexOf(todo.category));
                //inDesc.setText(todo.ViewSiteList);
                String ss="Selected";
                if(ss.equals(todo.ViewSiteList)) {
                    ViewSiteListCB.setChecked(true);
                }

                updateTodo = todo;
            }
        }.execute(todo_id);

    }

    @SuppressLint("StaticFieldLeak")
    private void insertRow(Role todo) {
        new AsyncTask<Role, Void, Long>() {
            @Override
            protected Long doInBackground(Role... params) {
                return myDatabase.daoAccess().insertTodo(params[0]);
            }

            @Override
            protected void onPostExecute(Long id) {
                super.onPostExecute(id);

                Intent intent = getIntent();
                intent.putExtra("isNew", true).putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(todo);

    }

    @SuppressLint("StaticFieldLeak")
    private void deleteRow(Role todo) {
        new AsyncTask<Role, Void, Integer>() {
            @Override
            protected Integer doInBackground(Role... params) {
                return myDatabase.daoAccess().deleteTodo(params[0]);
            }

            @Override
            protected void onPostExecute(Integer number) {
                super.onPostExecute(number);

                Intent intent = getIntent();
                intent.putExtra("isDeleted", true).putExtra("number", number);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(todo);

    }


    @SuppressLint("StaticFieldLeak")
    private void updateRow(Role todo) {
        new AsyncTask<Role, Void, Integer>() {
            @Override
            protected Integer doInBackground(Role... params) {
                return myDatabase.daoAccess().updateTodo(params[0]);
            }

            @Override
            protected void onPostExecute(Integer number) {
                super.onPostExecute(number);

                Intent intent = getIntent();
                intent.putExtra("isNew", false).putExtra("number", number);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(todo);

    }


}