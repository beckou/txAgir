package com.tx.agir.ShakePackage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.plattysoft.leonids.ParticleSystem;
import com.tx.agir.R;

import java.util.Random;

/**
 * Created by Rebecca on 13/04/2016.
 */
public class Shake extends Activity {
    private SensorManager mSensorManager;

    private ShakeListener mSensorListener;

    private Animation fade_in;
    private Animation fade_out;
    private Animation animRotate;

    private Dictio dico;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agiter_layout);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeListener();
         fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
         fade_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
         animRotate= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        dico = new Dictio();
        mSensorListener.setOnShakeListener(new ShakeListener.OnShakeListener() {

            public void onShake() throws InterruptedException {
                Toast.makeText(getApplicationContext(), "Shake!", Toast.LENGTH_SHORT).show();

                TextView Ae = (TextView) findViewById(R.id.textView2);
                TextView Ge = (TextView) findViewById(R.id.textView3);
                TextView Ie = (TextView) findViewById(R.id.textView4);
                TextView Te = (TextView) findViewById(R.id.textView5);
                TextView Ee = (TextView) findViewById(R.id.textView6);
                TextView Re = (TextView) findViewById(R.id.textView7);
                Random rand = new Random();
                int n = rand.nextInt(1001)- 500;
                int n1 = rand.nextInt(1001)- 500;

                TranslateAnimation anim = new TranslateAnimation( 0, n , 0, n1);
                anim.setDuration(3000);
                anim.setFillAfter( true );

                TranslateAnimation Invanim = new TranslateAnimation( 0, -n , 0, -n1);
                anim.setDuration(3000);
                anim.setFillAfter( true );

                 n = rand.nextInt(1001)- 500;
                 n1 = rand.nextInt(1001)- 500;


                TranslateAnimation anim1 = new TranslateAnimation( 0, n , 0, n1);
                anim1.setDuration(3000);
                anim1.setFillAfter( true );

                TranslateAnimation Invanim1 = new TranslateAnimation( 0, -n , 0, -n1);
                anim.setDuration(3000);
                anim.setFillAfter( true );



                n = rand.nextInt(1001)- 500;
                n1 = rand.nextInt(1001)- 500;

                TranslateAnimation anim2 = new TranslateAnimation( 0, n , 0, n1);
                anim2.setDuration(3000);
                anim2.setFillAfter( true );

                TranslateAnimation Invanim2 = new TranslateAnimation( 0, -n , 0, -n1);
                anim.setDuration(3000);
                anim.setFillAfter( true );


                n = rand.nextInt(1001)- 500;
                n1 = rand.nextInt(1001)- 500;

                TranslateAnimation anim3 = new TranslateAnimation( 0, n , 0, n1);
                anim3.setDuration(3000);
                anim3.setFillAfter( true );

                TranslateAnimation Invanim3 = new TranslateAnimation( 0, -n , 0, -n1);
                anim.setDuration(3000);
                anim.setFillAfter( true );


                n = rand.nextInt(1001)- 500;
                n1 = rand.nextInt(1001)- 500;

                TranslateAnimation anim4 = new TranslateAnimation( 0, n , 0, n1);
                anim4.setDuration(3000);
                anim4.setFillAfter( true );

                TranslateAnimation Invanim4 = new TranslateAnimation( 0, -n , 0, -n1);
                anim.setDuration(3000);
                anim.setFillAfter( true );

                n = rand.nextInt(1001)- 500;
                n1 = rand.nextInt(1001)- 500;

                TranslateAnimation anim5 = new TranslateAnimation( 0, n , 0, n1);
                anim5.setDuration(3000);
                anim5.setFillAfter( true );

                TranslateAnimation Invanim5 = new TranslateAnimation( 0, -n , 0, -n1);
                anim.setDuration(3000);
                anim.setFillAfter( true );

                AnimationSet s = new AnimationSet(false);
                //s.addAnimation(fade_in);

                animRotate.setDuration((long) 2000);
                animRotate.setStartOffset(anim.getDuration()/2);

                s.addAnimation(animRotate);

                fade_out.setStartOffset(anim.getDuration()+animRotate.getDuration());
                fade_in.setStartOffset(anim.getDuration() + fade_out.getDuration());
                Invanim.setStartOffset(anim.getDuration()+fade_out.getDuration() + fade_in.getDuration());
                Invanim1.setStartOffset(anim.getDuration()+fade_out.getDuration() + fade_in.getDuration());
                Invanim2.setStartOffset(anim.getDuration()+fade_out.getDuration() + fade_in.getDuration());
                Invanim3.setStartOffset(anim.getDuration()+fade_out.getDuration() + fade_in.getDuration());
                Invanim4.setStartOffset(anim.getDuration()+fade_out.getDuration() + fade_in.getDuration());
                Invanim5.setStartOffset(anim.getDuration()+fade_out.getDuration() + fade_in.getDuration());


                s.setFillAfter(true);
                s.addAnimation(animRotate);
                s.addAnimation(fade_out);
             //   s.addAnimation(fade_in);
             //   s.addAnimation(Invanim);

                s.addAnimation(anim);
                Ae.startAnimation(s);

                AnimationSet sGe = new AnimationSet(false);

                sGe.setFillAfter(true);

                sGe.addAnimation(anim1);
                sGe.addAnimation(animRotate);

                sGe.addAnimation(fade_out);
                // sGe.addAnimation(fade_in);
              //   sGe.addAnimation(Invanim);


                Ge.startAnimation(sGe);

                AnimationSet sIe = new AnimationSet(false);

                sIe.setFillAfter(true);

                sIe.addAnimation(anim2);
                sIe.addAnimation(animRotate);

                sIe.addAnimation(fade_out);

//                sIe.addAnimation(fade_in);
            //                   sIe.addAnimation(Invanim);

                Ie.startAnimation(sIe);

                AnimationSet sTe = new AnimationSet(false);

                sTe.setFillAfter(true);

                sTe.addAnimation(anim3);
                sTe.addAnimation(animRotate);

                sTe.addAnimation(fade_out);

//                sTe.addAnimation(fade_in);
            //                  sTe.addAnimation(Invanim);

                Te.startAnimation(sTe);

                AnimationSet sEe = new AnimationSet(false);
                sEe.setFillAfter(true);

                sEe.addAnimation(anim4);
                sEe.addAnimation(animRotate);

                sEe.addAnimation(fade_out);

//                sEe.addAnimation(fade_in);
          //                    sEe.addAnimation(Invanim);

                Ee.startAnimation(sEe);


                AnimationSet sRe = new AnimationSet(false);

                sRe.setFillAfter(true);

                sRe.addAnimation(anim5);

                sRe.addAnimation(animRotate);
                sRe.addAnimation(fade_out);

//                sRe.addAnimation(fade_in);
             //                 sRe.addAnimation(Invanim);

                Re.startAnimation(sRe);


                n = rand.nextInt(4);

                String mot = dico.getMot(n);
                if(mot != null) {

                    Ae.setText(mot.substring(0,1));
                    Ge.setText(mot.substring(1,2));
                    Ie.setText(mot.substring(2,3));
                    Te.setText(mot.substring(3,4));
                    Ee.setText(mot.substring(4,5));
                    Re.setText(mot.substring(5,6));
                }
                }

        });
        }
    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener,
                mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_UI);
    }



    @Override
    protected void onPause() {
        mSensorManager.unregisterListener(mSensorListener);
        super.onPause();
    }

    private void moveViewToScreenCenter( View view )
    {
        LinearLayout root = (LinearLayout) findViewById( R.id.agiterlay);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics( dm );
        int statusBarOffset = dm.heightPixels - root.getMeasuredHeight();

        int originalPos[] = new int[2];
        view.getLocationOnScreen( originalPos );

        int xDest = dm.widthPixels/2;
        xDest -= (view.getMeasuredWidth()/2);
        int yDest = dm.heightPixels/2 - (view.getMeasuredHeight()/2) - statusBarOffset;

        TranslateAnimation anim = new TranslateAnimation( 0, xDest - originalPos[0] , 0, yDest - originalPos[1] );
        anim.setDuration(1000);
        anim.setFillAfter( true );
        view.startAnimation(anim);
    }

}