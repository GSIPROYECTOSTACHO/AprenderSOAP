package utng.edu.mx.aprendersoap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class ResultActivity extends Activity implements View.OnClickListener{

	Button btnGuardar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);

		TextView textResult = (TextView) findViewById(R.id.textResult);

		Bundle b = getIntent().getExtras();

		int score = b.getInt("score");

        textResult.setText("Tu puntuacion es de " + " " + score + ".");

		btnGuardar=(Button)findViewById(R.id.btn_guardar_salir);
		btnGuardar.setOnClickListener(this);



	}

	public void playagain(View o) {

			Intent intent = new Intent(this, QuestionActivity.class);

			startActivity(intent);


	}

	@Override
	public void onClick(View v) {
		int resultado;
		Bundle b = getIntent().getExtras();
		int score = b.getInt("score");

		switch (v.getId()) {
			case R.id.btn_guardar_salir:

			if (score == 20) {
				Intent intent = new Intent(this, ModuloUno.class);

				startActivity(intent);
			}else{
				Toast temp = Toast.makeText(ResultActivity.this, "Debes obtener 20 putnos", Toast.LENGTH_SHORT);
				temp.show();
			}
				break;
		}

	}
}