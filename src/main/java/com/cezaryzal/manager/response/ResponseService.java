package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.serviceRepo.SentenceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {

    private Sentence currentlyUsedSentence;

    private IncorrectAnswer incorrectAnswer;
    private SentenceService sentenceService;
    private CorrectAnswer correctAnswer;

    public ResponseService(IncorrectAnswer incorrectAnswer, SentenceService sentenceService, CorrectAnswer correctAnswer) {
        this.incorrectAnswer = incorrectAnswer;
        this.sentenceService = sentenceService;
        this.correctAnswer = correctAnswer;
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
        correctAnswer.setCurrentlyUsedSentence(currentlyUsedSentence);
        updateCurrentlyUsedSentence(correctAnswer.getUpdatedSentence(inputAnswer));

        return getNextSentenceToShow();
    }

    private SentenceDTO handleIncorrectAnswer(Answer inputAnswer) {
        incorrectAnswer.setCurrentlyUsedSentence(currentlyUsedSentence);
        incorrectAnswer.setInputAnswer(inputAnswer);
//        jesli bedzie blednie po 5 razie powinno zwrocić kolejne "Poprawna odpowiedz: ......"
        return incorrectAnswer.inputValidationBasedOnNumberOfTries();
    }

    private void updateCurrentlyUsedSentence(Sentence updateSentence){
        sentenceService.updateSentence(updateSentence);
    }

    private SentenceDTO getNextSentenceToShow(){
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
