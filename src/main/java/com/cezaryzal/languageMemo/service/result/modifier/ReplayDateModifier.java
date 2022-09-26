package com.cezaryzal.languageMemo.service.result.modifier;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReplayDateModifier {

    public LocalDate changeReplayDateByReplayLevel(int replayLevel) {
        LocalDate today = LocalDate.now();

        switch (replayLevel) {
            case 0:
            case 1:
                return today.plusDays(1L);
            case 2:
                return today.plusDays(2L);
            case 3:
                return today.plusDays(3L);
            case 4:
                return today.plusDays(5L);
            default:
                return today.plusDays(10L);
        }
    }
}