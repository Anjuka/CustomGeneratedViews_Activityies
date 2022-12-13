package com.anjuka.deviceactivitypoc;

import androidx.annotation.NonNull;

import java.util.Comparator;

public class ActivityData /*implements Comparable<ActivityData>*/ {

    private int start_time;
    private int end_time;
    private String device_name;
    private String color;
    private int top_margin;
    private int left_margin;
    private int duration;

    public ActivityData(int start_time, int end_time, String device_name, String color) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.device_name = device_name;
        this.color = color;
    }

    public ActivityData(int start_time, int end_time, String device_name, String color, int top_margin, int left_margin) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.device_name = device_name;
        this.color = color;
        this.top_margin = top_margin;
        this.left_margin = left_margin;
    }

    public ActivityData(int start_time, int end_time, String device_name, String color, int top_margin, int left_margin, int duration) {
        this.start_time = start_time;
        this.end_time = end_time;
        this.device_name = device_name;
        this.color = color;
        this.top_margin = top_margin;
        this.left_margin = left_margin;
        this.duration = duration;
    }

    public int getStart_time() {
        return start_time;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public int getEnd_time() {
        return end_time;
    }

    public void setEnd_time(int end_time) {
        this.end_time = end_time;
    }

    public String getDevice_name() {
        return device_name;
    }

    public void setDevice_name(String device_name) {
        this.device_name = device_name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getTop_margin() {
        return top_margin;
    }

    public void setTop_margin(int top_margin) {
        this.top_margin = top_margin;
    }

    public int getLeft_margin() {
        return left_margin;
    }

    public void setLeft_margin(int left_margin) {
        this.left_margin = left_margin;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

/*    @Override
    public int compareTo(ActivityData activityData) {
        int compareage
                = ((ActivityData)activityData).getStart_time();

        //  For Ascending order
        return this.start_time - compareage;
    }

    public static Comparator<ActivityData> sortByEndTime = new Comparator<ActivityData>() {

        @Override
        public int compare(ActivityData activityData, ActivityData t1) {
            int rollno1 = activityData.getEnd_time();
            int rollno2 = t1.getEnd_time();
            return rollno2 - rollno1;
        }
    };*/

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}
