package com.tx.agir;

/**
 * Created by Rebecca Fribourg on 03/05/2016.
 */
public class Singleton {
    private static Singleton mInstance = null;

    private String mString;

    private String language;


    private Singleton(){
       // mString = "Hello";
        language = "fr";
    }

    public static Singleton getInstance(){
        if(mInstance == null)
        {
            mInstance = new Singleton();
        }
        return mInstance;
    }

    public String getString(){
       // return this.mString;
        return this.language;
    }

    public void setString(String value){
       // mString = value;
        language = value;
    }
}
