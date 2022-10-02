package com.cezaryzal.languageMemo.service.result.modifier;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class ReplayLevelModifierTest {

    @Mock
    public ServiceResultConfig serviceResultConfig;

    @InjectMocks
    private ReplayLevelModifier replayLevelModifier;

    @BeforeEach
    public void setup(){
        Mockito.when(serviceResultConfig.getScalesReplayLevelNumber())
                .thenReturn(1);
        Mockito.when(serviceResultConfig.getMinReplayLevelValue())
                .thenReturn(0);
        Mockito.when(serviceResultConfig.getMaxReplayLevelValue())
                .thenReturn(5);
    }

    @ParameterizedTest
    @ValueSource(ints = {0,1,2,3,4,5,6})
    public void changeReplayLevelToMinByNumberOfTriesWithInitialReplayLevelEquals0(int numberOfTries){
        int replayLevel = 0;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, replayLevel);

        Assertions.assertEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertNotEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }
    @Test
    public void changeReplayLevelToMinByNumberOfTriesWithInitialTriesEquals0() {
        int numberOfTries = 0;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, 1);

        Assertions.assertEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertNotEquals(1, updatedReplayLevel);
        Assertions.assertNotEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void changeReplayLevelToMinByNumberOfTriesWithInitialTriesEquals1(int replayLevel){
        int numberOfTries = 1;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, replayLevel);

        Assertions.assertEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertNotEquals(replayLevel, updatedReplayLevel);
        Assertions.assertNotEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void changeReplayLevelToMinByNumberOfTriesWithInitialTriesEquals2(int replayLevel){
        int numberOfTries = 2;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, replayLevel);

        Assertions.assertEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertNotEquals(replayLevel, updatedReplayLevel);
        Assertions.assertNotEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4})
    public void changeReplayLevelToMinByNumberOfTriesWithInitialTriesEquals3(int replayLevel){
        int numberOfTries = 3;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, replayLevel);

        Assertions.assertEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertNotEquals(replayLevel, updatedReplayLevel);
        Assertions.assertNotEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5})
    public void changeReplayLevelToMinByNumberOfTriesWithInitialTriesEquals4(int replayLevel){
        int numberOfTries = 4;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, replayLevel);

        Assertions.assertEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertNotEquals(replayLevel, updatedReplayLevel);
        Assertions.assertNotEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6})
    public void changeReplayLevelToMinByNumberOfTriesWithInitialTriesEquals5(int replayLevel){
        int numberOfTries = 5;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, replayLevel);

        Assertions.assertEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertNotEquals(replayLevel, updatedReplayLevel);
        Assertions.assertNotEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2,3,4,5,6,7})
    public void changeReplayLevelToMinByNumberOfTriesWithInitialTriesEquals6(int replayLevel){
        int numberOfTries = 6;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(numberOfTries, replayLevel);

        Assertions.assertEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertNotEquals(replayLevel, updatedReplayLevel);
        Assertions.assertNotEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }



    @Test
    public void changeReplayLevelToMaxByNumberOfTries(){
        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(0, 6);

        Assertions.assertNotEquals(serviceResultConfig.getMinReplayLevelValue(), updatedReplayLevel);
        Assertions.assertEquals(serviceResultConfig.getMaxReplayLevelValue(), updatedReplayLevel);
    }



    @Test
    public void changeReplayLevelTo1ByNumberOfTriesWithInitialReplayLevelEquals2() {
        int replayLevel = 2;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(0, replayLevel);

        Assertions.assertEquals(1, updatedReplayLevel);
        Assertions.assertNotEquals(replayLevel, updatedReplayLevel);
    }

    @Test
    public void changeReplayLevelTo1ByNumberOfTriesWithInitialReplayLevelEquals3() {
        int replayLevel = 3;

        int updatedReplayLevel =
                replayLevelModifier.changeReplayLevelByNumberOfTries(1, replayLevel);

        Assertions.assertEquals(1, updatedReplayLevel);
        Assertions.assertNotEquals(replayLevel, updatedReplayLevel);
    }

}
