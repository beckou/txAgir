package com.tx.agir;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Rebecca on 13/04/2016.
 */
public class Oublier extends AppCompatActivity implements Animation.AnimationListener {

    private Animation animation_firstText  = null;
    private  Animation animation_last_text = null;
    private Animation fade_in = null;
    private Animation fade_out = null;
    private Animation fade_out_bis = null;
    private Animation final_fade = null;
    private Animation zoom = null;

    private TextView wellcomeSentence = null;
    private EditText toForget = null;
    private Button bouton_effacer = null;
    private TextView Word_toForget = null;

    RelativeLayout touchview; // my view

    private TextView oublier_3 = null;

    public static Timer timer;
    public static Timer timer2;

    private ImageView img;

    private static final String IMAGEVIEW_TAG = "icon bitmap";

    private boolean isBeeingPressed = false;
    String msg;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    private int mLayoutWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oublier_layout);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoiretOne-Regular.ttf");


        // Get information VIEW
        touchview = (RelativeLayout) findViewById(R.id.relativeLayout);

        myTimer(123, "127.0.0.1");

        img = (ImageView)  findViewById(R.id.gomme);
        img.setVisibility(View.INVISIBLE);
        img.setTag(IMAGEVIEW_TAG);

        setupDragDrop();

        animation_firstText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.first_text_appear);
        animation_last_text = AnimationUtils.loadAnimation(this, R.anim.last_text_appear);
        fade_in =  AnimationUtils.loadAnimation(this, R.anim.fade_in);
        fade_out = AnimationUtils.loadAnimation(this, R.anim.fade_out_oublier);
        fade_out_bis = AnimationUtils.loadAnimation(this, R.anim.fade_out_other);
        final_fade =  AnimationUtils.loadAnimation(this, R.anim.final_fade_in);
        zoom =   AnimationUtils.loadAnimation(this, R.anim.zoom);

        wellcomeSentence = (TextView)findViewById(R.id.oublier_sentence);
        wellcomeSentence.setAnimation(animation_firstText);
        wellcomeSentence.setTypeface(myCustomFont);


        toForget = (EditText)findViewById(R.id.to_forget);
        toForget.setVisibility(View.INVISIBLE);
