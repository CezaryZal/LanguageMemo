package com.cezaryzal.languageMemo.testingpackage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/text")
    public String getExemplaryText(){
        return "Działa endpoint?";
    }
}
