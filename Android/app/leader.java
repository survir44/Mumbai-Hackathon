package com.example.veto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class leader extends AppCompatActivity {
    TextView a1;
    TextView a2;
    TextView a3;
    TextView a4;
    TextView a5;
    TextView a6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader);
        final Intent intent = getIntent();
        a1=(TextView)findViewById(R.id.l1);
        a2=(TextView)findViewById(R.id.l2);
        a3=(TextView)findViewById(R.id.l3);
        a4=(TextView)findViewById(R.id.l4);
        a5=(TextView)findViewById(R.id.l5);
        a6=(TextView)findViewById(R.id.l6);

        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(leader.this, info.class);
                intent.putExtra("name","Narendra Modi");
                startActivity(intent);
            }

        }  );
        a2.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View view) {
                                      Intent intent = new Intent(leader.this, info.class);
                                      intent.putExtra("name", "Rahul Gandhi");
                                      startActivity(intent);

                                  }
                              }
        );
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(leader.this, info.class);
                intent.putExtra("name","Poonam Mahajan");
                startActivity(intent);

            }



        }  );

        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(leader.this, info.class);
                intent.putExtra("name","Sushma Swaraj");
                startActivity(in);

            }



        }  );

        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(leader.this, info.class);
                intent.putExtra("name","Mamata Banerjee");
                startActivity(intent);

            }



        }  );

        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(leader.this, info.class);
                intent.putExtra("name","Arvind Kejriwal");
                startActivity(intent);

            }

        }  );

    }
}
