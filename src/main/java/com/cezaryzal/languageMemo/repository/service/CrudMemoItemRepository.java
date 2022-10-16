package com.cezaryzal.languageMemo.repository.service;

import com.cezaryzal.languageMemo.repository.SentenceJpaRepository;
import com.cezaryzal.languageMemo.repository.entity.MemoItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
abstract class CrudMemoItemRepository {
    final SentenceJpaRepository sentenceJpaRepository;

    @Autowired
    public CrudMemoItemRepository(SentenceJpaRepository sentenceJpaRepository) {
        this.sentenceJpaRepository = sentenceJpaRepository;
    }

    public Optional<MemoItem> findById(Long id){
        return sentenceJpaRepository.findById(id);
    }

    public List<MemoItem> findAll(){
        return sentenceJpaRepository.findAll();
    }

    public MemoItem addNewMemoItem(MemoItem memoItem){
        return sentenceJpaRepository.save(memoItem);
    }

    public MemoItem updateMemoItem(MemoItem memoItem){
        return sentenceJpaRepository.save(memoItem);
    }

    public void deleteMemoItemById(Long id){
        sentenceJpaRepository.deleteById(id);
    }

}
