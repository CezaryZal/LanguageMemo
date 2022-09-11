package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
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
public class InputLetterFilterTest {
    private CurrentPlayedSentenceComponent currentlyPlayedCase;

    @Autowired
    private ReplacementBlankCharacters replacementBlankCharacters;
    @Autowired
    private @Qualifier("inputLetterFilter") InputFilter inputLetterFilter;

    @Before
    public void setup(){
        Sentence sampleSentenceForTest = getSampleSentenceForTest();
        currentlyPlayedCase = new CurrentPlayedSentenceComponent(replacementBlankCharacters);
        currentlyPlayedCase.initialProgressPhrase(sampleSentenceForTest);
    }

    @Test
    public void catchCorrectPieceToProgressPhraseFirstTest() {
        String inputPhrases = "semi";
        String stubProgressPhrase = "___________________";

        inputLetterFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assert.assertEquals("semi_______________", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("semi-______________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseSecondTest() {
        String inputPhrases = "teni";
        currentlyPlayedCase.setProgressPhrase("semi_______________");

        inputLetterFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals("semi_______________", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("teni______________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseThirdTest() {
        String inputPhrases = "semi-detech";
        currentlyPlayedCase.setProgressPhrase("semi_______________");

        inputLetterFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals("semi-det_ch________", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("___________________", currentlyPlayedCase.getProgressPhrase());
    }

    private Sentence getSampleSentenceForTest(){
        Sentence testingSentence = new Sentence();
        testingSentence.setId(55L);
        testingSentence.setClues("bliźniak dom");
        testingSentence.setCorrectAnswer("semi-detached house");
        return testingSentence;
    }
}
