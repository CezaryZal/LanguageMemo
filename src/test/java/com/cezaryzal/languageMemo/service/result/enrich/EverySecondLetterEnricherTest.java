package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.service.result.filter.ReplacementBlankCharacters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EverySecondLetterEnricherTest {
    private CurrentPlayedMemoItem currentlyPlayedCase;

    @Mock
    public ServiceResultConfig serviceResultConfig;

    @Autowired
    private @Qualifier("everySecondLetterEnricher") Enricher everySecondLetterEnricher;
    @Autowired
    private ReplacementBlankCharacters replacementBlankCharacters;

    @Before
    public void setup() {
        Mockito.when(serviceResultConfig.getInitialStringOfLastMemoItem())
                .thenReturn("First try");
        MemoItem sampleMemoItemForTest = getSampleMemoItemForTest();
        currentlyPlayedCase = new CurrentPlayedMemoItem(replacementBlankCharacters, serviceResultConfig);
        currentlyPlayedCase.initialProgressPhrase(sampleMemoItemForTest);
    }

    @Test
    public void enrichProgressPhraseFirstTest() {
        String stubProgressPhrase = "____-________ _____";
        String expectedString = "_e_i-d_t_c_e_ _o_s_";
        String unexpectedString = "____-________ _____";
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);

        everySecondLetterEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseSecondTest() {
        String expectedString = "se_i-d_t_c_e_ _o_s_";
        String unexpectedString = "_e_i-d_t_c_e_ _o_s_";
        currentlyPlayedCase.setProgressPhrase("s___-________ _____");

        everySecondLetterEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseThirdTest() {
        String expectedString = "se_i-d_t_c_e_ _o_s_";
        String unexpectedString = "_e_i-d_t_c_e_ _____";
        currentlyPlayedCase.setProgressPhrase("s___-________ _____");

        everySecondLetterEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    private MemoItem getSampleMemoItemForTest() {
        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("bliźniak dom");
        testingMemoItem.setCorrectAnswer("semi-detached house");
        return testingMemoItem;
    }
}
