package com.cezaryzal.languageMemo.service.search;

import com.cezaryzal.languageMemo.model.SentenceNavigator;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class FinderBySimilarSpellings {
    private final RepositorySentenceService repositorySentenceService;

    @Autowired
    public FinderBySimilarSpellings(RepositorySentenceService repositorySentenceService) {
        this.repositorySentenceService = repositorySentenceService;
    }

    //potem z Set<> spradzić
    public Set<Sentence> findSentenceListOfSimilarSpellingsByClues(String sentenceInput, SentenceNavigator navigator){
        Set<Sentence> matchingSentenceListByInput = new HashSet<>();

        Arrays.stream(sentenceInput.split("[ _\\-]"))
                .map(this::parseWordBasedOnLength)
                .forEach(word -> {
                    matchingSentenceListByInput
                            .addAll(getSentenceListByCluesContainingInsideString(word, navigator));
                });
        return matchingSentenceListByInput;
    }

    public String parseWordBasedOnLength(String string){
        int wordLength = string.length();

        if (wordLength<3)
            return string;
        if (wordLength<5)
            return string.substring(0, wordLength-1);
        if (wordLength<6)
            return string.substring(1, wordLength-1);
        if (wordLength<10)
            return string.substring(1, wordLength-2);
        else
            return string.substring(1, wordLength-3);
    }

    private List<Sentence> getSentenceListByCluesContainingInsideString(String word, SentenceNavigator navigator){
        switch (navigator){
            case CLUES:
                return repositorySentenceService.getSentenceListByCluesContainingInsideString(word);
            case CORRECT_ANSWER:
                return repositorySentenceService.getSentenceListByAnswerContainingInsideString(word);
        }
        return null;
    }
}
