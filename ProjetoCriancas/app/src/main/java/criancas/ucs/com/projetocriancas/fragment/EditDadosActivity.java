package criancas.ucs.com.projetocriancas.fragment;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import criancas.ucs.com.projetocriancas.R;
import criancas.ucs.com.projetocriancas.db.DBAdapter;

public class EditDadosActivity extends android.app.Fragment {

    private Button btnSalvar;
    private EditText txtNome;
    private EditText txtSexo;
    private EditText txtDtaNascimento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_edit_dados, container, false);

        btnSalvar = (Button) view.findViewById(R.id.btnSalvar);
        txtNome = (EditText) view.findViewById(R.id.nome);
        txtSexo = (EditText) view.findViewById(R.id.sexo);
        txtDtaNascimento = (EditText) view.findViewById(R.id.dtaNascimento);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nome = txtNome.getText().toString();
                String sexo = txtSexo.getText().toString();
                String dtaNascimento = txtDtaNascimento.getText().toString();

                if(nome.isEmpty()) {
                    Toast.makeText(getActivity(), "Informe um nome", Toast.LENGTH_LONG).show();
                    return;
                }

                if(sexo.isEmpty()) {
                    Toast.makeText(getActivity(), "Informe o sexo", Toast.LENGTH_LONG).show();
                    return;
                }

                if(dtaNascimento.isEmpty()) {
                    Toast.makeText(getActivity(), "Informe a data de nascimento", Toast.LENGTH_LONG).show();
                    return;
                }

                new SalvarCrianca().execute(nome, sexo, dtaNascimento, 0,0.0,0);
                Toast.makeText(getActivity(), "Criança salva!",Toast.LENGTH_LONG).show();
                getFragmentManager().popBackStack();
            }
        });

        return view;
    }

    private class SalvarCrianca extends AsyncTask<Object, Object, Void> {
        DBAdapter conexaoDB = new DBAdapter(getActivity());
        @Override
        protected Void doInBackground(Object... params){
            conexaoDB.open(); //abre a base de dados
            conexaoDB.insereRegistro(params[0].toString(), params[1].toString(), params[2].toString(), Integer.valueOf(params[3].toString()), Double.valueOf(params[4].toString()), Integer.valueOf(params[5].toString()));
            return null;
        }
        // usa o cursor retornado pelo doInBackground
        @Override
        protected void onPostExecute(Void result){
            conexaoDB.close();
        }
    }
}
