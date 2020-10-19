package com.example.wifi.TabActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.wifi.DeviceList;
import com.example.wifi.MainActivity;
import com.example.wifi.R;
import com.example.wifi.ViewAlarm.ViewAlarm;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

    public class Tab2 extends Fragment {

    private String URLstring = "https://mature-railroads.000webhostapp.com/newparsing.JSON";
    String URLstring2="https://mature-railroads.000webhostapp.com/jsondevicelist.JSON";
    private static ProgressDialog mProgressDialog;
    ArrayList<DataModel> dataModelArrayList;
    private RvAdapter rvAdapter;
    private RecyclerView recyclerView;
    Button additem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fetchingJSON();
    }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            setHasOptionsMenu(true);
        }

        @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.tab1samplefragment, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        additem=view.findViewById(R.id.additembutton);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent iso=new Intent(getActivity(),DeviceList.class);
                startActivity(iso);

            }
        });
        return view;

    }
    @Override
    public void onStart() {
        Toast.makeText(getActivity(),"Onstart ",Toast.LENGTH_SHORT).show();
        super.onStart();
    }
    @Override
    public void onResume() {
       final String data= SingletonSession2.Instance().getUsername();


        Toast.makeText(getActivity(),"On Resume",Toast.LENGTH_SHORT).show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,

                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            removeSimpleProgressDialog();

                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("status").equals("true")) {


                                JSONArray dataArray2 = obj.getJSONArray(data);

                                for (int i = 0; i < dataArray2.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray2.getJSONObject(i);

                                    playerModel.setR1Value(dataobj.getString("name"));
                                    playerModel.setR2Value(dataobj.getString("country"));
                                    playerModel.setY1Value(dataobj.getString("city"));
                                    playerModel.setY2Value(dataobj.getString("imgURL"));
                                    playerModel.setB1Value(dataobj.getString("blue1"));
                                    playerModel.setB2Value(dataobj.getString("blue2"));
                                    playerModel.setIDDevice(data);
                                    dataModelArrayList.add(0, playerModel);
                                    rvAdapter.notifyItemInserted(1);
                                }

                                setupRecycler();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);
        super.onResume();
    }

    @Override
    public void onPause() {
        Toast.makeText(getActivity(),"On Pause",Toast.LENGTH_SHORT).show();
        super.onPause();
    }

    @Override
    public void onStop() {
        Toast.makeText(getActivity(),"On STOP",Toast.LENGTH_SHORT).show();

        super.onStop();
    }
        @Override
        public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
            super.onCreateOptionsMenu(menu, inflater);
            inflater.inflate(R.menu.menu_main,menu);
        }

        @Override
        public boolean onOptionsItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.viewalarm:
                    Toast.makeText(getActivity(), "Calls Icon Click", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(), ViewAlarm.class);
                    startActivity(intent);                    //return true;
                default:
                    return super.onOptionsItemSelected(item);
            }
        }

    private void fetchingJSON() {

        showSimpleProgressDialog(getActivity(), "Loading...","Fetching Json",false);
        final String data= SingletonSession.Instance().getUsername();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            removeSimpleProgressDialog();

                            JSONObject obj = new JSONObject(response);
                            if(obj.optString("status").equals("true")){

                                dataModelArrayList = new ArrayList<>();
                                JSONArray dataArray  = obj.getJSONArray(data);

                                for (int i = 0; i < dataArray.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);


                                    playerModel.setR1Value(dataobj.getString("name"));
                                    playerModel.setR2Value(dataobj.getString("country"));
                                    playerModel.setY1Value(dataobj.getString("city"));
                                    playerModel.setY2Value(dataobj.getString("imgURL"));
                                    playerModel.setB1Value(dataobj.getString("blue1"));
                                    playerModel.setB2Value(dataobj.getString("blue2"));
                                    playerModel.setIDDevice(data);
                                    dataModelArrayList.add(playerModel);

                                }

                                setupRecycler();

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);


    }

    private void setupRecycler(){

        rvAdapter = new RvAdapter(getActivity(),dataModelArrayList);
        recyclerView.setAdapter(rvAdapter);
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

    }

    public static void removeSimpleProgressDialog() {
        try {
            if (mProgressDialog != null) {
                if (mProgressDialog.isShowing()) {
                    mProgressDialog.dismiss();
                    mProgressDialog = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void showSimpleProgressDialog(Context context, String title,
                                                String msg, boolean isCancelable) {
        try {
            if (mProgressDialog == null) {
                mProgressDialog = ProgressDialog.show(context, title, msg);
                mProgressDialog.setCancelable(isCancelable);
            }

            if (!mProgressDialog.isShowing()) {
                mProgressDialog.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



 /*   public void alertdialog() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            removeSimpleProgressDialog();

                            JSONObject obj = new JSONObject(response);
                            if (obj.optString("status").equals("true")) {


                                JSONArray dataArray2 = obj.getJSONArray("data");

                                for (int i = 0; i < dataArray2.length(); i++) {

                                    DataModel playerModel = new DataModel();
                                    JSONObject dataobj = dataArray2.getJSONObject(i);

                                    String name = dataobj.getString("name");


                                    items.add(name);
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                                    builder.setTitle("Choose Device");
                                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                                            android.R.layout.simple_dropdown_item_1line, items);
                                    builder.setAdapter(dataAdapter, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Toast.makeText(getActivity(), "You have selected " + items.get(which), Toast.LENGTH_LONG).show();
                                        }
                                    });
                                    AlertDialog dialog = builder.create();
                                    dialog.show();

                                }


                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getActivity(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());

        requestQueue.add(stringRequest);
    }
*/

    }








