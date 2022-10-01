package com.cezaryzal.languageMemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootApplication
@ConfigurationProperties("application.yml")
public class Application {

    public static void main(String[] args) {


        SpringApplication.run(Application.class, args);
    }

}
