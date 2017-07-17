package com.izzan.bayesiantennis;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    private Spinner spinnerOutlook;

    private Spinner spinnerTemperature;

    private Spinner spinnerHumidity;

    private Spinner spinnerWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        spinnerOutlook = (Spinner) findViewById(R.id.spinnerOutlook);
        spinnerTemperature = (Spinner) findViewById(R.id.spinnerTemperature);
        spinnerHumidity = (Spinner) findViewById(R.id.spinnerHumidity);
        spinnerWind = (Spinner) findViewById(R.id.spinnerWind);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String outlook = spinnerOutlook.getSelectedItem().toString().toLowerCase();
                String temperature = spinnerTemperature.getSelectedItem().toString().toLowerCase();
                String humidity = spinnerHumidity.getSelectedItem().toString().toLowerCase();
                String wind = spinnerWind.getSelectedItem().toString().toLowerCase();

                PlayTennis mPlayTennis = new PlayTennis(outlook, temperature, humidity, wind);

                Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                intent.putExtra("outlook", outlook);
                intent.putExtra("temperature", temperature);
                intent.putExtra("humidity", humidity);
                intent.putExtra("wind", wind);
                startActivity(intent);

/*                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
