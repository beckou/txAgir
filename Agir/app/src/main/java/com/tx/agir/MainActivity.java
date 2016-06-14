package com.tx.agir;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


import com.plattysoft.leonids.ParticleSystem;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    // Switching languages

    private String languageToLoad = "fr";


    private Button fr_button;
    private Button en_button;



    //private LinearLayout linLayout;
    private RelativeLayout touchview;
    private TextView letterA;
    private TextView letterG ;
    private TextView letterI;
    private TextView letterR;
    FloatingActionButton revert_button;

    private String letterTouch = "";

    private AnimationSet replaceAnimation = null;
    private Animation TranslateAnimation = null;
    private Animation blink = null;

    private Boolean letter_A_clicked;
    private Boolean letter_G_clicked;
    private Boolean letter_I_clicked;
    private Boolean letter_R_clicked;

    private AnimationSet myanimSet = null;
    private Animation anim_letters  = null;
    private Animation slide = null;
    private Animation  fadeIn = null;
    private Animation fadeOut = null;
    private Animation fadeOutOther = null;


    private ParticleSystem parti1;
    private ParticleSystem parti2;
    private ParticleSystem parti3;

    private List<ParticleSystem> particleList;


    // saving pos and scales
    private float firstPosX_A;
    private float firstPosX_G;
    private float firstPosX_I;
    private float firstPosX_R;

    private float firstPosY_A;
    private float firstPosY_G;
    private float firstPosY_I;
    private float firstPosY_R;

    private float firstX_A;
    private float firstX_G;
    private float firstX_I;
    private float firstX_R;

    private float firstY_A;
    private float firstY_G;
    private float firstY_I;
    private float firstY_R;

    private TextView oublier;
    private TextView adapter;
    private TextView agiter;
    private TextView eclairer;

    private TextView erase;
    private TextView adapt;
    private TextView shake;
    private TextView lighten;

    // replace words ///
    private TextView adapter_bis;

    private TextView agiter_bis_1;
    private TextView agiter_bis_2;

    private TextView eclairer_bis_1;
    private TextView eclairer_bis_2;

    private TextView effacer_bis;

    private TextView adapt_bis_1;
    private TextView adapt_bis_2;

    private TextView shake_bis_1;
    private TextView shake_bis_2;

    private TextView erase_bis_1;
    private TextView erase_bis_2;

    private TextView lighten_bis_1;
    private TextView lighten_bis_2;


    ////////////////////

    private RelativeLayout english_menu;
    private RelativeLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MainActivity.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainLayout = (RelativeLayout) findViewById(R.id.mainLayout);

        // Language
        //---------------

        Locale current = getResources().getConfiguration().locale;
        if(Singleton.getInstance().getString() == "fr"){
            Locale locale = new Locale("fr");
            //Locale.setDefault(locale);
            Configuration config = new Configuration();
            config.locale = locale;
            getBaseContext().getResources().updateConfiguration(config,
                    getBaseContext().getResources().getDisplayMetrics());
        }

        setContentView(R.layout.activity_main);

        fr_button = (Button) findViewById(R.id.FR_button);
          en_button = (Button)findViewById(R.id.EN_button);


        if(fr_button != null){

            if(current.getLanguage() == "fr"){
                fr_button.setEnabled(false);
                en_button.setEnabled(true);
                //mainLayout.setVisibility(View.VISIBLE);
                //english_menu.setVisibility(View.INVISIBLE);

            }
            fr_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getBaseContext(),
                            "You have selected French", Toast.LENGTH_SHORT)
                            .show();
                    Singleton.getInstance().setString("fr");

                    setLocale("fr");
                }
            });

        }
        if(en_button != null){
            if(current.getLanguage() == "en"){
                System.out.println("coucou!   en");
               // fr_button.setText("coucou");
                fr_button.setEnabled(true);
                en_button.setEnabled(false);
                //mainLayout.setVisibility(View.INVISIBLE);
                //english_menu.setVisibility(View.VISIBLE);
            }
            en_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getBaseContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                    Singleton.getInstance().setString("en");

                    setLocale("en");


                }
            });
        }


        ///////////////:

