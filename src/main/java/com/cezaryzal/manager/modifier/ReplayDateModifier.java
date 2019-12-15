package com.cezaryzal.manager.modifier;

import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class ReplayDateModifier {

    private LocalDate replayDate;

    public void setReplayDate(LocalDate replayDate) {
        this.replayDate = replayDate;
    }

    public LocalDate changeReplayDateByReplayLevel(int replayLevel) {

        LocalDate modifierDate;
        switch (replayLevel) {
            case 0:
            case 1:
                return modifierDate = replayDate.plusDays(1L);
            case 2:
                return modifierDate = replayDate.plusDays(2L);
            case 3:
                return modifierDate = replayDate.plusDays(3L);
            case 4:
                return modifierDate = replayDate.plusDays(4L);
            default:
                return modifierDate = replayDate.plusDays(7L);
        }
    }
}