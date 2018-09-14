package com.example.spandananakkireddy.hw2_group32;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Spandana Nakkireddy on 2/1/2018.
 */

public class Task implements Serializable {

    String title;
    String date;
    String time;
    String priority;

public Task(){

}

    public Task(String title, String date, String time, String priority) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.priority = priority;
    }

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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

       @Override
    public String toString() {
        return "Task{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", priority='" + priority + '\'' +

                '}';
    }
}
