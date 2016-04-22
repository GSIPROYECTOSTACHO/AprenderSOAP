package utng.edu.mx.aprendersoap;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Display extends AppCompatActivity implements View.OnClickListener {

    private MediaPlayer sound;
    Button btnSiguiente;

    DatabaseHelper helper = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        String username= getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.txv_display_uname);
        tv.setText(username);

        btnSiguiente =(Button)findViewById(R.id.btnSiguiente);
        btnSiguiente.setOnClickListener(this);
        sound = MediaPlayer.create(this,R.raw.button22);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSiguiente:
                sound.start();
                startActivity(new Intent(this, PantallaOpciones.class));

        }

    }
}
