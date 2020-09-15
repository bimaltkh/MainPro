package com.example.wifi.TabActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Tab1 extends Fragment {
   int pStatus = 0;
    int pStatus2 = 0;
    int pStatus3 = 0;
    int pStatus4 = 0;
    int pStatus5 = 0;
    int pStatus6 = 0;
int i=0;
    int x = 2;
    int val = 113;
    int val2 = 100;
    int val3 = 124;
    int val4 = 123;
    int val5 = 156;
    int val6 = 178;
    int max = 200 * x;
    int max2 = 200 * x;
    int max3 = 200 * x;
    int max4 = 200 * x;
    int max5 = 200 * x;
    int max6 = 200 * x;
    TextView valuemax1,valuemax2,valuemax3,valuemax4,valuemax5,valuemax6;

    Resources res;
    Drawable drawable, drawable2, drawable3, drawable4, drawable5, drawable6;
    ProgressBar mProgress, mProgress2, mProgress3, mProgress4, mProgress5, mProgress6;
    private Handler handler = new Handler();
    TextView tv, tv2, tv3, tv4, tv5, tv6, systemstatus;

    Button systemON;
    // URL of object to be parsed
    String JsonURL = "https://mature-railroads.000webhostapp.com/samplejson.JSON";
    // This string will hold the results
    int data ;
    int data2;
    // Defining the Volley request queue that handles the URL request concurrently
    RequestQueue requestQueue;
    TextView v1,v2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab1, container, false);
        valuemax1=view.findViewById(R.id.textView9);
        valuemax2=view.findViewById(R.id.textView12);
        valuemax3=view.findViewById(R.id.textView13);
        valuemax4=view.findViewById(R.id.textView15);
        valuemax5=view.findViewById(R.id.textView17);
        valuemax6=view.findViewById(R.id.textView19);

        requestQueue = Volley.newRequestQueue(getActivity());

        // Casts results into the TextView found within the main layout XML with id jsonData



        valuemax1.setText(Integer.toString(max));
        valuemax2.setText(Integer.toString(max2));
        valuemax3.setText(Integer.toString(max2));
        valuemax4.setText(Integer.toString(max2));
        valuemax5.setText(Integer.toString(max2));
        valuemax6.setText(Integer.toString(max2));

//
//        SharedPreferences shared =getActivity().getPreferences(Context.MODE_PRIVATE);
//
//     max = shared.getInt("id", Integer.parseInt("100"));
//     max=max*x;

        //  button = view.findViewById(R.id.button);

        res = this.getResources();

        drawable = res.getDrawable(R.drawable.circular);
        drawable2 = res.getDrawable(R.drawable.circular2);
        drawable3 = res.getDrawable(R.drawable.circular3);
        drawable4 = res.getDrawable(R.drawable.circular4);
        drawable5 = res.getDrawable(R.drawable.circular5);
        drawable6 = res.getDrawable(R.drawable.circular6);

        mProgress = view.findViewById(R.id.circularProgressbar);
        mProgress2 = view.findViewById(R.id.circularProgressbar2);
        mProgress3 = view.findViewById(R.id.circularProgressbar3);
        mProgress4 = view.findViewById(R.id.circularProgressbar4);
        mProgress5 = view.findViewById(R.id.circularProgressbar5);
        mProgress6 = view.findViewById(R.id.circularProgressbar6);

        mProgress.setProgress(0);   // Main Progress
        mProgress2.setProgress(0);
        mProgress3.setProgress(0);
        mProgress4.setProgress(0);
        mProgress5.setProgress(0);
        mProgress6.setProgress(0);

        mProgress.setSecondaryProgress(val); // Secondary Progress
        mProgress2.setSecondaryProgress(val2);
        mProgress3.setSecondaryProgress(val3);
        mProgress4.setSecondaryProgress(val4);
        mProgress5.setSecondaryProgress(val5);
        mProgress6.setSecondaryProgress(val6);

        mProgress.setMax(max); // Maximum Progress
        mProgress2.setMax(max2);
        mProgress3.setMax(max3);
        mProgress4.setMax(max4);
        mProgress5.setMax(max5);
        mProgress6.setMax(max6);


        mProgress.setProgressDrawable(drawable);
        mProgress2.setProgressDrawable(drawable2);
        mProgress3.setProgressDrawable(drawable3);
        mProgress4.setProgressDrawable(drawable4);
        mProgress5.setProgressDrawable(drawable5);
        mProgress6.setProgressDrawable(drawable6);


