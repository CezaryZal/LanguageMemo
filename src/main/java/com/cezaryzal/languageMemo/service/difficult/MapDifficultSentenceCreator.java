package com.cezaryzal.languageMemo.service.difficult;

import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
abstract class MapDifficultSentenceCreator {

    protected Map<String, String> modifyListToGetMapDifficultSentences (List<Sentence> mostDifficultSentence){
        Map<String, String> mapDifficultSentences = new HashMap<>();
        mostDifficultSentence.forEach(sentence -> mapDifficultSentences
                .put(sentence.getClues(), sentence.getCorrectAnswer()));

        return mapDifficultSentences;
    }
}
