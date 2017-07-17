package com.izzan.bayesiantennis;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * Created by Aizen on 17 Jul 2017.
 */

@Table(name = "play_tennis", id = "_id")
public class PlayTennis extends Model{

    @Column(name = "outlook")
    public String outlook;

    @Column(name = "temperature")
    public String temperature;

    @Column(name = "humidity")
    public String humidity;

    @Column(name = "wind")
    public String wind;

    @Column(name = "is_playing")
    public String isPlaying;

    public PlayTennis(String outlook, String temperature, String humidity, String wind, String isPlaying) {
        super();
        this.outlook = outlook;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
        this.isPlaying = isPlaying;
    }

    public PlayTennis(String outlook, String temperature, String humidity, String wind) {
        super();
        this.outlook = outlook;
        this.temperature = temperature;
        this.humidity = humidity;
        this.wind = wind;
    }

    public PlayTennis() {
        super();
    }
}
