package com.cezaryzal.api;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.manager.service.SentenceService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SentenceControllerImp implements SentenceController {

    private SentenceService sentenceService;

    public SentenceControllerImp(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping("/{id}")
    @Override
    public Optional<Sentence> findById(@PathVariable Long id) {
        return sentenceService.findById(id);
    }

    @GetMapping
    @Override
    public Iterable<Sentence> findAll() {
        return sentenceService.findAll();
    }

    @PostMapping
    @Override
    public Sentence addNewSentence(@RequestBody Sentence sentence) {
        return sentenceService.addNewSentence(sentence);
    }

    @PutMapping
    @Override
    public Sentence updateSentence(@RequestBody Sentence sentence) {
        return sentenceService.updateSentence(sentence);
    }

    @DeleteMapping("/{index}")
    @Override
    public void deleteSentenceById(@PathVariable Long index) {
        sentenceService.deleteSentenceById(index);
    }
}
