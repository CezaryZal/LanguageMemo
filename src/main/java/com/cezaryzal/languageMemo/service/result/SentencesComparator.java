package com.cezaryzal.languageMemo.service.result;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import org.springframework.stereotype.Service;

@Service
public class SentencesComparator {

    public boolean comparingInputPhrasesWithPattern(ComponentDtoInput componentDtoInput,
                                                    Sentence patternSentence){
        return componentDtoInput.getPhrase().equalsIgnoreCase(patternSentence.getCorrectAnswer());
    }

}
