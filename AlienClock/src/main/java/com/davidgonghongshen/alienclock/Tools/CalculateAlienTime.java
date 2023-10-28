package com.davidgonghongshen.alienclock.Tools;

import com.davidgonghongshen.alienclock.domin.AlienTime;
import com.davidgonghongshen.alienclock.domin.AlienTimeVM;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.IntStream;

//step 1: get total seconds of a alien year , month, day...
//step 2: Make up time difference from Month 18, Day 31, Hour 2, Minute 2, Second 88 to next year
//step 3: get 1970-1-1, 12:00:00am timestamp
//step 4: get the total seconds of the earth time and convert to alien time


//1970-1-1, 12:00:00am Earth time is equivalent to Year 2804, Month 18, Day 31, Hour 2, Minute 2, Second 88 Alien’s time.
//1970-1-1 00:00:00
//1 second Alien time is 0.5 second Earth time.

//Month 1 2 3 4 5 6 7 8 9
//Days 44 42 48 40 48 44 40 44 42
//Month 10 11 12 13 14 15 16 17 18
//Days 40 40 42 44 48 42 40 44 38
//1 minute is 90 seconds.
//1 hour is 90 minutes.
//1 day is 36 hours

public class CalculateAlienTime {

    //Make up time difference from Month 18, Day 31, Hour 2, Minute 2, Second 88 to next year

    static int SecondsOfDay = 36 * 90 * 90;

   static  int[] Months = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
   static int[] DaysOfMonths = {44,42,48,40,48,44,40,44,42,40,40,42,44,48,42,40,44,38};
   static int T1 = 38*SecondsOfDay - (31*SecondsOfDay+2*90*90+2*90+88);

   static int[] SecondsOfMonths = new int[Months.length];


   static{
       for(int i = 0; i < Months.length; i++){
           SecondsOfMonths[i] = DaysOfMonths[i] * SecondsOfDay;
       }
   }

    //get the total seconds of a alien year
    static int TotalSecondsOfAlienYear;

    // 静态初始化块
    static {
        // get stream of elements
        IntStream stream = Arrays.stream(SecondsOfMonths);
        // add the elements in stream
        // get the total seconds of a alien year
        TotalSecondsOfAlienYear = stream.sum();
    }


    public static AlienTime getAlienTime(long timestamp){
        AlienTime alienTime = new AlienTime();

        //get the total seconds of the earth time
        long totalSecondsOfEarth = timestamp;

        //get the total seconds of the alien time
        long totalSecondsOfAlien = totalSecondsOfEarth * 2;
       // System.out.println("totalSecondsOfAlien: " + totalSecondsOfAlien);

        //Smooth out the time difference and start counting from the new year
        totalSecondsOfAlien -= T1;

      //  System.out.println("totalSecondsOfAlien - T1: " + totalSecondsOfAlien);

        //Year 2804, Month 18, Day 31, Hour 2, Minute 2, Second 88 Alien’s time.
        int year = Math.toIntExact(totalSecondsOfAlien / TotalSecondsOfAlienYear);
        alienTime.setYear(year);
        //get the total seconds of the alien year
        long totalSeconds = totalSecondsOfAlien % TotalSecondsOfAlienYear;

        //get the total seconds of the alien month
        for(int i = 0; i < Months.length; i++){
            if(totalSeconds < SecondsOfMonths[i]){
                alienTime.setMonth(i+1);//in calendar, month start from 1
                break;
            }
            totalSeconds -= SecondsOfMonths[i];
        }


        int day = Math.toIntExact(totalSeconds / SecondsOfDay);
        //get day of the alien month, in calndar, day start from 1
        alienTime.setDay(day+1);
        //get the total seconds of the alileecen day
        long totalSecondsOfAlienDay = totalSeconds % SecondsOfDay;


        int hour = Math.toIntExact(totalSecondsOfAlienDay / (90 * 90));
        //get hour of the alien day
        alienTime.setHour(hour);
        //get the total seconds of the alien hour
        long totalSecondsOfAlienHour = totalSecondsOfAlienDay % (90 * 90);


        int minute = Math.toIntExact(totalSecondsOfAlienHour / 90);
        //get minute of the alien hour
        alienTime.setMinute(minute);
        //get the total seconds of the alien minute
        long totalSecondsOfAlienMinute = totalSecondsOfAlienHour % 90;


        //get second of the alien minute
        alienTime.setSecond(Math.toIntExact(totalSecondsOfAlienMinute));
        return alienTime;
    }

    public static String convertToEarthTime(AlienTime alienTime){
        return convertToEarthTime(alienTime.getYear(), alienTime.getMonth(), alienTime.getDay(), alienTime.getHour(), alienTime.getMinute(), alienTime.getSecond());
    }

    public static String convertToEarthTime(int year, int month, int day, int hour, int minute, int second){
        //2804, 18, Day 31, Hour 2, Minute 2, Second 88 = 0
        //0+T1 = 2805, 1, Day 1, Hour 0, Minute 0, Second 0
    //    System.out.println("------------------------------");
      //  System.out.println(year + " " + month + " " + day + " " + hour + " " + minute + " " + second);

        long totalSeconds = 0;
        // Calculate total seconds for Alien year and month
        totalSeconds += (year - 2804l) * TotalSecondsOfAlienYear;
        totalSeconds += T1; // Add the time difference

        if(month > 1){
            for(int i = 0; i < month - 1; i++){
                totalSeconds += SecondsOfMonths[i];
            }
        }

        if(day > 1){
            totalSeconds += (day - 1) * SecondsOfDay;
        }
        totalSeconds += hour * 90 * 90;
        totalSeconds += minute * 90;
        totalSeconds += second;

     //   System.out.println("a2e totalSeconds: " + totalSeconds);


        long earthSeconds = totalSeconds / 2;

        //convert to earth time, YYYY-MM-DD HH:MM:SS

        // create  Instant object
        Instant instant = Instant.ofEpochSecond(earthSeconds);

        // use DateTimeFormatter yyyy-MM-dd HH:mm:ss
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                .withZone(ZoneId.systemDefault());

        // use DateTimeFormatter  Instant obj 2 str
        String formattedDate = formatter.format(instant);
      //  System.out.println("Formatted Date: " + formattedDate);

        return formattedDate;

    }


}
