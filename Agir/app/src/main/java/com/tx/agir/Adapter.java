package com.tx.agir;

import android.app.Activity;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
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

    FrameLayout frame;
    RelativeLayout mView;
    private TextView adapter_text = null;

    private Boolean TopLefttouched = false;
    ViewGroup.MarginLayoutParams marginParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adapter_layout);


        frame = (FrameLayout) findViewById(R.id.frame);
        if(frame != null){
            frame.getLayoutParams().height = 162;
            frame.getLayoutParams().width = 333;
        }


        mView = (RelativeLayout)  findViewById(R.id.view);

        adapter_text = (TextView) findViewById(R.id.adapter_text);
        if(adapter_text !=null){

            adapter_text.setText(" instable");
        }

        mView.setOnTouchListener(this);

        marginParams = new ViewGroup.MarginLayoutParams(adapter_text.getLayoutParams());

    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {


        double h =  frame.getLayoutParams().height;
        double w = frame.getLayoutParams().width;

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

                if(frame.getLayoutParams().width >=  587 && frame.getLayoutParams().height >= 247 ){
                    adapter_text.setText(" Tout est si instable autour de moi");
                }
                if(frame.getLayoutParams().width <  587 && frame.getLayoutParams().height < 247){
                    adapter_text.setText("instable");
                }


                marginParams.setMargins(3, 3, 3, 3);

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
}

