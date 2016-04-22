package utng.edu.mx.aprendersoap;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.Highlight;
import com.github.mikephil.charting.utils.PercentFormatter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import utng.edu.mx.aprendersoap.quiz.DbHelper;


/**
 * Created by dano on 16/04/16.
 */
public class Grafica extends AppCompatActivity {

    private View viewToBeCaptured;
    Bitmap b;
    private RelativeLayout graficaLayout;
    private PieChart grafica;
    private float[] yData;
    private String[] xData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grafica_layout);

        int puntuacion=0;

        try{
            SQLiteDatabase db = new DbHelper(Grafica.this).getReadableDatabase();
            Cursor c = db.rawQuery(" SELECT * FROM puntuacion WHERE id=1 ", null);
            c.moveToFirst();
            puntuacion = c.getInt(1);
            // Toast.makeText(this,"Puntuacion :" + puntuacion,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Log.i("EXECPION -", puntuacion + "");

        }

        yData = new float[]{puntuacion, 240 - puntuacion};
        xData = new String[]{"Cursado", "Restante"};



        graficaLayout = (RelativeLayout)findViewById(R.id.grafica_layout);
        grafica = new PieChart(this);

        // agregar la Grafica al layout
        graficaLayout.addView(grafica);
        graficaLayout.setBackgroundColor(Color.LTGRAY);

        // configurar la Grafica
        grafica.setUsePercentValues(true);
        grafica.setDescription("AVANCE ACTUAL DEL CURSO");
        grafica.setDescriptionTextSize(15f);
        grafica.setDescriptionPosition(370f,60f);

        //
        grafica.setDrawHoleEnabled(true);
        grafica.setHoleRadius(50);
        grafica.setTransparentCircleRadius(1);
        grafica.setHoleColorTransparent(true);

        //
        grafica.setRotationAngle(0);
        grafica.setRotationEnabled(true);

        grafica.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (e==null){
                    return;
                }
                // Toast.makeText(Grafica.this,xData[e.getXIndex()] + " = " + e.getVal() + " % ",
                //       Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        // Agregar datos
        addData();

        // customizando leyendas
        Legend l = grafica.getLegend();
        l.setPosition(Legend.LegendPosition.BELOW_CHART_CENTER);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);

        //-------------------------------------------------------------
        viewToBeCaptured = this.findViewById(R.id.grafica_layout);
        //---------------------------------------------------------------
        viewToBeCaptured.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewToBeCaptured.setDrawingCacheEnabled(true);
                b = viewToBeCaptured.getDrawingCache();
                saveToInternalSorage(b);


                Intent itSend = new Intent(Intent.ACTION_SEND);
                //vamos a enviar texto plano a menos que el checkbox esté marcado
                itSend.setType("plain/text");
                //colocamos los datos para el envío
                itSend.putExtra(Intent.EXTRA_EMAIL, new String[]{"josemanuel-enriquez@hotmail.com"});
                itSend.putExtra(Intent.EXTRA_SUBJECT,"Asunto");
                itSend.putExtra(Intent.EXTRA_TEXT, "Cuerpo del mensaje");
                itSend.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/Pictures/avancesoap.png"));
                //itSend.putExtra(Intent.EXTRA_STREAM, "file:///sdcard/Pictures/Avance_AprendeRubyOnRails.png");
                itSend.setType("image/png");
                //iniciamos la actividad
                startActivity(itSend);


            }
        });

    }

    /**
     * Implementacion de los datos dentro de la grafica
     */
    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i=0; i<yData.length; i++){
            yVals1.add(new Entry(yData[i],i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i=0; i < xData.length;i++){
            xVals.add(xData[i]);
        }

        //
        PieDataSet dataSet = new PieDataSet(yVals1,"");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // agregar colores
        ArrayList<Integer> colores = new ArrayList<Integer>();

        for (int c: ColorTemplate.LIBERTY_COLORS){
            colores.add(c);
        }

        colores.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colores);

        // instanciar el objeto data
        PieData data = new  PieData(xVals,dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        grafica.setData(data);
        grafica.highlightValues(null);
        grafica.invalidate();
    }

    /**
     *
     * @param bitmapImage
     * @return
     */
    private String saveToInternalSorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("images", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"avancesoap.png");

        FileOutputStream fos = null;
        try {

            fos = new FileOutputStream(mypath);

            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }

    /**
     * grabar una imagen en el directorio externo
     */
   /* private Void saveToExternalStorage(){
        File sd = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // El archivo que contendrá la captura
        File f = new File(sd, "avance.png");

        try {
            if (sd.canWrite()) {
                f.createNewFile();
                OutputStream os = new FileOutputStream(f);
                b.compress(Bitmap.CompressFormat.PNG, 90, os);
                os.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        viewToBeCaptured.setDrawingCacheEnabled(false);

    }*/

}
