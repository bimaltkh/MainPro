package com.example.wifi.RoleDataBase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

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

import com.example.wifi.R;

import java.util.ArrayList;
import java.util.Arrays;

public class NewRole extends AppCompatActivity {
    Spinner spinner;
    EditText inTitle, inDesc;
    Button btnDone, btnDelete;
    boolean isNewUser = false;
    CheckBox ViewSiteListCB,AddSiteCB,ViewSiteDetailsCB,
            EditSiteCB,DeleteSiteCB,ViewSiteParamsCB,ViewAlarmCB,
            ViewAlarmHistoryCB,ViewSiteSettingsCB,InitializeSettingsCB,
            ViewSiteIDRequestsCB,EditSiteSettingsCB,ControlSectionCB,
            SendOTPCB,EnergyLevelsCB,ViewReportHomepageCB,ViewUserListCB, AddUserCB,EditUserCB,DeleteUserCB,
            ChangeSupervisorCB,ViewRolelistCB,AddRoleCB,EditRoleCB,DeleteRoleCB,ViewSystemSettingsCB,EditSystemSettingsCB;

    MyDatabase myDatabase;

    DBRole updateDBRole;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_role2);

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
        ViewAlarmHistoryCB=findViewById(R.id.viewalarmhistory);
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

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, MyDatabase.DB_NAME).build();

        int role_id = getIntent().getIntExtra("id", -100);

        if (role_id == -100)
            isNewUser = true;

        if (!isNewUser) {
fetchDBRoleById(role_id);
            // btnDelete.setVisibility(View.VISIBLE);
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isNewUser) {
                    String ss="Selected";
                    DBRole dbRole=new DBRole();
                    dbRole.RoleName = inTitle.getText().toString();
                    dbRole.ExpiryDate = inDesc.getText().toString();
                    //todo.category = spinner.getSelectedItem().toString();
                    // boolean checked =  v.;

                    if(ViewSiteListCB.isChecked()) {
                      //  Toast.makeText(TodoNoteActivity.this, "clicked", Toast.LENGTH_SHORT).show();
                       dbRole.ViewSiteList = ss; }
                    if(AddSiteCB.isChecked()){
                     //   Toast.makeText(TodoNoteActivity.this,"clicked",Toast.LENGTH_SHORT).show();
                        dbRole.AddSite=ss;}
                    if(ViewSiteDetailsCB.isChecked()){
                        dbRole.ViewSiteDetails=ss;}
                    if(EditSiteCB.isChecked()){
                        dbRole.EditSite=ss;}
                    if(DeleteSiteCB.isChecked()){
                        dbRole.DeleteSite=ss;
                    }
                    if(ViewSiteParamsCB.isChecked()){
                        dbRole.ViewSiteParams=ss;
                    }
                    if(ViewAlarmCB.isChecked()){
                        dbRole.ViewAlarm=ss;
                    }
                    if(ViewAlarmHistoryCB.isChecked()){
                        dbRole.ViewAlarmHistory=ss;
                    }
                    if(ViewSiteSettingsCB.isChecked()){
                        dbRole.ViewSiteSettings=ss;
                    }
                    if(InitializeSettingsCB.isChecked()){
                        dbRole.InitializeSettings=ss;
                    }
                    if(ViewSiteIDRequestsCB.isChecked()){
                        dbRole.ViewSiteIDRequests=ss;
                    }
                    if(EditSiteSettingsCB.isChecked()){
                        dbRole.EditSiteSettings=ss;
                    }
                    if(ControlSectionCB.isChecked()){
                        dbRole.ControlSection=ss;
                    }
                    if(SendOTPCB.isChecked()){
                        dbRole.SendOTP=ss;
                    }
                    if(EnergyLevelsCB.isChecked()){
                        dbRole.EnergyLevels=ss;
                    }
                    if(ViewReportHomepageCB.isChecked()){
                        dbRole.ViewReportHomepage=ss;
                    }
                    if(ViewUserListCB.isChecked()){
                        dbRole.ViewUserList=ss;
                    }
                    if(AddUserCB.isChecked()){
                        dbRole.AddUser=ss;
                    }
                    if(EditUserCB.isChecked()){
                        dbRole.EditUser=ss;
                    }
                    if(DeleteUserCB.isChecked()){
                        dbRole.DeleteUser=ss;
                    }
                    if(ChangeSupervisorCB.isChecked()){
                        dbRole.ChangeSupervisor=ss;
                    }
                    if(ViewRolelistCB.isChecked()){
                        dbRole.ViewRolelist=ss;
                    }
                    if(AddRoleCB.isChecked()){
                        dbRole.AddRole=ss;
                    }
                    if(EditRoleCB.isChecked()){
                        dbRole.EditRole=ss;
                    }
                    if(DeleteRoleCB.isChecked()){
                        dbRole.DeleteRole=ss;
                    }
                    if(ViewSystemSettingsCB.isChecked()){
                        dbRole.ViewSystemSettings=ss;
                    }
                    if(EditSystemSettingsCB.isChecked()){
                        dbRole.EditSystemSettings=ss;
                    }

                    insertRow(dbRole);

                } else {

                    updateDBRole.RoleName = inTitle.getText().toString();
                    updateDBRole.ExpiryDate = inDesc.getText().toString();
                 //   updateTodo.category = spinner.getSelectedItem().toString();
                    updateRow(updateDBRole);
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
    @SuppressLint("StaticFieldLeak")
    private void fetchDBRoleById(final int role_id) {
        new AsyncTask<Integer, Void, DBRole>() {
            @Override
            protected DBRole doInBackground(Integer... params) {
                return myDatabase.daoAccess().fetchDBRoleListById(params[0]);
            }

            @Override
            protected void onPostExecute(DBRole dbRole) {
                super.onPostExecute(dbRole);
                inTitle.setText(dbRole.RoleName);
                inDesc.setText(dbRole.ExpiryDate);
                String ss="Selected";
                if(ss.equals(dbRole.ViewSiteList)) {
                    ViewSiteListCB.setChecked(true);
                }

                updateDBRole=dbRole;
            }
        }.execute(role_id);

    }

    @SuppressLint("StaticFieldLeak")
    private void insertRow(DBRole dbRole ) {
        new AsyncTask<DBRole, Void, Long>() {
            @Override
            protected Long doInBackground(DBRole... params) {
                return myDatabase.daoAccess().insertDBRole(params[0]);
            }

            @Override
            protected void onPostExecute(Long id) {
                super.onPostExecute(id);

                Intent intent = getIntent();
                intent.putExtra("isNew", true).putExtra("id", id);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(dbRole);

    }

    @SuppressLint("StaticFieldLeak")
    private void deleteRow(DBRole dbRole) {
        new AsyncTask<DBRole, Void, Integer>() {
            @Override
            protected Integer doInBackground(DBRole... params) {
                return myDatabase.daoAccess().deleteDBRole(params[0]);
            }

            @Override
            protected void onPostExecute(Integer number) {
                super.onPostExecute(number);

                Intent intent = getIntent();
                intent.putExtra("isDeleted", true).putExtra("number", number);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(dbRole);

    }


    @SuppressLint("StaticFieldLeak")
    private void updateRow(DBRole dbRole) {
        new AsyncTask<DBRole, Void, Integer>() {
            @Override
            protected Integer doInBackground(DBRole... params) {
                return myDatabase.daoAccess().updateDBRole(params[0]);
            }

            @Override
            protected void onPostExecute(Integer number) {
                super.onPostExecute(number);

                Intent intent = getIntent();
                intent.putExtra("isNew", false).putExtra("number", number);
                setResult(RESULT_OK, intent);
                finish();
            }
        }.execute(dbRole);

    }
}