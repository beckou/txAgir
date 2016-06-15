package com.tx.agir;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by Rebecca Fribourg on 03/06/2016.
 */
public class IntroAdapter extends AppCompatActivity {

    private static int TIME_OUT = 6000; //Time to launch the another activity
    private TextView intro;
    private Handler mHandler;
    private Runnable mRunnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_adapter);

        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(IntroAdapter.this, Adapter.class);
                startActivity(i);
                IntroAdapter.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);

                finish();
            }
        };

        IntroAdapter.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);


        intro = (TextView) findViewById(R.id.introAdapter);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoiretOne-Regular.ttf");
        intro.setTypeface(myCustomFont);

        mHandler.postDelayed(mRunnable, TIME_OUT);

    }

    public void onStop () {
//do your stuff here
        super.onStop();
        System.out.println("stopppp");
        mHandler.removeCallbacks(mRunnable);
    }

}
