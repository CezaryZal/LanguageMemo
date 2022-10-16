package com.cezaryzal.languageMemo.service.create;

import com.cezaryzal.languageMemo.model.ModelToCreateMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MemoItemCreator {

    public MemoItem createMemoItem(ModelToCreateMemoItem modelToCreateMemoItem){
         return MemoItem.builder()
                 .id(null)
                 .clues(modelToCreateMemoItem.getClues())
                 .correctAnswer(modelToCreateMemoItem.getCorrectAnswer())
                 .hint(modelToCreateMemoItem.getHint())
                 .exampleOfUse(modelToCreateMemoItem.getExampleOfUse())
                 .exampleOfUse(modelToCreateMemoItem.getExampleOfUse())
                 .partOfSpeechClues(modelToCreateMemoItem.getPartOfSpeechClues())
                 .similarWord(modelToCreateMemoItem.getSimilarWord())
                 .replayLevel(0)
                 .replayDate(LocalDate.now())
                 .dateCreated(LocalDate.now())
                 .build();
    }

}
