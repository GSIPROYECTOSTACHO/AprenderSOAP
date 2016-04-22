package utng.edu.mx.aprendersoap;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Manuel on 15/04/2016.
 */
public class SignUp extends Activity {
    private MediaPlayer sound;

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        sound = MediaPlayer.create(this,R.raw.button22);
    }
    public void onSignUpClick(View v){
        if(v.getId() == R.id.btn_registrase){
            sound.start();
            EditText name =(EditText)findViewById(R.id.edt_name);
            EditText email =(EditText)findViewById(R.id.edt_email);
            EditText uname =(EditText)findViewById(R.id.edt_user_name);
            EditText pass1 =(EditText)findViewById(R.id.edt_pass_uno);
            EditText pass2 =(EditText)findViewById(R.id.edt_pass_confirmar);

            String namestr = name.getText().toString();
            String emailstr = email.getText().toString();
            String unamestr = uname.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(!pass1str.equals(pass2str)){
                //mensage
                Toast pass = Toast.makeText(SignUp.this, R.string.contrasenia_invalida, Toast.LENGTH_SHORT);
                pass.show();

            }
            else{
                //insertar datos en la base de datos
                Contact c = new Contact();
                c.setName(namestr);
                c.setEmail(emailstr);
                c.setUname(unamestr);
                c.setPass(pass1str);

                helper.insertContact(c);
                Toast pass = Toast.makeText(SignUp.this, R.string.datos_guardados, Toast.LENGTH_SHORT);
                pass.show();
            }
        }
    }
}
