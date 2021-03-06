package utng.edu.mx.aprendersoap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


public class SeleccionaTema extends ActionBarActivity implements View.OnClickListener{

    Toolbar toolbar;
    SharedPreferences sharedPreferences;
    Button buttonRed, buttonIndigo, btnSalir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("VALUES", MODE_PRIVATE);
        int theme = sharedPreferences.getInt("THEME", 1);

        switch (theme){
            case 1: setTheme(R.style.AppTheme);
                break;
            case 2: setTheme(R.style.AppTheme2);
                break;
        }


        setContentView(R.layout.activity_selecciona_tema);

        // Setup Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);


        // Fix issues of navigation and status bars
        navigationBarStatusBar();

        // Setup buttons to apply themes.
        setMyTheme();

        btnSalir=(Button)findViewById(R.id.btn_salir);
        btnSalir.setOnClickListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selecciona_tema, menu);
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


    public void navigationBarStatusBar() {

        // Fix portrait issues
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            // Fix issues for KitKat setting Status Bar color primary
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                SeleccionaTema.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }

            // Fix issues for Lollipop, setting Status Bar color primary dark
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue21 = new TypedValue();
                SeleccionaTema.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue21, true);
                final int color = typedValue21.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }

        // Fix landscape issues (only Lollipop)
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (Build.VERSION.SDK_INT >= 19) {
                TypedValue typedValue19 = new TypedValue();
                SeleccionaTema.this.getTheme().resolveAttribute(R.attr.colorPrimary, typedValue19, true);
                final int color = typedValue19.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
            if (Build.VERSION.SDK_INT >= 21) {
                TypedValue typedValue = new TypedValue();
                SeleccionaTema.this.getTheme().resolveAttribute(R.attr.colorPrimaryDark, typedValue, true);
                final int color = typedValue.data;
                FrameLayout statusBar = (FrameLayout) findViewById(R.id.statusBar);
                statusBar.setBackgroundColor(color);
            }
        }
    }

    private void setMyTheme() {
        buttonRed = (Button) findViewById(R.id.buttonRed);
        buttonRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putInt("THEME",1).apply();
                Intent intent = new Intent(SeleccionaTema.this, SeleccionaTema.class);
                startActivity(intent);
            }
        });

        buttonIndigo = (Button) findViewById(R.id.buttonIndigo);
        buttonIndigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences.edit().putInt("THEME",2).apply();
                Intent intent = new Intent(SeleccionaTema.this, SeleccionaTema.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_salir:
                startActivity(new Intent(SeleccionaTema.this, PantallaOpciones.class));
                break;
        }

    }
}
