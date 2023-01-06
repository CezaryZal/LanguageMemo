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
public class InputWordsFilterTest {
    private final ReplacementBlankCharacters replacementBlankCharacters;
    private final InputFilter inputWordsFilter;
    private final String stubProgressPhrase = "________________________________________";
    private final String stubCorrectPhrase =  "What's color the Philips remote-control?";

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
        currentlyPlayedCase.setProgressPhrase(stubProgressPhrase);
    }

    @Test
    public void catchCorrectPieceToProgressPhraseFirstTest() {
        String inputPhrases = "What's";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals(currentlyPlayedCase.getProgressPhrase().length(), stubProgressPhrase.length());
        Assertions.assertEquals("What____________________________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("What's__________________________________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseSecondTest() {
        String inputPhrases = "What";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("What____________________________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("________________________________________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseThirdTest() {
        String inputPhrases = "remote";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("_________________________remote_________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("_________________________remote-________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("______ _____ ___ _______ remote-________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseFourthTest() {
        String inputPhrases = "mote";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("___________________________mote_________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("_________________________remote-________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("______ _____ ___ _______ __mote-________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseFifthTest() {
        String inputPhrases = "control";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("________________________________control_", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("_______________________________-control_", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("______ _____ ___ _______ ______-________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseSixthTest() {
        String inputPhrases = "olor ";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("________olor____________________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("_______color____________________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("_______color ___________________________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseMixWordsFirstTest(){
        String inputPhrases = "color remote";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("_______color_____________remote_________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceToProgressPhraseMixWordsSecondTest(){
        String inputPhrases = "color mote";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("_______color_______________mote_________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceWithDiffCaseFirstLetterToProgressPhraseFirstTest() {
        String inputPhrases = "what";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("What____________________________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("_________ched _____", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("___________________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceWithDiffCaseSecondLetterToProgressPhraseFirstTest() {
        String inputPhrases = "what's";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("What____________________________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("_________ched _____", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("___________________", currentlyPlayedCase.getProgressPhrase());
    }

    @Test
    public void catchCorrectPieceWithDiffCaseThirdLetterToProgressPhraseFirstTest() {
        String inputPhrases = " philips ";

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases);

        Assertions.assertEquals("_________________Philips________________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("________________ Philips _______________", currentlyPlayedCase.getProgressPhrase());
        Assertions.assertNotEquals("________________________________________", currentlyPlayedCase.getProgressPhrase());
    }


    private MemoItem getSampleMemoItemForTest(){
        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("Jakiego koloru jest pilot Philips?");
        testingMemoItem.setCorrectAnswer(stubCorrectPhrase);
        return testingMemoItem;
    }
}
