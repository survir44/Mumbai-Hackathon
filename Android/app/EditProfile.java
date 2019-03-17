package com.example.veto;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

public class EditProfile extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton radioButton;
    EditText usernameInput;
    EditText nameInput;
    EditText emailInput;
    EditText mobileInput;
    EditText addressInput;
    Button  EDbutton;
    String name,email,mobile,gender,address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        radioGroup=(RadioGroup)findViewById(R.id.radio_group);
        EDbutton=(Button)findViewById(R.id.EDbutton1);
        EDbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameInput=(EditText)findViewById(R.id.EDText6);
                emailInput=(EditText)findViewById(R.id.EDText8);
                mobileInput=(EditText)findViewById(R.id.EDText10);
                addressInput=(EditText)findViewById(R.id.EDText14);
                name=nameInput.getText().toString();
                email=emailInput.getText().toString();
                mobile=mobileInput.getText().toString();
                address=addressInput.getText().toString();
                if (TextUtils.isEmpty(name)){
                    nameInput.setError("Name Field cannot be left Empty");
                    nameInput.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(email)){
                    emailInput.setError("Email Address Field cannot be left Empty");
                    emailInput.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(mobile)){
                    mobileInput.setError("Mobile Number Field cannot be left Empty");
                    mobileInput.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(address)){
                    addressInput.setError("Address Field cannot be left Empty");
                    addressInput.requestFocus();
                    return;
                }
                insertInProfileServer();
           }
        });
    }

    public void checkButton(View v){
        int radioid=radioGroup.getCheckedRadioButtonId();

        radioButton=findViewById(radioid);
        gender=radioButton.getText().toString();
    }

    public void insertInProfileServer(){
        String server_url="http://192.168.42.156:8080/profile/edit";
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        final JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("username","sky44");
            jsonObject.put("name", name);
            jsonObject.put("email", email);
            jsonObject.put("mobile_no", mobile);
            jsonObject.put("gender", gender);
            jsonObject.put("address", address);

        }catch(JSONException e){
            e.printStackTrace();;
        }
        final String stringObject=jsonObject.toString();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, server_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();



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
}
