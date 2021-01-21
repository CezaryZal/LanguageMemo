package com.cezaryzal.languageMemo.application.translate.difficult;

import com.cezaryzal.languageMemo.application.model.SentenceModel;

import com.cezaryzal.languageMemo.application.reposervice.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShortSentenceFromNativeDifficultImpl extends MapDifficultSentenceCreator implements Difficult{

    private final RepoService repoService;

    @Autowired
    public ShortSentenceFromNativeDifficultImpl(@Qualifier("fromNativeRepoServiceImp") RepoService repoService) {
        this.repoService = repoService;
    }


    @Override
    public Map<String, String> getMapDifficultSentence(){
        List<SentenceModel> mostDifficultSentenceModel =
                repoService.getListSentenceByLowestReplayLevel(1);
        return modifyListToGetMapDifficultSentences(mostDifficultSentenceModel);
    }
}
