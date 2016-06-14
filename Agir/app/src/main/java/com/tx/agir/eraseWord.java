package com.tx.agir;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.view.View.DragShadowBuilder;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class eraseWord extends AppCompatActivity {

    private RelativeLayout rLayout;
    private ImageView img;
    // Create a string for the ImageView label
  private static final String IMAGEVIEW_TAG = "icon bitmap";
//    private ClipData dragData;

    String msg;
    private android.widget.RelativeLayout.LayoutParams layoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_erase_word);

        rLayout = (RelativeLayout) findViewById(R.id.rLayout);
        img = (ImageView) findViewById(R.id.myImage);
        img.setTag(IMAGEVIEW_TAG);




        setupDragDrop();

//        rLayout.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                if (event.getAction() ==   DragEvent.ACTION_DRAG_ENDED){
//                    int x_cord = (int) event.getX();
//                    int  y_cord = (int) event.getY();
//                    layoutParams.leftMargin = x_cord;
//                    layoutParams.topMargin = y_cord;
//                    img.setLayoutParams(layoutParams);
//
//                }
//                return true;
//            }
//        });


//        img.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//
//                System.out.println("-----------HEY");
//
//                switch(event.getAction())
//                {
//
//                    case DragEvent.ACTION_DRAG_STARTED:
//                        layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
//                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_STARTED");
//
//                        // Do nothing
//                        break;
//
//                    case DragEvent.ACTION_DRAG_ENTERED:
//                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENTERED");
//                        int x_cord = (int) event.getX();
//                        int y_cord = (int) event.getY();
//                        break;
//
//                    case DragEvent.ACTION_DRAG_EXITED :
//                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_EXITED");
//                        x_cord = (int) event.getX();
//                        y_cord = (int) event.getY();
//                        layoutParams.leftMargin = x_cord;
//                        layoutParams.topMargin = y_cord;
//                        v.setLayoutParams(layoutParams);
//                        break;
//
//                    case DragEvent.ACTION_DRAG_LOCATION  :
//                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_LOCATION");
//                        x_cord = (int) event.getX();
//                        y_cord = (int) event.getY();
//                        break;
//
//                    case DragEvent.ACTION_DRAG_ENDED   :
//                        Log.d(msg, "Action is DragEvent.ACTION_DRAG_ENDED");
//
//                        System.out.println("drag ended");
//                        v.setVisibility(View.VISIBLE);
//
//                        // Do nothing
//                        break;
//
//                    case DragEvent.ACTION_DROP:
//                        Log.d(msg, "ACTION_DROP event");
//                        // Do nothing
//                        break;
//                    default: break;
//                }
//                return true;
//            }
//        });


//
//        img.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    ClipData data = ClipData.newPlainText("", "");
//                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);
//
//                    img.startDrag(data, shadowBuilder, img, 0);
//                    img.setVisibility(View.INVISIBLE);
//                    return true;
//                }
//                else
//                {
//                    return false;
//                }
//            }
//        });
//
//


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
                return true;
            }
        });

        img.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String[] mimeTypes = { ClipDescription.MIMETYPE_TEXT_PLAIN};

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(img);
                img.setVisibility(View.INVISIBLE);

                v.startDrag( data, shadowBuilder, mimeTypes, 0);
                return true;
            }
        } );



        /////////////////////////
//
//        img.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                ClipData.Item item = new ClipData.Item((CharSequence)v.getTag());
//                String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
//                ClipData dragData = new ClipData(v.getTag().toString(),mimeTypes, item);
//                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(img);
//                v.startDrag(dragData, myShadow, img, 0);
//                img.setVisibility(View.INVISIBLE);
//
//                return true;
//
//            }
//        });
//
//
//        img.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                if (event.getAction() == DragEvent.ACTION_DRAG_STARTED){
//                    layoutParams = (RelativeLayout.LayoutParams)v.getLayoutParams();
//                } else if (event.getAction() ==   DragEvent.ACTION_DRAG_ENDED)
//                    v.setVisibility(View.VISIBLE);
//
//                return true;
//            }
//        });
//
//
//
//        rLayout.setOnDragListener(new View.OnDragListener() {
//            @Override
//            public boolean onDrag(View v, DragEvent event) {
//                if (event.getAction() ==   DragEvent.ACTION_DROP){
//                    int x_cord = (int) event.getX();
//                    int  y_cord = (int) event.getY();
//                    RelativeLayout.LayoutParams _rootLayoutParams = new RelativeLayout.LayoutParams(v.getLayoutParams());
//                    layoutParams = (RelativeLayout.LayoutParams)_rootLayoutParams;
//
//                    layoutParams.leftMargin = x_cord;
//                    layoutParams.topMargin = y_cord;
//                    img.setLayoutParams(layoutParams);
//
//                }
//                return true;
//            }
//        });
//    }
//

    }

}


