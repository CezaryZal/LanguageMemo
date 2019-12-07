package com.cezaryzal.manager.response;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.entity.SentenceDTO;
import com.cezaryzal.manager.phrase.PhraseComparator;
import com.cezaryzal.manager.serviceRepo.SentenceService;
import org.springframework.stereotype.Service;

import java.util.Optional;

//create other entity to input Object from client
@Service
public class ResponseService {

    private Sentence currentlyUseSentence;
    private SentenceDTO inputSentenceDTO;

    private SentenceService sentenceService;
    private PhraseComparator phraseComparator;

    public ResponseService(SentenceService sentenceService, PhraseComparator phraseComparator) {
        this.sentenceService = sentenceService;
        this.phraseComparator = phraseComparator;
    }

    public SentenceDTO resultByInputSentenceDTO (SentenceDTO sentenceDTO){
        setInputSentenceDTO(sentenceDTO);
        setFindSentenceToField(getCurrentlyUseSentenceFromDBById(inputSentenceDTO.getSentenceId()));
        boolean tmpB = checkingCorrectnessOfPhraseTranslation();

        String tmpHeader;
        if(tmpB)
            tmpHeader = "Słowa jednakowe. DZIAŁA!!!!";
        else
            tmpHeader = "Słowa się różnią, ale też DZIAŁĄ!!!";
        SentenceDTO tmpDto = new SentenceDTO(
                1L, tmpHeader, "NULL", "null", 1, "null");

        return tmpDto;
    }

    public void setInputSentenceDTO(SentenceDTO inputSentenceDTO){
        this.inputSentenceDTO = inputSentenceDTO;
    }

    public void setFindSentenceToField(Sentence sentence){
        this.currentlyUseSentence = sentence;
    }

    //Zrobić oddzielną klasę na sprawdzenie poprawności przesłanego SentenceDTO; tutaj nie będzie mozliwości umieszczenia nulla
    public Sentence getCurrentlyUseSentenceFromDBById (Long id){
        Optional<Sentence> searchSentence = sentenceService.findById(id);
        return searchSentence
                .orElseThrow(() -> new RuntimeException("Szukany record na podstawie id nie istnieje"));
    }

    public boolean checkingCorrectnessOfPhraseTranslation(){
        phraseComparator.setInputPhrase(inputSentenceDTO.getInputPhrase());
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
