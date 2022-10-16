package com.cezaryzal.languageMemo.repository;

import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SentenceJpaRepository extends JpaRepository<MemoItem, Long> {

    List<MemoItem> findByReplayDateLessThanEqual(LocalDate localDate);
    List<MemoItem> findByCorrectAnswer(String correctAnswer);


    //TODO change name method after rename Entity in DB
    //SQL get query works: (input) '2021-12-20' < (replay_date) '2021-12-20'
    @Query(value = "SELECT * FROM sentence WHERE replay_date <= :localDate ORDER BY rand() LIMIT 1",
            nativeQuery = true)
    Optional<MemoItem> findRandomByReplayDateLessThanEqual(@Param("localDate") LocalDate localDate);

    @Query(value = "SELECT * FROM sentence WHERE replay_level <= :limitReplayLevel", nativeQuery = true)
    List<MemoItem> listSentenceByLowerReplayLevel(@Param("limitReplayLevel") int limitReplayLevel);

    @Query(value = "SELECT count(*) FROM sentence WHERE replay_date <= :localDate",
            nativeQuery = true)
    Optional<Integer> getCounterReplayDateLessThanEqual(@Param("localDate") LocalDate localDate);

    @Query(value = "SELECT * FROM sentence WHERE clues LIKE %?1%", nativeQuery = true)
    List<MemoItem> getSentenceListByCluesContainingInsideString(String pattern);

    @Query(value = "SELECT * FROM sentence WHERE correct_answer LIKE %?1%", nativeQuery = true)
    List<MemoItem> getSentenceListByAnswerContainingInsideString(String pattern);

    @Query(value = "SELECT * FROM sentence WHERE correct_answer LIKE %?1% AND clues LIKE %?2%", nativeQuery = true)
    MemoItem getSentenceListByAnswerAndCluesContainingInsideString(String answerPattern, String cluesPattern);
}
