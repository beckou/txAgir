package com.tx.agir.ShakePackage;


import java.util.ArrayList;
import java.util.Random;

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

    public Dictio(){

        sparseArray1.put(0,"D'AGITER  LE");
        sparseArray2.put(0,"JE NE PEUX CESSER");
        sparseArray3.put(0,"MONDE AUTOUR DE MOI");

        String[] Mauvais1 = {"de TAIRE le "};

        String[] Bon1 =     {"de REAGIR au"};




        ABonList.add(Mauvais1);
        AMauvaisList.add(Bon1);

        sparseArray2.put(1,"J'AIME");
        sparseArray3.put(1,"MONDE AUTOUR DE MOI");

        sparseArray1.put(1,"SECOUER     ");

        String[] Mauvais2 = {"COURSEE     ","CREUSE      ","ECROUES     "};

        String[] Bon2 =     {"COEUR       ","CESURE      ","SUCREE      "};

        ABonList.add(Mauvais2);
        AMauvaisList.add(Bon2);


        sparseArray2.put(2,"J'AIME");
        sparseArray3.put(2,"MONDE AUTOUR DE MOI");
        sparseArray1.put(2,"ANIMER      ");

        String[] Mauvais3 = {"RANIME      ","NIERA       "};

        String[] Bon3 =     {"MARIE       ","AIMER       ","MAIN        "};


        ABonList.add(Mauvais3);
        AMauvaisList.add(Bon3);


        sparseArray2.put(3,"J'AI");
        sparseArray3.put(3,"DANS CE MONDE EPHEMERE");

        sparseArray1.put(3,  "dû m'AFFOLER");

        String[] Mauvais4 = {"RALE        "};

        String[] Bon4 =     {"un ROLE     "};


        ABonList.add(Mauvais4);
        AMauvaisList.add(Bon4);

        sparseArray2.put(4,"J'AIME");
        sparseArray3.put(4,"MONDE AUTOUR DE MOI");


        sparseArray1.put(4,"TOURBILLONER");

        String[] Mauvais5 = {"ROUILLERONT ","OUBLIERONT  ","BRULERONT   "};

        String[] Bon5 =     {"EBLOUIRONT  ","BRILLENT    "};


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


        sparseArray2.put(9,"JE DOIS");
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
}
