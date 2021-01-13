package com.cezaryzal.languageMemo.manager.difficult;

import com.cezaryzal.languageMemo.application.entity.Sentence;
import com.cezaryzal.languageMemo.repository.SentenceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MostDifficultShortSentence {

    private final SentenceRepo sentenceRepo;

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
        mostDifficultSentence.forEach(sentence -> mapDifficultSentences.put(sentence.getLanguagePol(), sentence.getLanguageEng()));

        return mapDifficultSentences;
    }
}
