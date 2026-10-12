package com.elvis.elias; //places this class in the projects package

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //this class handles web requests and returns data directly to the caller
public class HealthController {

    @GetMapping("/api/health") //connects an HTTP GET request at that path to the methods below it
    public String health() { //runs when that request arrives and retuns our message 
        return "Elias is running";
    }
}