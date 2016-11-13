package lauravelasquezcano.com.pruebatecnicaeleinco;

/**
 * Created by Laura on 12/11/2016.
 */

public class Restaurante {
    private String nombre;
    private String latitud;
    private String longitud;
    private String distancia;

    public Restaurante(){

    }

    public Restaurante(String nombre, String latitud, String longitud, String distancia) {
        this.nombre = nombre;
        this.latitud=latitud;
        this.longitud=longitud;
        this.distancia = distancia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }
}
