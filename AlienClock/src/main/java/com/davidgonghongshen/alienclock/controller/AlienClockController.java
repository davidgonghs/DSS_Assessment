package com.davidgonghongshen.alienclock.controller;

import com.davidgonghongshen.alienclock.domin.AlienTime;
import com.davidgonghongshen.alienclock.domin.AlienTimeVM;
import com.davidgonghongshen.alienclock.req.AlienTimeRequest;
import com.davidgonghongshen.alienclock.service.AlienClockService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNullApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/alienClock")
@RequiredArgsConstructor
public class AlienClockController {

    private final AlienClockService alienClockService;

    @GetMapping ("/getTime")
    public AlienTimeVM getTime() {
        AlienTimeVM alienTimeVM = alienClockService.getTime();
      //  System.out.println(alienTimeVM);
        return alienTimeVM;
    }

    @GetMapping ("/getAlienTime")
    public String getAlienTime() {
        return alienClockService.getAlienTime();
    }

    @PostMapping("/convertEarthTime")
    public String getEarthTime(@RequestBody AlienTimeRequest request) {
        String alienTime = request.getAlienTime();
        return alienClockService.getEarthTime(alienTime);
    }
}
