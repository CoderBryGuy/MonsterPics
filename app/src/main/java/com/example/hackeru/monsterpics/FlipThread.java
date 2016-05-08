package com.example.hackeru.monsterpics;
import android.os.Handler;
import android.widget.ImageView;

import com.example.hackeru.monsterpics.R;

/**
 * Created by hackeru on 08/05/2016.
 */
public class FlipThread extends Thread {
    private ImageView clickedCard;
    private ImageView previousCard;
    private Handler handler;
    private int delayTime = 1000;

    public FlipThread(ImageView clickedCard, ImageView previousCard)
    {
        this.clickedCard = clickedCard;
        this.previousCard = previousCard;
        handler = new Handler();
    }

    @Override
    public void run(){
        try {
            sleep(delayTime);
        }
       catch (InterruptedException e)
        {
            e.printStackTrace();
        }

    handler.post(new Runnable() {
        @Override
        public void run() {
            clickedCard.setImageResource((R.drawable.monster1));
            previousCard.setImageResource(R.drawable.monster1);
        }
    });
}
}
