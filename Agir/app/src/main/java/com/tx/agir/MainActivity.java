package com.tx.agir;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import java.util.Locale;


import com.plattysoft.leonids.ParticleSystem;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    // Switching languages

    private String languageToLoad = "fr";

    private Spinner spinnerctrl;
    private Button btn;
    private Locale myLocale;


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


    private TextView adapter_A;
    private TextView adapter_D;
    private TextView adapter_Abis;
    private TextView adapter_P;
    private TextView adapter_T;
    private TextView adapter_E;
    private TextView adapter_R;

    private TextView oublier;
    private TextView adapter;
    private TextView agiter;
    private TextView eclairer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // setLocale("fr");

        //languageToLoad  = "fr"; // your language

        System.out.println("coucou");


        setContentView(R.layout.activity_main);

        // Language
        //---------------

        spinnerctrl = (Spinner) findViewById(R.id.spinner1);

    // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter_spin = ArrayAdapter.createFromResource(this,
                R.array.language_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        adapter_spin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
        spinnerctrl.setAdapter(adapter_spin);




        spinnerctrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                if(pos == 0){


                }else if (pos == 1) {
                    Toast.makeText(parent.getContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");

                } else if (pos == 2) {
                    Toast.makeText(parent.getContext(),
                            "You have selected French", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("fr");

                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });


          fr_button = (Button) findViewById(R.id.FR_button);
          en_button = (Button)findViewById(R.id.EN_button);

        if(fr_button != null){
            fr_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getBaseContext(),
                            "You have selected French", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("fr");
                }
            });

        }
        if(en_button != null){

            en_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getBaseContext(),
                            "You have selected English", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");
                }
            });
        }


        ///////////////:

        letter_A_clicked = false;
        letter_I_clicked = false;
        letter_G_clicked = false;
        letter_R_clicked = false;


        adapter_A = (TextView) findViewById(R.id.adapter_A);
        adapter_D=(TextView) findViewById(R.id.adapter_D);
        adapter_Abis=(TextView) findViewById(R.id.adapter_Abis);
        adapter_P=(TextView) findViewById(R.id.adapter_P);
        adapter_T=(TextView) findViewById(R.id.adapter_T);
        adapter_E=(TextView) findViewById(R.id.adapter_E);
        adapter_R=(TextView) findViewById(R.id.adapter_R);

        adapter_A.setVisibility(View.INVISIBLE);
        adapter_D.setVisibility(View.INVISIBLE);
        adapter_Abis.setVisibility(View.INVISIBLE);
        adapter_P.setVisibility(View.INVISIBLE);
        adapter_T.setVisibility(View.INVISIBLE);
        adapter_E.setVisibility(View.INVISIBLE);
        adapter_R.setVisibility(View.INVISIBLE);


        //linLayout = (LinearLayout) findViewById(R.id.linLayout);
        touchview= (RelativeLayout) findViewById(R.id.test);
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

                adapter_A.setVisibility(View.INVISIBLE);
                adapter_D.setVisibility(View.INVISIBLE);
                adapter_Abis.setVisibility(View.INVISIBLE);
                adapter_P.setVisibility(View.INVISIBLE);
                adapter_T.setVisibility(View.INVISIBLE);
                adapter_E.setVisibility(View.INVISIBLE);
                adapter_R.setVisibility(View.INVISIBLE);


                oublier.setVisibility(View.INVISIBLE);
                adapter.setVisibility(View.INVISIBLE);
                agiter.setVisibility(View.INVISIBLE);
                eclairer.setVisibility(View.INVISIBLE);

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


            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if(fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }


        Button button_eclairer = (Button) findViewById(R.id.eclairer_button);
        if(button_eclairer != null) {
            button_eclairer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(MainActivity.this, Eclairer.class);
                    startActivity(intent);
                }
            });
        }

        Button button_oublier = (Button) findViewById(R.id.oublier_button);
        if(button_oublier != null) {
            button_oublier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent2 = new Intent(MainActivity.this, Oublier.class);
                    startActivity(intent2);
                }
            });
        }

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/HARRINGT.TTF");

        letterA = (TextView) findViewById(R.id.A);
        letterG = (TextView) findViewById(R.id.G);
        letterI = (TextView) findViewById(R.id.I);
        letterR = (TextView) findViewById(R.id.R);

