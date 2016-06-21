package com.tx.agir.ShakePackage;


import android.content.Context;
import android.util.Log;

import com.tx.agir.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by raphael on 29/05/16.
 */
public class Dictio {
    ArrayList<String[]> ABonList = new ArrayList<String[]>();
    ArrayList<String[]> AMauvaisList = new ArrayList<String[]>();

    android.util.SparseArray<String> sparseArray1 = new android.util.SparseArray<String>(30);
    android.util.SparseArray<String> sparseArray2 = new android.util.SparseArray<String>(30);
    android.util.SparseArray<String> sparseArray3 = new android.util.SparseArray<String>(30);
    private float ancienMouvement = 0.0f;
    public Dictio(Context myContext){


        sparseArray1.put(0, completeMot(myContext.getResources().getString(R.string.agiter_Mot00)));
        sparseArray2.put(0,myContext.getResources().getString(R.string.agiter_Phrase00));
        sparseArray3.put(0,myContext.getResources().getString(R.string.agiter_Phrase10));

        String[] Mauvais1 = {completeMot(myContext.getResources().getString(R.string.agiter_Mot20))};

        String[] Bon1 =     {completeMot(myContext.getResources().getString(R.string.agiter_Mot10))};



        ABonList.add(Mauvais1);
        AMauvaisList.add(Bon1);

        sparseArray2.put(1,myContext.getResources().getString(R.string.agiter_Phrase01));
        sparseArray3.put(1,myContext.getResources().getString(R.string.agiter_Phrase11));

        sparseArray1.put(1,completeMot(myContext.getResources().getString(R.string.agiter_Mot01)));

        String[] Mauvais2 = {completeMot(myContext.getResources().getString(R.string.agiter_Mot21))};

        String[] Bon2 =     {completeMot(myContext.getResources().getString(R.string.agiter_Mot11))};

        ABonList.add(Mauvais2);
        AMauvaisList.add(Bon2);


        sparseArray2.put(2, myContext.getResources().getString(R.string.agiter_Phrase02));
        sparseArray3.put(2,(myContext.getResources().getString(R.string.agiter_Phrase12)));
        sparseArray1.put(2,completeMot(myContext.getResources().getString(R.string.agiter_Mot02)));

        String[] Mauvais3 = {completeMot(myContext.getResources().getString(R.string.agiter_Mot22))};

        String[] Bon3 =     {completeMot(myContext.getResources().getString(R.string.agiter_Mot11))};

        ABonList.add(Mauvais3);
        AMauvaisList.add(Bon3);


        sparseArray2.put(3,(myContext.getResources().getString(R.string.agiter_Phrase03)));
        sparseArray3.put(3,(myContext.getResources().getString(R.string.agiter_Phrase13)));

        sparseArray1.put(3,  completeMot(myContext.getResources().getString(R.string.agiter_Mot03)));

        String[] Mauvais4 = {completeMot(myContext.getResources().getString(R.string.agiter_Mot23))};

        String[] Bon4 =     {completeMot(myContext.getResources().getString(R.string.agiter_Mot13))};

        ABonList.add(Mauvais4);
        AMauvaisList.add(Bon4);

        sparseArray2.put(4,(myContext.getResources().getString(R.string.agiter_Phrase04)));
        sparseArray3.put(4,(myContext.getResources().getString(R.string.agiter_Phrase14)));

        sparseArray1.put(4,completeMot(myContext.getResources().getString(R.string.agiter_Mot04)));

        String[] Mauvais5 = {completeMot(myContext.getResources().getString(R.string.agiter_Mot24))};

        String[] Bon5 =     {completeMot(myContext.getResources().getString(R.string.agiter_Mot14))};


        ABonList.add(Mauvais5);
        AMauvaisList.add(Bon5);

        sparseArray2.put(5,"J'AIME");
        sparseArray3.put(5,"MONDE AUTOUR DE MOI");


        sparseArray1.put(5,"GESTICULER  ");

        String[] Mauvais6 = {"STERILE     ","ECUEILS     ","RECLUS      "};

        String[] Bon6 =     {"LECTEURS    ","RECUEILS    ","GESTUEL     "};


        ABonList.add(Mauvais6);
        AMauvaisList.add(Bon6);


        sparseArray2.put(6,"J'AIME");
        sparseArray3.put(6,"MONDE AUTOUR DE MOI");

        sparseArray1.put(6,"TROUBLER    ");

        String[] Mauvais7 = {"BOULET      ","OBTURER     ","BRULER      "};

        String[] Bon7 =     {"BROUTER     ","LOUTRE      "};

        ABonList.add(Mauvais7);
        AMauvaisList.add(Bon7);

        sparseArray2.put(7,"J'AIME");
        sparseArray3.put(7,"MONDE AUTOUR DE MOI");


        sparseArray1.put(7,"BOUGER      ");

        String[] Mauvais8 = {"BOGUE       ","GORE        ","BOUE        "};

        String[] Bon8 =     {"RoBe rOUGE  ","ORGUE       ","ORBE        "};


        ABonList.add(Mauvais8);
        AMauvaisList.add(Bon8);

        sparseArray2.put(8,"J'AIME");
        sparseArray3.put(8,"MONDE AUTOUR DE MOI");


        sparseArray1.put(8,"FRETILLER   ");

        String[] Mauvais9 = {"FLETRIR     ","ETIRE       ","IRREEL      "};

        String[] Bon9 =     {"FERTILE     ","FIERE       ","TRIER       "};

        ABonList.add(Mauvais9);
        AMauvaisList.add(Bon9);


        sparseArray2.put(9,"JE VEUX");
        sparseArray3.put(9,"DANS CETTE CAGE");

        sparseArray1.put(9,"REMUER      ");

        String[] Mauvais10 = {"m'EMmURER   ","MEURE       ","ERRE        "};

        String[] Bon10 =     {"HUMER       ","MuRmUREr    ","EMUE        "};


        ABonList.add(Mauvais10);
        AMauvaisList.add(Bon10);



        sparseArray1.put(10,"MOUVOIR     ");
        sparseArray2.put(10,"JE VEUX");
        sparseArray3.put(10,"LES CHOSES, LES BIDULES, LES TRUCS");

        String[] Mauvais11 = {"VOMIR       ","fUIR        "};

        String[] Bon11 =     {"MURIR       ","VOIR        ","OUIR        "};

        ABonList.add(Mauvais11);
        AMauvaisList.add(Bon11);



    }

    public String getMot1(int key){

        return this.sparseArray1.get(key);

    }

    public String getMot3(int key){

        return this.sparseArray2.get(key);

    }

    public String getMot4(int key){

        return this.sparseArray3.get(key);

    }
    public String getMot2(int ligne, float totalMovement){
        Random rand = new Random();

        if(totalMovement > 12) {




            int i = this.ABonList.get(ligne).length;

            int randN = rand.nextInt(i);
            System.out.println(this.ABonList);
            return this.ABonList.get(ligne)[randN];



        }else {


                int i = this.AMauvaisList.get(ligne).length;
                System.out.println(this.AMauvaisList);

                int randN = rand.nextInt(i);

                return this.AMauvaisList.get(ligne)[randN];






        }


    }

public String completeMot(String mot){
    StringBuilder myString = new StringBuilder("            ");
    Log.w("s1", " le i :"+ myString.toString().length());
    Log.w("s2", " le i :"+ mot.length());

    for (int i = 0; i < mot.length(); i++){
        Log.w("i", " le i :"+ i);

        myString.setCharAt(i,mot.charAt(i));

    }


    return myString.toString();
}



}
