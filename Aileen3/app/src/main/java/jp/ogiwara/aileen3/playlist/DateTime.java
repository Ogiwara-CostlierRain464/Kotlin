package jp.ogiwara.aileen3.playlist;

import java.util.Date;

public class DateTime {
    private String dateTime;

    public DateTime(){
        this.dateTime = new Date().toString();
    }

    public String getName(){
        return dateTime;
    }
}
