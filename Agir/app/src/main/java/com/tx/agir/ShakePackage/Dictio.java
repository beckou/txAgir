package com.tx.agir.ShakePackage;

import android.util.ArrayMap;

/**
 * Created by raphael on 29/05/16.
 */
public class Dictio {

    android.util.SparseArray<String> sparseArray = new android.util.SparseArray<String>(16);

    public Dictio(){

        sparseArray.put(0,"AGITER");
        sparseArray.put(1,"SECOUE");
        sparseArray.put(2,"ANIMER");
        sparseArray.put(3,"AFFOLE");


    }

    public String getMot(int key){

        return this.sparseArray.get(key);

    }

}
