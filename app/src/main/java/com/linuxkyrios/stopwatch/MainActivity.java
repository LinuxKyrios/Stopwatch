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
    private boolean wasRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super .onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");
        }
        goTimer();
    }

    //Saving variable state in onSaveInstanceState method
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }
    //created method onStop to stop counting when app is not visible
    /*@Override
    protected void onStop() {
        super.onStop();
        wasRunning = running;
        running = false;
    }*/

    //Creating own onPause() method to replace previously created onStop() method

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    //this method if the stopwatch was running, it will continue to run
    /*@Override
    protected void onStart() {
        super.onStart();
        if (wasRunning) {
            running = true;
        }
    }
    */

    //Created own onResume() method to replace previously created onStart() method;
    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
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