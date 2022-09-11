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
public class FirstLetterEnricherTest {
    private CurrentPlayedSentenceComponent currentlyPlayedCase;

    @Autowired
    private @Qualifier("firstLetterEnricher") Enricher enricher;
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
        String expectedString = "s___-________ _____";
        String unexpectedString = "s___-d_______ h____";
        currentlyPlayedCase.setProgressPhrase("____-________ _____");

        enricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseSecondTest() {
        String expectedString = "sem_-________ _____";
        String unexpectedString = "s___-________ _____";
        currentlyPlayedCase.setProgressPhrase("sem_-________ _____");

        enricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseThirdTest() {
        String expectedString = "sen_-________ _____";
        String unexpectedString = "ten_-________ _____";
        currentlyPlayedCase.setProgressPhrase("ten_-________ _____");

        enricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseFourthTest() {
        String expectedString = "s_n____________o___";
        String unexpectedString = "sen_-________ _o___";
        currentlyPlayedCase.setProgressPhrase("__n____________o___");

        enricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    private Sentence getSampleSentenceForTest() {
        Sentence testingSentence = new Sentence();
        testingSentence.setId(55L);
        testingSentence.setClues("bliźniak dom");
        testingSentence.setCorrectAnswer("semi-detached house");
        return testingSentence;
    }
}
