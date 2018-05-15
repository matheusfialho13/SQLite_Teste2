package com.example.matheusfialho.sqlite_teste;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Crud.db";
    public static final String TABELA = "Clientes";
    public static String _ID = "_ID";
    public static String NOME = "Nome";
    public static String IDADE = "Idade";
    private static final int DATABASE_VERSION = 1;
    private final String CREATE_TABLE = "CREATE TABLE "+ TABELA +" ("
            +_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +NOME+" TEXT NOT NULL, "
            +IDADE+" INTEGER NOT NULL);";


    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
