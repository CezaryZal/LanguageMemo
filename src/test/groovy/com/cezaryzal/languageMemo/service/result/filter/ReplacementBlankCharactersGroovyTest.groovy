package com.cezaryzal.languageMemo.service.result.filter

import spock.lang.Specification

class ReplacementBlankCharactersGroovyTest extends Specification{

    //setup
    def replacementBlankCharacters = new ReplacementBlankCharacters()
    def wordTesting = "flash-cards";
    def specialChars = "*#_&-'!?@%^";
    def correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar = "___________";
    def correctlyReplacementSentenceWithSpecialCharsOnEmptyChar = "_____-_____";

    def "should replace all Sentence to empty chars String"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(wordTesting)
        then:
        emptyCharsAfterReplacement == correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar
    }

    def "shouldn't replace all Sentence to empty chars String or get this same string"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(wordTesting)
        then:
        emptyCharsAfterReplacement != correctlyReplacementSentenceWithSpecialCharsOnEmptyChar
        emptyCharsAfterReplacement != wordTesting
    }

    def "should correctly replace all sentence with special charts on empty char test"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(specialChars)
        then:
        emptyCharsAfterReplacement == correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar
    }

    def "shouldn't correctly replace all sentence with special charts on empty char test"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceAllSentenceOnEmptyChars(specialChars)
        then:
        emptyCharsAfterReplacement != specialChars
    }

    def "should correctly replace letter on empty char test"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceLetterOnEmptyChar(wordTesting)
        then:
        emptyCharsAfterReplacement == correctlyReplacementSentenceWithSpecialCharsOnEmptyChar
    }

    def "shouldn't correctly replace letter on empty char test or this same string"(){
        when:
        def emptyCharsAfterReplacement =
                replacementBlankCharacters.replaceLetterOnEmptyChar(wordTesting)
        then:
        emptyCharsAfterReplacement != correctlyReplacementSentenceWithoutSpecialCharsOnEmptyChar
        emptyCharsAfterReplacement != wordTesting
    }
}