//        letterA.setTypeface(myCustomFont);
//        letterG.setTypeface(myCustomFont);
//        letterI.setTypeface(myCustomFont);
//        letterR.setTypeface(myCustomFont);

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
//                    Intent adapter_intent = new Intent(MainActivity.this, Agiter.class);
//                    startActivity(adapter_intent);
                } else {


                    letter_A_clicked = true;

                    letterA.clearAnimation();

                    letterG.clearAnimation();
                    letterI.clearAnimation();
                    letterR.clearAnimation();
                    letter_A();
                    letterTouch = "A";

                    float si = adapter_A.getTextSize();
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

                    adapter.startAnimation(fadeIn);
                    adapter.setVisibility(View.VISIBLE);


                }
            }



        });

        letterG.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

                agiter.startAnimation(fadeIn);
                agiter.setVisibility(View.VISIBLE);

            }
        });

        letterI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (letter_I_clicked == true) {

                    // button has already been clicked once.
                    Intent oublier_intent = new Intent(MainActivity.this, Oublier.class);
                    startActivity(oublier_intent);



                } else {


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


                    oublier.startAnimation(fadeIn);
                    oublier.setVisibility(View.VISIBLE);


                    //replace(200,0,0.5f,0.5f,letterTouch);
                }
            }
        });

        letterR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (letter_R_clicked == true) {

                    Intent eclairer_intent = new Intent(MainActivity.this, Eclairer.class);
                    startActivity(eclairer_intent);

                } else {


                    letter_R_clicked = true;
                    letterG.clearAnimation();
                    letterA.clearAnimation();
                    letterI.clearAnimation();
                    letter_R();
                    letterTouch = "R";

                    letterR.startAnimation(blink);


                    eclairer.startAnimation(fadeIn);
                    eclairer.setVisibility(View.VISIBLE);


                    //replace(200,0,0.5f,0.5f,letterTouch);
                }
            }
        });



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
        finish();
        startActivity(refresh);
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
            adapter_D.setVisibility(View.VISIBLE);

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

//        touchview.setOnTouchListener(new View.OnTouchListener() {
//
//            private boolean isInside = false;
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//
//                int x = (int) event.getX();
//                int y = (int) event.getY();
//
//
////                if (!isPointWithin(x, y, letterA.getLeft(), letterA.getRight(), letterA.getTop(), letterA.getBottom())) {
////                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
////                }
////                if (!isPointWithin(x, y, letterG.getLeft(), letterG.getRight(), letterG.getTop(), letterG.getBottom())) {
////                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
////                }
////                if (!isPointWithin(x, y, letterI.getLeft(), letterI.getRight(), letterI.getTop(), letterI.getBottom())) {
////                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
////                }
////                if (!isPointWithin(x, y, letterR.getLeft(), letterR.getRight(), letterR.getTop(), letterR.getBottom())) {
////                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
////                }
//
//                if (isPointWithin(x, y, letterA.getLeft(), letterA.getRight(), letterA.getTop(), letterA.getBottom())) {
//                    //b.getBackground().setState(STATE_PRESSED);
//                            /*Toast.makeText(getApplicationContext(), "letterA",
//                                    Toast.LENGTH_SHORT).show();*/
//
//                   // letter_A();
//
//                    float val = letterA.getTextSize() + 10;
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);
//
//
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                }
//                if (isPointWithin(x, y, letterG.getLeft(), letterG.getRight(), letterG.getTop(), letterG.getBottom())) {
//                    //b.getBackground().setState(STATE_PRESSED);
//                            /*Toast.makeText(getApplicationContext(), "letterG",
//                                    Toast.LENGTH_SHORT).show();*/
//
//
//                    float val = letterG.getTextSize() + 10;
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);
//
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//
//
//
//                }
//                if (isPointWithin(x, y, letterI.getLeft(), letterI.getRight(), letterI.getTop(), letterI.getBottom())) {
//                    //b.getBackground().setState(STATE_PRESSED);
//                           /* Toast.makeText(getApplicationContext(), "letterI",
//                                    Toast.LENGTH_SHORT).show();*/
//
//
//                    float val = letterI.getTextSize() + 10;
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);
//
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//
//                }
//                if (isPointWithin(x, y, letterR.getLeft(), letterR.getRight(), letterR.getTop(), letterR.getBottom())) {
//                    //b.getBackground().setState(STATE_PRESSED);
//                            /*Toast.makeText(getApplicationContext(), "letterR",
//                                    Toast.LENGTH_SHORT).show();*/
//
//
//                    float val = letterR.getTextSize() + 10;
//                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);
//
//                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
//
//                }
//
//                return true;
//            }
//
//        });

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);
    }

    static boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }


    public void letter_A(){

        letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

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

        letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

        letterA.setVisibility(View.INVISIBLE);
        letterG.setVisibility(View.INVISIBLE);
        letterI.setVisibility(View.INVISIBLE);
        letterR.setVisibility(View.VISIBLE);
        revert_button.setVisibility(View.VISIBLE);
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
