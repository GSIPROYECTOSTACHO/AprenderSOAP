package utng.edu.mx.aprendersoap;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {

    int mPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
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

        int points = getIntent().getIntExtra("POINTS",0);

        TextView pointsTextView = (TextView) findViewById(R.id.textViewPoints);
        pointsTextView.setText(String.valueOf(points));

        mPoints = points;
    }
    public void saveScore(View v){
        //we create a SharedPreferences variable and read what we have stored
        SharedPreferences preferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE);

        //we get the name
        EditText editTextName = (EditText) findViewById(R.id.editTextName);

        String name = editTextName.getText().toString();

        //now we need a editor to write in the SharedPreferences
        SharedPreferences.Editor editor = preferences.edit();

        //save previous values
        String previousScores = preferences.getString("SCORE", "");

        //here is where we write what we want
        editor.putString("SCORE", name + " " + mPoints + " POINTS\n" + previousScores);

        //at last, we need to commit the changes or wont save
        editor.apply();

        finish();
    }

}
