package criancas.ucs.com.projetocriancas.activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import criancas.ucs.com.projetocriancas.R;
import criancas.ucs.com.projetocriancas.fragment.EditDadosActivity;
import criancas.ucs.com.projetocriancas.fragment.EmptyActivity;
import criancas.ucs.com.projetocriancas.fragment.IncludeInfoActivity;
import criancas.ucs.com.projetocriancas.fragment.ListagemActivity;
import criancas.ucs.com.projetocriancas.listener.OnItemClickListener;

public class CriancasFragmentActivity extends FragmentActivity {

    FloatingActionButton addCrianca;
    FloatingActionButton addInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criancas_fragment);

        addCrianca = (FloatingActionButton) findViewById(R.id.addCrianca);

        addCrianca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragManager = getFragmentManager();
                final FragmentTransaction fragTransaction = fragManager.beginTransaction();
                EditDadosActivity fragmentDados = new EditDadosActivity();
                fragTransaction.replace(R.id.EmptyActivity, fragmentDados);
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

        FragmentManager fragManager = getFragmentManager();
        final FragmentTransaction fragTransaction = fragManager.beginTransaction();
        ListagemActivity fragmentListagem = new ListagemActivity();
        fragTransaction.replace(R.id.ListagemCriancasActivity, fragmentListagem);

        EmptyActivity fragmentEmpty = new EmptyActivity();
        fragTransaction.replace(R.id.EmptyActivity, fragmentEmpty);
        fragTransaction.show(fragmentEmpty);

        fragTransaction.commit();
    }
}
