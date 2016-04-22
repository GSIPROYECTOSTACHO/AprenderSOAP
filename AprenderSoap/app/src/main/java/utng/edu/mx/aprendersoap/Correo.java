package utng.edu.mx.aprendersoap;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import utng.edu.mx.aprendersoap.quiz.DbHelper;

/**
 * Created by dano on 2/04/16.
 */
public class Correo extends AppCompatActivity {
    ImageButton btnEnviar;
    EditText txvPara;
    EditText txvTema;
    EditText txvMensaje;
    int puntuacion=0;
    Button btnPrinciapl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.correo_layout);


        btnEnviar=(ImageButton)findViewById(R.id.btn_enviar);
        txvPara=(EditText)findViewById(R.id.edt_para);
        txvTema=(EditText)findViewById(R.id.edt_tema);
        txvMensaje=(EditText)findViewById(R.id.edt_mensaje);



        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String para = txvPara.getText().toString();
                String tema = txvTema.getText().toString();
                String mensaje = txvMensaje.getText().toString();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{para});
                email.putExtra(Intent.EXTRA_SUBJECT, tema);
                email.putExtra(Intent.EXTRA_TEXT, mensaje);

                email.setType("message/rfc822");

                startActivity(Intent.createChooser(email, "Elige el email de tu preferencia :"));

            }
        });

        try{
            SQLiteDatabase db = new DbHelper(Correo.this).getReadableDatabase();
            Cursor c = db.rawQuery(" SELECT * FROM puntuacion WHERE id=1 ", null);
            c.moveToFirst();
            puntuacion = c.getInt(1);
            // Toast.makeText(this,"Puntuacion :" + puntuacion,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.i("EXECPION -", puntuacion + "");

        }

        String puntaje;
        puntaje = Integer.toString(puntuacion/42*10);

        String puntajeModulo1;
        puntajeModulo1=Integer.toString(42-puntuacion);

        String puntajeModulo2;
        puntajeModulo2=Integer.toString(42-puntuacion);

        String puntajeModulo3;
        puntajeModulo3=Integer.toString(42-puntuacion);

        String puntosTotales;
        puntosTotales=Integer.toString(puntuacion);


        txvTema.setText("Calificacion del curso");
        txvMensaje.setText("Tu calificacion final es de:" + puntaje+
                "\n"+"Puntuacion Modulo 1:"+puntajeModulo1+
                "\n"+"Puntuacion Modulo 2:"+puntajeModulo2+
                "\n"+"Puntuacion Modulo 3:"+puntajeModulo3+
                "\n"+"Putaje Total:"+puntosTotales);





    }

    protected void onPause(){
        super.onPause();
        finish(); //termina la actividad
    }
}
