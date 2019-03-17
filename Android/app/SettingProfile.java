package com.example.veto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class SettingProfile extends AppCompatActivity {
    TextView nameInput1,emailInput1,mobileInput1,genderInput1,addressInput1;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_profile);
        btn=(Button)findViewById(R.id.SPbutton1);
        nameInput1=findViewById(R.id.SPText6);
        emailInput1=findViewById(R.id.SPText8);
        mobileInput1=findViewById(R.id.SPText10);
        genderInput1=findViewById(R.id.SPText12);
        addressInput1=findViewById(R.id.SPText14);
        getFromMyServer();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),EditProfile.class);
                startActivity(intent);
            }
        });

    }
    public void getFromMyServer(){
        String server_url="http://192.168.42.156:8080/profile/view";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username","sky44");
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
        nameInput1.setText(jsonObject.getString("name"));
        genderInput1.setText(jsonObject.getString("gender"));
        emailInput1.setText(jsonObject.getString("email"));
        mobileInput1.setText(jsonObject.getString("mobile_no"));
        addressInput1.setText(jsonObject.getString("address"));


    }


}
