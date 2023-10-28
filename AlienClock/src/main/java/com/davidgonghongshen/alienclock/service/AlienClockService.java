package com.davidgonghongshen.alienclock.service;

import com.davidgonghongshen.alienclock.domin.AlienTime;
import com.davidgonghongshen.alienclock.domin.AlienTimeVM;

public interface AlienClockService {
    AlienTimeVM getTime();

    String getAlienTime();

    String getEarthTime(String str);
}
