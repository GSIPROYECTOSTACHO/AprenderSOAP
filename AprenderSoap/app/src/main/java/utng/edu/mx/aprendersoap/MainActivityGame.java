package utng.edu.mx.aprendersoap;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class MainActivityGame extends AppCompatActivity {


    ImageButton imvUneplayer;
    ImageButton imvtwoplayer;
    ImageButton score;

    public static final int segundos = 4;
    public static final int milisegundos=segundos*1000;
    public static final int delay=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_game);
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

        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.game);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);

        imvUneplayer =(ImageButton)findViewById(R.id.button);
        imvtwoplayer=(ImageButton)findViewById(R.id.button2);
        score=(ImageButton)findViewById(R.id.button3);

        imvUneplayer.startAnimation(an);
        imvtwoplayer.startAnimation(an);
        score.startAnimation(an);



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




    }
    public void startSinglePlayer(View v){

        Intent myIntent = new Intent(this, GameActivity.class);

        startActivity(myIntent);
    }

    public void startMultiplayer(View v){
        Intent myIntent = new Intent(this, MultiplayerActivity.class);

        startActivity(myIntent);
    }

    public void openScores(View v){
        Intent myIntent = new Intent(this, ScoresActivity.class);

        startActivity(myIntent);
    }
}
