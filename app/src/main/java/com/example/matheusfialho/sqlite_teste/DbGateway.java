package com.example.matheusfialho.sqlite_teste;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DbGateway {

    private static DbGateway gw;
    private SQLiteDatabase db;


    DbGateway(Context ctx){
        DbHelper helper = new DbHelper(ctx);
        db = helper.getWritableDatabase();
    }


    public static DbGateway getInstance(Context ctx){
        if(gw == null)
            gw = new DbGateway(ctx);
        return gw;
    }

    public SQLiteDatabase getDatabase(){
        return this.db;
    }
}