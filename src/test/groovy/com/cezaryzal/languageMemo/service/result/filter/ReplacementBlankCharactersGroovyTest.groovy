package com.cezaryzal.languageMemo.service.result.filter

import spock.lang.Specification

class ReplacementBlankCharactersGroovyTest extends Specification{

    //setup
    def replacementBlankCharacters = new ReplacementBlankCharacters()
    def wordTesting = "flash-cards";
    def specialChars = "*#_&-'!?@%^";
    def correctlyReplacementMemoItemWithoutSpecialCharsOnEmptyChar = "___________";
    def correctlyReplacementMemoItemWithSpecialCharsOnEmptyChar = "_____-_____";

    def "should replace all MemoItem to empty chars String"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllMemoItemOnEmptyChars(wordTesting)
        then:
        emptyCharsAfterReplacement == correctlyReplacementMemoItemWithoutSpecialCharsOnEmptyChar
    }

    def "shouldn't replace all MemoItem to empty chars String or get this same string"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllMemoItemOnEmptyChars(wordTesting)
        then:
        emptyCharsAfterReplacement != correctlyReplacementMemoItemWithSpecialCharsOnEmptyChar
        emptyCharsAfterReplacement != wordTesting
    }

    def "should correctly replace all MemoItem with special charts on empty char test"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllMemoItemOnEmptyChars(specialChars)
        then:
        emptyCharsAfterReplacement == correctlyReplacementMemoItemWithoutSpecialCharsOnEmptyChar
    }

    def "shouldn't correctly replace all MemoItem with special charts on empty char test"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllMemoItemOnEmptyChars(specialChars)
        then:
        emptyCharsAfterReplacement != specialChars
    }

    def "should correctly replace letter on empty char test"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceLetterOnEmptyChar(wordTesting)
        then:
        emptyCharsAfterReplacement == correctlyReplacementMemoItemWithSpecialCharsOnEmptyChar
    }

    def "shouldn't correctly replace letter on empty char test or this same string"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceLetterOnEmptyChar(wordTesting)
        then:
        emptyCharsAfterReplacement != correctlyReplacementMemoItemWithoutSpecialCharsOnEmptyChar
        emptyCharsAfterReplacement != wordTesting
    }
}
