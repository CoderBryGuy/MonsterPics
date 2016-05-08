package com.example.hackeru.monsterpics;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DoneActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int REMATCH = 5;
    public static final int QUIT = 6;
    private TextView scoreTextView;
    private Button rematchButton;
    private Button quitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done);

        scoreTextView = (TextView) findViewById(R.id.activity_done_score);
        rematchButton = (Button)findViewById(R.id.play_again);
        quitButton = (Button)findViewById(R.id.activity_done_quit_button);

        int score = getIntent().getIntExtra("SCORE", 0);
        scoreTextView.setText("Your score is: " + score);
        rematchButton.setOnClickListener(this);
        quitButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        if(v==rematchButton){
            setResult(REMATCH);
        }
        else{
            setResult(QUIT);
        }
    }
}
