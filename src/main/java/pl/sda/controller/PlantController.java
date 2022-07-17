package pl.sda.controller;

import org.springframework.web.bind.annotation.GetMapping;

public class PlantController {

    @GetMapping("/index")
    public String index(){
        return "index";
    }
}
