package com.cezaryzal.languageMemo.service.result.filter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ReplacementBlankCharactersTest {
    private final String wordTesting = "flash-cards";
    private final String specialChars = "*_&-'!?@%^";
    private final String correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar = "__________";
    private final String correctlyReplacementSentenceWithSpecialCharsOnEmptyChar = "_____-____";

    @Mock
    ReplacementBlankCharacters replacementBlankCharacters;

    @Test
    public void correctlyReplaceAllSentenceOnEmptyCharTest(){
        Mockito.when(replacementBlankCharacters
                .replaceAllSentenceOnEmptyChars(wordTesting))
                .thenReturn(correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);

        String emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(wordTesting);

        Assert.assertEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);
    }

    @Test
    public void wronglyReplaceAllSentenceOnEmptyCharTest(){
        Mockito.when(replacementBlankCharacters
                .replaceAllSentenceOnEmptyChars(wordTesting))
                .thenReturn("___");

        String emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(wordTesting);

        Assert.assertNotEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);
    }

    @Test
    public void correctlyReplaceAllSentenceWithSpecialChartsOnEmptyCharTest(){
        Mockito.when(replacementBlankCharacters
                .replaceAllSentenceOnEmptyChars(specialChars))
                .thenReturn(correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);

        String emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(specialChars);

        Assert.assertEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);
    }

    @Test
    public void correctlyReplaceLetterOnEmptyCharTest(){
        Mockito.when(replacementBlankCharacters
                .replaceLetterOnEmptyChar(wordTesting))
                .thenReturn(correctlyReplacementSentenceWithSpecialCharsOnEmptyChar);

        String replaceLetterOnEmptyChar =
                replacementBlankCharacters.replaceLetterOnEmptyChar(wordTesting);

        Assert.assertEquals(
                replaceLetterOnEmptyChar,
                correctlyReplacementSentenceWithSpecialCharsOnEmptyChar);
    }

    @Test
    public void wrongReplaceLetterOnEmptyCharTest(){
        Mockito.when(replacementBlankCharacters
                .replaceLetterOnEmptyChar(wordTesting))
                .thenReturn(correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);

        String replaceLetterOnEmptyChar =
                replacementBlankCharacters.replaceLetterOnEmptyChar(wordTesting);

        Assert.assertNotEquals(
                replaceLetterOnEmptyChar,
                correctlyReplacementSentenceWithSpecialCharsOnEmptyChar);
    }


}
