package com.cezaryzal.languageMemo.service.result.service;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import com.cezaryzal.languageMemo.service.result.answer.CorrectAnswer;
import com.cezaryzal.languageMemo.service.result.answer.IncorrectAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultService extends CheckingMemoItem {

    private final IncorrectAnswer incorrectAnswer;
    private final CorrectAnswer correctAnswer;
    private final RepositoryMemoItemService repositoryMemoItemService;
    private final CurrentPlayedMemoItem currentPlayedMemoItem;

    @Autowired
    public ResultService(IncorrectAnswer incorrectAnswer,
                         CorrectAnswer correctAnswer,
                         RepositoryMemoItemService repositoryMemoItemService,
                         CurrentPlayedMemoItem currentPlayedMemoItem) {
        this.incorrectAnswer = incorrectAnswer;
        this.correctAnswer = correctAnswer;
        this.repositoryMemoItemService = repositoryMemoItemService;
        this.currentPlayedMemoItem = currentPlayedMemoItem;
    }


    public MemoItemDtoOutput resultByInputAnswer(MemoItemDtoInput memoItemDtoInput) {

        if (memoItemDtoInput.getGuessCounter() == 0){
            MemoItem currentlyUsedMemoItem = repositoryMemoItemService
                    .findById(memoItemDtoInput.getMemoItemId())
                    .orElseThrow();
            currentPlayedMemoItem.initialProgressPhrase(currentlyUsedMemoItem);
        }
        boolean answerIsCorrect = checkingCorrectnessOfPhraseTranslation(
                memoItemDtoInput,
                currentPlayedMemoItem.getUsedMemoItem()
        );
        return answerIsCorrect ?
                correctAnswer.serviceByMemoItemInput(memoItemDtoInput) :
                incorrectAnswer.serviceByMemoItemInput(memoItemDtoInput);
    }
}
