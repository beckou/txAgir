package com.tx.agir;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.oublier_layout);


       /* Bitmap b = Bitmap.createBitmap(128, 128, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);*/

        animation_firstText = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.first_text_appear);
        animation_last_text = AnimationUtils.loadAnimation(this, R.anim.last_text_appear);
        fade_in =  AnimationUtils.loadAnimation(this, R.anim.fade_in);

        wellcomeSentence = (TextView)findViewById(R.id.oublier_sentence);
        wellcomeSentence.setAnimation(animation_firstText);


        toForget = (EditText)findViewById(R.id.to_forget);
        toForget.setVisibility(View.INVISIBLE);


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
            wellcomeSentence.setText("Et vous, que souhaitez-vous oublier ?");
            wellcomeSentence.startAnimation(animation_last_text);
        }

        if (animation == animation_last_text) {
            //wellcomeSentence.setVisibility(View.INVISIBLE);
            toForget.setVisibility(View.VISIBLE);
            toForget.startAnimation(fade_in);

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


}
