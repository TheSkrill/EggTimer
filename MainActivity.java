package com.nocomp.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    SeekBar seekbar;
    TextView textView;
    Boolean counterIsActive=false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer()
    {
        textView.setText("00:30");
        seekbar.setProgress(30);
        countDownTimer.cancel();
        button.setText("Go !");
        seekbar.setEnabled(true);
        counterIsActive = false;
    }


   void funcn(int secondsLeft)
    {
        int minutes = secondsLeft/60;
        int seconds = secondsLeft - (minutes*60);
        String secondString = Integer.toString(seconds);

        if(seconds<=9)
        {
            secondString="0"+secondString;
        }
        textView.setText(Integer.toString(minutes)+":"+secondString);
    }

    public void controlTimer(View view)
    {
        if(counterIsActive==false) {
            counterIsActive = true;
            seekbar.setEnabled(false);
            button.setText("Stop");
            countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long millisUntilFinished) {
                    funcn((int) millisUntilFinished / 1000);

                }

                @Override
                public void onFinish() {
                    resetTimer();
                    MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mplayer.start();
                }
            }.start();

        }
        else {
            resetTimer();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = (SeekBar) findViewById(R.id.seekBar);

       textView = (TextView) findViewById(R.id.textView);

        button = (Button) findViewById(R.id.button);
        seekbar.setMax(600);
        seekbar.setProgress(30);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                        funcn(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}


