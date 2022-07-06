package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.repository.SentenceRepository;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RepositorySentenceServiceImpl extends CrudSentenceRepository implements RepositorySentenceService {

    @Autowired
    public RepositorySentenceServiceImpl(SentenceRepository sentenceRepository) {
        super(sentenceRepository);
    }


    @Override
    public List<Sentence> getByReplayDateLessThanEqual(LocalDate localDate) {
        return sentenceRepository.findByReplayDateLessThanEqual(localDate);
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
        return sentenceRepository.findRandomByReplayDateLessThanEqual(currentDate);
    }

    @Override
    public List<Sentence> getListSentenceByLowerReplayLevel(int limitReplayLevel) {
        return sentenceRepository.listSentenceByLowerReplayLevel(limitReplayLevel);
    }

    @Override
    public List<Sentence> getSentenceByCorrectAnswer(String correctAnswer){
        return sentenceRepository.findByCorrectAnswer(correctAnswer);
    }

    @Override
    public Optional<Integer> getCounterReplayDateFromNativeLessThanEqual(LocalDate localDate) {
        return sentenceRepository.getCounterReplayDateFromNativeLessThanEqual(localDate);
    }


}
