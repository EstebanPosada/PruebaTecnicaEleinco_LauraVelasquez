package lauravelasquezcano.com.pruebatecnicaeleinco;

import android.app.DownloadManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laura on 12/11/2016.
 */

public class Adapter extends ArrayAdapter {

    //private String URL_BASE="http://bit.ly/MapasEleinco";
    public String URL_BASE="https://www.dropbox.com/s/livuxsw8wmk6tsr/pruebaEleinco_mapas.txt?dl=0";
    private static final String TAG="Adapter";
    List<Restaurante> items;
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;

    public Adapter(Context context) {
        super(context, 0);

        requestQueue= Volley.newRequestQueue(context);

        jsArrayRequest=new JsonObjectRequest(Request.Method.GET, URL_BASE, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.e(TAG,response.toString());
                items = parseJson(response);
                JSONObject prueba= response;
                notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"Error respuesta en JSON: "+error.getMessage());
            }
        });
        requestQueue.add(jsArrayRequest);
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
        return listItemView;
    }

    public List<Restaurante> parseJson (JSONObject jsonObject){
        List<Restaurante> restaurantes=new ArrayList<>();
        JSONArray jsonArray=null;

        return restaurantes;
    }
}
