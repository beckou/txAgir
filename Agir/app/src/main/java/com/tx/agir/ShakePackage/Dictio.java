package com.tx.agir.ShakePackage;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by raphael on 29/05/16.
 */
public class Dictio {
    ArrayList<String[]> ABonList = new ArrayList<String[]>();
    ArrayList<String[]> AMauvaisList = new ArrayList<String[]>();

    android.util.SparseArray<String> sparseArray1 = new android.util.SparseArray<String>(16);

    public Dictio(){

        sparseArray1.put(0,"AGITER      ");

        String[] Mauvais1 = {"TAIRE       ","RATEE       ","TARI        "};

        String[] Bon1 =     {"REAGIR      ","TIRAGE      ","TRIAGE      "};

        ABonList.add(Mauvais1);
        AMauvaisList.add(Bon1);

        sparseArray1.put(1,"SECOUER     ");

        String[] Mauvais2 = {"COURSEE     ","CREUSE      ","ECROUES     "};

        String[] Bon2 =     {"COEUR       ","CESURE      ","SUCREE      "};

        ABonList.add(Mauvais2);
        AMauvaisList.add(Bon2);



        sparseArray1.put(2,"ANIMER      ");

        String[] Mauvais3 = {"RANIME      ","NIERA       ","MAIN        "};

        String[] Bon3 =     {"MARIE       ","AIMER       ","RIMA        "};

        ABonList.add(Mauvais3);
        AMauvaisList.add(Bon3);



        sparseArray1.put(3,"AFFOLER     ");

        String[] Mauvais4 = {"RAFLE       ","FROLE       ","RALE        "};

        String[] Bon4 =     {"RAFFOLE     ","OFFRE       ","ROLE        "};

        ABonList.add(Mauvais4);
        AMauvaisList.add(Bon4);


        sparseArray1.put(4,"TOURBILLONER");

        String[] Mauvais5 = {"ROUILLERONT ","OUBLIERONT  ","BRULERONT   "};

        String[] Bon5 =     {"EBLOUIRONT  ","BRILLENT    "};


        ABonList.add(Mauvais5);
        AMauvaisList.add(Bon5);


        sparseArray1.put(5,"GESTICULER  ");

        String[] Mauvais6 = {"STERILE     ","ECUEILS     ","RECLUS      "};

        String[] Bon6 =     {"LECTEURS    ","RECUEILS    ","GESTUEL     "};


        ABonList.add(Mauvais6);
        AMauvaisList.add(Bon6);


        sparseArray1.put(6,"TROUBLER    ");

        String[] Mauvais7 = {"BOULET      ","OBTURER     ","BRULER      "};

        String[] Bon7 =     {"BROUTER     ","LOUTRE      "};

        ABonList.add(Mauvais7);
        AMauvaisList.add(Bon7);



        sparseArray1.put(7,"BOUGER      ");

        String[] Mauvais8 = {"BOGUE       ","GORE        ","BOUE        "};

        String[] Bon8 =     {"RoBe rOUGE  ","ORGUE       ","ORBE        "};


        ABonList.add(Mauvais8);
        AMauvaisList.add(Bon8);



        sparseArray1.put(8,"FRETILLER   ");

        String[] Mauvais9 = {"FLETRIR     ","ETIRE       ","IRREEL      "};

        String[] Bon9 =     {"FERTILE     ","FIERE       ","TRIER       "};

        ABonList.add(Mauvais9);
        AMauvaisList.add(Bon9);



        sparseArray1.put(9,"REMUER      ");

        String[] Mauvais10 = {"EMmURER     ","MEURE       ","ERRE        "};

        String[] Bon10 =     {"HUMER       ","MuRmUREr    ","EMUE        "};


        ABonList.add(Mauvais10);
        AMauvaisList.add(Bon10);


        sparseArray1.put(10,"MOUVOIR     ");

        String[] Mauvais11 = {"VOMIR       ","MOUrIR      ","fUIRe       "};

        String[] Bon11 =     {"MURIR       ","VOIR        ","OUIR        "};

        ABonList.add(Mauvais11);
        AMauvaisList.add(Bon11);



    }

    public String getMot1(int key){

        return this.sparseArray1.get(key);

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
}
