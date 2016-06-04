package com.tx.agir;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class IntroAgiter extends AppCompatActivity {

    private static int TIME_OUT = 4000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_agiter);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                Intent i = new Intent(IntroAgiter.this, Agiter.class);
//                startActivity(i);
//                IntroAgiter.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
//
//                finish();
            }
        }, TIME_OUT);
    }
}
