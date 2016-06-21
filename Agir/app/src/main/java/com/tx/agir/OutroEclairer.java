package com.tx.agir;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.tx.agir.LightPackage.Light;

public class OutroEclairer extends AppCompatActivity {

    private static int TIME_OUT = 6000; //Time to launch the another activity

    private TextView intro;

    private FloatingActionButton back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outro_eclairer);
        Bundle b = getIntent().getExtras();
        String Phrase_to_print[] = b.getStringArray("phraseOutro");
        OutroEclairer.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);

        intro = (TextView) findViewById(R.id.outroEclairer);
        intro.setText(Phrase_to_print[0] + " " + Phrase_to_print[1] + " " + Phrase_to_print[2] + " " + Phrase_to_print[3]);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoiretOne-Regular.ttf");
        intro.setTypeface(myCustomFont);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(OutroEclairer.this, MainActivity.class);
//                startActivity(i);
//                OutroEclairer.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
//
//                finish();
//            }
//        }, TIME_OUT);


        back = (FloatingActionButton)  findViewById(R.id.back);
        if(back != null){
            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(OutroEclairer.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
            });

        }


    }
}
