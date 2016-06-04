package com.tx.agir;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WellcomeActivity extends AppCompatActivity {

    private static int TIME_OUT = 4000; //Time to launch the another activity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wellcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(WellcomeActivity.this, MainActivity.class);
                startActivity(i);
                WellcomeActivity.this.overridePendingTransition(R.anim.activity_fade_out, R.anim.activity_fade_out);

                finish();
            }
        }, TIME_OUT);


    }
}
