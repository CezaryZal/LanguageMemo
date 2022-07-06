package com.cezaryzal.languageMemo.service.create;

import com.cezaryzal.languageMemo.model.AppendSentence;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class SentenceCreator {

    public Sentence createSentenceByAppendedSentenceModel(AppendSentence appendSentence){
         return Sentence.builder()
                 .id(null)
                 .clues(appendSentence.getClues())
                 .correctAnswer(appendSentence.getCorrectAnswer())
                 .hint(appendSentence.getHint())
                 .exampleOfUse(appendSentence.getExampleOfUse())
                 .exampleOfUse(appendSentence.getExampleOfUse())
                 .partOfSpeechClues(appendSentence.getPartOfSpeechClues())
                 .similarWord(appendSentence.getSimilarWord())
                 .replayLevel(0)
                 .replayDate(LocalDate.now())
                 .dateCreated(LocalDate.now())
                 .build();
    }

}
