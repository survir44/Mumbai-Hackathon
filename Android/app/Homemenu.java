package com.example.veto;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class Homemenu extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    NavigationView navigationView;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ListItem> listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homemenu);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        listItems=new ArrayList<>();
        try {
            loadRecyclereViewData();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        drawerLayout=(DrawerLayout)findViewById(R.id.drawer);
        navigationView=(NavigationView)findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item){
                switch (item.getItemId()){
                    case R.id.account:
                        Intent intent=new Intent(getApplicationContext(),SettingProfile.class);
                        startActivity(intent);
                        break;
                    case R.id.leaders:
                        Intent i=new Intent(getApplicationContext(),leader.class);
                        startActivity(i);
                        break;
                    case R.id.voters:
                        Toast.makeText(getApplicationContext(), "Account was Selected", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }

        });
    }

    @Override
    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);

        }else{
            super.onBackPressed();
        }

    }

    public void loadRecyclereViewData() throws JSONException {


        String server_url="http://192.168.43.106:1337/";

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        final JSONObject jsonObject=new JSONObject();
        jsonObject.put("abc","abc");

        final String stringObject=jsonObject.toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray array=new JSONArray(response);
                    for (int it=0;it<array.length();it++){
                        JSONObject o=array.getJSONObject(it);

                        ListItem well=new ListItem(
                                o.getString("head"),
                                o.getString("n")
                        );

                        listItems.add(well);

                    }
                    adapter=new MyAdapter(listItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(),"Got some Response from server",Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        });


        requestQueue.add(stringRequest);








    }


}
