package com.cezaryzal.languageMemo.service.result.answer;

import com.cezaryzal.languageMemo.model.MemoItemDtoInput;
import com.cezaryzal.languageMemo.model.MemoItemDtoOutput;

public interface ServiceAnswer {
    MemoItemDtoOutput serviceByMemoItemInput(MemoItemDtoInput memoItemDtoInput);
}
