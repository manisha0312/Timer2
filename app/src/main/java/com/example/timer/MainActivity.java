package com.example.timer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    SeekBar seekBar;
    boolean activecounter=false;
    Button btn2;
    CountDownTimer countDownTimer;
    public void resettimer(){
        textView.setText("00:30");//again set text view
        seekBar.setProgress(30);
        seekBar.setEnabled(true);//now we can chaNge seek baar value
        countDownTimer.cancel();//to cancel that
        btn2.setText("GO");
        activecounter=false;
    }

    public void Click(View view){
        Log.i("click","nice");
        // it will work when we stop AND START
        if(activecounter){//TO WORK AGAIN OR UPDATE THE VALUE
           resettimer();

        }
        else {// WHEN WE STOP THE VALUE THEN IT GOES TO IF
            activecounter = true;// for active the counter
            seekBar.setEnabled(false);// when first time start timer then seekbar will not move
            btn2.setText("STOP");


           countDownTimer= new CountDownTimer(seekBar.getProgress() * 1000, 1000) {// seekbar value will show here in mili second
                public void onTick(long l) {
                    updatetimer((int)l/1000); // for every second
                }

                public void onFinish() {
                    Log.i("finish", "working");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.analogsimion);
                    mediaPlayer.start();
                    resettimer();
                }
            }.start();
        }}

    public void updatetimer(int secondleft){
        int min=secondleft/60;
        int sec=secondleft-min*60;
        String second=Integer.toString(sec);
        if (sec<=9){
            second="0"+second;
        }
        String minutes=Integer.toString(min);
        if (minutes.equals("0")){
            minutes="00";
        }
        textView.setText(minutes+":"+second);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         seekBar=findViewById(R.id.seekBar);
         textView=findViewById(R.id.textView);
         btn2=findViewById(R.id.button);
        seekBar.setMax(600);// 10 min 600sec
        seekBar.setProgress(30);//start in 30 sec
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updatetimer(progress);//calling the method
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