//        english_menu = (RelativeLayout) findViewById(R.id.english_menu);
//        english_menu.setVisibility(View.GONE);

        particleList = new ArrayList<ParticleSystem>();



        letter_A_clicked = false;
        letter_I_clicked = false;
        letter_G_clicked = false;
        letter_R_clicked = false;



        //linLayout = (LinearLayout) findViewById(R.id.linLayout);
        touchview= (RelativeLayout) findViewById(R.id.relativeLayout);
        revert_button = (FloatingActionButton)  findViewById(R.id.revert);
        revert_button.setVisibility(View.INVISIBLE);
        revert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                letter_A_clicked = false;
                letter_I_clicked = false;
                letter_G_clicked = false;
                letter_R_clicked = false;


                letterA.setVisibility(View.VISIBLE);
                letterG.setVisibility(View.VISIBLE);
                letterI.setVisibility(View.VISIBLE);
                letterR.setVisibility(View.VISIBLE);
                revert_button.setVisibility(View.INVISIBLE);

                oublier.setVisibility(View.INVISIBLE);
                adapter.setVisibility(View.INVISIBLE);
                agiter.setVisibility(View.INVISIBLE);
                eclairer.setVisibility(View.INVISIBLE);

                adapt.setVisibility(View.INVISIBLE);
                shake.setVisibility(View.INVISIBLE);
                erase.setVisibility(View.INVISIBLE);
                lighten.setVisibility(View.INVISIBLE);



                // replace words
                adapter_bis.setVisibility(View.INVISIBLE);

                agiter_bis_1.setVisibility(View.INVISIBLE);
                agiter_bis_2.setVisibility(View.INVISIBLE);

                eclairer_bis_1.setVisibility(View.INVISIBLE);
                eclairer_bis_2.setVisibility(View.INVISIBLE);

                effacer_bis.setVisibility(View.INVISIBLE);

                adapt_bis_1.setVisibility(View.INVISIBLE);
                adapt_bis_2.setVisibility(View.INVISIBLE);

                shake_bis_1.setVisibility(View.INVISIBLE);
                shake_bis_2.setVisibility(View.INVISIBLE);

                erase_bis_1.setVisibility(View.INVISIBLE);

                erase_bis_2.setVisibility(View.INVISIBLE);

                lighten_bis_1.setVisibility(View.INVISIBLE);
                lighten_bis_2.setVisibility(View.INVISIBLE);



                ///////////




                if (letterTouch.equals("A")) {
                   // replace((int) firstPosX_A, (int) firstPosY_A, firstX_A, firstY_A, letterTouch);
                    letterA.clearAnimation();

                }
                if (letterTouch.equals("G")) {
                    //replace((int) firstPosX_G, (int) firstPosY_G, firstX_G, firstY_G, letterTouch);
                    letterG.clearAnimation();
                }
                if (letterTouch.equals("I")) {
                    //replace((int) firstPosX_I, (int) firstPosY_I, firstX_I, firstY_I, letterTouch);
                    letterI.clearAnimation();
                }
                if (letterTouch.equals("R")) {
                    //replace((int) firstPosX_R, (int) firstPosY_R, firstX_R, firstY_R, letterTouch);
                    letterR.clearAnimation();
                }


                // remove particles
//                if(parti1!=null && parti2!=null && parti2!=null ) {
//
//
//                    parti1.cancel();
//                    parti2.cancel();
//                    parti3.cancel();
//
//
//
//                }

                for (ParticleSystem p : particleList) {
                    p.cancel();
                    p = null;
                }



            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    // information activity

                    Intent i = new Intent(MainActivity.this, InfoActivity.class);
                    startActivity(i);
                  //  MainActivity.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);

                }
            });
        }


//        Button button_eclairer = (Button) findViewById(R.id.eclairer_button);
//        if(button_eclairer != null) {
//            button_eclairer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(MainActivity.this, Eclairer.class);
//                    startActivity(intent);
//                }
//            });
//        }

