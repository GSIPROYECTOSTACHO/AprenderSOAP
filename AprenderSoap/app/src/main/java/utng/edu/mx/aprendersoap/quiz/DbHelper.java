package utng.edu.mx.aprendersoap.quiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dano on 29/03/16.
 */
public class DbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="dbquiz.sqlite";
    private static final String DATABASE_TABLE="quiz";
    private static final String ID="id";
    private static final String PREGUNTA="pregunta";
    private static final String OPCION_A="opcion_a";
    private static final String OPCION_B="opcion_b";
    private static final String OPCION_C="opcion_c";
    private static final String RESPUESTA="respuesta";

    private static final String PUNTUACION="puntuacion";
    private static final String PUNTOS="puntos";

    private SQLiteDatabase dbase;
    Context mContext;






    public DbHelper(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        dbase=db;
        String sql= "CREATE TABLE IF NOT EXISTS " + DATABASE_TABLE + " (  "
                + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PREGUNTA
                + " TEXT, " + RESPUESTA + " TEXT, " + OPCION_A + " TEXT, "
                + OPCION_B + " TEXT, " + OPCION_C + " TEXT) ";

        String sql2= "CREATE TABLE IF NOT EXISTS " + PUNTUACION + " ( "
               + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PUNTOS
               + " INTEGER) ";

        db.execSQL(sql);
        db.execSQL(sql2);
        InputStream is = null;
        try {
            is = mContext.getAssets().open("quiz.sql");
            if (is != null) {
                db.beginTransaction();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = reader.readLine();
                while (!TextUtils.isEmpty(line)) {
                    db.execSQL(line);
                    line = reader.readLine();
                }
                db.setTransactionSuccessful();
            }
        } catch (Exception ex) {
            Log.i("error base de datos","Error al cargar base de datos" + ex.toString());
        } finally {
            db.endTransaction();
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    // Muestra log
                }
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + DATABASE_TABLE);
        onCreate(db);
    }

    public void agregarPreguntas(Question pregunta){

    }


    public List<Question> llenarCursor(){
        List<Question> questions = new ArrayList<Question>();
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;
        dbase = this.getReadableDatabase();
        Cursor cursor= dbase.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Question question = new Question();
                question.setId(cursor.getInt(0));
                question.setPregunta(cursor.getString(1));
                question.setRespuesta(cursor.getString(2));
                question.setOpcionA(cursor.getString(3));
                question.setOpcionB(cursor.getString(4));
                question.setOpcionC(cursor.getString(5));

                questions.add(question);
            }while (cursor.moveToNext());

        }
        return questions;
    }

    public int rowCount(){
        int row=0;
        String selectQuery = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        row = cursor.getCount();
        return  row;
    }
}
