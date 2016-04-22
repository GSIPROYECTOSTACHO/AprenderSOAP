package utng.edu.mx.aprendersoap;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer sound;

    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sound = MediaPlayer.create(this,R.raw.button22);
    }
    public void onButtonClick(View view){
        if(view.getId() == R.id.btn_login){
            sound.start();
            EditText a = (EditText)findViewById(R.id.edt_username);
            String str = a.getText().toString();

            EditText b = (EditText)findViewById(R.id.edt_password);
            String pass = b.getText().toString();

            String password = helper.searchPass(str);

            if(pass.equals(password)){
                Intent i = new Intent(MainActivity.this, Display.class);


                i.putExtra("Username",str);


                startActivity(i);


            }else{
                Toast temp = Toast.makeText(MainActivity.this, R.string.contrasenia_invalida, Toast.LENGTH_SHORT);
                temp.show();
            }




        }
        if(view.getId() == R.id.btn_signup){
            //sound.start();
            Intent i = new Intent(MainActivity.this, SignUp.class);
            startActivity(i);

        }
    }

}
