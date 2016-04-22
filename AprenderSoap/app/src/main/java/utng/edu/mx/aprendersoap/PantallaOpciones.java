package utng.edu.mx.aprendersoap;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

public class PantallaOpciones extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    ImageButton imvAcercaDe;
    ImageButton imvTemario;

    private MediaPlayer sound;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_opciones);
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

        imvAcercaDe =(ImageButton)findViewById(R.id.imv_acercade);
        imvAcercaDe.setOnClickListener(this);
        sound = MediaPlayer.create(this,R.raw.button22);

        imvTemario = (ImageButton)findViewById(R.id.imv_temario);
        imvTemario.setOnClickListener(this);



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
        getMenuInflater().inflate(R.menu.pantalla_opciones, menu);
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
            startActivity(new Intent(PantallaOpciones.this, Ayuda.class));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_temas) {
            startActivity(new Intent(PantallaOpciones.this, TemarioCurso.class));
        } else if (id == R.id.nav_avance) {
            startActivity(new Intent(PantallaOpciones.this, Grafica.class));
        } else if (id == R.id.nav_videos) {
            startActivity(new Intent(PantallaOpciones.this, MainVideos.class));

        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(PantallaOpciones.this, SeleccionaTema.class));

        } else if (id == R.id.nav_games) {
            startActivity(new Intent(PantallaOpciones.this, Games.class));

        } else if (id == R.id.nav_send) {
            startActivity(new Intent(PantallaOpciones.this, Correo.class));

        }else if (id == R.id.nav_ubicacion) {
            startActivity(new Intent(PantallaOpciones.this, MapsActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imv_acercade:
                sound.start();
                startActivity(new Intent(this, AcercaDe.class));

                break;
            case R.id.imv_temario:
                sound.start();
                startActivity(new Intent(this, TemarioCurso.class));
                break;
        }

    }
}
