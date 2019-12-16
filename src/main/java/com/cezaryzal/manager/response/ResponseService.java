package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.serviceRepo.SentenceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {
    final int MAX_REPLAY_LEVEL_VALUE = 5;
    private Sentence currentlyUsedSentence;

    private IncorrectAnswer incorrectAnswer;
    private SentenceService sentenceService;
    private UpdateSentenceByAnswer updateSentenceByAnswer;

    public ResponseService(IncorrectAnswer incorrectAnswer, SentenceService sentenceService, UpdateSentenceByAnswer updateSentenceByAnswer) {
        this.incorrectAnswer = incorrectAnswer;
        this.sentenceService = sentenceService;
        this.updateSentenceByAnswer = updateSentenceByAnswer;
    }

    public SentenceDTO resultByInputAnswer(Answer inputAnswer) {
        currentlyUsedSentence = getCurrentlyUsedSentenceFromDBById(inputAnswer.getSentenceId());
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(inputAnswer);

        return answerIsCorrect ? handleCorrectAnswer(inputAnswer) : handleIncorrectAnswer(inputAnswer);
    }

    private boolean checkingCorrectnessOfPhraseTranslation(Answer inputAnswer) {
        ResultComparator resultComparator = new ResultComparator(inputAnswer, currentlyUsedSentence);
        return resultComparator.comparingInputPhrasesWithPattern();
    }


    //Zrobić oddzielną klasę na sprawdzenie poprawności przesłanego Answer; tutaj nie będzie mozliwości umieszczenia nulla
    private Sentence getCurrentlyUsedSentenceFromDBById(Long id) {
        Optional<Sentence> searchSentence = sentenceService.findById(id);
        return searchSentence
                .orElseThrow(() -> new RuntimeException("Szukany record na podstawie id nie istnieje"));
    }

    private SentenceDTO handleCorrectAnswer(Answer inputAnswer) {
        updateSentenceByAnswer.setCurrentlyUsedSentence(currentlyUsedSentence);
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(inputAnswer));

        return getNextSentenceToShow();
    }

    private SentenceDTO handleIncorrectAnswer(Answer inputAnswer) {

        if (inputAnswer.getNumberOfTries() <= MAX_REPLAY_LEVEL_VALUE){
            incorrectAnswer.setCurrentlyUsedSentence(currentlyUsedSentence);
            incorrectAnswer.setInputAnswer(inputAnswer);
            return incorrectAnswer.inputValidationBasedOnNumberOfTries();
        }
        updateSentenceByAnswer.setCurrentlyUsedSentence(currentlyUsedSentence);
        updateCurrentlyUsedSentence(updateSentenceByAnswer.getUpdatedSentence(inputAnswer));

        return getNextSentenceToShow();
    }

    private void updateCurrentlyUsedSentence(Sentence updateSentence) {
        sentenceService.updateSentence(updateSentence);
    }

    private SentenceDTO getNextSentenceToShow() {
        return new SentenceDTO(
                2L,
                "Nastepny",
                "Jest progress",
                true,
                0,
                "podpowiedz");
    }


//    public String getPhraseInEnglishFromSentenceById (Long id){
//
//        Optional<Sentence> searchSentence = sentenceService.findById(id);
//
//        return searchSentence
//                .map(Sentence::getLanguageEng)
//                .orElseThrow(() -> new RuntimeException("Brak frazy, nie istnieje record o podanym id"));
//    }

}
