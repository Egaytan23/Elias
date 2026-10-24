package com.elvis.elias; //places this class in the projects package
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //this class handles web requests and returns data directly to the caller
public class HealthController {

    @GetMapping("/api/health") //connects an HTTP GET request at that path to the methods below it
public Map<String, String> health() { //the keys and values are strings Map.of() creates those key value pairs and spriong converts the map into JSON 
    return Map.of(
        "status", "UP",
        "application", "Elias"
    );
}
}
