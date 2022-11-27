package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.repository.service.RepositoryMemoItemService;
import com.cezaryzal.languageMemo.service.result.service.NextMemoItemDtoOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CorrectAnswerTest {
    private final CorrectAnswer correctAnswer;

    @Mock
    private RepositoryMemoItemService repositoryMemoItemService;
    @Mock
    private UpdateMemoItemByAnswer updateMemoItemByAnswer;
    @Mock
    private NextMemoItemDtoOutput nextMemoItemDtoOutput;


    public CorrectAnswerTest() {
        this.correctAnswer = new CorrectAnswer(repositoryMemoItemService,
                                                updateMemoItemByAnswer,
                                                nextMemoItemDtoOutput);
    }

    @BeforeEach
    void setUp() {

    }

    //TODO test integration
}
