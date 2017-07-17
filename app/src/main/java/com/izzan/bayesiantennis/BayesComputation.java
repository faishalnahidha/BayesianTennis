package com.izzan.bayesiantennis;

import android.util.Log;

import com.activeandroid.query.Select;

/**
 * Created by Aizen on 17 Jul 2017.
 */

public class BayesComputation {

    public final float P_playYes;
    public final float P_playNo;

    public BayesComputation() {
        this.P_playYes = count("is_playing", "yes") / countAll();
        this.P_playNo = count("is_playing", "no") / countAll();
        Log.i("count_yes", String.valueOf(count("is_playing", "yes")));
        Log.i("count_all", String.valueOf(countAll()));
        Log.i("count_playYes", String.valueOf(P_playYes));
    }

    public float likehoodOfYes(String outlook, String temperature, String humidity, String wind) {

        float P_outlookYes = count("outlook", outlook, "is_playing", "yes") / P_playYes;
        float P_tempYes = count("temperature", temperature, "is_playing", "yes") / P_playYes;
        float P_humidityYes = count("humidity", humidity, "is_playing", "yes") / P_playYes;
        float P_windYes = count("wind", wind, "is_playing", "yes") / P_playYes;

        return P_outlookYes * P_tempYes * P_humidityYes * P_windYes * P_playYes;

    }

    public float likehoodOfNo(String outlook, String temperature, String humidity, String wind) {

        float P_outlookNo = count("outlook", outlook, "is_playing", "no") / P_playNo;
        float P_tempNo = count("temperature", temperature, "is_playing", "no") / P_playNo;
        float P_humidityNo = count("humidity", humidity, "is_playing", "no") / P_playNo;
        float P_windNo = count("wind", wind, "is_playing", "no") / P_playNo;

        return P_outlookNo * P_tempNo * P_humidityNo * P_windNo * P_playNo;

    }

    public String predict(String outlook, String temperature, String humidity, String wind) {

        float yes = likehoodOfYes(outlook, temperature, humidity, wind);
        float no = likehoodOfNo(outlook, temperature, humidity, wind);

        if (yes > no) {
            return "Playing Tennis";
        } else if (yes < no) {
            return "Not Playing Tennis";
        } else {
            return "Maybe Play, Maybe No";
        }
    }

    public String predict(float yes, float no) {

        if (yes > no) {
            return "Playing Tennis";
        } else if (yes < no) {
            return "Not Playing Tennis";
        } else {
            return "Maybe Play, Maybe No";
        }
    }

    public static float countAll() {
        return new Select()
                .from(PlayTennis.class)
                .count();
    }

    public static float count(String collumn, String param) {
        return new Select()
                .from(PlayTennis.class)
                .where(collumn + " = ?", param)
                .count();
    }

    public static float count(String collumn1, String param1, String collumn2, String param2) {
        return new Select()
                .from(PlayTennis.class)
                .where(collumn1 + " = ?", param1)
                .where(collumn2 + " = ?", param2)
                .count();
    }
}
