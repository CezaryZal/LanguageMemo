package com.cezaryzal.languageMemo.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class ServiceResultConfig {

    @Value("${service.descriptions.incorrect-letter-mark}")
    private char incorrectLetterMark;

    @Value("${service.descriptions.initial-item-DTO-output.last-memo-item}")
    private String initialStringOfLastMemoItem;

    @Value("${service.variables.scales-replay-level-number}")
    private int scalesReplayLevelNumber;

    @Value("${service.variables.min-replay-level-value}")
    private int minReplayLevelValue;

    @Value("${service.variables.max-replay-level-value}")
    private int maxReplayLevelValue;
}
