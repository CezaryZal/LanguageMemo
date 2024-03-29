package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import com.cezaryzal.languageMemo.service.result.enrich.Enricher;
import com.cezaryzal.languageMemo.service.result.filter.InputFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class IncorrectAnswer implements ServiceAnswer{
    private final InputFilter inputLetterFilter;
    private final InputFilter inputWordsFilter;
    private final Enricher specialMarkEnricher;
    private final Enricher firstLetterEnricher;
    private final Enricher firstLettersEnricher;
    private final Enricher everySecondLetterEnricher;
    private final Enricher fullLetterEnrich;
    private final CurrentPlayedMemoItem currentPlayedMemoItem;
    private final RepositoryMemoItemService repositoryMemoItemService;
    private final UpdateMemoItemByAnswer updateMemoItemByAnswer;

    @Autowired
    public IncorrectAnswer(@Qualifier("specialMarkEnricher") Enricher specialMarkEnricher,
                           @Qualifier("inputLetterFilter") InputFilter inputLetterFilter,
                           @Qualifier("inputWordsFilter") InputFilter inputWordsFilter,
                           @Qualifier("firstLetterEnricher") Enricher firstLetterEnricher,
                           @Qualifier("firstLettersEnricher") Enricher firstLettersEnricher,
                           @Qualifier("everySecondLetterEnricher") Enricher everySecondLetterEnricher,
                           @Qualifier("fullLetterEnrich") Enricher fullLetterEnrich,
                           CurrentPlayedMemoItem currentPlayedMemoItem,
                           RepositoryMemoItemService repositoryMemoItemService,
                           UpdateMemoItemByAnswer updateMemoItemByAnswer) {
        this.specialMarkEnricher = specialMarkEnricher;
        this.inputLetterFilter = inputLetterFilter;
        this.inputWordsFilter = inputWordsFilter;
        this.firstLetterEnricher = firstLetterEnricher;
        this.firstLettersEnricher = firstLettersEnricher;
        this.everySecondLetterEnricher = everySecondLetterEnricher;
        this.fullLetterEnrich = fullLetterEnrich;
        this.currentPlayedMemoItem = currentPlayedMemoItem;
        this.repositoryMemoItemService = repositoryMemoItemService;
        this.updateMemoItemByAnswer = updateMemoItemByAnswer;
    }

    public MemoItemDtoOutput serviceByMemoItemInput(MemoItemDtoInput memoItemDtoInput) {

        String inputPhrase = memoItemDtoInput.getPhrase();

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentPlayedMemoItem, inputPhrase);
        inputLetterFilter.catchCorrectPieceToProgressPhrase(currentPlayedMemoItem, inputPhrase);

        return validationByOnNumberOfTries(memoItemDtoInput);
    }

    private MemoItemDtoOutput validationByOnNumberOfTries(MemoItemDtoInput memoItemDtoInput){
        int guess = memoItemDtoInput.getGuessCounter();

        switch (guess){
            case 1:
                specialMarkEnricher.enrichProgressPhrase(currentPlayedMemoItem);
                return createMemoItemDTOByValidator(memoItemDtoInput);
            case 2:
                firstLetterEnricher.enrichProgressPhrase(currentPlayedMemoItem);
                return createMemoItemDTOByValidator(memoItemDtoInput);
            case 3:
                firstLettersEnricher.enrichProgressPhrase(currentPlayedMemoItem);
                return createMemoItemDTOByValidator(memoItemDtoInput);
            case 4:
                everySecondLetterEnricher.enrichProgressPhrase(currentPlayedMemoItem);
                return createMemoItemDTOByValidator(memoItemDtoInput);
            case 5:
                fullLetterEnrich.enrichProgressPhrase(currentPlayedMemoItem);
                return createMemoItemDTOByValidator(memoItemDtoInput);
            case 6:
                repositoryMemoItemService.updateMemoItem(
                        updateMemoItemByAnswer.getUpdatedReplayDataMemoItem(memoItemDtoInput));
                return createMemoItemDTOByValidator(memoItemDtoInput);
            default:
                return createMemoItemDTOByValidator(memoItemDtoInput);
        }
    }

    private MemoItemDtoOutput createMemoItemDTOByValidator(MemoItemDtoInput memoItemDtoInput) {
        int guess = memoItemDtoInput.getGuessCounter();
        if (guess < 7){
            guess++;
        }

        return MemoItemDtoOutput.builder()
                .memoItemId(memoItemDtoInput
                        .getMemoItemId())
                .headerToTranslate(currentPlayedMemoItem
                        .getUsedMemoItem()
                        .getClues())
                .progressThroughLastTries(currentPlayedMemoItem
                        .getProgressPhrase())
                .lastMemoItem(currentPlayedMemoItem.getLastMemoItem())
                .isCorrectAnswer(false)
                .guessCounter(guess)
                .hint(currentPlayedMemoItem
                        .getUsedMemoItem()
                        .getHint())
                .build();
    }
}
