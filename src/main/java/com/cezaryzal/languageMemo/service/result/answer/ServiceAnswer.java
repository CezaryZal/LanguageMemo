package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.ComponentDtoInput;
import com.cezaryzal.languageMemo.model.ComponentDtoOutput;

public interface ServiceAnswer {
    ComponentDtoOutput serviceByInputComponent(ComponentDtoInput componentDtoInput);
}
