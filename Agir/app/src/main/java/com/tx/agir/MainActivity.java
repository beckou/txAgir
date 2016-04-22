package com.tx.agir;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.plattysoft.leonids.Particle;
import com.plattysoft.leonids.ParticleSystem;
import com.plattysoft.leonids.modifiers.ParticleModifier;

public class MainActivity extends AppCompatActivity {

    private RelativeLayout touchview;
    private TextView letterA;
    private TextView letterG ;
    private TextView letterI;
    private TextView letterR;
    FloatingActionButton revert_button;

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


  /*              if (!isPointWithin(x, y, Word_toForget.getLeft(), Word_toForget.getRight(), Word_toForget.getTop(),
                        Word_toForget.getBottom())) {
                    //b.getBackground().setState(defaultStates);

                }*/

                if (isPointWithin(x, y, letterA.getLeft(), letterA.getRight(), letterA.getTop(), letterA.getBottom())) {
                    //b.getBackground().setState(STATE_PRESSED);
                            /*Toast.makeText(getApplicationContext(), "letterA",
                                    Toast.LENGTH_SHORT).show();*/
                    letterA.setVisibility(View.VISIBLE);
                    letterG.setVisibility(View.INVISIBLE);
                    letterI.setVisibility(View.INVISIBLE);
                    letterR.setVisibility(View.INVISIBLE);
                    revert_button.setVisibility(View.VISIBLE);

                }
                if (isPointWithin(x, y, letterG.getLeft(), letterG.getRight(), letterG.getTop(), letterG.getBottom())) {
                    //b.getBackground().setState(STATE_PRESSED);
                            /*Toast.makeText(getApplicationContext(), "letterG",
                                    Toast.LENGTH_SHORT).show();*/
                    letterA.setVisibility(View.INVISIBLE);
                    letterG.setVisibility(View.VISIBLE);
                    letterI.setVisibility(View.INVISIBLE);
                    letterR.setVisibility(View.INVISIBLE);
                    revert_button.setVisibility(View.VISIBLE);

                }
                if (isPointWithin(x, y, letterI.getLeft(), letterI.getRight(), letterI.getTop(), letterI.getBottom())) {
                    //b.getBackground().setState(STATE_PRESSED);
                           /* Toast.makeText(getApplicationContext(), "letterI",
                                    Toast.LENGTH_SHORT).show();*/
                    letterA.setVisibility(View.INVISIBLE);
                    letterG.setVisibility(View.INVISIBLE);
                    letterI.setVisibility(View.VISIBLE);
                    letterR.setVisibility(View.INVISIBLE);
                    revert_button.setVisibility(View.VISIBLE);

                }
                if (isPointWithin(x, y, letterR.getLeft(), letterR.getRight(), letterR.getTop(), letterR.getBottom())) {
                    //b.getBackground().setState(STATE_PRESSED);
                            /*Toast.makeText(getApplicationContext(), "letterR",
                                    Toast.LENGTH_SHORT).show();*/
                    letterA.setVisibility(View.INVISIBLE);
                    letterG.setVisibility(View.INVISIBLE);
                    letterI.setVisibility(View.INVISIBLE);
                    letterR.setVisibility(View.VISIBLE);
                    revert_button.setVisibility(View.VISIBLE);

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
