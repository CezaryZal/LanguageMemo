package com.cezaryzal.languageMemo.service.result.enrich;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
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
public class FirstLetterEnricherTest {
    private CurrentPlayedMemoItem currentlyPlayedCase;

    @Mock
    public ServiceResultConfig serviceResultConfig;

    @Autowired
    private @Qualifier("firstLetterEnricher") Enricher firstLetterEnricher;
    @Autowired
    private ReplacementBlankCharacters replacementBlankCharacters;

    @Before
    public void setup() {
        Mockito.when(serviceResultConfig.getInitialStringOfLastSentence())
                .thenReturn("First try");
        Sentence sampleSentenceForTest = getSampleSentenceForTest();
        currentlyPlayedCase = new CurrentPlayedMemoItem(replacementBlankCharacters, serviceResultConfig);
        currentlyPlayedCase.initialProgressPhrase(sampleSentenceForTest);
    }

    @Test
    public void enrichProgressPhraseFirstTest() {
        String stubProgressPhrase = "____-________ _____";
        String expectedString = "s___-________ _____";
        String unexpectedString = "s___-d_______ h____";
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);

        firstLetterEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseSecondTest() {
        String expectedString = "sem_-________ _____";
        String unexpectedString = "s___-________ _____";
        currentlyPlayedCase.setProgressPhrase("sem_-________ _____");

        firstLetterEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseThirdTest() {
        String expectedString = "sen_-________ _____";
        String unexpectedString = "ten_-________ _____";
        currentlyPlayedCase.setProgressPhrase("ten_-________ _____");

        firstLetterEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseFourthTest() {
        String expectedString = "s_n____________o___";
        String unexpectedString = "sen_-________ _o___";
        currentlyPlayedCase.setProgressPhrase("__n____________o___");

        firstLetterEnricher.enrichProgressPhrase(currentlyPlayedCase);

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
