package com.davidgonghongshen.alienclock.service.impl;

import com.davidgonghongshen.alienclock.Tools.CalculateAlienTime;
import com.davidgonghongshen.alienclock.domin.AlienTime;
import com.davidgonghongshen.alienclock.domin.AlienTimeVM;
import com.davidgonghongshen.alienclock.service.AlienClockService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Service
public class AlienClockServiceImpl implements AlienClockService {
    @Override
    public AlienTimeVM getTime() {
        return new AlienTimeVM(CalculateAlienTime.getAlienTime(System.currentTimeMillis()/1000), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @Override
    public String getAlienTime() {
        return null;
    }

    @Override
    public String getEarthTime(String alienTime){
        //2819-03-07 16:30:58
        String[] time = alienTime.split(" ");
        String[] date = time[0].split("-");
        String[] clock = time[1].split(":");
        AlienTime alienTime1 = new AlienTime(Long.parseLong(date[0]), Long.parseLong(date[1]), Long.parseLong(date[2]), Long.parseLong(clock[0]), Long.parseLong(clock[1]), Long.parseLong(clock[2]));
        return CalculateAlienTime.convertToEarthTime(alienTime1);
    }
}
