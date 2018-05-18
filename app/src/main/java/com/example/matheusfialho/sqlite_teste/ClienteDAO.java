package com.example.matheusfialho.sqlite_teste;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public static final String TABLE_CLIENTES = DbHelper.TABELA;
    public static DbGateway gw;
    public static ClienteDAO dao;
    public static DbHelper dbHelper;
    public static SQLiteDatabase db;
    private Cliente cliente;


    public ClienteDAO(Context ctx){
        gw = DbGateway.getInstance(ctx);
    }


    /*
    ClienteDAO(Context ctx) { cliente = new Cliente(ctx);}

    public static ClienteDAO getInstance(Context ctx){
        if (dao == null) {
            dao = new ClienteDAO(ctx);
        }
        gw = DbGateway.getInstance(ctx);
        return dao;
    }
    */

    public boolean salvar(String nome, int idade){
        return salvar(0, nome, idade);
    }

    public boolean salvar(int id, String nome, int idade){
        boolean resultado;
        //db = gw.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("Nome", nome);
        cv.put("Idade", idade);
        //resultado = gw.getDatabase().insert(TABLE_CLIENTES, null, cv) > -1;

        if(id > 0)
            resultado = gw.getDatabase().update(TABLE_CLIENTES, cv, "_ID = ?", new String[]{ id + "" }) > 0;
        else
            resultado = gw.getDatabase().insert(TABLE_CLIENTES, null, cv) > 0;
        return resultado;

        /*resultado = db.insert(TABLE_CLIENTES, null, cv);
        resultado = gw.getDatabase().insert(TABLE_CLIENTES, null, cv);
        if (resultado == -1)
        //    return "Erro ao inserir registro";
        else
            return "Registro Inserido com sucesso";
        */
    }

    public List<Cliente> retornarTodos(){
        List<Cliente> listClientes = null;

        //Cursor cursor = gw.getDatabase().rawQuery("SELECT Nome FROM Clientes", null);
        Cursor cursor = gw.getDatabase().query(
                "Clientes",
                new String[]{DbHelper._ID,DbHelper.NOME,DbHelper.IDADE},
                null,
                null,
                null,
                null,
                null);
        if(cursor != null && cursor.moveToFirst()){
            listClientes = new ArrayList<>();
            cursor.moveToFirst();
            do {
                int _id = cursor.getInt(cursor.getColumnIndex(DbHelper._ID));
                String nome = cursor.getString(cursor.getColumnIndex(DbHelper.NOME));
                int idade = cursor.getInt(cursor.getColumnIndex(DbHelper.IDADE));
                listClientes.add(new Cliente(_id, nome, idade));
            } while (cursor.moveToNext());
            cursor.close();
        }
        return listClientes;
    }

    public List<Cliente> retornaConsulta(String nomeCliente){

        List<Cliente> listClientes = null;
        String selection = DbHelper.NOME + " LIKE ?";
        String[] selectionArgs = new String[]{"%"+nomeCliente+"%"};

        Cursor cursor = gw.getDatabase().query(
                "Clientes",
                new String[]{DbHelper._ID, DbHelper.NOME, DbHelper.IDADE},
                selection,
                selectionArgs,
                null,
                null,
                null);

        if (cursor != null && cursor.moveToFirst()) {
            listClientes = new ArrayList<>();
            do {
                int _id = cursor.getInt(cursor.getColumnIndex(DbHelper._ID));
                String nome = cursor.getString(cursor.getColumnIndex(DbHelper.NOME));
                int idade = cursor.getInt(cursor.getColumnIndex(DbHelper.IDADE));
                listClientes.add(new Cliente(_id, nome, idade));
            } while (cursor.moveToNext());
                cursor.close();
        } else if(cursor == null){
            Log.d("Clientes", "Não existe nenhum registro de "+nomeCliente);
        }
        if (listClientes != null && !listClientes.isEmpty()) {
            for (int i = 0; i < listClientes.size(); i++) {
                Log.d("Clientes", listClientes.get(i).getNome());
            }
        }
        return listClientes;
    }

    public boolean deletar(int id){
        return gw.getDatabase().delete(TABLE_CLIENTES, "_ID = ?", new String[]{ id + "" }) > 0;
    }

}
