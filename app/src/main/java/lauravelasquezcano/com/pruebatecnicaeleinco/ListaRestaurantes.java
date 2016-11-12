package lauravelasquezcano.com.pruebatecnicaeleinco;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

    ListView lista;
    TextView pruebita;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_restaurantes);

        lista=(ListView) findViewById(R.id.lvLista);
        pruebita=(TextView) findViewById(R.id.tvPruebita) ;

        //adapter=new Adapter(this);
        //lista.setAdapter(adapter);

        new JSONTask().execute("https://www.dropbox.com/s/livuxsw8wmk6tsr/pruebaEleinco_mapas.txt?dl=0");

    }

    public class JSONTask extends AsyncTask<String ,String ,String>{

        @Override
        protected String doInBackground(String... strings) {

            HttpURLConnection connection=null;
            BufferedReader reader=null;

            try {
                URL url=new URL(strings[0]);
                connection=(HttpURLConnection) url.openConnection();
                connection.connect();

                InputStream stream=connection.getInputStream();
                reader=new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer=new StringBuffer();

                String line="";
                while ((line=reader.readLine())!=null){
                    buffer.append(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if (connection!=null){
                    connection.disconnect();
                }
                connection.disconnect();
                try {
                    if (reader!=null){
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            pruebita.setText(s);

        }
    }
}
