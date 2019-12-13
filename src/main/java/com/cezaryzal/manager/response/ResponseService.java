package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.serviceRepo.SentenceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {

    private Sentence currentlyUseSentence;

    private ResponseValidator responseValidator;
    private SentenceService sentenceService;
    private ResponseCorrect responseCorrect;

    public ResponseService(ResponseValidator responseValidator, SentenceService sentenceService, ResponseCorrect responseCorrect) {
        this.responseValidator = responseValidator;
        this.sentenceService = sentenceService;
        this.responseCorrect = responseCorrect;
    }

    public SentenceDTO resultByInputAnswer(Answer inputAnswer) {
        currentlyUseSentence = getCurrentlyUseSentenceFromDBById(inputAnswer.getSentenceId());
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(inputAnswer);

        return answerIsCorrect ? handleCorrectAnswer(inputAnswer) : handleIncorrectAnswer(inputAnswer);
    }

    private boolean checkingCorrectnessOfPhraseTranslation(Answer inputAnswer) {
        ResultComparator resultComparator = new ResultComparator(inputAnswer, currentlyUseSentence);
        return resultComparator.comparingInputPhrasesWithPattern();
    }


    //Zrobić oddzielną klasę na sprawdzenie poprawności przesłanego SentenceDTO; tutaj nie będzie mozliwości umieszczenia nulla
    private Sentence getCurrentlyUseSentenceFromDBById(Long id) {
        Optional<Sentence> searchSentence = sentenceService.findById(id);
        return searchSentence
                .orElseThrow(() -> new RuntimeException("Szukany record na podstawie id nie istnieje"));
    }

    private SentenceDTO handleCorrectAnswer(Answer inputAnswer) {
        responseCorrect.setCurrentlyUseSentence(currentlyUseSentence);
        sentenceService.updateSentence(responseCorrect.createUpdatedSentence(inputAnswer));
        return getNextSentenceToShow();
    }

    private SentenceDTO handleIncorrectAnswer(Answer inputAnswer) {
        responseValidator.setCurrentlyUseSentence(currentlyUseSentence);
        responseValidator.setInputAnswer(inputAnswer);
        return responseValidator.inputValidationBaseOnNumberOfTries();
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
