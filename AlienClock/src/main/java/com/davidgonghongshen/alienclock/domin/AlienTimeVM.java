package com.davidgonghongshen.alienclock.domin;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class AlienTimeVM {
    private String alienTime;
    private String earthTime;

    public AlienTimeVM() {
    }

    public AlienTimeVM(AlienTime alienTime, String earthTime) {
        String formattedTime = String.format("%04d-%02d-%02d %02d:%02d:%02d",
                alienTime.getYear(),
                alienTime.getMonth(),
                alienTime.getDay(),
                alienTime.getHour(),
                alienTime.getMinute(),
                alienTime.getSecond()
        );

        this.alienTime = formattedTime;
        this.earthTime = earthTime;
    }

    @Override
    public String toString() {
        return "AlienTimeVM{" +
                "alienTime=" + alienTime +
                ", earthTime='" + earthTime + '\'' +
                '}';
    }

}
