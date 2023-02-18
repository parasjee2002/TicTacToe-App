package com.converter.tictacktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    Button Play , Pause;
    MediaPlayer mediaPlayer;
    SeekBar volumeseekBar;
    AudioManager audioManager;
int activePlayer =0;
int[] gameState = {2,2,2,2,2,2,2,2,2};
int[][] winpos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
boolean isGameActive =true;

    public void dropIn(View view) {
        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        gameState[tappedCounter] = activePlayer;

        if(isGameActive){

        }
//        int[] gameState = {2,2,2,2,2,2,2,2,2};
        if (activePlayer == 0) {
            counter.setImageResource(R.drawable.cross);
            activePlayer = 1;
        } else {
            counter.setImageResource(R.drawable.dot);
            activePlayer = 0;
        }

        counter.setTranslationY(-1500);
        counter.animate().translationYBy(1500).setDuration(300);

        for (int[] winpos : winpos) {
            if (gameState[winpos[0]] == gameState[winpos[3]] && gameState[winpos[3]] == gameState[winpos[6]] && gameState[winpos[0]] != 2)
//                Toast.makeText(this , "Someone has won the game"  ,Toast.LENGTH_SHORT).show();
              isGameActive=false;
            if(activePlayer == 0){
                Toast.makeText(this,"Dot one has won",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"Cross one has won", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Play= findViewById(R.id.Play);
        Pause=findViewById(R.id.Pause);
        volumeseekBar=findViewById(R.id.volumeseekBar);
        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        mediaPlayer = MediaPlayer.create(this,R.raw.madness);
        audioManager= (AudioManager) getSystemService(AUDIO_SERVICE);
        int maxVolume=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currVolume=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeseekBar.setMax(maxVolume);
        volumeseekBar.setProgress(currVolume);


            Play.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    mediaPlayer.start();
          }
        });
        Pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mediaPlayer.pause();
            }
        });

        volumeseekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
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