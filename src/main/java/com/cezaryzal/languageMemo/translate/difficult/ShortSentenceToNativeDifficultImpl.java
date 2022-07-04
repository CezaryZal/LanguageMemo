package com.cezaryzal.languageMemo.translate.difficult;

import com.cezaryzal.languageMemo.model.SentenceModel;
import com.cezaryzal.languageMemo.repository.service.RepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShortSentenceToNativeDifficultImpl extends MapDifficultSentenceCreator implements Difficult{

    private final RepoService repoService;

    @Autowired
    public ShortSentenceToNativeDifficultImpl(@Qualifier("toNativeRepoServiceImp") RepoService repoService) {
        this.repoService = repoService;
    }


    @Override
    public Map<String, String> getMapDifficultSentence() {
        List<SentenceModel> mostDifficultSentenceModel =
                repoService.getListSentenceByLowestReplayLevel(1);
        return modifyListToGetMapDifficultSentences(mostDifficultSentenceModel);
    }
}
