package com.cezaryzal.languageMemo.service.result.filter

import com.cezaryzal.languageMemo.config.ServiceResultConfig
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem
import com.cezaryzal.languageMemo.repository.entity.Sentence
import spock.lang.Specification

class InputLetterFilterTestGroovyTest extends Specification{
    def replacementBlankCharacters
    def currentlyPlayedCase

    def setup(){
        replacementBlankCharacters = new ReplacementBlankCharacters()
        currentlyPlayedCase  = new CurrentPlayedMemoItem(replacementBlankCharacters, serviceResultConfig)
        currentlyPlayedCase.initialProgressPhrase(getSampleSentenceForTest())
        given:
        serviceResultConfig.getIncorrectLetter() >> '_'
    }

    def serviceResultConfig = Mock(ServiceResultConfig)
    def inputLetterFilter = new InputLetterFilter(serviceResultConfig)


    def "catch correct piece to progress phrase first test"(){
        given:
        def inputPhrases = "semi"
        def stubProgressPhrase = "___________________"
        when:
        inputLetterFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases)
        then:
        currentlyPlayedCase.getProgressPhrase().length() == stubProgressPhrase.length()
        "semi_______________" == currentlyPlayedCase.getProgressPhrase()
        "semi-______________" != currentlyPlayedCase.getProgressPhrase()
    }

    def "catch correct piece to progress phrase second test"(){
        given:
        def inputPhrases = "teni"
        currentlyPlayedCase.setProgressPhrase("semi_______________")
        when:
        inputLetterFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases)
        then:
        "semi_______________" == currentlyPlayedCase.getProgressPhrase()
        "teni______________" != currentlyPlayedCase.getProgressPhrase()
    }

    def "catch correct piece to progress phrase third test"(){
        given:
        def inputPhrases = "semi-detech"
        currentlyPlayedCase.setProgressPhrase("semi_______________")
        when:
        inputLetterFilter.catchCorrectPieceToProgressPhrase(currentlyPlayedCase, inputPhrases)
        then:
        "semi-det_ch________" == currentlyPlayedCase.getProgressPhrase()
        "___________________" != currentlyPlayedCase.getProgressPhrase()
    }


    def getSampleSentenceForTest(){
        Sentence testingSentence = new Sentence();
        testingSentence.setId(55L);
        testingSentence.setClues("bliźniak dom");
        testingSentence.setCorrectAnswer("semi-detached house");
        return testingSentence;
    }
}
