package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.model.CurrentPlayedSentenceComponent;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InputWordsFilterTest {
    private CurrentPlayedSentenceComponent currentPlayedSentenceComponent;

    @Autowired
    private ReplacementBlankCharacters replacementBlankCharacters;

    @Before
    public void setup(){
        Sentence sampleSentenceForTest = getSampleSentenceForTest();
        currentPlayedSentenceComponent = new CurrentPlayedSentenceComponent(replacementBlankCharacters);
        currentPlayedSentenceComponent.initialProgressPhrase(sampleSentenceForTest);
    }

    @Test
    public void catchCorrectWordToProgressPhraseFirstTest() {
        InputWordsFilter inputWordsFilter = new InputWordsFilter(currentPlayedSentenceComponent);
        String stubProgressPhrase = "_______________";

        String inputPhrases = "semi";
        String[] splittedInputPhrases = inputPhrases.split("[ _\\-]");
        inputWordsFilter.catchCorrectWordToProgressPhrase(splittedInputPhrases, stubProgressPhrase);
        Assert.assertEquals("semi___________", currentPlayedSentenceComponent.getProgressPhrase());
        Assert.assertNotEquals("semi-__________", currentPlayedSentenceComponent.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseSecondTest() {
        InputWordsFilter inputWordsFilter = new InputWordsFilter(currentPlayedSentenceComponent);
        String stubProgressPhrase = "_______________";
        //TODO w testach integracyjnych przepisać przyapdek
//        String inputSecondPhrases = "se";
//        String[] splittedInputSecondPhrases = inputSecondPhrases.split("[ _\\-]");
//        wordsByProgressPhrase.catchCorrectWordToProgressPhrase(splittedInputSecondPhrases, stubProgressPhrase);
//        Assert.assertEquals("_______________", currentPlayedSentenceComponent.getProgressPhrase());
//        Assert.assertNotEquals("se___________", currentPlayedSentenceComponent.getProgressPhrase());

        String inputPhrases = "sem";
        String[] splittedInputPhrases = inputPhrases.split("[ _\\-]");
        inputWordsFilter.catchCorrectWordToProgressPhrase(splittedInputPhrases, stubProgressPhrase);
        Assert.assertEquals("sem____________", currentPlayedSentenceComponent.getProgressPhrase());
        Assert.assertNotEquals("_____________", currentPlayedSentenceComponent.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseThirdTest() {
        InputWordsFilter inputWordsFilter = new InputWordsFilter(currentPlayedSentenceComponent);
        String stubProgressPhrase = "___________________";

        String inputPhrases = "detached";
        String[] splittedInputPhrases = inputPhrases.split("[ _\\-]");
        inputWordsFilter.catchCorrectWordToProgressPhrase(splittedInputPhrases, stubProgressPhrase);
        Assert.assertEquals("_____detached______", currentPlayedSentenceComponent.getProgressPhrase());
        Assert.assertNotEquals("____-detached______", currentPlayedSentenceComponent.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseFourthTest() {
        InputWordsFilter inputWordsFilter = new InputWordsFilter(currentPlayedSentenceComponent);
        String stubProgressPhrase = "___________________";

        String inputPhrases = "ched";
        String[] splittedInputPhrases = inputPhrases.split("[ _\\-]");
        inputWordsFilter.catchCorrectWordToProgressPhrase(splittedInputPhrases, stubProgressPhrase);
        Assert.assertEquals("_________ched______", currentPlayedSentenceComponent.getProgressPhrase());
        Assert.assertNotEquals("_____detached______", currentPlayedSentenceComponent.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseFifthTest() {
        InputWordsFilter inputWordsFilter = new InputWordsFilter(currentPlayedSentenceComponent);
        String stubProgressPhrase = "___________________";

        String inputPhrases = "house";
        String[] splittedInputPhrases = inputPhrases.split("[ _\\-]");
        inputWordsFilter.catchCorrectWordToProgressPhrase(splittedInputPhrases, stubProgressPhrase);
        Assert.assertEquals("______________house", currentPlayedSentenceComponent.getProgressPhrase());
        Assert.assertNotEquals("____________- house", currentPlayedSentenceComponent.getProgressPhrase());
    }

    @Test
    public void catchCorrectWordToProgressPhraseSixthTest() {
        InputWordsFilter inputWordsFilter = new InputWordsFilter(currentPlayedSentenceComponent);
        String stubProgressPhrase = "___________________";

        String inputPhrases = "ched ";
        String[] splittedInputPhrases = inputPhrases.split("[ _\\-]");
        inputWordsFilter.catchCorrectWordToProgressPhrase(splittedInputPhrases, stubProgressPhrase);
        Assert.assertEquals("_________ched______", currentPlayedSentenceComponent.getProgressPhrase());
        Assert.assertNotEquals("_________ched _____", currentPlayedSentenceComponent.getProgressPhrase());
    }


    private Sentence getSampleSentenceForTest(){
        Sentence testingSentence = new Sentence();
        testingSentence.setId(55L);
        testingSentence.setClues("bliźniak dom");
        testingSentence.setCorrectAnswer("semi-detached house");
        return testingSentence;
    }

}
