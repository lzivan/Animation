package com.example.animation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button reset;
    ImageView pig;
    private CountDownTimer countDownTimer;
    private long timeleftinmiliseconds = 10000;
    private boolean isTimerunning;
    TextView txt;
    AnimationSet animSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reset = findViewById(R.id.button);
        pig = findViewById(R.id.imageView);
        txt = findViewById(R.id.txtView);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.animation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        txt.setText("0");
        switch (item.getItemId()){
            case R.id.menu_item1:

                timeleftinmiliseconds = 10000;
                startStop();
                updateTime();
                Animation anSetone = AnimationUtils.loadAnimation(this, R.anim.animation_setone);
                pig.startAnimation(anSetone);
                Toast.makeText(this, "item1 selected",Toast.LENGTH_SHORT);
                return true;
            case R.id.menu_item2:
                Toast.makeText(this, "item2 selected",Toast.LENGTH_SHORT);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void startStop(){
        if (isTimerunning){
            stopTime();
        }else{
            starTime();
        }
    }
    public void starTime(){
        countDownTimer = new CountDownTimer(timeleftinmiliseconds,1000) {
            @Override
            public void onTick(long l) {
                timeleftinmiliseconds = l;
                updateTime();
            }

            @Override
            public void onFinish() {

            }
        }.start();
        isTimerunning = true;
    }

    public void stopTime(){
        countDownTimer.cancel();
        isTimerunning = false;
    }

    public void updateTime(){

        int seconds =  (int) timeleftinmiliseconds % 10000 / 1000;
        seconds = 10 - seconds;


        String timeLeftText;
        timeLeftText = "" + seconds;
        txt.setText(timeLeftText);
    }
}