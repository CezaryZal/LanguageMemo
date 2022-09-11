package com.cezaryzal.languageMemo.model;

import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.service.result.filter.ReplacementBlankCharacters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.Arrays;
import java.util.List;

@Component
@ApplicationScope
public class CurrentPlayedSentenceComponent {

    private final ReplacementBlankCharacters replacementBlankCharacters;

    private Sentence usedSentence;
    private List<String> splittedCorrectAnswer;
    private String progressPhrase;
    private boolean isInitialize;

    @Autowired
    public CurrentPlayedSentenceComponent(ReplacementBlankCharacters replacementBlankCharacters) {
        this.replacementBlankCharacters = replacementBlankCharacters;
    }

    public void setProgressPhrase(String progressPhrase){
        this.progressPhrase = progressPhrase;
    }

    public Sentence getUsedSentence() {
        return usedSentence;
    }

    public String getProgressPhrase() {
        return progressPhrase;
    }

    public List<String> getSplittedCorrectAnswer(){
        return splittedCorrectAnswer;
    }

    public boolean getInitialize(){
        return isInitialize;
    }

    public void changeWasInitiatedToFalse(){
        isInitialize = false;
    }

    public void initialProgressPhrase(Sentence usedSentence) {
        this.isInitialize = true;
        this.usedSentence = usedSentence;
        this.progressPhrase = replacementBlankCharacters.replaceAllSentenceOnEmptyChars(usedSentence.getCorrectAnswer());
        this.splittedCorrectAnswer = Arrays
                .asList(usedSentence
                        .getCorrectAnswer()
                        .split("[ _\\-]"));

    }
}
