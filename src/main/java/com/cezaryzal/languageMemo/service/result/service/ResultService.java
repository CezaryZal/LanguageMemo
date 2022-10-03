package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import com.cezaryzal.languageMemo.service.result.answer.CorrectAnswer;
import com.cezaryzal.languageMemo.service.result.answer.IncorrectAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService extends CheckingSentences{

    private final IncorrectAnswer incorrectAnswer;
    private final CorrectAnswer correctAnswer;
    private final RepositorySentenceService repositorySentenceService;
    private final CurrentPlayedMemoItem currentlyPlayedCase;

    @Autowired
    public ResultService(IncorrectAnswer incorrectAnswer,
                         CorrectAnswer correctAnswer,
                         RepositorySentenceService repositorySentenceService,
                         CurrentPlayedMemoItem currentlyPlayedCase) {
        this.incorrectAnswer = incorrectAnswer;
        this.correctAnswer = correctAnswer;
        this.repositorySentenceService = repositorySentenceService;
        this.currentlyPlayedCase = currentlyPlayedCase;
    }


    public MemoItemDtoOutput resultByInputAnswer(MemoItemDtoInput memoItemDtoInput) {

        if (memoItemDtoInput.getNumberOfTries() == 0){
            Sentence currentlyUsedSentence = repositorySentenceService
                    .findById(memoItemDtoInput.getSentenceId())
                    .orElseThrow();

            currentlyPlayedCase.initialProgressPhrase(currentlyUsedSentence);
        }
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(
                memoItemDtoInput,
                                                                currentlyPlayedCase.getUsedSentence());

        return answerIsCorrect ?
                correctAnswer.serviceByMemoItemInput(memoItemDtoInput) :
                incorrectAnswer.serviceByMemoItemInput(memoItemDtoInput);
    }
}
