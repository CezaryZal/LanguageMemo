package com.cezaryzal.languageMemo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ApiConstants {

    @Value("${service.incorrect-letter}")
    public char incorrectLetter;

    @Value("${service.scales-replay-level-number}")
    public int scalesReplayLevelNumber;

    @Value("${service.min-replay-level-value}")
    public int minReplayLevelValue;

    @Value("${service.max-replay-level-value}")
    public int maxReplayLevelValue;
}
