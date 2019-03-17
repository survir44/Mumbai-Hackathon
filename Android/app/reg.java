package com.example.veto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.veto.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
public class reg extends AppCompatActivity {
    EditText Name, Username, Password, CPassword;
    Button Register;
    String server_url = "http://192.168.42.156:8080/profile/register";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        Name = (EditText) findViewById(R.id.txt1);
        Username = (EditText) findViewById(R.id.txt2);
        Password = (EditText) findViewById(R.id.txt3);
        CPassword = (EditText) findViewById(R.id.txt4);
        Register = (Button) findViewById(R.id.btn);

        final String name = Name.getText().toString();
        final String user = Username.getText().toString();
        final String pass = Password.getText().toString();
        final String cpass = CPassword.getText().toString();

        Register.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            registerUser();
                                        }
                                    }
        );
    }
    private void registerUser()
    {
        final String name = Name.getText().toString();
        final String user = Username.getText().toString();
        final String pass = Password.getText().toString();
        final String cpass = CPassword.getText().toString();

        if (TextUtils.isEmpty(name)) {
            Name.setError("Please enter name");
            Name.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(user)) {
            Username.setError("Please Enter username");
            Username.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(pass)) {
            Password.setError("Please Enter Password");
            Password.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(cpass)) {
            CPassword.setError("Please Confirm your Password");
            CPassword.requestFocus();
            return;
        }
        if (!(pass.equals(cpass))) {
            Toast.makeText(getApplicationContext(), "Please Enter Valid Password", Toast.LENGTH_SHORT).show();
            Password.setText("");
            CPassword.setText("");
            return;
        }
        else
        {
            Intent in = new Intent(getApplicationContext(),Homemenu.class);
            startActivity(in);
        }

        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username", user);
            jsonObject.put("password", pass);
            jsonObject.put("name",name);



        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        final String requestBody = jsonObject.toString();
        Log.i("volleyABC", requestBody);

        StringRequest stringRequest =new StringRequest(Request.Method.POST,server_url,new Response.Listener<String>(){
            @Override
            public void onResponse(String response) {

                Log.i("volleyABC" ,"got response"+response);

                Toast.makeText(getApplicationContext(),response ,Toast.LENGTH_SHORT).show();

            }
        },new Response.ErrorListener()  {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();

            }
        }){

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}









