package com.cezaryzal.languageMemo.model;

import com.cezaryzal.languageMemo.config.ServiceResultConfig;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.service.result.filter.ReplacementBlankCharacters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
//@ApplicationScope
public class CurrentPlayedMemoItem {

    private final ReplacementBlankCharacters replacementBlankCharacters;
    private final ServiceResultConfig serviceResultConfig;

    private MemoItem usedMemoItem;
    private String lastMemoItem;
    private List<String> splittedCorrectAnswer;
    private String progressPhrase;
    private boolean isInitialize;

    @Autowired
    public CurrentPlayedMemoItem(ReplacementBlankCharacters replacementBlankCharacters,
                                 ServiceResultConfig serviceResultConfig) {
        this.replacementBlankCharacters = replacementBlankCharacters;
        this.serviceResultConfig = serviceResultConfig;
        this.lastMemoItem = serviceResultConfig.getInitialStringOfLastMemoItem();
    }

    public void saveMemoItemInCacheBeforeNextOne(){
        lastMemoItem = usedMemoItem.getClues() + "-" + usedMemoItem.getCorrectAnswer();
    }
    public String getLastMemoItem(){
        return lastMemoItem;
    }

    public void setProgressPhrase(String progressPhrase){
        this.progressPhrase = progressPhrase;
    }

    public MemoItem getUsedMemoItem() {
        return usedMemoItem;
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

    public void initialProgressPhrase(MemoItem usedMemoItem) {
        this.isInitialize = true;
        this.usedMemoItem = usedMemoItem;
        this.progressPhrase = replacementBlankCharacters.replaceAllMemoItemOnEmptyChars(usedMemoItem.getCorrectAnswer());
        this.splittedCorrectAnswer = Arrays
                .asList(usedMemoItem
                        .getCorrectAnswer()
                        .split("[ _\\-]"));

    }
}
