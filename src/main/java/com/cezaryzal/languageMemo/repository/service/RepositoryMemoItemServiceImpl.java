package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.repository.SentenceJpaRepository;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RepositoryMemoItemServiceImpl extends CrudMemoItemRepository implements RepositoryMemoItemService {

    @Autowired
    public RepositoryMemoItemServiceImpl(SentenceJpaRepository sentenceJpaRepository) {
        super(sentenceJpaRepository);
    }


    @Override
    public List<MemoItem> getByReplayDateLessThanEqual(LocalDate localDate) {
        return sentenceJpaRepository.findByReplayDateLessThanEqual(localDate);
    }

    //SQL get query works: (input) '2021-12-20' < (replay_date) '2021-12-20'
    @Override
    public Optional<MemoItem> getRandomByReplayDateLessThanEqual(Object inputDate) {
        LocalDate currentDate;
        if (inputDate.getClass().equals(String.class)){
            currentDate = LocalDate.parse((String) inputDate);
        } else {
            currentDate = (LocalDate) inputDate;
        }
        Optional<MemoItem> randomMemoItem = sentenceJpaRepository.findRandomByReplayDateLessThanEqual(currentDate);
        log.debug("Random MemoItem by replay date less:\n\n\n" + randomMemoItem.toString());
        return randomMemoItem;
    }

    @Override
    public List<MemoItem> getListMemoItemByLowerReplayLevel(int limitReplayLevel) {
        return sentenceJpaRepository.listSentenceByLowerReplayLevel(limitReplayLevel);
    }

    @Override
    public List<MemoItem> getMemoItemByCorrectAnswer(String correctAnswer){
        return sentenceJpaRepository.findByCorrectAnswer(correctAnswer.trim());
    }

    @Override
    public Optional<Integer> getCounterReplayDateLessThanEqual(LocalDate localDate) {
        return sentenceJpaRepository.getCounterReplayDateLessThanEqual(localDate);
    }

    @Override
    public List<MemoItem> getMemoItemListByCluesContainingInsideString(String pattern) {
        return sentenceJpaRepository.getSentenceListByCluesContainingInsideString(pattern.trim());
    }

    @Override
    public List<MemoItem> getMemoItemListByAnswerContainingInsideString(String pattern) {
        return sentenceJpaRepository.getSentenceListByAnswerContainingInsideString(pattern.trim());
    }

    @Override
    public MemoItem getMemoItemListByAnswerAndCluesContainingInsideString(String answerPattern, String cluesPattern) {
        return sentenceJpaRepository
                .getSentenceListByAnswerAndCluesContainingInsideString(answerPattern.trim(), cluesPattern.trim());
    }


}
