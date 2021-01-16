package com.cezaryzal.languageMemo.application.translate.difficult;

import com.cezaryzal.languageMemo.application.model.SentenceModel;

import com.cezaryzal.languageMemo.application.reposervice.SentenceRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShortSentenceFromNativeDifficultImpl implements Difficult{

    private final SentenceRepoService sentenceRepoService;

    @Autowired
    public ShortSentenceFromNativeDifficultImpl(SentenceRepoService sentenceRepoService) {
        this.sentenceRepoService = sentenceRepoService;
    }


    @Override
    public Map<String, String> getMapDifficultSentence(){
        List<SentenceModel> mostDifficultSentenceModel =
                sentenceRepoService.getListSentenceByLowestReplayLevel(1);
        return modifyListToGetMapDifficultSentences(mostDifficultSentenceModel);
    }

    private Map<String, String> modifyListToGetMapDifficultSentences (List<SentenceModel> mostDifficultSentenceModel){
        Map<String, String> mapDifficultSentences = new HashMap<>();
        mostDifficultSentenceModel.forEach(sentence -> mapDifficultSentences.put(sentence.getLanguagePol(), sentence.getLanguageEng()));

        return mapDifficultSentences;
    }
}
