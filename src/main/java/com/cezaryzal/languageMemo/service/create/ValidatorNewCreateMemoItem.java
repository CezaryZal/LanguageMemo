package com.cezaryzal.languageMemo.service.create;

import com.cezaryzal.languageMemo.model.ModelToCreateMemoItem;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import com.cezaryzal.languageMemo.service.search.SimilarSpellingsSearcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidatorNewCreateMemoItem {


    private final RepositoryMemoItemService repositoryMemoItemService;
    private final SimilarSpellingsSearcher similarSpellingsSearcher;

    @Autowired
    public ValidatorNewCreateMemoItem(RepositoryMemoItemService repositoryMemoItemService,
                                      SimilarSpellingsSearcher similarSpellingsSearcher) {
        this.repositoryMemoItemService = repositoryMemoItemService;
        this.similarSpellingsSearcher = similarSpellingsSearcher;
    }

    public boolean isValidObjectToCreateNewMemoItem(ModelToCreateMemoItem modelToCreateMemoItem){
        MemoItem similarMemoItem = getSimilarMemoItemToNewOne(
                                            modelToCreateMemoItem.getCorrectAnswer(),
                                            modelToCreateMemoItem.getClues());

        return similarMemoItem == null || !isNotBlankMemoItem(modelToCreateMemoItem);
    }

    private MemoItem getSimilarMemoItemToNewOne(String answerPattern, String cluesPattern) {
        return repositoryMemoItemService.getMemoItemListByAnswerAndCluesContainingInsideString(
                similarSpellingsSearcher.parseWordBasedOnLength(answerPattern),
                similarSpellingsSearcher.parseWordBasedOnLength(cluesPattern));
    }

    //    TODO Fine pattern to validation method
    private boolean isNotBlankMemoItem(ModelToCreateMemoItem modelToCreateMemoItem){
        if (modelToCreateMemoItem.getCorrectAnswer().isBlank() ||
                modelToCreateMemoItem.getCorrectAnswer().isEmpty() ||
                modelToCreateMemoItem.getCorrectAnswer() == null){
            return false;
        } else if (modelToCreateMemoItem.getClues().isBlank() ||
                modelToCreateMemoItem.getClues().isEmpty() ||
                modelToCreateMemoItem.getClues() == null){
            return false;
        } else if (modelToCreateMemoItem.getHint().isBlank() ||
                modelToCreateMemoItem.getHint().isEmpty() ||
                modelToCreateMemoItem.getHint() == null){
            return false;
        } else return !modelToCreateMemoItem.getExampleOfUse().isBlank() &&
                !modelToCreateMemoItem.getExampleOfUse().isEmpty() &&
                modelToCreateMemoItem.getExampleOfUse() != null;
    }
}
