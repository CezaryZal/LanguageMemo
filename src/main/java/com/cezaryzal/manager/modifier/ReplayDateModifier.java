package com.cezaryzal.manager.modifier;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReplayDateModifier {

    private LocalDate today = LocalDate.now();

    public LocalDate changeReplayDateByReplayLevel(int replayLevel) {

        LocalDate modifierDate;
        switch (replayLevel) {
            case 0:
            case 1:
                return modifierDate = today.plusDays(1L);
            case 2:
                return modifierDate = today.plusDays(2L);
            case 3:
                return modifierDate = today.plusDays(3L);
            case 4:
                return modifierDate = today.plusDays(4L);
            default:
                return modifierDate = today.plusDays(7L);
        }
    }
}