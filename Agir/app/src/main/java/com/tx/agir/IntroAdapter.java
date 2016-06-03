package com.tx.agir;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Rebecca Fribourg on 03/06/2016.
 */
public class IntroAdapter extends AppCompatActivity {

    private static int TIME_OUT = 4000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro_adapter);
       // final View myLayout = findViewById(R.id.intro_adapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(IntroAdapter.this, Adapter.class);
                startActivity(i);
                IntroAdapter.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);

                finish();
            }
        }, TIME_OUT);
    }


}
