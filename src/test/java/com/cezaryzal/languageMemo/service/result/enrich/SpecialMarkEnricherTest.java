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
public class SpecialMarkEnricherTest {
    private CurrentPlayedMemoItem currentlyPlayedCase;

    @Mock
    public ServiceResultConfig serviceResultConfig;

    @Autowired
    private @Qualifier("specialMarkEnricher") Enricher specialMarkEnricher;
    @Autowired
    private ReplacementBlankCharacters replacementBlankCharacters;

    @Before
    public void setup(){
        Mockito.when(serviceResultConfig.getInitialStringOfLastSentence())
                .thenReturn("First try");
        Sentence sampleSentenceForTest = getSampleSentenceForTest();
        currentlyPlayedCase = new CurrentPlayedMemoItem(replacementBlankCharacters, serviceResultConfig);
        currentlyPlayedCase.initialProgressPhrase(sampleSentenceForTest);
    }

    @Test
    public void enrichProgressPhraseFirstTest(){
        String stubProgressPhrase = "___________________";
        String expectedString = "____-________ _____";
        String unexpectedString = "___________________";
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);

        specialMarkEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void enrichProgressPhraseSecondTest() {
        String expectedString = "s___-________ _____";
        String unexpectedString = "s___-______________";
        currentlyPlayedCase.setProgressPhrase("s__________________");

        specialMarkEnricher.enrichProgressPhrase(currentlyPlayedCase);

        Assert.assertEquals(expectedString, currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals(unexpectedString, currentlyPlayedCase.getProgressPhrase());
    }

    private Sentence getSampleSentenceForTest(){
        Sentence testingSentence = new Sentence();
        testingSentence.setId(55L);
        testingSentence.setClues("bliźniak dom");
        testingSentence.setCorrectAnswer("semi-detached house");
        return testingSentence;
    }
}
