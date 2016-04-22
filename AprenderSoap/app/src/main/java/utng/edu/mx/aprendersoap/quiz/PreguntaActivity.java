package utng.edu.mx.aprendersoap.quiz;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.TimeUnit;

import utng.edu.mx.aprendersoap.R;


/**
 * Created by dano on 29/03/16.
 */

public class PreguntaActivity extends Activity implements RadioGroup.OnCheckedChangeListener,View.OnClickListener{
    List<Question> questions;
    int puntuacion;
    int idpregunta;
    int atajo;
    int preguntaInicial;
    int ultimaPregunta=8;
    Question qActual;
    CounterClass timer;

    TextView txvPregunta, txvTiempo, txvPuntuacion ;
    Button botonSiguiente;
    RadioButton rbtnOpcionA, rbtnOpcionB, rbtnOpcionC;
    RadioGroup radioGroup;
    String cadenaDeRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);

        Bundle extras = getIntent().getExtras();
        preguntaInicial =  extras.getInt("preguntaInicial");
        atajo = extras.getInt("atajo");

        switch (atajo){
            case 1:
                ultimaPregunta = 10; // depende del numero de preguntas de la seccion
                break;
            case 2:
                ultimaPregunta = 24;
                break;
            case 3:
                ultimaPregunta = 35;
                break;
            default:

                break;

        }

        DbHelper db= new DbHelper(this);

        questions = db.llenarCursor();
        qActual = questions.get(idpregunta+preguntaInicial);

        txvPregunta = (TextView)findViewById(R.id.txv_pregunta);
        txvPuntuacion = (TextView)findViewById(R.id.txv_puntuacion);
        txvTiempo = (TextView)findViewById(R.id.txv_tiempo);

        rbtnOpcionA = (RadioButton)findViewById(R.id.rbtn_quiz1);
        rbtnOpcionB = (RadioButton)findViewById(R.id.rbtn_quiz2);
        rbtnOpcionC = (RadioButton)findViewById(R.id.rbtn_quiz3);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup);
        botonSiguiente = (Button)findViewById(R.id.boton_sigueinte);

        radioGroup.setOnCheckedChangeListener(this);
        botonSiguiente.setOnClickListener(this);

        txvPregunta.setText("00:02:00");
        CounterClass timer = new CounterClass(120000, 1000);
        timer.start();
        Toast.makeText(PreguntaActivity.this, " " +preguntaInicial, Toast.LENGTH_SHORT).show();
        insertarPreguntaEnVista();

    }


   @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.rbtn_quiz1:
                cadenaDeRespuesta = rbtnOpcionA.getText().toString();
                //Toast.makeText(PreguntaActivity.this, cadenaDeRespuesta, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbtn_quiz2:
                cadenaDeRespuesta = rbtnOpcionB.getText().toString();
                //Toast.makeText(PreguntaActivity.this, cadenaDeRespuesta, Toast.LENGTH_SHORT).show();
                break;
            case R.id.rbtn_quiz3:
                cadenaDeRespuesta = rbtnOpcionC.getText().toString();
                //Toast.makeText(PreguntaActivity.this, cadenaDeRespuesta, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onClick(View v) {
        obtenerRespuesta(cadenaDeRespuesta);
    }


    // ********  iNSERTAR LAS PREGUNTAS DESDE DEL OBJETO EN LA POSICION ACTUAL DEL CURSOR ************
    private void insertarPreguntaEnVista(){
        txvPregunta.setText(qActual.getPregunta());
        rbtnOpcionA.setText(qActual.getOpcionA());
        rbtnOpcionB.setText(qActual.getOpcionB());
        rbtnOpcionC.setText(qActual.getOpcionC());
        idpregunta++;
    }

    /**
     * COMPROBAR LAS RESPUESTAS CORRECTAS DEPENDIENDO DEL RADIO BUTON SELECCIONADO
     * @param cadenaRespuesta
     */
    public void obtenerRespuesta(String cadenaRespuesta){
        if (qActual.getRespuesta().equals(cadenaRespuesta)){
            puntuacion++;
            radioGroup.clearCheck();
            txvPuntuacion.setText("Puntuación: " + puntuacion);
            Toast.makeText(PreguntaActivity.this, " " +idpregunta, Toast.LENGTH_SHORT).show();

        }else{
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
            radioGroup.clearCheck();
            // agregar numero de errores o numero de intentos
        }
        if (idpregunta < ultimaPregunta)//cabiar condicion para adaptaro al numero de preguntas y modulos
        {
            qActual = questions.get(idpregunta+preguntaInicial);
            insertarPreguntaEnVista();
        }
        else{
            Intent intent = new Intent(getApplicationContext(),Resultado.class);//Tu Grafica de resultados
            Bundle b = new Bundle();
            b.putInt("puntuacion", puntuacion);
            intent.putExtras(b);
            startActivity(intent);
            finish();
        }

    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    @SuppressLint("NewApi")
    public class CounterClass extends CountDownTimer {
        public CounterClass(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onFinish() {

        }

        @Override
        public void onTick(long millisUntilFinished) {
            // TODO Auto-generated method stub
            long millis = millisUntilFinished;
            String hms = String.format(
                    "%02d:%02d:%02d",
                    TimeUnit.MILLISECONDS.toHours(millis),
                    TimeUnit.MILLISECONDS.toMinutes(millis)
                            - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS
                            .toHours(millis)),
                    TimeUnit.MILLISECONDS.toSeconds(millis)
                            - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS
                            .toMinutes(millis)));
            System.out.println(hms);
            txvTiempo.setText(hms);
        }
    }
}
