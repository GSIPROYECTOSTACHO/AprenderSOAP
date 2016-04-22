package utng.edu.mx.aprendersoap.quiz;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import utng.edu.mx.aprendersoap.Grafica;
import utng.edu.mx.aprendersoap.R;


/**
 * Created by dano on 30/03/16.
 */
public class Resultado extends Activity {

    int puntuacion;
    int total;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_test);

        RatingBar estrellas = (RatingBar)findViewById(R.id.rating);
        TextView mensaje = (TextView)findViewById(R.id.mensaje);
        Button continuar = (Button)findViewById(R.id.continuar);

        Bundle extras = getIntent().getExtras();
        puntuacion = extras.getInt("puntuacion");


        estrellas.setRating(puntuacion);

        if (puntuacion < 3) {
            mensaje.setText(":( Mala suerte, revisa nuevamente la Información del modulo");
        }else if(puntuacion < 6){
            mensaje.setText(":/ No esta mal, Sigue aprendiendo");
        }else if(puntuacion > 6){
            mensaje.setText(":) Felicidades !!! \n Ahora puedes contunuar con: Introducción-Modulo 2");
        }

        try{
            SQLiteDatabase db = new DbHelper(Resultado.this).getReadableDatabase();
            Cursor c = db.rawQuery(" SELECT * FROM puntuacion WHERE id=1 ", null);
            c.moveToFirst();
            total = c.getInt(1);
            //Toast.makeText(this,"Puntuacion :" + puntuacion,Toast.LENGTH_SHORT).show();
            //puntuacion = extras.getInt("puntuacion");
        }catch (Exception e){
            Log.i("EXECPION -", puntuacion + "");

        }

        SQLiteDatabase db = new DbHelper(this).getWritableDatabase();
        //Establecemos los campos-valores a actualizar
        ContentValues valores = new ContentValues();
        valores.put("puntos", puntuacion + total);
        //Actualizamos el registro en la base de datos
        db.update("puntuacion", valores, "id=1", null);


        continuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Resultado.this, Grafica.class);
                Bundle b = new Bundle();
                b.putInt("puntuacion", puntuacion);
               i.putExtras(b);
                startActivity(i);
                finish();
            }
        });
    }
}
