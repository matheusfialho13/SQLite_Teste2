package com.example.matheusfialho.sqlite_teste;

import android.content.Context;
import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cliente implements Serializable {
    private int id;
    private String nome;
    private int idade;

    public Cliente (Context context){ }

    public Cliente(int id, String nome, int idade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
    }

    public int getId(){ return this.id; }
    public String getNome(){ return this.nome; }
    public int getIdade(){ return this.idade; }

    @Override
    public boolean equals(Object o){
        return this.id == ((Cliente)o).id;
    }

    @Override
    public int hashCode(){
        return this.id;
    }
}
/*
List<Cliente> clientes = asdkashdkjasdh;

        for (int i =0; i < clientes.size(); i++) {
            System.out.println(clientes.get(i).nome);
            Log.d("Cliente", clientes.get(i).nome);
        }

 */
