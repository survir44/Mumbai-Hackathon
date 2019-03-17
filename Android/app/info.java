package com.example.veto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class info extends AppCompatActivity {
    TextView age,edu,prof,assests;
    TextView cc,bio;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        Intent intent=getIntent();
        String n= intent.getStringExtra("name");
        getFromMyServer();
    }
    public void getFromMyServer(){
        age=(TextView) findViewById(R.id.txt1);
        edu=(TextView) findViewById(R.id.txt2);
        prof=(TextView) findViewById(R.id.txt3);
        assests=(TextView) findViewById(R.id.txt4);
        cc= (TextView) findViewById(R.id.txt5);
        bio=(TextView) findViewById(R.id.txt6);
        Intent intent=getIntent();
        String n= intent.getStringExtra("name");
        String server_url="http://192.168.0.4:1337/";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("name",n);
        }catch(JSONException e){
            e.printStackTrace();;
        }

        final String stringObject=jsonObject.toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),"Got some Response from server",Toast.LENGTH_SHORT).show();
                try {
                    recievedData(response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        })
        {
            @Override
            public String getBodyContentType(){ return "application/json; charset=utf-8";}

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return stringObject == null ? null : stringObject.getBytes("utf-8");
                }catch (UnsupportedEncodingException em){
                    return null;
                }
            }
        };

        requestQueue.add(stringRequest);

    }

    public void  recievedData(String response) throws JSONException {


        JSONObject jsonObject=new JSONObject(response);
        age.setText(jsonObject.getString("age"));
        edu.setText(jsonObject.getString("education"));
        prof.setText(jsonObject.getString("profession"));
        assests.setText(jsonObject.getString("assests"));
        cc.setText(jsonObject.getString("crime"));
        bio.setText(jsonObject.getString("bio"));


    }


}



