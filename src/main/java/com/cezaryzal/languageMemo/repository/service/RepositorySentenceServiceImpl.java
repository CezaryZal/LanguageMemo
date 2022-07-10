package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.repository.SentenceJpaRepository;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RepositorySentenceServiceImpl extends CrudSentenceRepository implements RepositorySentenceService {

    @Autowired
    public RepositorySentenceServiceImpl(SentenceJpaRepository sentenceJpaRepository) {
        super(sentenceJpaRepository);
    }


    @Override
    public List<Sentence> getByReplayDateLessThanEqual(LocalDate localDate) {
        return sentenceJpaRepository.findByReplayDateLessThanEqual(localDate);
    }

    //SQL get query works: (input) '2021-12-20' < (replay_date) '2021-12-20'
    @Override
    public Optional<Sentence> getRandomByReplayDateLessThanEqual(Object inputDate) {
        LocalDate currentDate;
        if (inputDate.getClass().equals(String.class)){
            currentDate = LocalDate.parse((String) inputDate);
        } else {
            currentDate = (LocalDate) inputDate;
        }
        System.out.println(currentDate.toString());
        return sentenceJpaRepository.findRandomByReplayDateLessThanEqual(currentDate);
    }

    @Override
    public List<Sentence> getListSentenceByLowerReplayLevel(int limitReplayLevel) {
        return sentenceJpaRepository.listSentenceByLowerReplayLevel(limitReplayLevel);
    }

    @Override
    public List<Sentence> getSentenceByCorrectAnswer(String correctAnswer){
        return sentenceJpaRepository.findByCorrectAnswer(correctAnswer.trim());
    }

    @Override
    public Optional<Integer> getCounterReplayDateLessThanEqual(LocalDate localDate) {
        return sentenceJpaRepository.getCounterReplayDateLessThanEqual(localDate);
    }

    @Override
    public List<Sentence> getSentenceListByCluesContainingInsideString(String pattern) {
        return sentenceJpaRepository.getSentenceListByCluesContainingInsideString(pattern.trim());
    }

    @Override
    public List<Sentence> getSentenceListByAnswerContainingInsideString(String pattern) {
        return sentenceJpaRepository.getSentenceListByAnswerContainingInsideString(pattern.trim());
    }

    @Override
    public Sentence getSentenceListByAnswerAndCluesContainingInsideString(String answerPattern, String cluesPattern) {
        return sentenceJpaRepository
                .getSentenceListByAnswerAndCluesContainingInsideString(answerPattern.trim(), cluesPattern.trim());
    }


}
