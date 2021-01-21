package com.cezaryzal.languageMemo.application.translate.difficult;

import com.cezaryzal.languageMemo.application.model.SentenceModel;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
abstract class MapDifficultSentenceCreator {

    protected Map<String, String> modifyListToGetMapDifficultSentences (List<SentenceModel> mostDifficultSentenceModel){
        Map<String, String> mapDifficultSentences = new HashMap<>();
        mostDifficultSentenceModel.forEach(sentence -> mapDifficultSentences
                .put(sentence.getLanguagePol(), sentence.getLanguageEng()));

        return mapDifficultSentences;
    }
}
