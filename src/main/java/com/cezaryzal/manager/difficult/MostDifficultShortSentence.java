package com.cezaryzal.manager.difficult;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.repository.SentenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MostDifficultShortSentence {

    private SentenceRepo sentenceRepo;

    @Autowired
    public MostDifficultShortSentence(SentenceRepo sentenceRepo) {
        this.sentenceRepo = sentenceRepo;
    }

    public Map<String, String> getMapWithDifficultSentence(){
        List<Sentence> mostDifficultSentence = sentenceRepo.listSentenceByLowestReplayLevel(1);
        return modifyListToGetMapDifficultSentences(mostDifficultSentence);
    }

    private Map<String, String> modifyListToGetMapDifficultSentences (List<Sentence> mostDifficultSentence){
        Map<String, String> mapDifficultSentences = new HashMap<>();
        for (Sentence currentSentence : mostDifficultSentence){
            mapDifficultSentences.put(currentSentence.getLanguagePol(), currentSentence.getLanguageEng());
        }
        return mapDifficultSentences;
    }
}
