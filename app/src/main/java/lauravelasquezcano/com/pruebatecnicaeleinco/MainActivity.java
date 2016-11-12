package lauravelasquezcano.com.pruebatecnicaeleinco;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String correo="laura.velasquez.cano@gmail.com";
    String contrasena= "holita123";

    Button botonLogin;
    EditText etcontrasena;
    EditText etcorreo;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonLogin=(Button)findViewById(R.id.btnLogin);
        etcorreo=(EditText) findViewById(R.id.etCorreo);
        etcontrasena=(EditText)findViewById(R.id.etContraseña);

        sharedPreferences=getPreferences(MODE_PRIVATE);
        editor=sharedPreferences.edit();

        cargarPreferencias();
    }

    public void logear(View view){
        String correo_capturado;
        String contrasena_capturada;

        correo_capturado=etcorreo.getText().toString();
        contrasena_capturada=etcontrasena.getText().toString();

        if (correo_capturado.equals(correo) && contrasena_capturada.equals(contrasena)){
            guardarPreferencias();

            Intent intent = new Intent(this, ActivityMapa.class);
            startActivity(intent);
            Toast.makeText(this, "Iniciando sesión...", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, getResources().getString(R.string.error_login), Toast.LENGTH_SHORT).show();
        }

    }

    public void guardarPreferencias (){
        editor.putString("correo",correo);
        editor.putString("contrasena",contrasena);
        editor.commit();
    }

    public void cargarPreferencias (){
        etcorreo.setText(sharedPreferences.getString("correo", ""));
        etcontrasena.setText(sharedPreferences.getString("contrasena",""));
    }

}
