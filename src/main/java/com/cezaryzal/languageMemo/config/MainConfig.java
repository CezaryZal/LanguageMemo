package com.cezaryzal.languageMemo.config;

import com.cezaryzal.languageMemo.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.cezaryzal.languageMemo")
public class MainConfig {
    public static final Logger LOGGER = LoggerFactory.getLogger(Application.class);
}
