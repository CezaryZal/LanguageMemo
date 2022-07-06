package com.cezaryzal.languageMemo.web.controllers.sentence;

import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface SentenceController {

    Optional<Sentence> findById(Long id);
    List<Sentence> findAll();
    Sentence addNewSentence(Sentence sentence);
    Sentence updateSentence(Sentence sentence);
    void deleteSentenceById(Long id);

    List<Sentence> getByReplayDateLessThanEqual(String date);
    Optional<Sentence> getRandomSentenceByReplayDateLessThanEqual(String date);
    List<Sentence> getListSentenceByLowerReplayLevel(int number);
    List<Sentence> getSentenceByCorrectAnswer(String correctAnswer);
}
