package com.cezaryzal.languageMemo.service.result.filter;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

public class ReplacementBlankCharactersTest {
    private final ReplacementBlankCharacters replacementBlankCharacters = new ReplacementBlankCharacters();

    private final String wordTesting = "flash-cards";
    private final String specialChars = "*#_&-'!?@%^";
    private final String correctlyReplacementMemoItemWithoutSpecialCharsOnEmptyChar = "___________";
    private final String correctlyReplacementMemoItemWithSpecialCharsOnEmptyChar = "_____-_____";


    @Test
    public void replaceAllMemoItemOnEmptyCharTest(){
        String emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllMemoItemOnEmptyChars(wordTesting);

        Assert.assertEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementMemoItemWithoutSpecialCharsOnEmptyChar);

        Assert.assertNotEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementMemoItemWithSpecialCharsOnEmptyChar);
        Assert.assertNotEquals(emptyCharsAfterReplacement, wordTesting);
    }

    @Test
    public void correctlyReplaceAllMemoItemWithSpecialChartsOnEmptyCharTest(){
        String emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllMemoItemOnEmptyChars(specialChars);

        Assert.assertEquals(
                emptyCharsAfterReplacement,
                correctlyReplacementMemoItemWithoutSpecialCharsOnEmptyChar);
        Assert.assertNotEquals(emptyCharsAfterReplacement, specialChars);
    }

    @Test
    public void correctlyReplaceLetterOnEmptyCharTest(){
        String replaceLetterOnEmptyChar =
                replacementBlankCharacters.replaceLetterOnEmptyChar(wordTesting);

        Assert.assertEquals(
                replaceLetterOnEmptyChar,
                correctlyReplacementMemoItemWithSpecialCharsOnEmptyChar);

        Assert.assertNotEquals(
                replaceLetterOnEmptyChar,
                correctlyReplacementMemoItemWithoutSpecialCharsOnEmptyChar);
        Assert.assertNotEquals(replaceLetterOnEmptyChar, wordTesting);
    }
}
