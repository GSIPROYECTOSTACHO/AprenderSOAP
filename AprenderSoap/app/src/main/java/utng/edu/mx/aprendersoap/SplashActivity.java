package utng.edu.mx.aprendersoap;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class SplashActivity extends Activity {

    public static final int segundos = 4;
    public static final int milisegundos=segundos*1000;
    private ProgressBar pbprogreso;
    public static final int delay=2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final ImageView iv = (ImageView)findViewById(R.id.imv_loading);
        final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.rotate);
        final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);
        pbprogreso =(ProgressBar)findViewById(R.id.pbprogreso);
        pbprogreso.setMax(maximoProgreso());
        empezaranimacion();





        iv.startAnimation(an);
        an.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv.startAnimation(an2);
                finish();
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


    }
    public void empezaranimacion(){
        new CountDownTimer(milisegundos,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                pbprogreso.setProgress(establecerProgreso(millisUntilFinished));

            }

            @Override
            public void onFinish() {
                finish();

            }
        }.start();


    }
    public int establecerProgreso(Long miliseconds){
        return (int)((milisegundos-miliseconds)/1000);

    }
    public int maximoProgreso(){
        return segundos-delay;
    }
}
