package lauravelasquezcano.com.pruebatecnicaeleinco;

import android.app.DownloadManager;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laura on 12/11/2016.
 */

public class MyAdapter extends ArrayAdapter {


    static List<Restaurante> items;


    public MyAdapter(Context context) {
        super(context, 0);
        items = llenar();
    }

    @Override
    public int getCount() {
        if(items!=null){
            return items.size();
        }else{
            return 0;
        }
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());

        View listItemView=convertView;

        if (null==convertView){
            listItemView=layoutInflater.inflate(R.layout.forma_lista,parent,false);
        }

        Restaurante item= items.get(position);

        TextView textoNombre=(TextView) listItemView.findViewById(R.id.tvRestaurante);
        TextView textoLatitud=(TextView) listItemView.findViewById(R.id.tvLatitud);
        TextView textoLongitud=(TextView) listItemView.findViewById(R.id.tvLongitud);
        TextView textoDistancia=(TextView) listItemView.findViewById(R.id.tvDistancia);

        textoNombre.setText(item.getNombre());
        textoLatitud.setText(item.getLatitud());
        textoLongitud.setText(item.getLongitud());
        textoDistancia.setText(item.getDistancia());


        return listItemView;
    }

    public List<Restaurante> llenar(){
        List<Restaurante> thelist = new ArrayList<>();
        double mylat = 6.2319399, mylong = -75.5998279, lat[] = {6.248095, 6.235702, 6.200377, 6.205283}, lon[]= {-75.561143, -75.579790, -75.554084, -75.590411};
        String rest[] = {"Coma Rico 1", "Coma Rico 2", "Coma Rico 3", "Coma en paz"};
        Location coordenadas = new Location("a");
        Location mylocation= new Location("b");
        mylocation.setLatitude(mylat);
        mylocation.setLongitude(mylong);
        float distancia;

        for (int i=0; i<lat.length;i++){
            //Location.distanceBetween(mylat,mylong,lat[i],lon[i], float[] distancia);
            coordenadas.setLatitude(lat[i]);
            coordenadas.setLongitude(lon[i]);
            distancia=mylocation.distanceTo(coordenadas);
            Restaurante restaurante=new Restaurante(String.valueOf(rest[i]), String.valueOf(lat[i]),String.valueOf(lon[i]), String.valueOf(distancia));
            thelist.add(restaurante);
        }
        return thelist;
    }

}
