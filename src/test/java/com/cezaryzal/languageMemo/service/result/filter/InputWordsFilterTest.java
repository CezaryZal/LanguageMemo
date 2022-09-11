package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InputWordsFilterTest {
    private CurrentPlayedSentenceComponent currentlyPlayedCase;
    private final String stubProgressPhrase = "___________________";

    @Autowired
    private ReplacementBlankCharacters replacementBlankCharacters;
    @Autowired
    private @Qualifier("inputWordsFilter") InputFilter inputWordsFilter;

    @Before
    public void setup(){
        Sentence sampleSentenceForTest = getSampleSentenceForTest();
        currentlyPlayedCase = new CurrentPlayedSentenceComponent(replacementBlankCharacters);
        currentlyPlayedCase.initialProgressPhrase(sampleSentenceForTest);
    }

    @BeforeEach
    public void beforeEachTest(){
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);
    }

    @Test
    public void catchCorrectPieceToProgressPhraseFirstTest() {
        String inputPhrases = "semi";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assert.assertEquals("semi_______________", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("semi-______________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseSecondTest() {
        //TODO w testach integracyjnych przepisać przyapdek
//        String inputSecondPhrases = "se";
//        String[] splittedInputSecondPhrases = inputSecondPhrases.split("[ _\\-]");
//        wordsByProgressPhrase.catchCorrectWordToProgressPhrase(splittedInputSecondPhrases, stubProgressPhrase);
//        Assert.assertEquals("_______________", currentPlayedSentenceComponent.getProgressPhrase());
//        Assert.assertNotEquals("se___________", currentPlayedSentenceComponent.getProgressPhrase());

        String inputPhrases = "sem";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals("sem________________", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("_________________", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("semi_____________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseThirdTest() {
        String inputPhrases = "detached";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals("_____detached______", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("____-detached______", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("____-________ _____", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseFourthTest() {
        String inputPhrases = "ched";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals("_________ched______", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("_____detached______", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("_____detached______", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseFifthTest() {
        String inputPhrases = "house";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals("______________house", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("____________- house", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseSixthTest() {
        String inputPhrases = "ched ";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assert.assertEquals("_________ched______", currentlyPlayedCase.getProgressPhrase());
        Assert.assertNotEquals("_________ched _____", currentlyPlayedCase.getProgressPhrase());
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
