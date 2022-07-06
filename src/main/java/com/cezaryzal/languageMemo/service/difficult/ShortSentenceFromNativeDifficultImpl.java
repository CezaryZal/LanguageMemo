package com.cezaryzal.languageMemo.service.difficult;

import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ShortSentenceFromNativeDifficultImpl extends MapDifficultSentenceCreator implements Difficult{

    private final RepositorySentenceService repositorySentenceService;

    @Autowired
    public ShortSentenceFromNativeDifficultImpl(RepositorySentenceService repositorySentenceService) {
        this.repositorySentenceService = repositorySentenceService;
    }

    @Override
    public Map<String, String> getMapDifficultSentence(){
        List<Sentence> mostDifficultSentence =
                repositorySentenceService.getListSentenceByLowerReplayLevel(1);
        return modifyListToGetMapDifficultSentences(mostDifficultSentence);
    }
}
