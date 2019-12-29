package com.cezaryzal.controllers.sentence;

import com.cezaryzal.entity.Sentence;
import com.cezaryzal.manager.service.repository.SentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sentence")
public class SentenceControllerImp implements SentenceController {

    private SentenceService sentenceService;

    @Autowired
    public SentenceControllerImp(SentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping("/{id}")
    @Override
    public Optional<Sentence> findById(@PathVariable Long id) {
        return sentenceService.findById(id);
    }

    @GetMapping("/dates/{date}")
    @Override
    public List<Sentence> findAllByReplayDateLessThanEqual(@PathVariable String date) {
        return sentenceService.findAllByReplayDateLessThanEqual(date);
    }

    @GetMapping("/limit/{number}")
    @Override
    public List<Sentence> getListSentenceByLowestReplayLevel(@PathVariable int number) {
        return sentenceService.getListSentenceByLowestReplayLevel(number);
    }

    @GetMapping("/random/{date}")
    @Override
    public Optional<Sentence> findRandomFirstByReplayDateLessThanEqual(@PathVariable String date){
        return sentenceService.findRandomFirstByReplayDateLessThanEqual(date);
    }

    @GetMapping
    @Override
    public List<Sentence> findAll() {
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

    @DeleteMapping("/{id}")
    @Override
    public void deleteSentenceById(@PathVariable Long id) {
        sentenceService.deleteSentenceById(id);
    }
}
