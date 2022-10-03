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
    private final RepositorySentenceService repositorySentenceService;

    @Autowired
    public SentenceControllerImpl(RepositorySentenceService repositorySentenceService) {
        this.repositorySentenceService = repositorySentenceService;
    }

    @GetMapping("/{id}")
    @Override
    public Optional<Sentence> findById(@PathVariable Long id) {
        return repositorySentenceService.findById(id);
    }
    @Override
    public List<Sentence> findAll() {
        return repositorySentenceService.findAll();
    }
    @PostMapping
    @Override
    public Sentence addNewSentence(@RequestBody Sentence sentence) {
        return repositorySentenceService.addNewSentence(sentence);
    }
    @PutMapping
    @Override
    public Sentence updateSentence(@RequestBody Sentence sentence) {
        return repositorySentenceService.updateSentence(sentence);
    }
    @DeleteMapping("/{id}")
    @Override
    public void deleteSentenceById(@PathVariable Long id) {
        repositorySentenceService.deleteSentenceById(id);
    }

    //YYYY-MM-DD; {date}<replay_date
    @GetMapping("/dates/{date}")
    @Override
    public List<Sentence> getByReplayDateLessThanEqual(@PathVariable String date) {
        return repositorySentenceService.getByReplayDateLessThanEqual(LocalDate.parse(date));
    }
    @GetMapping("/random/{date}")
    @Override
    public Optional<Sentence> getRandomSentenceByReplayDateLessThanEqual(@PathVariable String date) {
        return repositorySentenceService.getRandomByReplayDateLessThanEqual(date);
    }
    @GetMapping("/limit/{number}")
    @Override
    public List<Sentence> getListSentenceByLowerReplayLevel(@PathVariable int number) {
        return repositorySentenceService.getListSentenceByLowerReplayLevel(number);
    }
    @GetMapping("/answer/{correctAnswer}")
    @Override
    public List<Sentence> getSentenceByCorrectAnswer(String correctAnswer) {
        return repositorySentenceService.getSentenceByCorrectAnswer(correctAnswer);
    }

    @GetMapping("/part-word/clues/{phrase}")
    @Override
    public List<Sentence> getSentenceListByCluesContainingInsideString(@PathVariable String inside){
        return repositorySentenceService.getSentenceListByCluesContainingInsideString(inside);
    }

    @GetMapping("/part-word/correct_answer/{phrase}")
    @Override
    public List<Sentence> getSentenceListByAnswerContainingInsideString(@PathVariable String inside) {
        return repositorySentenceService.getSentenceListByAnswerContainingInsideString(inside);
    }


}
