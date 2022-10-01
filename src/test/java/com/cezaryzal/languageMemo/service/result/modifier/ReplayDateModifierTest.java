package com.cezaryzal.languageMemo.service.result.modifier;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.LocalDate;

public class ReplayDateModifierTest {
    private final ReplayDateModifier replayDateModifier = new ReplayDateModifier();

    @Test
    public void changeReplayDateByReplayLevelWith0Level(){
        LocalDate replayedDate = replayDateModifier.changeReplayDateByReplayLevel(0);

        Assert.assertEquals(LocalDate.now().plusDays(1), replayedDate);
        Assert.assertNotEquals(LocalDate.now(), replayedDate);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void changeReplayDateByReplayLevelWith123Level(int level){
        LocalDate replayedDate = replayDateModifier.changeReplayDateByReplayLevel(level);

        Assert.assertEquals(LocalDate.now().plusDays(level), replayedDate);
        Assert.assertNotEquals(LocalDate.now(), replayedDate);
    }

    @Test
    public void changeReplayDateByReplayLevelWith4Level(){
        LocalDate replayedDate = replayDateModifier.changeReplayDateByReplayLevel(4);

        Assert.assertEquals(LocalDate.now().plusDays(5), replayedDate);
        Assert.assertNotEquals(LocalDate.now(), replayedDate);
        Assert.assertNotEquals(LocalDate.now().plusDays(4), replayedDate);
        Assert.assertNotEquals(LocalDate.now().plusDays(6), replayedDate);
    }

    @Test
    public void changeReplayDateByReplayLevelWith5Level(){
        LocalDate replayedDate = replayDateModifier.changeReplayDateByReplayLevel(5);

        Assert.assertEquals(LocalDate.now().plusDays(10), replayedDate);
        Assert.assertNotEquals(LocalDate.now(), replayedDate);
        Assert.assertNotEquals(LocalDate.now().plusDays(9), replayedDate);
        Assert.assertNotEquals(LocalDate.now().plusDays(11), replayedDate);
    }
}
