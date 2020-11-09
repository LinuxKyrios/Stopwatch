package com.linuxkyrios.stopwatch;

import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Locale;

public class MainActivity extends Activity {

    private int seconds = 0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goTimer();
    }

    //This method activate stopwatch when button start is pressed
    public void onClickStart(View view) {
        running = true;
    }

    //This method stops watch when stop button is pressed
    public void onClickStop(View view) {
        running = false;
    }

    //This method resets stopwatch when reset button is pressed
    public void onClickReset(View view) {
        running = false;
        seconds = 0;
    }

    private void goTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        //Creating new handler instance
        final Handler handler = new Handler();
        //This method contains new Runnable object. Post method executes code instantly
        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secon = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secon);
                //This instruction displays text in TextView component
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }
}