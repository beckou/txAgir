package com.tx.agir.LightPackage;

import android.graphics.Typeface;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by raphael on 11/06/16.
 */
public class DictioLight {
    ArrayList<String[]> ListPhrases = new ArrayList<String[]>();
     int Choix = 0;
    public DictioLight() {

        String[] P1 = {"Entrevoir","Si Loin","Le Bout","Si Proche"};
        String[] P2 = {"Tourner","En Rond","jusqu'à","L'infini"};
        String[] P3 = {"Decouvrir","Le sens","du chemin","à suivre"};
        String[] P4 = {"Y voir","plus clair","autour","de moi"};

        ListPhrases.add(P1);
        ListPhrases.add(P2);
        ListPhrases.add(P3);
        ListPhrases.add(P4);

        final Random rand = new Random();

         Choix = rand.nextInt(4);


    }

    public String[] getPhrase(){

        return this.ListPhrases.get(Choix);

    }

}


