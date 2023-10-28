package com.davidgonghongshen.alienclock.domin;

import com.davidgonghongshen.alienclock.Tools.CalculateAlienTime;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
@Component
public class AlienTime {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    public AlienTime() {
        this.year = 2804;
        this.month = 0;
        this.day = 0;
        this.hour = 0;
        this.minute = 0;
        this.second = 0;
    }

    public AlienTime(long year, long month, long day, long hour, long minute, long second) {
        this.year = Math.toIntExact(year);
        this.month = Math.toIntExact(month);
        this.day = Math.toIntExact(day);
        this.hour = Math.toIntExact(hour);
        this.minute = Math.toIntExact(minute);
        this.second = Math.toIntExact(second);
    }
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year += year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if(month == 0)
            this.month = 1;
        else
            this.month = month;
    }

    public int getDay() {

        return day;
    }

    public void setDay(int day) {
        if(day == 0)
            this.day = 1;
        else
            this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }


    //toString
    @Override
    public String toString() {
        return "AlienTime{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", hour=" + hour +
                ", minute=" + minute +
                ", second=" + second +
                '}';
    }




//    public static void main(String[] args) {
//
//        //get system year
//        Date date = new Date();
//        int year = date.getYear() + 1900;
//        int month = date.getMonth() + 1;
//        int day = date.getDate();
//        int hour = date.getHours();
//        int minute = date.getMinutes();
//        int second = date.getSeconds();
//
//        System.out.println(year + " " + month + " " + day + " " + hour + " " + minute + " " + second);
//        AlienTime alienTime = CalculateAlienTime.getAlienTime(System.currentTimeMillis()/1000);
//
//        System.out.println(alienTime);
//        System.out.println("------------------------------------");
//
//        System.out.println(CalculateAlienTime.convertToEarthTime(alienTime.getYear(), alienTime.getMonth(), alienTime.getDay(), alienTime.getHour(), alienTime.getMinute(), alienTime.getSecond()));
//
//
//
//    }

}