//        toForget.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });

        toForget.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                bouton_effacer.setVisibility(View.VISIBLE);
                bouton_effacer.startAnimation(fade_in);
                return false;
            }
        });



        Word_toForget = (TextView)findViewById(R.id.word_toForget);
        Word_toForget.setVisibility(View.INVISIBLE);

        oublier_3 = (TextView)findViewById(R.id.oublier_3);
        if(oublier_3 != null){
            oublier_3.setVisibility(View.INVISIBLE);
            oublier_3.setTypeface(myCustomFont);

        }





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

                img.setVisibility(View.VISIBLE);
                img.startAnimation(zoom);
                zoom.setRepeatCount(Animation.INFINITE);

                //update();

                //Word_toForget.setAnimation(fade_out_bis);
            }
        });



    // set animation listener
        animation_firstText.setAnimationListener(this);
        animation_last_text.setAnimationListener(this);
        //fade_out_bis.setAnimationListener(this);

    }




    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);


        mLayoutWidth = touchview.getWidth();


    }


    public void myTimer(final long MAC, final String ipAddress) {
        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                System.out.println("MAC: " + MAC + "ipAddress:" + ipAddress);
                update();
                update2();

            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 1000);

    }
    public void update() {
        final TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

//stuff that updates ui
          disappear() ;

                    }
                });

            }

        };
        timer.cancel();
        timer = new Timer();
        timer.schedule(timerTask, 5000);
        System.out.println("Time:    "+timerTask.scheduledExecutionTime());
    }
    public void update2() {
        final TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //lastText(); ;
                    }
                });

            }

        };
        timer.cancel();
        timer = new Timer();
        timer.schedule(timerTask, 10000);
        System.out.println("Time:    "+timerTask.scheduledExecutionTime());
    }


    public void lastText(){
        oublier_3.setVisibility(View.VISIBLE);

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
        }else if (animation == animation_last_text) {
            //wellcomeSentence.setVisibility(View.INVISIBLE);
            toForget.setVisibility(View.VISIBLE);
            toForget.startAnimation(fade_in);
           // wellcomeSentence.clearAnimation();
//            bouton_effacer.setVisibility(View.VISIBLE);
//            bouton_effacer.startAnimation(fade_in);

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

    public void disappear(){

        Word_toForget.startAnimation(fade_out_bis);
        Word_toForget.setVisibility(View.INVISIBLE);
        img.startAnimation(fade_out_bis);
        img.setVisibility(View.INVISIBLE);
        //Word_toForget.clearAnimation();

        oublier_3.setVisibility(View.VISIBLE);
        oublier_3.startAnimation(final_fade);


        //wellcomeSentence.setText(R.string.oublier_phrase03);
       // wellcomeSentence.setVisibility(View.VISIBLE);
       // wellcomeSentence.startAnimation(fade_in);

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

//                System.out.println("x "+img.getX());
//                System.out.println("y "+img.getY());
//                System.out.println("left "+img.getLeft());
//                System.out.println("Word_toForget.getLeft() "+Word_toForget.getLeft());



                if (!isPointWithin((int)img.getX(), (int)img.getY(), Word_toForget.getLeft(), Word_toForget.getRight(), Word_toForget.getTop(),
                                Word_toForget.getBottom())) {
                            //b.getBackground().setState(defaultStates);
                        }

                        if (isPointWithin((int)img.getX(), (int)img.getY(), Word_toForget.getLeft(),Word_toForget.getRight(), Word_toForget.getTop(),
                                Word_toForget.getBottom())) {
                            //b.getBackground().setState(STATE_PRESSED);
                       /*     Toast.makeText(getApplicationContext(), "word is being touched",
                                    Toast.LENGTH_SHORT).show();*/

//                            float textSize = Word_toForget.getTextSize();
//                            float incr = (float) 4;
//                            textSize = textSize + incr;
//
//                            //Word_toForget.setTextSize(textSize);
//                            Word_toForget.setTextSize(TypedValue.COMPLEX_UNIT_PX,textSize);

                            //update();

                        }

                return true;
            }

        });

    }


    static boolean isPointWithin(int x, int y, int x1, int x2, int y1, int y2) {
        return (x <= x2 && x >= x1 && y <= y2 && y >= y1);
    }


    public void setupDragDrop() {



        img.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {


                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED){
                    layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
                } else if (event.getAction() ==   DragEvent.ACTION_DRAG_ENDED)
                    v.setVisibility(View.VISIBLE);






                return true;
            }
        });

        img.getRootView().setOnDragListener( new View.OnDragListener()
        {
            @Override
            public boolean onDrag ( View v, DragEvent event )
            {

                int x = (int) event.getX();
                int y = (int) event.getY();

                System.out.println("(int) event.getX() " +(int) event.getX());
                System.out.println("(int) event.getY() " +(int) event.getY());


                System.out.println("x " + img.getX());
                System.out.println("y " + img.getY());
                System.out.println("Word_toForget.getLeft() " + Word_toForget.getLeft());

                int shift = img.getWidth() + 30;
                System.out.println("shift " + shift);

                if (!isPointWithin(x,y, Word_toForget.getLeft(), Word_toForget.getRight()+shift, Word_toForget.getTop(),
                        Word_toForget.getBottom()+shift)) {
                    //b.getBackground().setState(defaultStates);
                }

                if (isPointWithin(x, y, Word_toForget.getLeft(),Word_toForget.getRight()+shift, Word_toForget.getTop(),
                        Word_toForget.getBottom()+shift)) {
                    //b.getBackground().setState(STATE_PRESSED);
                       /*     Toast.makeText(getApplicationContext(), "word is being touched",
                                    Toast.LENGTH_SHORT).show();*/


                    if(Word_toForget.getWidth() < mLayoutWidth-10  ) {
                        System.out.println("in thereee");

                        float textSize = Word_toForget.getTextSize();
                        float incr = (float) 1;
                        textSize = textSize + incr;

                        //Word_toForget.setTextSize(textSize);
                        Word_toForget.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);

                    }
                    update();
                }

                if ( event.getAction() == DragEvent.ACTION_DROP )
                {
                    float X = event.getX();
                    float Y = event.getY();

                    // Log.d(LOGCAT, "X " + (int) X + "Y " + (int) Y);
                    //  View view = (View) event.getLocalState();
                    img.setX(X-(img.getWidth()/2));
                    img.setY(Y-(img.getHeight()/2));


                    img.setVisibility(View.VISIBLE);

                }
                if(event.getAction() == DragEvent.ACTION_DRAG_EXITED){


                }


                return true;
            }
        });


        img.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN};

                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);
                    img.setVisibility(View.INVISIBLE);

                    v.startDrag( data, shadowBuilder, mimeTypes, 0);

                    isBeeingPressed = true;

                    img.clearAnimation();



                    return true;
                }else  if (event.getAction() == MotionEvent.ACTION_UP) {
                    isBeeingPressed = false;
                    return true;
                }
                else
                {
                    return false;
                }
            }
        });


    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

}
