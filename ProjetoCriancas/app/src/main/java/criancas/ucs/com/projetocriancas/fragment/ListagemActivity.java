package criancas.ucs.com.projetocriancas.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import criancas.ucs.com.projetocriancas.R;
import criancas.ucs.com.projetocriancas.listener.OnItemClickListener;

public class ListagemActivity extends android.app.Fragment {

    OnItemClickListener privateCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_listagem, container, false);
    }

    /**
     @Override
     public void onAttach(Activity activity) {
     super.onAttach(activity);
     privateCallback = (OnItemClickListener) activity;
     }

    @Override
    public void onStart() {
        super.onStart();
        if (getFragmentManager().findFragmentById(R.id.listagem) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        privateCallback.onItemClicked(position);
        getListView().setItemChecked(position, true);
    }
    **/
}
