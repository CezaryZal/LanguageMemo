package com.cezaryzal.languageMemo.service.result.filter;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class InputLetterFilterTest {
    private CurrentPlayedMemoItem currentlyPlayedCase;
    private final ReplacementBlankCharacters replacementBlankCharacters;

    @Mock
    public ServiceResultConfig serviceResultConfig;
    @InjectMocks
    private InputLetterFilter inputLetterFilter;


    public InputLetterFilterTest() {
        this.replacementBlankCharacters = new ReplacementBlankCharacters();
    }

    @BeforeEach
    public void setup(){
        Mockito.when(serviceResultConfig.getInitialStringOfLastMemoItem())
                .thenReturn("First try");
        MemoItem sampleMemoItemForTest = getSampleMemoItemForTest();
        currentlyPlayedCase = new CurrentPlayedMemoItem(replacementBlankCharacters, serviceResultConfig);
        currentlyPlayedCase.initialProgressPhrase(sampleMemoItemForTest);
        Mockito.when(serviceResultConfig.getIncorrectLetterMark())
                .thenReturn('_');
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

    private MemoItem getSampleMemoItemForTest(){
        MemoItem testingMemoItem = new MemoItem();
        testingMemoItem.setId(55L);
        testingMemoItem.setClues("bliźniak dom");
        testingMemoItem.setCorrectAnswer("semi-detached house");
        return testingMemoItem;
    }
}
