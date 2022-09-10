package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
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
    private CurrentPlayedSentenceComponent currentlyPlayedCase;

    @Autowired
    private @Qualifier("firstLettersEnricher") Enricher enricher;
    @Autowired
    private ReplacementBlankCharacters replacementBlankCharacters;

    @Before
    public void setup() {
        Sentence sampleSentenceForTest = getSampleSentenceForTest();
        currentlyPlayedCase = new CurrentPlayedSentenceComponent(replacementBlankCharacters);
        currentlyPlayedCase.initialProgressPhrase(sampleSentenceForTest);
    }

    @Test
    public void catchCorrectWordToProgressPhraseFirstTest() {
        String expectedString = "s___-d_______ h____";
        String unexpectedString = "s___-________ _____";
        currentlyPlayedCase.setProgressPhrase("____-________ _____");

        enricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseSecondTest() {
        String expectedString = "s____d________h____";
        String unexpectedString = "s___-d_______ h____";
        currentlyPlayedCase.setProgressPhrase("___________________");

        enricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseThirdTest() {
        String expectedString = "semi-d_______ h____";
        String unexpectedString = "s___-d_______ h____";
        currentlyPlayedCase.setProgressPhrase("semi-________ _____");

        enricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseFourthTest() {
        String expectedString = "sent-d_______ h____";
        String unexpectedFirstString = "semi-d_______ h____";
        String unexpectedSecondString = "s___-d_______ h____";
        currentlyPlayedCase.setProgressPhrase("dent-________ _____");

        enricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedFirstString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedSecondString, currentlyPlayedCase.getProgressPhrase());
    }

    private Sentence getSampleSentenceForTest() {
        Sentence testingSentence = new Sentence();
        testingSentence.setId(55L);
        testingSentence.setClues("bliźniak dom");
        testingSentence.setCorrectAnswer("semi-detached house");
        return testingSentence;
    }
}
