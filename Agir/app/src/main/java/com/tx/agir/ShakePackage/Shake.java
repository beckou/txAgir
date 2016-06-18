package com.tx.agir.ShakePackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tx.agir.MainActivity;
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
    private Animation animInvRotate;
    private Dictio dico;

    FloatingActionButton back;




    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        setContentView(R.layout.agiter_layout);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorListener = new ShakeListener();
         fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
         fade_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out);
         animRotate= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        animInvRotate= AnimationUtils.loadAnimation(getApplicationContext(), R.anim.invrotate);
        final Random rand = new Random();
        Typeface myCustomFont = Typeface.createFromAsset(getAssets(),"fonts/PoiretOne-Regular.ttf");

        final int choix = rand.nextInt(11);
        dico = new Dictio();
        final String mot = dico.getMot1(choix);
        final String motP1 = dico.getMot3(choix);
        final String motP2 = dico.getMot4(choix);

        final TextView Ae = (TextView) findViewById(R.id.textView2);
        final TextView Ge = (TextView) findViewById(R.id.textView3);
        final TextView Ie = (TextView) findViewById(R.id.textView4);
        final TextView Te = (TextView) findViewById(R.id.textView5);
        final TextView Ee = (TextView) findViewById(R.id.textView6);
        final TextView Re = (TextView) findViewById(R.id.textView7);
        final TextView E1 = (TextView) findViewById(R.id.textView8);
        final TextView E2 = (TextView) findViewById(R.id.textView9);
        final TextView E3 = (TextView) findViewById(R.id.textView10);
        final TextView E4 = (TextView) findViewById(R.id.textView11);
        final TextView E5 = (TextView) findViewById(R.id.textView12);
        final TextView E6 = (TextView) findViewById(R.id.textView13);
        final TextView P1 = (TextView) findViewById(R.id.textView14);
        final TextView P2 = (TextView) findViewById(R.id.textView15);


        P1.setText(motP1);
        P2.setText(motP2);


        Ae.setText(mot.substring(0,1));

        Ge.setText(mot.substring(1,2));

        Ie.setText(mot.substring(2,3));

        Te.setText(mot.substring(3,4));

        Ee.setText(mot.substring(4,5));

        Re.setText(mot.substring(5,6));

        E1.setText(mot.substring(6,7));

        E2.setText(mot.substring(7,8));
        E3.setText(mot.substring(8,9));

        E4.setText(mot.substring(9,10));
        E5.setText(mot.substring(10,11));

        E6.setText(mot.substring(11,12));


        Ae.setTypeface(myCustomFont);

        Ge.setTypeface(myCustomFont);

        Ie.setTypeface(myCustomFont);

        Te.setTypeface(myCustomFont);

        Ee.setTypeface(myCustomFont);

        Re.setTypeface(myCustomFont);

        E1.setTypeface(myCustomFont);

        E2.setTypeface(myCustomFont);
        E3.setTypeface(myCustomFont);

        E4.setTypeface(myCustomFont);
        E5.setTypeface(myCustomFont);

        E6.setTypeface(myCustomFont);



        P1.setTypeface(myCustomFont);
        P2.setTypeface(myCustomFont);

        mSensorListener.setOnShakeListener(new ShakeListener.OnShakeListener() {


            public void onShake(float totalMovement) throws InterruptedException {
                Toast.makeText(getApplicationContext(), "Shake!"+ totalMovement, Toast.LENGTH_SHORT).show();
                final String mot2 = dico.getMot2(choix,totalMovement);




                int n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                int n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                final TranslateAnimation anim = new TranslateAnimation( 0, n , 0, n1);

                final float nInv = n;
                final float nInv2 = n1;
                 n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                 n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;


                TranslateAnimation anim1 = new TranslateAnimation( 0, n , 0, n1);
                anim1.setFillAfter( true );
                anim1.setFillEnabled( true );

                anim1.setDuration(3000/(int)(totalMovement/4));

                final float n1Inv = n;
                final float n1Inv2 = n1;




                 n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                 n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim2 = new TranslateAnimation( 0, n , 0, n1);
                anim2.setDuration(3000/(int)(totalMovement/4));
                anim2.setFillAfter( false );


                final float n2Inv = n;
                final float n2Inv2 = n1;


                 n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                 n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim3 = new TranslateAnimation( 0, n , 0, n1);
                anim3.setDuration(3000/(int)(totalMovement/4));
                anim3.setFillAfter( false );

                final float n3Inv = n;
                final float n3Inv2 = n1;

                 n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                 n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim4 = new TranslateAnimation( 0, n , 0, n1);
                anim4.setDuration(3000/(int)(totalMovement/4));

                final float n4Inv = n;
                final float n4Inv2 = n1;


                 n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                 n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim5 = new TranslateAnimation( 0, n , 0, n1);
                anim5.setDuration(3000/(int)(totalMovement/4));

                final float n5Inv = n;
                final float n5Inv2 = n1;

                n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim6 = new TranslateAnimation( 0, n , 0, n1);
                anim6.setDuration(3000/(int)(totalMovement/4));

                final float n6Inv = n;
                final float n6Inv2 = n1;

                n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim7 = new TranslateAnimation( 0, n , 0, n1);
                anim7.setDuration(3000/(int)(totalMovement/4));

                final float n7Inv = n;
                final float n7Inv2 = n1;

                n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim8 = new TranslateAnimation( 0, n , 0, n1);
                anim8.setDuration(3000/(int)(totalMovement/4));

                final float n8Inv = n;
                final float n8Inv2 = n1;

                n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim9 = new TranslateAnimation( 0, n , 0, n1);
                anim9.setDuration(3000/(int)(totalMovement/4));

                final float n9Inv = n;
                final float n9Inv2 = n1;

                n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim10 = new TranslateAnimation( 0, n , 0, n1);
                anim10.setDuration(3000/(int)(totalMovement/4));

                final float n10Inv = n;
                final float n10Inv2 = n1;


                n = rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;
                n1 =  rand.nextInt((int)totalMovement/5*300)- ((int)totalMovement/5*300)/2;

                TranslateAnimation anim11 = new TranslateAnimation( 0, n , 0, n1);
                anim11.setDuration(3000/(int)(totalMovement/4));

                final float n11Inv = n;
                final float n11Inv2 = n1;



                animRotate.setStartOffset(anim.getDuration()/4);
                animRotate.setFillEnabled(true);
                animRotate.setFillAfter(true);
                animRotate.setDuration((long) 3000);

                animInvRotate.setStartOffset(anim.getDuration()/2);
                animInvRotate.setFillEnabled(true);
                animInvRotate.setFillAfter(true);
                animInvRotate.setDuration((long) 1000);


                anim.setFillEnabled(true);
                anim.setFillAfter(true);
                anim.setDuration(3000/(int)(totalMovement/4));
                fade_out.setDuration(300);
                fade_out.setStartOffset(anim.getDuration()-300);
                fade_in.setDuration(500);


                final AnimationSet s = new AnimationSet(false);

                s.addAnimation(anim);
               // s.addAnimation(animRotate);
              //  s.addAnimation(fade_out);
                Ae.startAnimation(s);
                s.setFillEnabled(true);
                s.setFillAfter(true);
                s.setInterpolator(new DecelerateInterpolator());

                s.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Ae.setVisibility(View.GONE);

                        final AnimationSet sBIS = new AnimationSet(false);
                        sBIS.setFillEnabled(true);
                        sBIS.setFillAfter(true);

                        sBIS.addAnimation(fade_in);

                        //sBIS.addAnimation(Invanim);
                        Ae.setText(mot2.substring(0,1));
                        Ae.setVisibility(View.VISIBLE);
                     //   Ae.startAnimation(fade_in);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                s.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Ae.setVisibility(View.GONE);

                        final AnimationSet sBIS = new AnimationSet(false);
                        sBIS.setFillEnabled(true);
                        sBIS.setFillAfter(true);

                        sBIS.addAnimation(fade_in);

                        //sBIS.addAnimation(Invanim);
                        Ae.setText(mot2.substring(0,1));
                        Ae.setVisibility(View.VISIBLE);
                     //   Ae.startAnimation(fade_in);

                        TranslateAnimation Invanim = new TranslateAnimation( nInv, 0 , nInv2, 0);


                        Invanim.setFillAfter( true );
                        Invanim.setFillEnabled( true );
                        Invanim.setDuration(3000);
                        Ae.startAnimation(Invanim);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                AnimationSet sGe = new AnimationSet(false);

                sGe.setFillAfter(true);

                sGe.addAnimation(anim1);
              //  sGe.addAnimation(animRotate);
              //  sGe.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);
                sGe.setInterpolator(new DecelerateInterpolator());
                sGe.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Ge.setVisibility(View.GONE);

                        final AnimationSet sGBIS = new AnimationSet(false);
                        sGBIS.setFillEnabled(true);
                        sGBIS.setFillAfter(true);


                        sGBIS.addAnimation(fade_in);
                       // sGBIS.addAnimation(Invanim1);

                        Ge.setText(mot2.substring(1,2));
                        Ge.setVisibility(View.VISIBLE);
                    //    Ge.startAnimation(fade_in);

                        final TranslateAnimation Invanim1 = new TranslateAnimation(n1Inv, 0 , n1Inv2, 0);
                        Invanim1.setDuration(3000);
                        Invanim1.setFillAfter( true );
                        Ge.startAnimation(Invanim1);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                Ge.startAnimation(sGe);

                AnimationSet sIe = new AnimationSet(false);

                sIe.setFillAfter(true);

                sIe.addAnimation(anim2);
              //  sIe.addAnimation(animRotate);

             //   sIe.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);

//                sIe.addAnimation(fade_in);
            //                   sIe.addAnimation(Invanim);



                sIe.setInterpolator(new DecelerateInterpolator());

                sIe.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Ie.setVisibility(View.GONE);

                        final AnimationSet sIBIS = new AnimationSet(false);
                        sIBIS.setFillEnabled(true);
                        sIBIS.setFillAfter(true);


                        sIBIS.addAnimation(fade_in);
                        //sIBIS.addAnimation(Invanim2);

                        Ie.setText(mot2.substring(2,3));
                        Ie.setVisibility(View.VISIBLE);
                   //     Ie.startAnimation(fade_in);

                        final TranslateAnimation Invanim2 = new TranslateAnimation( n2Inv, 0 , n2Inv2, 0);
                        Invanim2.setDuration(3000);
                        Invanim2.setFillAfter( true );
                        Ie.startAnimation(Invanim2);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });


                Ie.startAnimation(sIe);

                AnimationSet sTe = new AnimationSet(false);

                sTe.setFillAfter(true);

                sTe.addAnimation(anim3);
              //  sTe.addAnimation(animRotate);

              //  sTe.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);
                sTe.setInterpolator(new DecelerateInterpolator());

                sTe.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Te.setVisibility(View.GONE);
                        final AnimationSet sTBIS = new AnimationSet(false);
                        sTBIS.setFillEnabled(true);
                        sTBIS.setFillAfter(true);

                        sTBIS.addAnimation(fade_in);
                     //   sTBIS.addAnimation(Invanim3);

                        Te.setText(mot2.substring(3,4));
                        Te.setVisibility(View.VISIBLE);
                    //    Te.startAnimation(fade_in);

                        //Te.startAnimation(sTBIS);


                        final TranslateAnimation Invanim3 = new TranslateAnimation(n3Inv, 0 , n3Inv2, 0);
                        Invanim3.setDuration(3000);
                        Invanim3.setFillAfter( true );
                        Te.startAnimation(Invanim3);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                Te.startAnimation(sTe);

                AnimationSet sEe = new AnimationSet(false);
                sEe.setFillAfter(true);

                sEe.addAnimation(anim4);
              //  sEe.addAnimation(animRotate);

             //   sEe.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);
                sEe.setInterpolator(new DecelerateInterpolator());

                sEe.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Ee.setVisibility(View.GONE);

                        final AnimationSet sEBIS = new AnimationSet(false);
                        sEBIS.setFillEnabled(true);
                        sEBIS.setFillAfter(true);



                 //       sEBIS.addAnimation(fade_in);
                 //       sEBIS.addAnimation(Invanim4);

                        Ee.setText(mot2.substring(4,5));
                        Ee.setVisibility(View.VISIBLE);

                 //       Ee.startAnimation(fade_in);



                        final TranslateAnimation Invanim4 = new TranslateAnimation(n4Inv, 0 , n4Inv2, 0);
                        Invanim4.setDuration(3000);
                        Invanim4.setFillAfter( true );
                        Ee.startAnimation(Invanim4);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                Ee.startAnimation(sEe);


                AnimationSet sRe = new AnimationSet(false);

                sRe.setFillAfter(true);

                sRe.addAnimation(anim5);
                sRe.setInterpolator(new DecelerateInterpolator());

             //   sRe.addAnimation(animRotate);
          //      sRe.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);

                sRe.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Re.setVisibility(View.GONE);

                        final AnimationSet sRBIS = new AnimationSet(false);
                        sRBIS.setFillEnabled(true);
                        sRBIS.setFillAfter(true);



                        sRBIS.addAnimation(fade_in);
                    //    sRBIS.addAnimation(Invanim5);

                        Re.setText(mot2.substring(5,6));
                        Re.setVisibility(View.VISIBLE);
              //          Re.startAnimation(fade_in);


                        final TranslateAnimation Invanim5 = new TranslateAnimation(n5Inv, 0 , n5Inv2, 0);
                        Invanim5.setDuration(3000);
                        Invanim5.setFillAfter( true );
                        Re.startAnimation(Invanim5);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                Re.startAnimation(sRe);


                AnimationSet sE1e = new AnimationSet(false);

                sE1e.setFillAfter(true);

                sE1e.addAnimation(anim6);
                sE1e.setInterpolator(new DecelerateInterpolator());

              //  sE1e.addAnimation(animRotate);
        //        sE1e.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);

                sE1e.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        E1.setVisibility(View.GONE);

                        final AnimationSet sE1BIS = new AnimationSet(false);
                        sE1BIS.setFillEnabled(true);
                        sE1BIS.setFillAfter(true);

                        sE1BIS.addAnimation(fade_in);
                       // sE1BIS.addAnimation(Invanim6);

                        E1.setText(mot2.substring(6,7));
                        E1.setVisibility(View.VISIBLE);

              //          E1.startAnimation(fade_in);




                        final TranslateAnimation Invanim6 = new TranslateAnimation(n6Inv, 0 , n6Inv2, 0);
                        Invanim6.setDuration(3000);
                        Invanim6.setFillAfter( true );
                        E1.startAnimation(Invanim6);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                E1.startAnimation(sE1e);

                AnimationSet sE2e = new AnimationSet(false);

                sE2e.setFillAfter(true);

                sE2e.addAnimation(anim7);

              //  sE2e.addAnimation(animRotate);
         //       sE2e.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);
                sE2e.setInterpolator(new DecelerateInterpolator());

                sE2e.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        E2.setVisibility(View.GONE);

                        final AnimationSet sE2BIS = new AnimationSet(false);
                        sE2BIS.setFillEnabled(true);
                        sE2BIS.setFillAfter(true);



                        sE2BIS.addAnimation(fade_in);
                      //sE2BIS.addAnimation(Invanim7);

                        E2.setText(mot2.substring(7,8));
                        E2.setVisibility(View.VISIBLE);

              //          E2.startAnimation(fade_in);



                        final TranslateAnimation Invanim7 = new TranslateAnimation(n7Inv, 0 , n7Inv2, 0);
                        Invanim7.setDuration(3000);
                        Invanim7.setFillAfter( true );
                        E2.startAnimation(Invanim7);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                E2.startAnimation(sE2e);


                AnimationSet sE3e = new AnimationSet(false);

                sE3e.setFillAfter(true);

                sE3e.addAnimation(anim8);

             //   sE3e.addAnimation(animRotate);
          //      sE3e.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);
                sE3e.setInterpolator(new DecelerateInterpolator());

                sE3e.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        E3.setVisibility(View.GONE);

                        final AnimationSet sE3BIS = new AnimationSet(false);
                        sE3BIS.setFillEnabled(true);
                        sE3BIS.setFillAfter(true);

             //           sE3BIS.addAnimation(fade_in);
                    //    sE3BIS.addAnimation(Invanim8);

                        E3.setText(mot2.substring(8,9));
                        E3.setVisibility(View.VISIBLE);

                  //      E3.startAnimation(fade_in);



                        final TranslateAnimation Invanim8 = new TranslateAnimation(n8Inv, 0 , n8Inv2, 0);
                        Invanim8.setDuration(3000);
                        Invanim8.setFillAfter( true );
                        E3.startAnimation(Invanim8);


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                E3.startAnimation(sE3e);

                AnimationSet sE4e = new AnimationSet(false);

                sE4e.setFillAfter(true);

                sE4e.addAnimation(anim9);

             //   sE4e.addAnimation(animRotate);
            //    sE4e.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);
                sE4e.setInterpolator(new DecelerateInterpolator());

                sE4e.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        E4.setVisibility(View.GONE);

                        final AnimationSet sE4BIS = new AnimationSet(false);
                        sE4BIS.setFillEnabled(true);
                        sE4BIS.setFillAfter(true);



               //         sE4BIS.addAnimation(fade_in);
                       // sE4BIS.addAnimation(Invanim9);

                        E4.setText(mot2.substring(9,10));
                        E4.setVisibility(View.VISIBLE);

                //        E4.startAnimation(fade_in);






                        final TranslateAnimation Invanim9 = new TranslateAnimation(n9Inv, 0 , n9Inv2, 0);
                        Invanim9.setDuration(3000);
                        Invanim9.setFillAfter( true );
                        E4.startAnimation(Invanim9);





                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                E4.startAnimation(sE4e);
                AnimationSet sE5e = new AnimationSet(false);

                sE5e.setFillAfter(true);

                sE5e.addAnimation(anim10);

            //    sE5e.addAnimation(animRotate);
        //        sE5e.addAnimation(fade_out);
                sE5e.setInterpolator(new DecelerateInterpolator());

                sE5e.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        E5.setVisibility(View.GONE);

                        final AnimationSet sE5BIS = new AnimationSet(false);
                        sE5BIS.setFillEnabled(true);
                        sE5BIS.setFillAfter(true);


                        sE5BIS.addAnimation(fade_in);
                     //   sE5BIS.addAnimation(Invanim10);

                        E5.setText(mot2.substring(10,11));
                        E5.setVisibility(View.VISIBLE);

               //         E5.startAnimation(fade_in);



                        final TranslateAnimation Invanim10 = new TranslateAnimation(n10Inv, 0 , n10Inv2, 0);
                        Invanim10.setDuration(3000);
                        Invanim10.setFillAfter( true );
                        E5.startAnimation(Invanim10);




                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                E5.startAnimation(sE5e);

                AnimationSet sE6e = new AnimationSet(false);

                sE6e.setFillAfter(true);

                sE6e.addAnimation(anim11);

             //   sE6e.addAnimation(animRotate);
          //      sE6e.addAnimation(fade_out);
                fade_out.setFillEnabled(true);
                fade_out.setFillAfter(true);
                sE6e.setInterpolator(new DecelerateInterpolator());

                sE6e.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        E6.setVisibility(View.GONE);

                        final AnimationSet sE6BIS = new AnimationSet(false);
                        sE6BIS.setFillEnabled(true);
                        sE6BIS.setFillAfter(true);



                   //     sE6BIS.addAnimation(fade_in);

                      //  sE6BIS.addAnimation(Invanim11);

                        E6.setText(mot2.substring(11,12));

                        E6.setVisibility(View.VISIBLE);

                    //    E6.startAnimation(fade_in);




                        final TranslateAnimation Invanim11 = new TranslateAnimation(n11Inv, 0 , n11Inv2, 0);
                        Invanim11.setDuration(3000);
                        Invanim11.setFillAfter( true );

                        E6.startAnimation(Invanim11);



                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                E6.startAnimation(sE6e);




                back = (FloatingActionButton)  findViewById(R.id.back);
                if(back != null){
                    back.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Shake.this, MainActivity.class);
                            startActivity(i);
                            finish();
                        }
                    });

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


    @Override
    protected void onDestroy() {
        super.onDestroy();

        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }
}