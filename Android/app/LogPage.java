package com.example.veto;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;
public class LogPage extends AppCompatActivity {
    Button btn,btn1;
    EditText id,pass;
    String user,pas;
    String server_url = "http://192.168.42.156:8080/login/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_page);
        Toast.makeText(this, "Welcome", Toast.LENGTH_SHORT).show();
        btn= findViewById(R.id.LoginButton);
        btn1=findViewById(R.id.RegisterButton);
        id= findViewById(R.id.IdField);
        pass= findViewById(R.id.PassField);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent j=new Intent(getApplicationContext(),reg.class);
                startActivity(j);
            }});
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=id.getText().toString();
                pas=pass.getText().toString();
                if(TextUtils.isEmpty(user)){
                   id.setError("Empty");
                   id.requestFocus();
                   return;
                }
                if(TextUtils.isEmpty(pas)){
                    pass.setError("Empty");
                    pass.requestFocus();
                    return;
                }
                func();
                Intent i=new Intent(getApplicationContext(),Homemenu.class);
                startActivity(i);
            }
        });
    }
    private void func()
    {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("username",user);
            jsonObject.put("password",pas );
        }catch(JSONException e){
            e.printStackTrace();
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
                    return stringObject.getBytes("utf-8");
                }catch (UnsupportedEncodingException em){
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }
}
