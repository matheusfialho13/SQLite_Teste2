package com.example.matheusfialho.sqlite_teste;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText txtNome, txtIdade, txtCNome;
    private Button btnCadastrar, btnConsultar, btnCNome;
    private ClienteDAO dao;
    private List<Cliente> clientes;
    private RecyclerView recyclerView;
    private ClienteAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtNome = (EditText) findViewById(R.id.txtNome);
        txtIdade = (EditText) findViewById(R.id.txtIdade);
        txtCNome = (EditText) findViewById(R.id.txtCNome);

        btnCadastrar = (Button)findViewById(R.id.butCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Salvando...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                //pegando os valores
                String nome = txtNome.getText().toString();
                int idade = Integer.parseInt(txtIdade.getText().toString());

                //salvando os dados
                dao = new ClienteDAO(getBaseContext());
                boolean sucesso = dao.salvar(nome, idade);
                if(sucesso) {
                    //limpa os campos
                    txtNome.setText("");
                    txtIdade.setText("");

                    Snackbar.make(view, "Salvou!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    findViewById(R.id.includemain).setVisibility(View.VISIBLE);
                    findViewById(R.id.includecadastro).setVisibility(View.INVISIBLE);
                    findViewById(R.id.add).setVisibility(View.VISIBLE);
                }else{
                    Snackbar.make(view, "Erro ao salvar, consulte os logs!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }

                //String resultado;
                boolean resultado;
                //resultado = ClienteDAO.getInstance(getApplicationContext()).salvar(nome, idade);
                //Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                /*
                boolean resultado;
                resultado = ClienteDAO.salvar(nome, idade);
                Toast.makeText(getApplicationContext(), resultado, Toast.LENGTH_LONG).show();
                */
            }
        });

        btnConsultar = (Button) findViewById(R.id.butConsultar);
        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dao = new ClienteDAO(getBaseContext());

                List<Cliente> listaClientes = new ArrayList<>();
                listaClientes = dao.retornarTodos();

                if (listaClientes != null && !listaClientes.isEmpty()) {
                    for (int i = 0; i < listaClientes.size(); i++) {
                        Log.d("Clientes", listaClientes.get(i).getNome());
                    }
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findViewById(R.id.includemain).setVisibility(View.INVISIBLE);
                findViewById(R.id.includecadastro).setVisibility(View.VISIBLE);
                findViewById(R.id.add).setVisibility(View.INVISIBLE);

            }
        });

        btnCNome = (Button) findViewById(R.id.butCNome);
        btnCNome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("teste", "Clicou!");
                String nome = txtCNome.getText().toString();
                dao = new ClienteDAO(getBaseContext());
                dao.retornaConsulta(nome);


            }
        });

    }


    /*
    private void configurarRecycler() {
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        ClienteDAO dao = new ClienteDAO(this);
        adapter = new ClienteAdapter(dao.retornarTodos());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
