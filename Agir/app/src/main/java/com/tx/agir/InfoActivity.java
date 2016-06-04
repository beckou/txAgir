package com.tx.agir;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    private TextView intro;
    private FloatingActionButton revert_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        InfoActivity.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);


        intro = (TextView) findViewById(R.id.infoText);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(), "fonts/PoiretOne-Regular.ttf");
        intro.setTypeface(myCustomFont);


        revert_button = (FloatingActionButton) findViewById(R.id.revertInfo);
        revert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // information activity

                Intent i = new Intent(InfoActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }

}
