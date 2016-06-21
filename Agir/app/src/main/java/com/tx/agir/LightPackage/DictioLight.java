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

        String[] P1 = {"entrevoir","si loin","le Bout","si proche"};
        String[] P2 = {"tourner","en rond","jusqu'à","l'infini"};
        String[] P3 = {"decouvrir","le sens","du chemin","à suivre"};
        String[] P4 = {"y voir","plus clair","autour","de moi"};

        ListPhrases.add(P1);
        ListPhrases.add(P2);
        ListPhrases.add(P3);
        ListPhrases.add(P4);

        final Random rand = new Random();

         Choix = rand.nextInt(ListPhrases.size());


    }

    public String[] getPhrase(){

        return this.ListPhrases.get(Choix);

    }

}


