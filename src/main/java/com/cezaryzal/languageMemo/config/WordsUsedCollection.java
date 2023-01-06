package com.cezaryzal.languageMemo.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "service.filter.word-used")
@Getter
@AllArgsConstructor
public class WordsUsedCollection {
    private List<String> thirdLetters;
    private List<String> lessThanTreeLetters;
}
