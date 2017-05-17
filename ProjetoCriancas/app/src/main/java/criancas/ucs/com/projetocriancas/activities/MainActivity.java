package criancas.ucs.com.projetocriancas.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import criancas.ucs.com.projetocriancas.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                    Log.e("Falha ao aguardar!", e.getMessage());
                }
                finish();
                Intent viewCriancas = new Intent(getApplicationContext(), CriancasFragmentActivity.class);
                startActivity(viewCriancas);
            }
        }, 3000);

    }
}
