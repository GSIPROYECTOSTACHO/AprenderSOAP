package utng.edu.mx.aprendersoap.grafica;

/**
 * Created by Manuel on 21/04/2016.
 */

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

import utng.edu.mx.aprendersoap.quiz.DbHelper;

public class GraficaModTres extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int puntuacion=0;

        try{
            SQLiteDatabase db = new DbHelper(GraficaModTres.this).getReadableDatabase();
            Cursor c = db.rawQuery(" SELECT * FROM puntuacion WHERE id=1 ", null);
            c.moveToFirst();
            puntuacion = c.getInt(1);
            //Toast.makeText(this,"Puntuacion :" + puntuacion,Toast.LENGTH_SHORT).show();
            //puntuacion = extras.getInt("puntuacion");
        }catch (Exception e){
            Log.i("EXECPION -", puntuacion + "");

        }


    ArrayList<BarEntry> entradas = new ArrayList<>();
        if (puntuacion>=33) {
            entradas.add(new BarEntry(5f, 0));
        }else if (puntuacion<=31){
            entradas.add(new BarEntry(0f, 0));
        }
        if (puntuacion>=34) {
            entradas.add(new BarEntry(5f, 1));
        }else if(puntuacion<=32){
            entradas.add(new BarEntry(0f, 1));
        }
        if(puntuacion>35) {
            entradas.add(new BarEntry(5f, 2));
        }else if(puntuacion<=33){
            entradas.add(new BarEntry(0f, 2));
        }
        if (puntuacion>=36) {
            entradas.add(new BarEntry(5f, 3));
        }else if (puntuacion<=34){
            entradas.add(new BarEntry(0f, 3));
        }
        if (puntuacion>=37) {
            entradas.add(new BarEntry(5f, 4));
        }else if (puntuacion<=35){
            entradas.add(new BarEntry(0f, 4));
        }
        if (puntuacion>=38) {
            entradas.add(new BarEntry(5f, 5));
        }else if (puntuacion<=36){
            entradas.add(new BarEntry(0,5));
        }
        if (puntuacion>=39) {
            entradas.add(new BarEntry(5, 6));
        }else if (puntuacion<=37){
            entradas.add(new BarEntry(0,6));
        }
        if (puntuacion>=40) {
            entradas.add(new BarEntry(5, 7));
        }else if (puntuacion<=38){
            entradas.add(new BarEntry(0,7));
        }
        if (puntuacion>=41) {
            entradas.add(new BarEntry(5, 8));
        }else if (puntuacion<=39){
            entradas.add(new BarEntry(0, 8));
        }
        if (puntuacion>=42){
            entradas.add(new BarEntry(5,9));
        }else if (puntuacion<=40){
            entradas.add(new BarEntry(0,9));
        }

    //Creamos el conjunto de datos a partir de las entradas

    BarDataSet dataset = new BarDataSet(entradas, "# de Preguntas");

    //Etiquetas para el eje X

    ArrayList<String> etiquetas = new ArrayList<String>();
    etiquetas.add("Pregunta 1");
    etiquetas.add("Pregunta 2");
    etiquetas.add("Pregunta 3");
    etiquetas.add("Pregunta 4");
    etiquetas.add("Pregunta 5");
    etiquetas.add("Pregunta 6");
        etiquetas.add("Pregunta 7");
        etiquetas.add("Pregunta 8");
        etiquetas.add("Pregunta 9");
        etiquetas.add("Pregunta 10");

    //Aplicamos una plantillas de colores al conjunto de datos
    dataset.setColors(ColorTemplate.COLORFUL_COLORS);

    //Definimos la gráfica

    BarChart grafica = new BarChart(getApplicationContext());
    setContentView(grafica);

    //Incluimos los datos y etiquetas en la gráfica

    BarData datos = new BarData(etiquetas, dataset);
    grafica.setData(datos);

    //Añadimos una descripción a la gráfica
    grafica.setDescription("# preguntas contestadas");

    //Aplicamos una animación al eje Y
    grafica.animateY(5000);

    //Incluímos una línea límite
    LimitLine linea = new LimitLine(22f);
    YAxis ejeY = grafica.getAxisLeft();
    ejeY.addLimitLine(linea);



}


}