//        Button button_oublier = (Button) findViewById(R.id.oublier_button);
//        if(button_oublier != null) {
//            button_oublier.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent2 = new Intent(MainActivity.this, Oublier.class);
//                    startActivity(intent2);
//                }
//            });
//        }

       // Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/HARRINGT.TTF");
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoiretOne-Regular.ttf");

        letterA = (TextView) findViewById(R.id.A);
        letterG = (TextView) findViewById(R.id.G);
        letterI = (TextView) findViewById(R.id.I);
        letterR = (TextView) findViewById(R.id.R);





        letterA.setTypeface(myCustomFont);
        letterG.setTypeface(myCustomFont);
        letterI.setTypeface(myCustomFont);
        letterR.setTypeface(myCustomFont);

        firstPosX_A = letterA.getX();
        firstPosX_G = letterG.getX();
        firstPosX_I = letterI.getX();
        firstPosX_R = letterR.getX();

        firstPosY_A = letterA.getY();
        firstPosY_G = letterG.getY();
        firstPosY_I = letterI.getY();
        firstPosY_R = letterR.getY();

        firstX_A = letterA.getScaleX();
        firstX_G = letterG.getScaleX();
        firstX_I = letterI.getScaleX();
        firstX_R = letterR.getScaleX();

        firstY_A = letterA.getScaleY();
        firstY_G = letterG.getScaleY();
        firstY_I = letterI.getScaleY();
        firstY_R = letterR.getScaleY();

        oublier = (TextView) findViewById(R.id.oublier);
        oublier.setVisibility(View.INVISIBLE);


        adapter= (TextView) findViewById(R.id.adapter);
        adapter.setVisibility(View.INVISIBLE);


        agiter = (TextView) findViewById(R.id.agiter);
        agiter.setVisibility(View.INVISIBLE);


        eclairer= (TextView) findViewById(R.id.eclairer);
        eclairer.setVisibility(View.INVISIBLE);



        adapt = (TextView) findViewById(R.id.adapt);
        adapt.setVisibility(View.INVISIBLE);

        shake = (TextView) findViewById(R.id.shake);
        shake.setVisibility(View.INVISIBLE);

        erase = (TextView) findViewById(R.id.erase);
        erase.setVisibility(View.INVISIBLE);

        lighten = (TextView) findViewById(R.id.lighten);
        lighten.setVisibility(View.INVISIBLE);

        /// replace words

        adapter_bis = (TextView) findViewById(R.id.adapter_bis);
        adapter_bis.setTypeface(myCustomFont);

        agiter_bis_1 = (TextView) findViewById(R.id.agiter_bis_1);
        agiter_bis_2 = (TextView) findViewById(R.id.agiter_bis_2);
        agiter_bis_1.setTypeface(myCustomFont);
        agiter_bis_2.setTypeface(myCustomFont);


        eclairer_bis_1 = (TextView) findViewById(R.id.eclairer_bis_1);
        eclairer_bis_2 = (TextView) findViewById(R.id.eclairer_bis_2);
        eclairer_bis_1.setTypeface(myCustomFont);
        eclairer_bis_2.setTypeface(myCustomFont);


        effacer_bis = (TextView) findViewById(R.id.effacer_bis);
        effacer_bis.setTypeface(myCustomFont);


        adapt_bis_1 = (TextView) findViewById(R.id.adapt_bis_1);
        adapt_bis_2 = (TextView) findViewById(R.id.adapt_bis_2);
        adapt_bis_1.setTypeface(myCustomFont);
        adapt_bis_2.setTypeface(myCustomFont);


        shake_bis_1 = (TextView) findViewById(R.id.shake_bis_1);
        shake_bis_2 = (TextView) findViewById(R.id.shake_bis_2);
        shake_bis_1.setTypeface(myCustomFont);
        shake_bis_2.setTypeface(myCustomFont);


        erase_bis_1 = (TextView) findViewById(R.id.erase_bis_1);
        erase_bis_2 = (TextView) findViewById(R.id.erase_bis_2);
        erase_bis_1.setTypeface(myCustomFont);
        erase_bis_2.setTypeface(myCustomFont);


        lighten_bis_1 = (TextView) findViewById(R.id.lighten_bis_1);
        lighten_bis_2 = (TextView) findViewById(R.id.lighten_bis_2);
        lighten_bis_1.setTypeface(myCustomFont);
        lighten_bis_2.setTypeface(myCustomFont);


        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        fadeOut = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
        fadeOutOther = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_other);
        blink = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);

        fadeOut.setAnimationListener(this);
        letterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (letter_A_clicked == true) {
                    // button has already been clicked once.
//                    Intent adapter_intent = new Intent(MainActivity.this, Adapter.class);
//                    startActivity(adapter_intent);

                    Intent adapter_intent = new Intent(MainActivity.this, IntroAdapter.class);
                    startActivity(adapter_intent);



                } else {

                    if ((!letter_G_clicked) && (!letter_I_clicked) && (!letter_R_clicked)) {

                        generateParticules(letterA);

                        letter_A_clicked = true;

                        letterA.clearAnimation();

                        letterG.clearAnimation();
                        letterI.clearAnimation();
                        letterR.clearAnimation();
                        letter_A();
                        letterTouch = "A";

                        //letterA.setTextSize(TypedValue.COMPLEX_UNIT_PX,si);
                        // replace(adapter_A.getX(), adapter_A.getY(),1.0f, 1.0f, letterTouch );

                        letterA.startAnimation(blink);

                        letterG.startAnimation(fadeOut);
                        letterI.startAnimation(fadeOut);
                        letterR.startAnimation(fadeOut);

                        letterG.setVisibility(View.INVISIBLE);
                        letterI.setVisibility(View.INVISIBLE);
                        letterR.setVisibility(View.INVISIBLE);
                        revert_button.setVisibility(View.VISIBLE);


                        if (Singleton.getInstance().getString() == "fr") {
//                        adapter.startAnimation(fadeIn);
//                        adapter.setVisibility(View.VISIBLE);
                            adapter_bis.startAnimation(fadeIn);
                            adapter_bis.setVisibility(View.VISIBLE);
                        } else if (Singleton.getInstance().getString() == "en") {
//                        adapt.startAnimation(fadeIn);
//                        adapt.setVisibility(View.VISIBLE);
                            adapt_bis_1.startAnimation(fadeIn);
                            adapt_bis_1.setVisibility(View.VISIBLE);

                            adapt_bis_2.startAnimation(fadeIn);
                            adapt_bis_2.setVisibility(View.VISIBLE);
                        }


                    }
                }
            }



        });

        letterG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (letter_G_clicked == true) {

                    Intent agiter_intent = new Intent(MainActivity.this, IntroAgiter.class);
                    startActivity(agiter_intent);

                } else {

                    if ((!letter_A_clicked) && (!letter_I_clicked) && (!letter_R_clicked)) {

                        letter_G_clicked = true;

                        generateParticules(letterG);

                        letterA.clearAnimation();
                        letterI.clearAnimation();
                        letterR.clearAnimation();
                        letter_G();
                        letterTouch = "G";
                        // replace(200,0,0.5f,0.5f,letterTouch);


                        letterG.startAnimation(blink);

                        letterI.startAnimation(fadeOut);
                        letterA.startAnimation(fadeOut);
                        letterR.startAnimation(fadeOut);

                        letterI.setVisibility(View.INVISIBLE);
                        letterA.setVisibility(View.INVISIBLE);
                        letterR.setVisibility(View.INVISIBLE);
                        revert_button.setVisibility(View.VISIBLE);


                        if (Singleton.getInstance().getString() == "fr") {
//                    agiter.startAnimation(fadeIn);
//                    agiter.setVisibility(View.VISIBLE);

                            agiter_bis_1.startAnimation(fadeIn);
                            agiter_bis_1.setVisibility(View.VISIBLE);
                            agiter_bis_2.startAnimation(fadeIn);
                            agiter_bis_2.setVisibility(View.VISIBLE);

                        } else if (Singleton.getInstance().getString() == "en") {
//                    shake.startAnimation(fadeIn);
//                    shake.setVisibility(View.VISIBLE);

                            shake_bis_1.startAnimation(fadeIn);
                            shake_bis_1.setVisibility(View.VISIBLE);
                            shake_bis_2.startAnimation(fadeIn);
                            shake_bis_2.setVisibility(View.VISIBLE);
                        }

                    }
                }
            }
        });

        letterI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (letter_I_clicked == true) {

                    // button has already been clicked once
                    if(Singleton.getInstance().getString() == "fr"){
                        Intent eclairer_intent = new Intent(MainActivity.this, IntroEclairer.class);
                        startActivity(eclairer_intent);
                    }else if(Singleton.getInstance().getString() == "en"){
                        Intent oublier_intent = new Intent(MainActivity.this, Oublier.class);
                        startActivity(oublier_intent);
                    }


                } else {

                    if ((!letter_A_clicked) && (!letter_G_clicked) && (!letter_R_clicked)) {


                        generateParticules(letterI);


                        letter_I_clicked = true;
                        letterG.clearAnimation();
                        letterA.clearAnimation();
                        letterR.clearAnimation();
                        letter_I();
                        letterTouch = "I";

                        letterG.startAnimation(fadeOut);
                        letterA.startAnimation(fadeOut);
                        letterR.startAnimation(fadeOut);

                        letterG.setVisibility(View.INVISIBLE);
                        letterA.setVisibility(View.INVISIBLE);
                        letterR.setVisibility(View.INVISIBLE);
                        revert_button.setVisibility(View.VISIBLE);

                        letterI.startAnimation(blink);

                        if (Singleton.getInstance().getString() == "fr") {
//                        eclairer.startAnimation(fadeIn);
//                        eclairer.setVisibility(View.VISIBLE);

                            eclairer_bis_1.startAnimation(fadeIn);
                            eclairer_bis_1.setVisibility(View.VISIBLE);

                            eclairer_bis_2.startAnimation(fadeIn);
                            eclairer_bis_2.setVisibility(View.VISIBLE);

                        } else if (Singleton.getInstance().getString() == "en") {
//                        erase.startAnimation(fadeIn);
//                        erase.setVisibility(View.VISIBLE);

                            erase_bis_1.startAnimation(fadeIn);
                            erase_bis_1.setVisibility(View.VISIBLE);
                            erase_bis_2.startAnimation(fadeIn);
                            erase_bis_2.setVisibility(View.VISIBLE);
                        }

                        //replace(200,0,0.5f,0.5f,letterTouch);
                    }
                }
            }
        });

        letterR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (letter_R_clicked == true) {

                    if(Singleton.getInstance().getString() == "fr"){
                        Intent oublier_intent = new Intent(MainActivity.this, Oublier.class);
                        startActivity(oublier_intent);
                    }else if(Singleton.getInstance().getString() == "en"){
                        Intent eclairer_intent = new Intent(MainActivity.this, IntroEclairer.class);
                        startActivity(eclairer_intent);
                    }

                } else {

                    if ((!letter_A_clicked) && (!letter_I_clicked) && (!letter_G_clicked)) {

                        generateParticules(letterR);

                        letter_R_clicked = true;
                        letterG.clearAnimation();
                        letterA.clearAnimation();
                        letterI.clearAnimation();
                        letter_R();
                        letterTouch = "R";

                        letterR.startAnimation(blink);

                        if (Singleton.getInstance().getString() == "fr") {
//                        oublier.startAnimation(fadeIn);
//                        oublier.setVisibility(View.VISIBLE);

                            effacer_bis.startAnimation(fadeIn);
                            effacer_bis.setVisibility(View.VISIBLE);

                        } else if (Singleton.getInstance().getString() == "en") {
//                        lighten.startAnimation(fadeIn);
//                        lighten.setVisibility(View.VISIBLE);

                            lighten_bis_1.startAnimation(fadeIn);
                            lighten_bis_1.setVisibility(View.VISIBLE);
                            lighten_bis_2.startAnimation(fadeIn);
                            lighten_bis_2.setVisibility(View.VISIBLE);
                        }

                        //replace(200,0,0.5f,0.5f,letterTouch);
                    }
                }
            }
        });


        // anims

