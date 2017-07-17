package com.izzan.bayesiantennis;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    //private PlayTennis mPlayTennis;

    private TextView tvLikehoodYes;

    private TextView tvLikehoodNo;

    private TextView tvPrediction;

    private String outlook;
    private String temperature;
    private String humidity;
    private String wind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        //add to activity you want to pull variables from
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.outlook = getIntent().getStringExtra("outlook");
            this.temperature = getIntent().getStringExtra("temperature");
            this.humidity = getIntent().getStringExtra("humidity");
            this.wind = getIntent().getStringExtra("wind");
        }

        tvLikehoodYes = (TextView) findViewById(R.id.textViewLikehoodYes);
        tvLikehoodNo = (TextView) findViewById(R.id.textViewLikehoodNo);
        tvPrediction = (TextView) findViewById(R.id.textViewPlayingTennis);

        BayesComputation mBayesComputation = new BayesComputation();

        Log.i("P_playYes", String.valueOf(mBayesComputation.P_playYes));
        Log.i("P_playNo", String.valueOf(mBayesComputation.P_playNo));

        Float yesLikehood = mBayesComputation.likehoodOfYes(
                outlook, temperature, humidity, wind
        );

        Float noLikehood = mBayesComputation.likehoodOfNo(
                outlook, temperature, humidity, wind
        );

        String playTennis = mBayesComputation.predict(yesLikehood, noLikehood);

        tvLikehoodYes.setText("Likehood of yes : " + yesLikehood);
        tvLikehoodNo.setText("Likehood of no : " + noLikehood);
        tvPrediction.setText(playTennis);
    }
}
