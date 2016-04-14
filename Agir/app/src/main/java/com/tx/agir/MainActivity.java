package com.tx.agir;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ParticleModifier;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Button button_eclairer = (Button) findViewById(R.id.eclairer_button);
        button_eclairer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Eclairer.class);
                startActivity(intent);
            }
        });


        TextView textViewCustom = (TextView) findViewById(R.id.TitleText);
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/HARRINGT.TTF");
        textViewCustom.setTypeface(myCustomFont);
        new ParticleSystem(this, 1,R.drawable.essai, 10000)
                .setSpeedRange(0.001f, 0.1f)
                .setFadeOut(10000 , new AccelerateInterpolator())
                .emit(300, 300, 1);

        new ParticleSystem(this, 1,R.drawable.essai, 7000)
                .setSpeedRange(0.001f, 0.1f)
                .setFadeOut(7000 , new AccelerateInterpolator())
                .emit(500, 1000, 1);

        new ParticleSystem(this, 1,R.drawable.essai, 5000)
                .setSpeedRange(0.001f, 0.1f)
                .setFadeOut(5000 , new AccelerateInterpolator())
                .emit(400, 850, 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
