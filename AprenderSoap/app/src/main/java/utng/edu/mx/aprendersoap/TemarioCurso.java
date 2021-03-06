package utng.edu.mx.aprendersoap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;

import utng.edu.mx.aprendersoap.quiz.DbHelper;

public class TemarioCurso extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    int nivel=0;




    public static final int segundos = 4;
    public static final int milisegundos=segundos*1000;
    public static final int delay=2;

    private ImageButton btnModulo1;
    private ImageButton btnModulo2;
    private ImageButton btnModulo3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temario_curso);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        btnModulo1 = (ImageButton)findViewById(R.id.imv_modulo1);
        btnModulo1.setOnClickListener(this);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotacion);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        btnModulo2 =(ImageButton)findViewById(R.id.imv_modulo2);
        btnModulo2.setOnClickListener(this);
        btnModulo3=(ImageButton)findViewById(R.id.imv_modulo3);
        btnModulo3.setOnClickListener(this);







        btnModulo2.startAnimation(an);
        btnModulo1.startAnimation(an);
        btnModulo3.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {



            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        // ************** EJECUTAR EL BUNDLE QUE TRAE LA PUNTUACION DESDE LA BASE DE DATOS *************
        //Bundle extras = getIntent().getExtras();
        int puntuacion=0;

        try{
            SQLiteDatabase db = new DbHelper(TemarioCurso.this).getReadableDatabase();
            Cursor c = db.rawQuery(" SELECT * FROM puntuacion WHERE id=1 ", null);
            c.moveToFirst();
            puntuacion = c.getInt(1);
            //Toast.makeText(this,"Puntuacion :" + puntuacion,Toast.LENGTH_SHORT).show();
            //puntuacion = extras.getInt("puntuacion");
        }catch (Exception e){
            Log.i("EXECPION -", puntuacion + "");

        }


        if (puntuacion>=7) /* 30 AGREGA LA PUNTUACIÓN DESEADA PARA DESBLOQUEAR PRINCIPIANTES  */ {
            btnModulo2.setEnabled(true);
        }else{
            btnModulo2.setEnabled(false);
            btnModulo2.setBackgroundResource(R.drawable.modulo2);
        }
        if (puntuacion>=15) /* 30 AGREGA LA PUNTUACIÓN DESEADA PARA DESBLOQUEAR PRINCIPIANTES  */ {
            btnModulo3.setEnabled(true);
        }else{
            btnModulo3.setEnabled(false);
            btnModulo3.setBackgroundResource(R.drawable.modulo3);
        }






    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.temario_curso, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imv_modulo1:
                startActivity(new Intent(TemarioCurso.this, ModuloUno.class));
                break;
            case R.id.imv_modulo2:
                startActivity(new Intent(TemarioCurso.this, ModuloDos.class));
                break;
            case R.id.imv_modulo3:
                startActivity(new Intent(TemarioCurso.this, ModuloTres.class));
                break;
        }

    }
}
