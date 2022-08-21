package com.cezaryzal.languageMemo;

import ch.qos.logback.classic.LoggerContext;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
    private static final LoggerContext LC = (LoggerContext) LoggerFactory.getILoggerFactory();

    public static void main(String[] args) {
        LC.setPackagingDataEnabled(true);

        SpringApplication.run(Application.class, args);
    }

}
