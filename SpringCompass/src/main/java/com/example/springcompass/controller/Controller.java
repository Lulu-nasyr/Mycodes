package com.example.springcompass.controller;

import com.example.springcompass.logic.Compass;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Controller {

    private static final Compass compass = Compass.getInstance();

    @PostMapping(value="/createCompass",consumes = "application/json", produces = "application/json")
    public String createCompass (@RequestBody Map<String, String> compassMap){
        compass.importFromMap(compassMap);
        String result = "Компас создан!";
        return result;
    }

    @GetMapping (value="/getSide",consumes = "application/json", produces = "application/json")
    public String getSide (@RequestBody int degree){
        String side = compass.getSideByDegrees(degree);
        return side;
    }
}
