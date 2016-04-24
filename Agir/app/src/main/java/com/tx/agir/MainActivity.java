package com.tx.agir;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ParticleModifier;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener{

    private RelativeLayout touchview;
    private TextView letterA;
    private TextView letterG ;
    private TextView letterI;
    private TextView letterR;
    FloatingActionButton revert_button;

    private String letterTouch = "";

    private AnimationSet replaceAnimation = null;
    private Animation TranslateAnimation = null;

    private Boolean letter_A_clicked;

    private AnimationSet myanimSet = null;
    private Animation anim_letters  = null;
    private Animation slide = null;


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






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        touchview= (RelativeLayout) findViewById(R.id.relativeLayout);
        revert_button = (FloatingActionButton)  findViewById(R.id.revert);
        revert_button.setVisibility(View.INVISIBLE);
        revert_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterA.setVisibility(View.VISIBLE);
                letterG.setVisibility(View.VISIBLE);
                letterI.setVisibility(View.VISIBLE);
                letterR.setVisibility(View.VISIBLE);
                revert_button.setVisibility(View.INVISIBLE);

//                replace((int)firstPosX_A,(int)firstPosY_A,firstX_A,firstY_A,letterA);
//                replace((int)firstPosX_G,(int)firstPosY_G,firstX_G,firstY_G,letterG);
//                replace((int)firstPosX_I,(int)firstPosY_I,firstX_I,firstY_I,letterI);
//                replace((int)firstPosX_R,(int)firstPosY_R,firstX_R,firstY_R,letterR);

                if (letterTouch.equals("A")) {
                    replace((int) firstPosX_A, (int) firstPosY_A, firstX_A, firstY_A, letterTouch);
                    letterA.clearAnimation();
                }
                if (letterTouch.equals("G")) {
                    replace((int) firstPosX_G, (int) firstPosY_G, firstX_G, firstY_G, letterTouch);
                    letterG.clearAnimation();
                }
                if (letterTouch.equals("I")) {
                    replace((int) firstPosX_I, (int) firstPosY_I, firstX_I, firstY_I, letterTouch);
                    letterI.clearAnimation();
                }
                if (letterTouch.equals("R")) {
                    replace((int) firstPosX_R, (int) firstPosY_R, firstX_R, firstY_R, letterTouch);
                    letterR.clearAnimation();
                }


            }
        });

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

        Button button_oublier = (Button) findViewById(R.id.oublier_button);
        button_oublier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(MainActivity.this, Oublier.class);
                startActivity(intent2);
            }
        });

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/HARRINGT.TTF");

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


        letterA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterG.clearAnimation();
                letterI.clearAnimation();
                letterR.clearAnimation();
                letter_A();
                letterTouch = "A";
                replace(200,0,0.5f,0.5f,letterTouch);
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
                replace(200,0,0.5f,0.5f,letterTouch);
            }
        });

        letterI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterG.clearAnimation();
                letterA.clearAnimation();
                letterR.clearAnimation();
                letter_I();
                letterTouch = "I";
                replace(200,0,0.5f,0.5f,letterTouch);
            }
        });

        letterR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                letterG.clearAnimation();
                letterA.clearAnimation();
                letterI.clearAnimation();
                letter_R();
                letterTouch = "R";
                replace(200,0,0.5f,0.5f,letterTouch);
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

    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (animation == myanimSet) {

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
                    //b.getBackground().setState(STATE_PRESSED);
                            /*Toast.makeText(getApplicationContext(), "letterA",
                                    Toast.LENGTH_SHORT).show();*/

                   // letter_A();

                    float val = letterA.getTextSize() + 10;
                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);


                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                }
                if (isPointWithin(x, y, letterG.getLeft(), letterG.getRight(), letterG.getTop(), letterG.getBottom())) {
                    //b.getBackground().setState(STATE_PRESSED);
                            /*Toast.makeText(getApplicationContext(), "letterG",
                                    Toast.LENGTH_SHORT).show();*/


                    float val = letterG.getTextSize() + 10;
                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);

                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);



                }
                if (isPointWithin(x, y, letterI.getLeft(), letterI.getRight(), letterI.getTop(), letterI.getBottom())) {
                    //b.getBackground().setState(STATE_PRESSED);
                           /* Toast.makeText(getApplicationContext(), "letterI",
                                    Toast.LENGTH_SHORT).show();*/


                    float val = letterI.getTextSize() + 10;
                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);

                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

                }
                if (isPointWithin(x, y, letterR.getLeft(), letterR.getRight(), letterR.getTop(), letterR.getBottom())) {
                    //b.getBackground().setState(STATE_PRESSED);
                            /*Toast.makeText(getApplicationContext(), "letterR",
                                    Toast.LENGTH_SHORT).show();*/


                    float val = letterR.getTextSize() + 10;
                    letterR.setTextSize(TypedValue.COMPLEX_UNIT_PX,val);

                    letterA.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                    letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);
                    letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

                }

                return true;
            }

        });

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
        letterG.setVisibility(View.INVISIBLE);
        letterI.setVisibility(View.INVISIBLE);
        letterR.setVisibility(View.INVISIBLE);
        revert_button.setVisibility(View.VISIBLE);

    }

    public void letter_G(){

        letterG.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

        letterA.setVisibility(View.INVISIBLE);
        letterG.setVisibility(View.VISIBLE);
        letterI.setVisibility(View.INVISIBLE);
        letterR.setVisibility(View.INVISIBLE);
        revert_button.setVisibility(View.VISIBLE);
    }

    public void letter_I(){

        letterI.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

        letterA.setVisibility(View.INVISIBLE);
        letterG.setVisibility(View.INVISIBLE);
        letterI.setVisibility(View.VISIBLE);
        letterR.setVisibility(View.INVISIBLE);
        revert_button.setVisibility(View.VISIBLE);
    }

    public void letter_R(){

        letterR.setTextSize(TypedValue.COMPLEX_UNIT_DIP,150);

        letterA.setVisibility(View.INVISIBLE);
        letterG.setVisibility(View.INVISIBLE);
        letterI.setVisibility(View.INVISIBLE);
        letterR.setVisibility(View.VISIBLE);
        revert_button.setVisibility(View.VISIBLE);
    }

    public void replace(int xTo, int yTo, float xScale, float yScale,String letter) {

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
            TranslateAnimation trans = new TranslateAnimation((int) letterA.getX(), (int) letterA.getY(),
                    TranslateAnimation.ABSOLUTE, xTo - touchview.getLeft(), 0, 0,
                    TranslateAnimation.ABSOLUTE, yTo);
            trans.setDuration(1000);

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
