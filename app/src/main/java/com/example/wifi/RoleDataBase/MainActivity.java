package com.example.wifi.RoleDataBase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.wifi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ClickListener, AdapterView.OnItemSelectedListener {
    MyDatabase myDatabase;
    RecyclerView recyclerView;
    Spinner spinner;
    RecyclerViewAdapter recyclerViewAdapter;
    FloatingActionButton floatingActionButton;


    ArrayList<DBRole> dbRoleArrayList = new ArrayList<>();
   // ArrayList<String> spinnerList = new ArrayList<>(Arrays.asList(categories));

    public static final int NEW_TODO_REQUEST_CODE = 200;
    public static final int UPDATE_TODO_REQUEST_CODE = 300;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();

        myDatabase = Room.databaseBuilder(getApplicationContext(), MyDatabase.class, MyDatabase.DB_NAME).fallbackToDestructiveMigration().build();
        checkIfAppLaunchedFirstTime();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(MainActivity.this, NewRole.class), NEW_TODO_REQUEST_CODE);


            }
        });
    }
    private void initViews() {
        floatingActionButton = findViewById(R.id.fab);
        spinner = findViewById(R.id.spinner);
   //     ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerList);
   //     adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
   //     spinner.setAdapter(adapter);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);

        // String text = (message == null) ? “No data found.” : message.getMsg();

    }

    @Override
    public void launchIntent(int id) {
        startActivityForResult(new Intent(MainActivity.this, NewRole.class).putExtra("id", id), UPDATE_TODO_REQUEST_CODE);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


      /*  if (position == 0) {
            loadAllTodos();
        } else {
            String string = parent.getItemAtPosition(position).toString();
            loadFilteredTodos(string);
        }*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


/*    @SuppressLint("StaticFieldLeak")
    private void loadFilteredTodos(String category) {
        new AsyncTask<String, Void, List<DBRole>>() {
            @Override
            protected List<DBRole> doInBackground(String... params) {
                return myDatabase.daoAccess().fetchTodoListByCategory(params[0]);

            }

            @Override
            protected void onPostExecute(List<Todo> todoList) {
                recyclerViewAdapter.updateTodoList(todoList);
            }
        }.execute(category);

    }*/


    @SuppressLint("StaticFieldLeak")
    private void fetchTodoByIdAndInsert(int id) {
        new AsyncTask<Integer, Void, DBRole>() {
            @Override
            protected DBRole doInBackground(Integer... params) {
                return myDatabase.daoAccess().fetchDBRoleListById(params[0]);

            }

            @Override
            protected void onPostExecute(DBRole dbRoleList) {
                recyclerViewAdapter.addRow(dbRoleList);
            }
        }.execute(id);

    }

    @SuppressLint("StaticFieldLeak")
    private void loadAllTodos() {
        new AsyncTask<String, Void, List<DBRole>>() {
            @Override
            protected List<DBRole> doInBackground(String... params) {
                return myDatabase.daoAccess().fetchAllDBRoles();
            }

            @Override
            protected void onPostExecute(List<DBRole> dbRoleList) {
                recyclerViewAdapter.updateTodoList(dbRoleList);
            }
        }.execute();
    }

    /*private void buildDummyTodos() {
        Todo todo = new Todo();
        todo.name = "Android";
        todo.description = "Cover";
        todo.category = "Android";

        todoArrayList.add(todo);

        todo = new Todo();
        todo.name = "iOS ";
        todo.description = "Covers ";
        todo.category = "iOS";

        todoArrayList.add(todo);

        todo = new Todo();
        todo.name = "Kotlin Arrays";
        todo.description = "Cover .";
        todo.category = "Kotlin";

        todoArrayList.add(todo);

        todo = new Todo();
        todo.name = "Swift Arrays";
        todo.description = "Cover the ";
        todo.category = "Swift";

        todoArrayList.add(todo);
        insertList(todoArrayList);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            //reset spinners
            spinner.setSelection(0);

            if (requestCode == NEW_TODO_REQUEST_CODE) {
                long id = data.getLongExtra("id", -1);
                Toast.makeText(getApplicationContext(), "Row inserted", Toast.LENGTH_SHORT).show();
                fetchTodoByIdAndInsert((int) id);

            } else if (requestCode == UPDATE_TODO_REQUEST_CODE) {

                boolean isDeleted = data.getBooleanExtra("isDeleted", false);
                int number = data.getIntExtra("number", -1);
                if (isDeleted) {
                    Toast.makeText(getApplicationContext(), number + " rows deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), number + " rows updated", Toast.LENGTH_SHORT).show();
                }

                loadAllTodos();

            }


        } else {
            Toast.makeText(getApplicationContext(), "No action done by user", Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("StaticFieldLeak")
    private void insertList(List<DBRole> dbRoleList) {
        new AsyncTask<List<DBRole>, Void, Void>() {
            @Override
            protected Void doInBackground(List<DBRole>... params) {
                myDatabase.daoAccess().insertDBRoleList(params[0]);

                return null;

            }

            @Override
            protected void onPostExecute(Void voids) {
                super.onPostExecute(voids);
            }
        }.execute(dbRoleList);

    }

    private void checkIfAppLaunchedFirstTime() {
        final String PREFS_NAME = "SharedPrefs";

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        if (settings.getBoolean("firstTime", true)) {
            settings.edit().putBoolean("firstTime", false).apply();
           // buildDummyTodos();
        }
    }
}