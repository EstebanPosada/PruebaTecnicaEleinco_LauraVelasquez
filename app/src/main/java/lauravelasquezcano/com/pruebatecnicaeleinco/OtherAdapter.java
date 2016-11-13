package lauravelasquezcano.com.pruebatecnicaeleinco;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Laura on 12/11/2016.
 */

public class OtherAdapter extends ArrayAdapter {

    private String URL_BASE="http://servidorexterno.site90.com/datos";
    private String URL_JSON="/social_media.json";
    private static final String TAG="OtherAdapter";

    List<Post> items;

    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;

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
            listItemView=layoutInflater.inflate(R.layout.formaotralista,parent,false);

        }

        Post item=items.get(position);

        TextView textoTitulo=(TextView) listItemView.findViewById(R.id.textoTitulo);
        TextView textoDescripcion=(TextView) listItemView.findViewById(R.id.textoDescripcion);
        final ImageView imagenPost=(ImageView) listItemView.findViewById(R.id.imagenPost);

        textoTitulo.setText(item.getTitulo());
        textoDescripcion.setText(item.getDescripcion());

        ImageRequest request=new ImageRequest(URL_BASE + item.getImagen(), new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap response) {
                imagenPost.setImageBitmap(response);
            }
        }, 0, 0, null, null, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
              imagenPost.setImageResource(R.drawable.logoeleinco);
                Log.d(TAG,"Error en respuesta Bitmap: "+error.getMessage() );
            }
        });
        requestQueue.add(request);
        return listItemView;

    }

    public List<Post> parseJson(JSONObject jsonObject){
        List<Post> posts=new ArrayList();
        JSONArray jsonArray=null;

        try {
            jsonArray=jsonObject.getJSONArray("items");
            for (int i=0;i<jsonArray.length();i++){
                try {
                    JSONObject objeto=jsonArray.getJSONObject(i);

                    Post post=new Post(objeto.getString("titulo"),objeto.getString("descripcion"),objeto.getString("imagen"));

                    posts.add(post);
                }catch (JSONException e){
                    Log.e(TAG,"Error de Parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return posts;
    }


    public OtherAdapter(Context context) {
        super(context, 0);

        requestQueue= Volley.newRequestQueue(context);

        jsArrayRequest=new JsonObjectRequest(Request.Method.GET, URL_BASE + URL_JSON, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                items = parseJson(response);
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
}
