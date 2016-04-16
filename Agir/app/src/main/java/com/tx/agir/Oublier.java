package com.tx.agir;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Rebecca on 13/04/2016.
 */
public class Oublier extends AppCompatActivity implements Animation.AnimationListener {

    private Animation animation_firstText  = null;
    private  Animation animation_last_text = null;
    private Animation fade_in = null;

    private TextView wellcomeSentence = null;
    private EditText toForget = null;
    private Button bouton_effacer = null;
    private TextView Word_toForget = null;

    RelativeLayout touchview; // my view
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oublier_layout);

       // Get information VIEW
        touchview = (RelativeLayout) findViewById(R.id.relativeLayout);



        animation_firstText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.first_text_appear);
        animation_last_text = AnimationUtils.loadAnimation(this, R.anim.last_text_appear);
        fade_in =  AnimationUtils.loadAnimation(this, R.anim.fade_in);

        wellcomeSentence = (TextView)findViewById(R.id.oublier_sentence);
        wellcomeSentence.setAnimation(animation_firstText);


        toForget = (EditText)findViewById(R.id.to_forget);
        toForget.setVisibility(View.INVISIBLE);




        Word_toForget = (TextView)findViewById(R.id.word_toForget);
        Word_toForget.setVisibility(View.INVISIBLE);




 /*       touchview.setOnTouchListener(new OnSwipeTouchListener(getBaseContext()) {
            @Override
            public void onSwipeLeft() {
                Toast.makeText(getApplicationContext(), "Word touched",
                        Toast.LENGTH_SHORT).show();
            }
        });*/







        bouton_effacer = (Button) findViewById(R.id.button_effacer);
        bouton_effacer.setVisibility(View.INVISIBLE);
        bouton_effacer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toForget.setVisibility(View.INVISIBLE);
                wellcomeSentence.setVisibility(View.INVISIBLE);
                bouton_effacer.setVisibility(View.INVISIBLE);
                Word_toForget.setText(toForget.getText());
                Word_toForget.setVisibility(View.VISIBLE);

            }
        });



    // set animation listener
        animation_firstText.setAnimationListener(this);
        animation_last_text.setAnimationListener(this);
    }




    // animation listeners
    @Override
    public void onAnimationEnd(Animation animation) {
        // Take any action after completing the animation
        // check for fade in animation
        if (animation == animation_firstText) {
/*            Toast.makeText(getApplicationContext(), "Animation Stopped",
                    Toast.LENGTH_SHORT).show();*/
            wellcomeSentence.setVisibility(View.INVISIBLE);
            wellcomeSentence.setText(R.string.oublier_phrase02);
            wellcomeSentence.startAnimation(animation_last_text);
        }

        if (animation == animation_last_text) {
            //wellcomeSentence.setVisibility(View.INVISIBLE);
            toForget.setVisibility(View.VISIBLE);
            toForget.startAnimation(fade_in);

            bouton_effacer.setVisibility(View.VISIBLE);
            bouton_effacer.startAnimation(fade_in);

        }




    }

    @Override
    public void onAnimationRepeat(Animation animation) {
        // Animation is repeating
    }

    @Override
    public void onAnimationStart(Animation animation) {
        // Animation started

        if (animation == animation_last_text) {
            wellcomeSentence.setVisibility(View.VISIBLE);
        }
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

        private final class GestureListener extends SimpleOnGestureListener {

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


                        if (!isPointWithin(x, y, Word_toForget.getLeft(), Word_toForget.getRight(), Word_toForget.getTop(),
                                Word_toForget.getBottom())) {
                            //b.getBackground().setState(defaultStates);

                        }

                        if (isPointWithin(x, y, Word_toForget.getLeft(),Word_toForget.getRight(), Word_toForget.getTop(),
                                Word_toForget.getBottom())) {
                            //b.getBackground().setState(STATE_PRESSED);
                            Toast.makeText(getApplicationContext(), "word is being touched",
                                    Toast.LENGTH_SHORT).show();

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





}
