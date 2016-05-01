package com.tx.agir;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by Rebecca Fribourg on 02/05/2016.
 */
public class view_adapter  extends LinearLayout implements View.OnTouchListener {


    public view_adapter(Context context, AttributeSet attrs) {
        super(context, attrs);



    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }
}
