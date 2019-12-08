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
    private SentenceDTO sentenceDTO;

    private ResponseValidator responseValidator;
    private ResultComparator resultComparator;
    private SentenceService sentenceService;

    public ResponseService(ResponseValidator responseValidator, ResultComparator resultComparator, SentenceService sentenceService) {
        this.responseValidator = responseValidator;
        this.resultComparator = resultComparator;
        this.sentenceService = sentenceService;
    }

    public SentenceDTO resultByInputAnswer(Answer inputAnswer) {
        currentlyUseSentence = getCurrentlyUseSentenceFromDBById(inputAnswer.getSentenceId());
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(inputAnswer);


        SentenceDTO tmpDto = answerIsCorrect?createTmpSentence(): createSentenceDTOByInputAnswer(inputAnswer);

        return tmpDto;
    }

    private boolean checkingCorrectnessOfPhraseTranslation(Answer inputAnswer) {
        resultComparator.setInputAnswer(inputAnswer);
        resultComparator.setPatternSentence(currentlyUseSentence);
        return resultComparator.comparingInputPhrasesWithPattern();
    }


    //Zrobić oddzielną klasę na sprawdzenie poprawności przesłanego SentenceDTO; tutaj nie będzie mozliwości umieszczenia nulla
    private Sentence getCurrentlyUseSentenceFromDBById(Long id) {
        Optional<Sentence> searchSentence = sentenceService.findById(id);
        return searchSentence
                .orElseThrow(() -> new RuntimeException("Szukany record na podstawie id nie istnieje"));
    }

    private SentenceDTO createTmpSentence(){
        return new SentenceDTO(
                1L, "Słowa jednakowe. DZIAŁA!!!!", "NULL", true, 1, "null");
    }

    private SentenceDTO createSentenceDTOByInputAnswer(Answer inputAnswer){
        responseValidator.setCurrentlyUseSentence(currentlyUseSentence);
        return responseValidator.createSentenceDTOByInputAnswer(inputAnswer);
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
