package com.tx.agir;

/**
 * Created by Rebecca Fribourg on 03/05/2016.
 */
public class DataHolder {

    private String data;
    public String getData() {return data;}
    public void setData(String data) {this.data = data;}

    private static final DataHolder holder = new DataHolder();
    public static DataHolder getInstance() {return holder;}
}
