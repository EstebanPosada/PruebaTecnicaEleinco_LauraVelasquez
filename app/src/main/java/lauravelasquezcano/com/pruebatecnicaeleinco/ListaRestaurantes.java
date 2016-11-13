package lauravelasquezcano.com.pruebatecnicaeleinco;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListaRestaurantes extends AppCompatActivity {

    ListView lista,lista2;
    ArrayAdapter adapter,adapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_restaurantes);

        lista=(ListView) findViewById(R.id.lvLista);
        adapter=new MyAdapter(this);
        lista.setAdapter(adapter);

        lista2=(ListView) findViewById(R.id.lvLista2);
        adapter2=new OtherAdapter(this);
        lista2.setAdapter(adapter2);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.iMapa:
                finish();
                Intent intent=new Intent(this, ActivityMapa.class);
                startActivity(intent);
                break;
            case R.id.iRestaurantes:
                break;
            case R.id.iLogout:
                finish();
                break;

        }
        return true;
    }





}