//    ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
//    animation.setDuration(5000);
//    animation.setInterpolator(new DecelerateInterpolator());
//    animation.start();
        tv = view.findViewById(R.id.tv);
        tv2 = view.findViewById(R.id.tv2);
        tv3 = view.findViewById(R.id.tv3);
        tv4 = view.findViewById(R.id.tv4);
        tv5 = view.findViewById(R.id.tv5);
        tv6 = view.findViewById(R.id.tv6);
        progressbar();
        progressbar2();
        progressbar3();
        progressbar4();
        progressbar5();
        progressbar6();
        mProgress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerForContextMenu(mProgress);
                registerForContextMenu(mProgress4);
            }
        });
        systemON=view.findViewById(R.id.button3);
        systemstatus=view.findViewById(R.id.textView21);
        systemON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // TODO Auto-generated method stub

                if(i==0)

                {
                    systemON.setText("SYSTEM OFF");
                systemstatus.setText("YES");
                    i++;

                }

                else if(i==1)

                {

             systemON.setText("SYSTEM ON");
            systemstatus.setText("NO");
                    i=0;

                }

            }
        });
        return view;

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        if(v.getId() == R.id.circularProgressbar ) {
            inflater.inflate(R.menu.menu_main, menu);
            menu.setHeaderTitle("Select The Action");
        }else if(v.getId() == R.id.circularProgressbar4){
            menu.setHeaderTitle("Select The Action");
            inflater.inflate(R.menu.menu_main2,menu);
        }

    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.maxvoltage) {
            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

            alert.setTitle("Set Max Voltage");
            alert.setMessage("Enter the value");

// Set an EditText view to get user input
            final EditText input = new EditText(getActivity());
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alert.setView(input);
            //set button


            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String temp = input.getText().toString();
                    int value = 0;
                    if (!(temp.isEmpty())) {
                        value = Integer.parseInt(temp);
                        if(value<val){
                            Toast.makeText(getActivity(),"Not Possible",Toast.LENGTH_LONG).show();
                        }
                        else {
                            int userdefmax;
                            valuemax1.setText(Integer.toString(value));
                            valuemax2.setText(Integer.toString(value));
                            valuemax3.setText(Integer.toString(value));


                            userdefmax = value * 2;
                            mProgress.setMax(userdefmax);
                            mProgress2.setMax(userdefmax);
                            mProgress3.setMax(userdefmax);

                            progressbar();
                            progressbar2();
                            progressbar3();
                        }
                    }

                }

            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            alert.show();

        }else if(item.getItemId() == R.id.maxcurrent)
        {
            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

            alert.setTitle("Set Max Current");
            alert.setMessage("Enter the value");

// Set an EditText view to get user input
            final EditText input = new EditText(getActivity());
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            alert.setView(input);
            //set button


            alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    String temp = input.getText().toString();
                    int value = 0;
                    if (!(temp.isEmpty())) {
                        value = Integer.parseInt(temp);
                        if(value<val){
                            Toast.makeText(getActivity(),"Not Possible",Toast.LENGTH_LONG).show();
                        }
                        else {

                            int userdefmax;
                            valuemax4.setText(Integer.toString(value));
                            valuemax5.setText(Integer.toString(value));
                            valuemax6.setText(Integer.toString(value));


                            userdefmax = value * 2;
                            mProgress4.setMax(userdefmax);
                            mProgress5.setMax(userdefmax);
                            mProgress6.setMax(userdefmax);
                            progressbar4();
                            progressbar5();
                            progressbar6();
                        }
                    }

                }

            }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                }
            });

            alert.show();

        }
        return super.onContextItemSelected(item);
    }




    public void progressbar () {
//getdata();

        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus < data) {
                    pStatus += 1;


                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress.setProgress(pStatus);
                            tv.setText(pStatus + "V");

                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void progressbar2 () {


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus2 < val2 ) {
                    pStatus2 += 1;


                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress2.setProgress(pStatus2);
                            tv2.setText(pStatus2 + "V");


                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void progressbar3 () {


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus3 < val3 ) {
                    pStatus3 += 1;


                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress3.setProgress(pStatus3);
                            tv3.setText(pStatus3 + "V");


                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void progressbar4 () {


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus4 < val4 ) {
                    pStatus4 += 1;


                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress4.setProgress(pStatus4);
                            tv4.setText(pStatus4 + "V");


                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void progressbar5 () {


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus5 < val5 ) {
                    pStatus5 += 1;


                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress5.setProgress(pStatus5);
                            tv5.setText(pStatus5 + "V");


                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void progressbar6 () {


        new Thread(new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                while (pStatus6 < val6 ) {
                    pStatus6 += 1;


                    handler.post(new Runnable() {

                        @Override
                        public void run() {
                            // TODO Auto-generated method stub
                            mProgress6.setProgress(pStatus2);
                            tv6.setText(pStatus6 + "V");


                        }
                    });
                    try {
                        // Sleep for 200 milliseconds.
                        // Just to display the progress slowly
                        Thread.sleep(16); //thread will take approx 3 seconds to finish
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void getdata(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                Timer t=new Timer();
                t.scheduleAtFixedRate(new TimerTask() {
                    @Override
                    public void run() {

                        // Creating the JsonObjectRequest class called obreq, passing required parameters:
                        //GET is used to fetch data from the server, JsonURL is the URL to be fetched from.
                        JsonObjectRequest obreq = new JsonObjectRequest(Request.Method.GET, JsonURL,null,
                                // The third parameter Listener overrides the method onResponse() and passes
                                //JSONObject as a parameter
                                new Response.Listener<JSONObject>() {
                                    // Takes the response from the JSON request
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        try {

                                            JSONObject obj = response.getJSONObject("employee");
                                            // Retrieves the string labeled "colorName" and "description" from
                                            //the response JSON Object
                                            //and converts them into javascript objects
                                            int color = obj.getInt("volt1");

                                            int desc = obj.getInt("volt2");

                                            // Adds strings from object to the "data" string

                                            data = color;
                                            data2 = desc;


                                            v2.setText(data2);

                                            v1.setText(data);




                                            // Adds the data string to the TextView "results"


                                        }
                                        // Try and catch are included to handle any errors due to JSON
                                        catch (JSONException e) {
                                            // If an error occurs, this prints the error to the log
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                // The final parameter overrides the method onErrorResponse() and passes VolleyError
                                //as a parameter
                                new Response.ErrorListener() {
                                    @Override
                                    // Handles errors that occur due to Volley
                                    public void onErrorResponse(VolleyError error) {
                                        Log.e("Volley", "Error");
                                    }
                                }
                        );
                        // Adds the JSON object request "obreq" to the request queue
                        requestQueue.add(obreq);

                    }
                },0,10000);


            }
        }).start();


    }
}



