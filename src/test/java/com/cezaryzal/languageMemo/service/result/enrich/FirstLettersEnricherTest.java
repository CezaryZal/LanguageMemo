package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.service.result.filter.ReplacementBlankCharacters;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FirstLettersEnricherTest {
    private CurrentPlayedMemoItem currentlyPlayedCase;

    @Autowired
    private @Qualifier("firstLettersEnricher") Enricher firstLettersEnricher;
    @Autowired
    private ReplacementBlankCharacters replacementBlankCharacters;

    @Before
    public void setup() {
        MemoItem sampleMemoItemForTest = getSampleMemoItemForTest();
        currentlyPlayedCase = new CurrentPlayedMemoItem(replacementBlankCharacters, new ServiceResultConfig());
        currentlyPlayedCase.initialProgressPhrase(sampleMemoItemForTest);
    }

    @Test
    public void enrichProgressPhraseFirstTest() {
        String stubProgressPhrase = "____-________ _____";
        String expectedString = "s___-d_______ h____";
        String unexpectedString = "s___-________ _____";
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);

        firstLettersEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseSecondTest() {
        String expectedString = "s____d________h____";
        String unexpectedString = "s___-d_______ h____";
        currentlyPlayedCase.setProgressPhrase("___________________");

        firstLettersEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseThirdTest() {
        String expectedString = "semi-d_______ h____";
        String unexpectedString = "s___-d_______ h____";
        currentlyPlayedCase.setProgressPhrase("semi-________ _____");

        firstLettersEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseFourthTest() {
        String expectedString = "sent-d_______ h____";
        String unexpectedFirstString = "semi-d_______ h____";
        String unexpectedSecondString = "s___-d_______ h____";
        currentlyPlayedCase.setProgressPhrase("dent-________ _____");

        firstLettersEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedFirstString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedSecondString, currentlyPlayedCase.getProgressPhrase());
    }

    private MemoItem getSampleMemoItemForTest() {
        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("bliźniak dom");
        testingMemoItem.setCorrectAnswer("semi-detached house");
        return testingMemoItem;
    }
}