//        anim_letters = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.letter_anim);
//        //anim_letters.setDuration(Animation.INFINITE);
//        letterA.setAnimation(anim_letters);
//        anim_letters.setRepeatCount(Animation.INFINITE);

        myanimSet = new AnimationSet(true);
        anim_letters = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.letter_anim);
        slide = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
        myanimSet.addAnimation(anim_letters);
        myanimSet.addAnimation(slide);


        //anim_letters.setRepeatCount(Animation.INFINITE);



        /////////////////////////////////////////
        // ORGANISATION DE LA VIEW
        // //////////////////////////////////////





        //////////////////////////////////////////:::
    }


    public void setLocale(String lang) {
        //languageToLoad = lang;
        Locale locale = new Locale(lang);
        //Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        Intent refresh = new Intent(this, MainActivity.class);
        startActivity(refresh);
        MainActivity.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);
        finish();
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

    @Override
    public void onAnimationStart(Animation animation) {
        if(animation ==  fadeIn){
        }
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == myanimSet) {

        }

        if(animation ==  fadeIn){

        }

        if(animation == fadeOut) {
            System.out.println("Je suis dans fade out");
            if(letterTouch == "A") {


//                letterA.startAnimation(fadeOutOther);
//                letterA.setVisibility(View.INVISIBLE);

                //letterA.clearAnimation();

                letterG.clearAnimation();
                letterI.clearAnimation();
                letterR.clearAnimation();

            }

        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


    public class OnSwipeTouchListener implements View.OnTouchListener {

        private final GestureDetector gestureDetector;

        public OnSwipeTouchListener(Context context) {
            gestureDetector = new GestureDetector(context, new GestureListener());
        }

        public void onSwipeLeft() {
        }

        public void onSwipeRight() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return gestureDetector.onTouchEvent(event);
        }

        private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

            private static final int SWIPE_DISTANCE_THRESHOLD = 100;
            private static final int SWIPE_VELOCITY_THRESHOLD = 100;

            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                float distanceX = e2.getX() - e1.getX();
                float distanceY = e2.getY() - e1.getY();
                if (Math.abs(distanceX) > Math.abs(distanceY) && Math.abs(distanceX) > SWIPE_DISTANCE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (distanceX > 0)
                        onSwipeRight();
                    else
                        onSwipeLeft();
                    return true;
                }
                return false;
            }
        }
    }

    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();


        // remove particles
        for (ParticleSystem p : particleList) {
            p.cancel();
            p = null;
        }



        touchview.setOnTouchListener(new View.OnTouchListener() {

            private boolean isInside = false;

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int x = (int) event.getX();
                int y = (int) event.getY();


//                if (!isPointWithin(x, y, letterA.getLeft(), letterA.getRight(), letterA.getTop(), letterA.getBottom())) {
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                }
//                if (!isPointWithin(x, y, letterG.getLeft(), letterG.getRight(), letterG.getTop(), letterG.getBottom())) {
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                }
//                if (!isPointWithin(x, y, letterI.getLeft(), letterI.getRight(), letterI.getTop(), letterI.getBottom())) {
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                }
//                if (!isPointWithin(x, y, letterR.getLeft(), letterR.getRight(), letterR.getTop(), letterR.getBottom())) {
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                }

                if (isPointWithin(x, y, letterA.getLeft(), letterA.getRight(), letterA.getTop(), letterA.getBottom())) {
                    System.out.println("coucou tu es sur le A");

                    Random rnd = new Random();
                    //int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    int color = Color.argb(rnd.nextInt(255), 255, 255, 255);


                    //letterA.setTextColor(  ContextCompat.getColor(getBaseContext(), R.color.letter01));
                    letterA.setTextColor(color);


//                    float val = letterA.getTextSize() + 10;
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);
//
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                }
                if (isPointWithin(x, y, letterG.getLeft(), letterG.getRight(), letterG.getTop(), letterG.getBottom())) {

                    Random rnd = new Random();
                    //int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    int color = Color.argb(rnd.nextInt(255), 255, 255, 255);

                    letterG.setTextColor(color);

//                    float val = letterG.getTextSize() + 10;
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);
//
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);



                }
                if (isPointWithin(x, y, letterI.getLeft(), letterI.getRight(), letterI.getTop(), letterI.getBottom())) {
                    Random rnd = new Random();
                    //int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
                    int color = Color.argb(rnd.nextInt(255), 255, 255, 255);

                    letterI.setTextColor(color);


//                    float val = letterI.getTextSize() + 10;
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);
//
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);



                }
                if (isPointWithin(x, y, letterR.getLeft(), letterR.getRight(), letterR.getTop(), letterR.getBottom())) {
                    Random rnd = new Random();
                    int color = Color.argb(rnd.nextInt(255), 255, 255, 255);
                    letterR.setTextColor(color);
//
//                    float val = letterR.getTextSize() + 10;
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);
//
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

                }

                return true;
            }

        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);


        System.out.println("letterA size is "+letterA.getWidth());


        int lettersWidth = letterA.getWidth()+letterG.getWidth()+letterI.getWidth()+letterR.getWidth();
        int layoutWidth = touchview.getWidth();
        int shift = (layoutWidth - lettersWidth)/2;

        RelativeLayout.LayoutParams head_params = (RelativeLayout.LayoutParams)letterA.getLayoutParams();
        head_params.setMargins(shift, 0, 0, 0); //substitute parameters for left, top, right, bottom
        letterA.setLayoutParams(head_params);

    }

    static boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }


    public void letter_A(){

       // letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

        letterA.setVisibility(View.VISIBLE);

//        letterG.startAnimation(fadeOut);
//        letterI.startAnimation(fadeOut);
//        letterR.startAnimation(fadeOut);
//
//
//        letterG.setVisibility(View.INVISIBLE);
//        letterI.setVisibility(View.INVISIBLE);
//        letterR.setVisibility(View.INVISIBLE);
//        revert_button.setVisibility(View.VISIBLE);

    }

    public void letter_G(){

//        letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//
//        letterA.setVisibility(View.INVISIBLE);
//        letterG.setVisibility(View.VISIBLE);
//        letterI.setVisibility(View.INVISIBLE);
//        letterR.setVisibility(View.INVISIBLE);
//        revert_button.setVisibility(View.VISIBLE);
    }

    public void letter_I(){

//        letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//
//        letterA.setVisibility(View.INVISIBLE);
//        letterG.setVisibility(View.INVISIBLE);
//        letterI.setVisibility(View.VISIBLE);
//        letterR.setVisibility(View.INVISIBLE);
//        revert_button.setVisibility(View.VISIBLE);
    }

    public void letter_R(){

        //letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

        letterA.setVisibility(View.INVISIBLE);
        letterG.setVisibility(View.INVISIBLE);
        letterI.setVisibility(View.INVISIBLE);
        letterR.setVisibility(View.VISIBLE);
        revert_button.setVisibility(View.VISIBLE);
    }

    public void generateParticules(TextView letter_origin){

        System.out.println(letter_origin.getText());

        if( letter_origin.getText().toString().equals("I")  ) {
            System.out.println("dans le IIII");
            parti1 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrei, 10000);
            parti2 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrei, 7000);
            parti3 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrei, 5000);
        }

        if( letter_origin.getText().toString().equals("A")) {
            parti1 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrea, 10000);
            parti2 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrea, 7000);
            parti3 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrea, 5000);
        }else

        if( letter_origin.getText().toString().equals("G")) {
            parti1 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettreg, 10000);
            parti2 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettreg, 7000);
            parti3 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettreg, 5000);
        }else


        if( letter_origin.getText().toString().equals("R")) {
            parti1 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrer, 10000);
            parti2 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrer, 7000);
            parti3 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettrer, 5000);
        }else
        if( letter_origin.getText().toString().equals("D")) {
            parti1 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettred, 10000);
            parti2 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettred, 7000);
            parti3 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettred, 5000);
        }else
        if( letter_origin.getText().toString().equals("E")) {
            parti1 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettree, 10000);
            parti2 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettree, 7000);
            parti3 = new ParticleSystem(MainActivity.this, 1, R.drawable.lettree, 5000);
        }


        parti3.setSpeedRange(0.001f, 0.1f);
        parti3.setFadeOut(5000, new AccelerateInterpolator());
        parti3.emit(letter_origin, 1);

        parti2.setSpeedRange(0.001f, 0.1f);
        parti2.setFadeOut(7000, new AccelerateInterpolator());
        parti2.emit(letter_origin, 1);

        parti1.setSpeedRange(0.001f, 0.1f);
        parti1.setFadeOut(10000, new AccelerateInterpolator());
        parti1.emit(letter_origin, 1);


        particleList.add(parti1);
        particleList.add(parti2);
        particleList.add(parti3);

    }

    public void replace(float xTo, float yTo, float xScale, float yScale,String letter) {

        if(letter.equals("A")) {


            System.out.println("letterA");
            // create set of animations
            replaceAnimation = new AnimationSet(false);
            // animations should be applied on the finish line
            replaceAnimation.setFillAfter(true);

            // create scale animation
            ScaleAnimation scale = new ScaleAnimation(1.0f, xScale, 1.0f, yScale);
            scale.setDuration(1000);

            // create translation animation
//            TranslateAnimation trans = new TranslateAnimation((int) letterA.getX(), (int) letterA.getY(),
//                    Animation.ABSOLUTE, xTo - touchview.getLeft(), 0, 0,
//                    Animation.ABSOLUTE, yTo - touchview.getTop());
//            trans.setDuration(1000);
float test = (float) ((float)  yTo*0.6);
            TranslateAnimation trans = new TranslateAnimation(
                    0,letterA.getX(),
                    Animation.ABSOLUTE, xTo,
                    0,  letterA.getY(),
                    Animation.ABSOLUTE, test );
            trans.setDuration(1000);

//            TranslateAnimation trans = new TranslateAnimation(
//                    Animation.ABSOLUTE,  letterA.getX(),
//                    Animation.ABSOLUTE, xTo,
//                    Animation.ABSOLUTE,  letterA.getY(),
//                    Animation.ABSOLUTE,  yTo);
//            trans.setDuration(1000);

//TranslateAnimation(int fromXType, float fromXValue, int toXType, float toXValue, int fromYType, float fromYValue, int toYType, float toYValue)


            // add new animations to the set
            replaceAnimation.addAnimation(scale);
            replaceAnimation.addAnimation(trans);

            // start our animation

            letterA.startAnimation(replaceAnimation);

        }else if(letter.equals("G")) {
// create set of animations
            System.out.println("letterG");

            replaceAnimation = new AnimationSet(false);
            // animations should be applied on the finish line
            replaceAnimation.setFillAfter(true);

            // create scale animation
            ScaleAnimation scale = new ScaleAnimation(1.0f, xScale, 1.0f, yScale);
            scale.setDuration(1000);

            // create translation animation
            TranslateAnimation trans = new TranslateAnimation((int) letterG.getX(), (int) letterG.getY(),
                    TranslateAnimation.ABSOLUTE, xTo - touchview.getLeft(), 0, 0,
                    TranslateAnimation.ABSOLUTE, yTo);
            trans.setDuration(1000);

            // add new animations to the set
            replaceAnimation.addAnimation(scale);
            replaceAnimation.addAnimation(trans);

            // start our animation

            letterG.startAnimation(replaceAnimation);

        }else if(letter.equals("I")) {
// create set of animations

            System.out.println("letterI");

            replaceAnimation = new AnimationSet(false);
            // animations should be applied on the finish line
            replaceAnimation.setFillAfter(true);

            // create scale animation
            ScaleAnimation scale = new ScaleAnimation(1.0f, xScale, 1.0f, yScale);
            scale.setDuration(1000);

            // create translation animation
            TranslateAnimation trans = new TranslateAnimation((int) letterI.getX(), (int) letterI.getY(),
                    TranslateAnimation.ABSOLUTE, xTo - touchview.getLeft(), 0, 0,
                    TranslateAnimation.ABSOLUTE, yTo);
            trans.setDuration(1000);

            // add new animations to the set
            replaceAnimation.addAnimation(scale);
            replaceAnimation.addAnimation(trans);

            // start our animation

            letterI.startAnimation(replaceAnimation);

        }else  if(letter.equals("R")) {
// create set of animations

            System.out.println("letterR");

            replaceAnimation = new AnimationSet(false);
            // animations should be applied on the finish line
            replaceAnimation.setFillAfter(true);

            // create scale animation
            ScaleAnimation scale = new ScaleAnimation(1.0f, xScale, 1.0f, yScale);
            scale.setDuration(1000);

            // create translation animation
            TranslateAnimation trans = new TranslateAnimation((int) letterR.getX(), (int) letterR.getY(),
                    TranslateAnimation.ABSOLUTE, xTo - touchview.getLeft(), 0, 0,
                    TranslateAnimation.ABSOLUTE, yTo);
            trans.setDuration(1000);

            // add new animations to the set
            replaceAnimation.addAnimation(scale);
            replaceAnimation.addAnimation(trans);

            // start our animation

            letterR.startAnimation(replaceAnimation);

        }


    }






}
