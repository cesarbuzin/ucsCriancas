package criancas.ucs.com.projetocriancas.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import criancas.ucs.com.projetocriancas.R;
import criancas.ucs.com.projetocriancas.db.DBAdapter;
import criancas.ucs.com.projetocriancas.fragment.EditDadosActivity;
import criancas.ucs.com.projetocriancas.fragment.EmptyActivity;
import criancas.ucs.com.projetocriancas.fragment.GraficoActivity;
import criancas.ucs.com.projetocriancas.fragment.IncludeInfoActivity;
import criancas.ucs.com.projetocriancas.fragment.ListagemActivity;
import criancas.ucs.com.projetocriancas.listener.OnItemClickListener;

public class CriancasFragmentActivity extends FragmentActivity {

    private ListView criancasListView;
    private FloatingActionButton addCrianca;
    private FloatingActionButton addInfo;


    private CursorAdapter criancasAdapter; // Adaptador para a ListView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criancas_fragment);

        FragmentManager fragManager = getFragmentManager();
        final FragmentTransaction fragTransaction = fragManager.beginTransaction();
        ListagemActivity fragmentListagem = new ListagemActivity();
        fragTransaction.replace(R.id.ListagemCriancasActivity, fragmentListagem);

        fragTransaction.commit();

        criancasListView = (ListView) findViewById(R.id.listagemCriancas);

        String[] origem = new String[]{"nome","dtaNascimento"};
        int[] destino = new int[] { R.id.txtNome, R.id.txtDtaNascimento };
        int flags = 0;
        criancasAdapter = new SimpleCursorAdapter(CriancasFragmentActivity.this,R.layout.activity_view_criancas,null,origem,destino,flags);
        criancasListView.setAdapter(criancasAdapter);

        criancasListView.setOnItemClickListener(viewCriancasListener);

        addCrianca = (FloatingActionButton) findViewById(R.id.addCrianca);

        addCrianca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragManager = getFragmentManager();
                final FragmentTransaction fragTransaction = fragManager.beginTransaction();
                EditDadosActivity fragmentDados = new EditDadosActivity();
                if(findViewById(R.id.EmptyActivity) != null)
                    fragTransaction.replace(R.id.EmptyActivity, fragmentDados);
                else {
                    fragTransaction.replace(R.id.ListagemCriancasActivity, fragmentDados);
                    fragTransaction.addToBackStack(null);
                }
                fragTransaction.commit();
            }
        });



        /**
        addInfo = (FloatingActionButton) findViewById(R.id.addInfo);

        addInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragManager = getFragmentManager();
                final FragmentTransaction fragTransaction = fragManager.beginTransaction();
                IncludeInfoActivity fragmentInfo = new IncludeInfoActivity();
                fragTransaction.replace(R.id.EmptyActivity, fragmentInfo);
                fragTransaction.commit();
            }
        });
        **/

    }

    AdapterView.OnItemClickListener viewCriancasListener = new AdapterView.OnItemClickListener(){
        public void onItemClick(AdapterView<?> parent, View view, int posicao,long id){
            FragmentManager fragManager = getFragmentManager();
            final FragmentTransaction fragTransaction = fragManager.beginTransaction();
            GraficoActivity graficoActivity = new GraficoActivity();
            fragTransaction.replace(R.id.EmptyActivity, graficoActivity);
            fragTransaction.show(graficoActivity);
            fragTransaction.commit();
        }
    };

    @Override
    protected void onResume() {
        super.onResume();
        new ObtemCriancas().execute();
    }

    ////////////////////////////////////////////////////////////
    // Quando precisamos dos resultados de uma operação do BD na thread da
    // interface gráfica, vamos usar AsyncTask para efetuar a operação em
    // uma thread e receber os resultados na thread da interface gráfica
    private class ObtemCriancas extends AsyncTask<Object, Object, Cursor> {
        DBAdapter conexaoDB = new DBAdapter(CriancasFragmentActivity.this);
        @Override
        protected Cursor doInBackground(Object... params){
            conexaoDB.open(); //abre a base de dados
            return conexaoDB.getTodosRegistros(); //retorna todos os livros
        }
        // usa o cursor retornado pelo doInBackground
        @Override
        protected void onPostExecute(Cursor result){
            criancasAdapter.changeCursor(result); //altera o cursor para um novo cursor
            conexaoDB.close();
        }
    }
}
