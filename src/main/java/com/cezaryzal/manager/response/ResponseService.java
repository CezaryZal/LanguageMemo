package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Answer;
import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.phrase.PhraseComparator;
import com.cezaryzal.manager.serviceRepo.SentenceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ResponseService {

//    private Answer inputAnswer;
    private Sentence currentlyUseSentence;
    private SentenceDTO sentenceDTO;

    private SentenceService sentenceService;
    private PhraseComparator phraseComparator;

    public ResponseService(SentenceService sentenceService, PhraseComparator phraseComparator) {
        this.sentenceService = sentenceService;
        this.phraseComparator = phraseComparator;
    }

    public SentenceDTO resultByInputAnswer (Answer inputAnswer){
//        setInputAnswer(inputAnswer);
        setFindSentenceToField(getCurrentlyUseSentenceFromDBById(inputAnswer.getSentenceId()));
        boolean tmpB = checkingCorrectnessOfPhraseTranslation(inputAnswer.getInputPhrase());

        String tmpHeader;
        if(tmpB)
            tmpHeader = "Słowa jednakowe. DZIAŁA!!!!";
        else
            tmpHeader = "Słowa się różnią, ale też DZIAŁĄ!!!";
        SentenceDTO tmpDto = new SentenceDTO(
                1L, tmpHeader, "NULL", 1, "null");

        return tmpDto;
    }

    private void setInputSentenceDTO(SentenceDTO sentenceDTO){
        this.sentenceDTO = sentenceDTO;
    }

    private void setFindSentenceToField(Sentence sentence){
        this.currentlyUseSentence = sentence;
    }

//    private void setInputAnswer(Answer inputAnswer){
//        this.inputAnswer = inputAnswer;
//    }

    //Zrobić oddzielną klasę na sprawdzenie poprawności przesłanego SentenceDTO; tutaj nie będzie mozliwości umieszczenia nulla
    public Sentence getCurrentlyUseSentenceFromDBById (Long id){
        Optional<Sentence> searchSentence = sentenceService.findById(id);
        return searchSentence
                .orElseThrow(() -> new RuntimeException("Szukany record na podstawie id nie istnieje"));
    }

    public boolean checkingCorrectnessOfPhraseTranslation(String inputPhrase){
        phraseComparator.setInputPhrase(inputPhrase);
        phraseComparator.setCorrectPhrase(currentlyUseSentence.getLanguageEng());
        return phraseComparator.comparingPhrases();
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
