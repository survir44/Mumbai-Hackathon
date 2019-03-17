package com.example.veto;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
public class MainActivity extends AppCompatActivity {
    android.widget.ProgressBar mProgressBar;
    CountDownTimer mCountDownTimer;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        mProgressBar= findViewById(R.id.progressbar);
        mProgressBar.setProgress(i);
        mCountDownTimer=new CountDownTimer(5000,100) {
            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Log_tag", "Tick of Progress"+ i+ millisUntilFinished);
                i++;
                mProgressBar.setProgress(i *100/(5000/1000));
            }
            @Override
            public void onFinish() {
                //Do what you want
                i++;
                mProgressBar.setProgress(100);
            }
        };
        mCountDownTimer.start();
        Thread thread = new Thread(){
            @Override
            public void run(){
                try{
                    sleep(1*1000);
                    Intent i= new Intent(getApplicationContext(),LogPage.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                }
            }
        };
        thread.start();
    }
}
