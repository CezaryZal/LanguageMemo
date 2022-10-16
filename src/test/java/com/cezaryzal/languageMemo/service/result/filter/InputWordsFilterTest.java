package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InputWordsFilterTest {
    private final ReplacementBlankCharacters replacementBlankCharacters;
    private final InputFilter inputWordsFilter;
    private final String stubProgressPhrase = "___________________";

    @Mock
    public ServiceResultConfig serviceResultConfig;

    private CurrentPlayedMemoItem currentlyPlayedCase;

    public InputWordsFilterTest() {
        this.replacementBlankCharacters = new ReplacementBlankCharacters();
        this.inputWordsFilter = new InputWordsFilter();
    }

    @BeforeEach
    public void setup(){
        Mockito.when(serviceResultConfig.getInitialStringOfLastMemoItem())
                .thenReturn("First try");
        MemoItem sampleMemoItemForTest = getSampleMemoItemForTest();
        currentlyPlayedCase = new CurrentPlayedMemoItem(replacementBlankCharacters, serviceResultConfig);
        currentlyPlayedCase.initialProgressPhrase(sampleMemoItemForTest);
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


    private MemoItem getSampleMemoItemForTest(){
        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("bliźniak dom");
        testingMemoItem.setCorrectAnswer("semi-detached house");
        return testingMemoItem;
    }
}
