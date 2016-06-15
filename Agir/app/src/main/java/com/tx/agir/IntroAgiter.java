package com.tx.agir;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.tx.agir.ShakePackage.Shake;

public class IntroAgiter extends AppCompatActivity {

    private static int TIME_OUT = 6000; //Time to launch the another activity

    private TextView intro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_agiter);

        IntroAgiter.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);


        intro = (TextView) findViewById(R.id.introAgiter);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoiretOne-Regular.ttf");
        intro.setTypeface(myCustomFont);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(IntroAgiter.this, Shake.class);
                startActivity(i);
                IntroAgiter.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);

                finish();
            }
        }, TIME_OUT);
    }

}
