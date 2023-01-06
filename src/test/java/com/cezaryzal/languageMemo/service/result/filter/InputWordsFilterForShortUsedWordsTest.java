package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InputWordsFilterForShortUsedWordsTest {
    private final ReplacementBlankCharacters replacementBlankCharacters;
    private final InputFilter inputWordsFilter;


    @Mock
    public ServiceResultConfig serviceResultConfig;

    private CurrentPlayedMemoItem currentlyPlayedCase;

    public InputWordsFilterForShortUsedWordsTest() {
        this.replacementBlankCharacters = new ReplacementBlankCharacters();
        this.inputWordsFilter = new InputWordsFilter();
    }

    @BeforeEach
    public void setup(){
        Mockito.when(serviceResultConfig.getInitialStringOfLastMemoItem())
                .thenReturn("First try");
        currentlyPlayedCase = new CurrentPlayedMemoItem(replacementBlankCharacters, serviceResultConfig);
    }

    @Test
    public void catchCorrectShortUsedWordToProgressPhraseFirstTest() {
        String inputPhrases = "to of";
        String stubProgressPhrase = "______________________________";
        String stubCorrectPhrase =  "there is nothing to ashamed of";

        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("nie ma czego się wstydzić");
        testingMemoItem.setCorrectAnswer(stubCorrectPhrase);

        currentlyPlayedCase.initialProgressPhrase(testingMemoItem);
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);


        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assertions.assertEquals("_________________to_________of", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("______________________________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectShortUsedWordToProgressPhraseSecondTest() {
        String inputPhrases = "Is of";
        String stubProgressPhrase = "______________________________";
        String stubCorrectPhrase =  "there is nothing to ashamed of";

        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("nie ma czego się wstydzić");
        testingMemoItem.setCorrectAnswer(stubCorrectPhrase);

        currentlyPlayedCase.initialProgressPhrase(testingMemoItem);
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);


        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assertions.assertEquals("____________________________of", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("______________________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("______is____________________of", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectShortUsedWordToProgressPhraseThirdTest() {
        String inputPhrases = "I'm";
        String stubProgressPhrase = "________________________";
        String stubCorrectPhrase =  "I'm sill recovering from";

        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("Wciąż wracam do zdrowia");
        testingMemoItem.setCorrectAnswer(stubCorrectPhrase);

        currentlyPlayedCase.initialProgressPhrase(testingMemoItem);
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);


        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assertions.assertEquals("I_______________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("I'm_____________________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectShortUsedWordToProgressPhraseFourthTest() {
        String inputPhrases = "do you";
        String stubProgressPhrase = "______________";
        String stubCorrectPhrase =  "Do you know if";

        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("Czy wiesz że");
        testingMemoItem.setCorrectAnswer(stubCorrectPhrase);

        currentlyPlayedCase.initialProgressPhrase(testingMemoItem);
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);


        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assertions.assertEquals("___you________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("______________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectShortUsedWordToProgressPhraseFifthTest() {
        String inputPhrases = "for one in";
        String stubProgressPhrase = "_____________________";
        String stubCorrectPhrase =  "For once in your life";

        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("Raz w życiu");
        testingMemoItem.setCorrectAnswer(stubCorrectPhrase);

        currentlyPlayedCase.initialProgressPhrase(testingMemoItem);
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);


        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assertions.assertEquals("For______in__________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("_____________________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectShortUsedWordToProgressPhraseSixthTest() {
        String inputPhrases = "i Aim at";
        String stubProgressPhrase = "________";
        String stubCorrectPhrase =  "I aim at";

        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("Raz w życiu");
        testingMemoItem.setCorrectAnswer(stubCorrectPhrase);

        currentlyPlayedCase.initialProgressPhrase(testingMemoItem);
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);


        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assertions.assertEquals("__aim_at", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("________", currentlyPlayedCase.getProgressPhrase());
    }
}
