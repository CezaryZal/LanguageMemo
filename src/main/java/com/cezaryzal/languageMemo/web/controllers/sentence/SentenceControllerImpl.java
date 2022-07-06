package com.cezaryzal.languageMemo.web.controllers.sentence;

import com.cezaryzal.languageMemo.repository.entity.Sentence;
import com.cezaryzal.languageMemo.repository.service.RepositorySentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/sentence")
public class SentenceControllerImpl implements SentenceController{
    private final RepositorySentenceService sentenceService;

    @Autowired
    public SentenceControllerImpl(RepositorySentenceService sentenceService) {
        this.sentenceService = sentenceService;
    }

    @GetMapping("/{id}")
    @Override
    public Optional<Sentence> findById(@PathVariable Long id) {
        return sentenceService.findById(id);
    }
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

    //YYYY-MM-DD; {date}<replay_date
    @GetMapping("/dates/{date}")
    @Override
    public List<Sentence> getByReplayDateLessThanEqual(@PathVariable String date) {
        return sentenceService.getByReplayDateLessThanEqual(LocalDate.parse(date));
    }
    @GetMapping("/random/{date}")
    @Override
    public Optional<Sentence> getRandomSentenceByReplayDateLessThanEqual(@PathVariable String date) {
        return sentenceService.getRandomByReplayDateLessThanEqual(date);
    }
    @GetMapping("/limit/{number}")
    @Override
    public List<Sentence> getListSentenceByLowerReplayLevel(@PathVariable int number) {
        return sentenceService.getListSentenceByLowerReplayLevel(number);
    }
    @GetMapping("/answer/{correctAnswer}")
    @Override
    public List<Sentence> getSentenceByCorrectAnswer(String correctAnswer) {
        return sentenceService.getSentenceByCorrectAnswer(correctAnswer);
    }


}
