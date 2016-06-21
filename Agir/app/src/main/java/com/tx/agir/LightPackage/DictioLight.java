package com.tx.agir.LightPackage;

import android.content.Context;
import android.graphics.Typeface;

import com.tx.agir.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by raphael on 11/06/16.
 */
public class DictioLight {
    ArrayList<String[]> ListPhrases = new ArrayList<String[]>();
     int Choix = 0;
    public DictioLight(Context myContext) {

        String[] P1 = {myContext.getResources().getString(R.string.Eclairer_Phrase10),myContext.getResources().getString(R.string.Eclairer_Phrase11),myContext.getResources().getString(R.string.Eclairer_Phrase12),myContext.getResources().getString(R.string.Eclairer_Phrase13)};
        String[] P2 = {myContext.getResources().getString(R.string.Eclairer_Phrase20),myContext.getResources().getString(R.string.Eclairer_Phrase21),myContext.getResources().getString(R.string.Eclairer_Phrase22),myContext.getResources().getString(R.string.Eclairer_Phrase23)};
        String[] P3 = {myContext.getResources().getString(R.string.Eclairer_Phrase30),myContext.getResources().getString(R.string.Eclairer_Phrase31),myContext.getResources().getString(R.string.Eclairer_Phrase32),myContext.getResources().getString(R.string.Eclairer_Phrase33)};
        String[] P4 = {myContext.getResources().getString(R.string.Eclairer_Phrase40),myContext.getResources().getString(R.string.Eclairer_Phrase41),myContext.getResources().getString(R.string.Eclairer_Phrase42),myContext.getResources().getString(R.string.Eclairer_Phrase43)};
        String[] P5 = {myContext.getResources().getString(R.string.Eclairer_Phrase50),myContext.getResources().getString(R.string.Eclairer_Phrase51),myContext.getResources().getString(R.string.Eclairer_Phrase52),myContext.getResources().getString(R.string.Eclairer_Phrase53)};


        ListPhrases.add(P1);
        ListPhrases.add(P2);
        ListPhrases.add(P3);
        ListPhrases.add(P4);
        ListPhrases.add(P5);

        final Random rand = new Random();

         Choix = rand.nextInt(ListPhrases.size());


    }

    public String[] getPhrase(){

        return this.ListPhrases.get(Choix);

    }

}


