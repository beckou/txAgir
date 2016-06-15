package com.tx.agir;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Rebecca on 13/04/2016.
 */
public class Adapter extends AppCompatActivity implements View.OnTouchListener {

    private static final int INVALID_POINTER_ID = 0;
    private int mActivePointerId = INVALID_POINTER_ID;

    private float mLastTouchX;
    private float mLastTouchY;

    private float mPosX;
    private float mPosY;

    private float w;
    private float h;

    FrameLayout frame;
    RelativeLayout mView;
    private TextView adapter_text = null;

    private Boolean TopLefttouched = false;
    ViewGroup.MarginLayoutParams marginParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_layout);
        Adapter.this.overridePendingTransition(R.anim.activity_fade_in, R.anim.activity_fade_out);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        frame = (FrameLayout) findViewById(R.id.frame);
        if(frame != null){
//            frame.getLayoutParams().height = 173;
//            frame.getLayoutParams().width = 351;
        }


        mView = (RelativeLayout)  findViewById(R.id.view);

        adapter_text = (TextView) findViewById(R.id.adapter_text);
        if(adapter_text !=null){

            adapter_text.setText(R.string.adapter_texte02);
        }

        mView.setOnTouchListener(this);

        marginParams = new ViewGroup.MarginLayoutParams(adapter_text.getLayoutParams());

    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        // TODO Auto-generated method stub
        super.onWindowFocusChanged(hasFocus);

        DisplayMetrics metrics = getBaseContext().getResources().getDisplayMetrics();
        float dp = 30f;
        float fpixels = metrics.density * dp;
        int pixels = (int) (fpixels + 0.5f);

        frame.getLayoutParams().height = adapter_text.getHeight()+ pixels;
        frame.getLayoutParams().width = adapter_text.getWidth()+ pixels ;

    }



    @Override
    public boolean onTouch(View v, MotionEvent event) {


      //  Log.d("y touched :", String.valueOf(w));

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);

                // Remember where we started (for dragging)
                mLastTouchX = x;
                mLastTouchY = y;
                // Save the ID of this pointer (for dragging)
                mActivePointerId = MotionEventCompat.getPointerId(event, 0);
//
//                Log.d("x touched :", String.valueOf(x));
//                Log.d("y touched :", String.valueOf(y));

                // added

                int[] intArray = new int[2];
                frame.getLocationOnScreen(intArray);
                if((x > intArray[0]-100) && (x < intArray[0]+100) && (y < intArray[1]+100) && (y > intArray[1]-100)){
                    TopLefttouched = true;
                    Log.d("TopLeftTouched :", String.valueOf(TopLefttouched));
                }else{
                    TopLefttouched = false;
                }


                break;
            }

            case MotionEvent.ACTION_MOVE: {
                // Find the index of the active pointer and fetch its position
                final int pointerIndex =
                        MotionEventCompat.findPointerIndex(event, mActivePointerId);




                final float x = MotionEventCompat.getX(event, pointerIndex);
                final float y = MotionEventCompat.getY(event, pointerIndex);

                // Calculate the distance moved

                final float dx = x - mLastTouchX;
                final float dy = y - mLastTouchY;

                mPosX += dx;
                mPosY += dy;

                int[] intArray = new int[2];
                frame.getLocationOnScreen(intArray);
//
//                float tempX = intArray[0]+dx;
//                float tempY = intArray[1]+dy;
//                frame.setLeft(((int) tempX));
//                frame.setTop(((int) tempY));

//                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) frame.getLayoutParams();
//// Changes the height and width to the specified *pixels*
//                params.height += dy;
//                params.width += dx;
//                frame.setLayoutParams(params);

                frame.getLayoutParams().height +=dy;
                frame.getLayoutParams().width += dx;

                Log.d("w:", String.valueOf( frame.getLayoutParams().width));
                Log.d("h:", String.valueOf( frame.getLayoutParams().height));

                w =  frame.getLayoutParams().width;
                h = frame.getLayoutParams().height;

                if(w <  550 && h < 171){
                    adapter_text.setText(R.string.adapter_texte02);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w <  990 && h < 171){
                    adapter_text.setText(R.string.adapter_texte021);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  550 && h >= 171 ){
                    adapter_text.setText(R.string.adapter_texte03);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  560 && h >= 270){
                    adapter_text.setText(R.string.adapter_texte04);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  935 && h >= 306){
                    adapter_text.setText(R.string.adapter_texte05);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
                }
                if(w >=  950 && h >= 436) {
                    adapter_text.setText(R.string.adapter_texte06);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  950 && h >= 500) {
                    adapter_text.setText(R.string.adapter_texte07);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  950 && h >= 550) {
                    adapter_text.setText(R.string.adapter_texte08);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
                }
                if(w >=  950 && h >= 550) {
                    adapter_text.setText(R.string.adapter_texte08);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
                }
                if(w >=  190 && h >= 1018) {
                    adapter_text.setText(R.string.adapter_texte091);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
                }
                if(w >=  960 && h >= 610) {
                    adapter_text.setText(R.string.adapter_texte09);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  960 && h >= 640) {
                    adapter_text.setText(R.string.adapter_texte092);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  800 && h >= 660) {
                    adapter_text.setText(R.string.adapter_texte10);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,80);
                }
                if(w >=  960 && h >= 690) {
                    adapter_text.setText(R.string.adapter_texte11);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,60);
                }
                if(w >=  960 && h >= 787) {
                    adapter_text.setText(R.string.adapter_texte12);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 850) {
                    adapter_text.setText(R.string.adapter_texte13);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 1140) {
                    adapter_text.setText(R.string.adapter_texte14);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 1300) {
                    adapter_text.setText(R.string.adapter_texte15);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 1320) {
                    adapter_text.setText(R.string.adapter_texte151);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 1378) {
                    adapter_text.setText(R.string.adapter_texte152);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 1443) {
                    adapter_text.setText(R.string.adapter_texte153);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 1480) {
                    adapter_text.setText(R.string.adapter_texte154);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 1573) {
                    adapter_text.setText(R.string.adapter_texte155);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1000 && h >= 1747) {
                    adapter_text.setText(R.string.adapter_texte16);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1090 && h >= 1787) {
                    adapter_text.setText(R.string.adapter_texte17);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w >=  1090 && h >= 1880) {
                    adapter_text.setText(R.string.adapter_texte18);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,25);
                }
                if(w <197 && h >= 1880) {
                    adapter_text.setText(R.string.adapter_texte19);
                    adapter_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,55);
                }





                //  marginParams.setMargins(3, 3, 3, 3);

                frame.requestLayout();


                v.invalidate();

                // Remember this touch position for the next move event
                mLastTouchX = x;
                mLastTouchY = y;

                break;
            }

            case MotionEvent.ACTION_UP: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_CANCEL: {
                mActivePointerId = INVALID_POINTER_ID;
                break;
            }

            case MotionEvent.ACTION_POINTER_UP: {

                final int pointerIndex = MotionEventCompat.getActionIndex(event);
                final int pointerId = MotionEventCompat.getPointerId(event, pointerIndex);

                if (pointerId == mActivePointerId) {
                    // This was our active pointer going up. Choose a new
                    // active pointer and adjust accordingly.
                    final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
                    mLastTouchX = MotionEventCompat.getX(event, newPointerIndex);
                    mLastTouchY = MotionEventCompat.getY(event, newPointerIndex);
                    mActivePointerId = MotionEventCompat.getPointerId(event, newPointerIndex);
                }
                break;
            }
        }
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
}

