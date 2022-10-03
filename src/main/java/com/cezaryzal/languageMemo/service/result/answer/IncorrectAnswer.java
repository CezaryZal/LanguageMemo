package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;
import com.cezaryzal.languageMemo.model.CurrentPlayedMemoItem;
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

    @Autowired
    public IncorrectAnswer(@Qualifier("specialMarkEnricher") Enricher specialMarkEnricher,
                           @Qualifier("inputLetterFilter") InputFilter inputLetterFilter,
                           @Qualifier("inputWordsFilter") InputFilter inputWordsFilter,
                           @Qualifier("firstLetterEnricher") Enricher firstLetterEnricher,
                           @Qualifier("firstLettersEnricher") Enricher firstLettersEnricher,
                           @Qualifier("everySecondLetterEnricher") Enricher everySecondLetterEnricher,
                           @Qualifier("fullLetterEnrich") Enricher fullLetterEnrich,
                           CurrentPlayedMemoItem currentPlayedMemoItem) {
        this.specialMarkEnricher = specialMarkEnricher;
        this.inputLetterFilter = inputLetterFilter;
        this.inputWordsFilter = inputWordsFilter;
        this.firstLetterEnricher = firstLetterEnricher;
        this.firstLettersEnricher = firstLettersEnricher;
        this.everySecondLetterEnricher = everySecondLetterEnricher;
        this.fullLetterEnrich = fullLetterEnrich;
        this.currentPlayedMemoItem = currentPlayedMemoItem;
    }

    public MemoItemDtoOutput serviceByMemoItemInput(MemoItemDtoInput memoItemDtoInput) {

        String inputPhrase = memoItemDtoInput.getPhrase();

        inputWordsFilter.catchCorrectPieceToProgressPhrase(currentPlayedMemoItem, inputPhrase);
        inputLetterFilter.catchCorrectPieceToProgressPhrase(currentPlayedMemoItem, inputPhrase);

        return validationByOnNumberOfTries(memoItemDtoInput);
    }

    private MemoItemDtoOutput validationByOnNumberOfTries(MemoItemDtoInput memoItemDtoInput){
        int numberOfTries = memoItemDtoInput.getNumberOfTries();

        switch (numberOfTries){
            case 1:
                specialMarkEnricher.enrichProgressPhrase(currentPlayedMemoItem);
                return createSentenceDTOByValidator(memoItemDtoInput);
            case 2:
                firstLetterEnricher.enrichProgressPhrase(currentPlayedMemoItem);
                return createSentenceDTOByValidator(memoItemDtoInput);
            case 3:
                firstLettersEnricher.enrichProgressPhrase(currentPlayedMemoItem);
                return createSentenceDTOByValidator(memoItemDtoInput);
            case 4:
                everySecondLetterEnricher.enrichProgressPhrase(currentPlayedMemoItem);
                return createSentenceDTOByValidator(memoItemDtoInput);
            case 5:
                fullLetterEnrich.enrichProgressPhrase(currentPlayedMemoItem);
                //TODO zapisać do store nieudaną próbę (ostatnia szansa)
                return createSentenceDTOByValidator(memoItemDtoInput);
            default:
                return createSentenceDTOByValidator(memoItemDtoInput);
        }
    }

    private MemoItemDtoOutput createSentenceDTOByValidator(MemoItemDtoInput memoItemDtoInput) {
        int numberOfTries = memoItemDtoInput.getNumberOfTries();
        if (numberOfTries < 6){
            numberOfTries++;
        }

        return MemoItemDtoOutput.builder()
                .sentenceId(memoItemDtoInput
                        .getSentenceId())
                .headerToTranslate(currentPlayedMemoItem
                        .getUsedSentence()
                        .getClues())
                .progressThroughLastTries(currentPlayedMemoItem
                        .getProgressPhrase())
                .isCorrectAnswer(false)
                .numberOfTries(numberOfTries)
                .hint(currentPlayedMemoItem
                        .getUsedSentence()
                        .getHint())
                .build();
    }
}
