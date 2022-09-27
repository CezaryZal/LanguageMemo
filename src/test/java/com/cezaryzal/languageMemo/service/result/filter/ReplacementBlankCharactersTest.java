package com.cezaryzal.languageMemo.service.result.filter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ReplacementBlankCharactersTest {
    private final ReplacementBlankCharacters replacementBlankCharacters = new ReplacementBlankCharacters();

    private final String wordTesting = "flash-cards";
    private final String specialChars = "*#_&-'!?@%^";
    private final String correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar = "___________";
    private final String correctlyReplacementSentenceWithSpecialCharsOnEmptyChar = "_____-_____";


    @Test
    public void replaceAllSentenceOnEmptyCharTest(){
        String emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(wordTesting);

        Assert.assertEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);

        Assert.assertNotEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementSentenceWithSpecialCharsOnEmptyChar);
        Assert.assertNotEquals(emptyCharsAfterReplacement, wordTesting);
    }

    @Test
    public void correctlyReplaceAllSentenceWithSpecialChartsOnEmptyCharTest(){
        String emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(specialChars);

        Assert.assertEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);
        Assert.assertNotEquals(emptyCharsAfterReplacement, specialChars);
    }

    @Test
    public void correctlyReplaceLetterOnEmptyCharTest(){
        String replaceLetterOnEmptyChar =
                replacementBlankCharacters.replaceLetterOnEmptyChar(wordTesting);

        Assert.assertEquals(
                replaceLetterOnEmptyChar,
                correctlyReplacementSentenceWithSpecialCharsOnEmptyChar);

        Assert.assertNotEquals(
                replaceLetterOnEmptyChar,
                correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar);
        Assert.assertNotEquals(replaceLetterOnEmptyChar, wordTesting);
    }
}
