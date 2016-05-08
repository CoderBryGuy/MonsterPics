package com.example.hackeru.monsterpics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {
    int counter = 0;
    LinearLayout mainLayout;
    int[] images = {R.drawable.monster8, R.drawable.monster2, R.drawable.monster3,
            R.drawable.monster4, R.drawable.monster5, R.drawable.monster6};
    int[] numbers={0,0,1,1,2,2,3,3,4,4,5,5};
    int turns = 1;
    private ImageView previousCard;
    private int rightGuesses = 0;
    private final static int GAME_OVER_REQUEST_CODE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainLayout = (LinearLayout) findViewById(R.id.activity_main_layout);
        LinearLayout.LayoutParams params =
                new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT);

        params.weight = 1;

        for (int i = 0; i <4 ; i++) {
        LinearLayout rowlayout = new LinearLayout(this);
            for (int j = 0; j <3 ; j++) {
                ImageView imageView = new ImageView(this);
                imageView.setTag(counter++);
                imageView.setOnClickListener(this);
                imageView.setImageResource(R.drawable.monster1);
                rowlayout.addView(imageView, params);
            }
            mainLayout.addView(rowlayout, params);
        }


    }


    @Override
    public void onClick(View v) {
        ImageView clickedCard = (ImageView)v;
        int image =  images[numbers[(int)v.getTag()]];
        clickedCard.setImageResource(image);

if(clickedCard != previousCard) {
    if (turns % 2 == 0) {
        //check cards
        if (checkCards(clickedCard, previousCard)) {
            //they are the same
            clickedCard.setOnClickListener(null);
            previousCard.setOnClickListener(null);
            rightGuesses++;
         /*   if(rightGuesses == images.length)
            {
                return;
            }*/
            //they are the same
        } else {
            //flip them
            FlipThread thread = new FlipThread(clickedCard, previousCard);
            thread.start();
        }
    } else {
        //  cardToMatch =  Integer.parseInt(v.getTag().toString());

        previousCard = clickedCard;
    }
    turns++;
    if(rightGuesses == images.length)
    {
        Intent intent = new Intent(this, DoneActivity.class);
        intent.putExtra("score", turns);
        startActivityForResult(intent, GAME_OVER_REQUEST_CODE);
    }
    //Toast.makeText(this, v.getTag().toString(), Toast.LENGTH_SHORT).show();
}
else{
//Toast.makeText(this, "this is nor the")
}

    }
@Override
protected void onActivityResult(int RequestCode, int resultCode, Intent Data)
{
    if(RequestCode == GAME_OVER_REQUEST_CODE){
        if(resultCode==DoneActivity.REMATCH){
            //new game
            startNewGame();
            Toast.makeText(this, "new game", Toast.LENGTH_SHORT).show();
        }
        else if(resultCode == DoneActivity.QUIT){
            finish();
        }
    }

}
    private void startNewGame(){
        counter = 0;
        rightGuesses = 0;
        turns = 0;
        for (int i = 0; i < numbers.length; i++) {
            previousCard = (ImageView)mainLayout.findViewWithTag(i);
            previousCard.setImageResource(R.drawable.monster1);
            }
        //shuffle();
    }

    private void shuffle(){
        //shuffle to number array...
        int temp = 0;
        Random random = new Random();

        for (int i = 0; i < numbers.length; i++) {
            int r = random.nextInt(numbers.length);
            temp = numbers[i];
            numbers[i] = numbers[r];
            numbers[r] = temp;
        }
    }

    private boolean checkCards(ImageView clickedCard, ImageView previousCard) {
        return numbers[(int)previousCard.getTag()] == numbers[(int)clickedCard.getTag()];
    }
}
