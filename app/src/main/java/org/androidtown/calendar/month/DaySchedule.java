package org.androidtown.calendar.month;

import java.io.Serializable;

/**
 * Created by 20100220 on 2015-12-12.
 */
public class DaySchedule implements Serializable {

    private String date;
    private String title;
    private String time;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
