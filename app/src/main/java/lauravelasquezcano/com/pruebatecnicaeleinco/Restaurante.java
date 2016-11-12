package lauravelasquezcano.com.pruebatecnicaeleinco;

/**
 * Created by Laura on 12/11/2016.
 */

public class Restaurante {
    private String nombre;
    private String distancia;

    public Restaurante(){

    }

    public Restaurante(String nombre, String distancia) {
        this.nombre = nombre;
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
}
