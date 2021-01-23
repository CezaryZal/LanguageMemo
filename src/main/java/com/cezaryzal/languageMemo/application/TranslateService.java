package com.cezaryzal.languageMemo.application;

import com.cezaryzal.languageMemo.application.translate.FromNativeTranslateService;

import com.cezaryzal.languageMemo.application.translate.ToNativeTranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranslateService {

    private final FromNativeTranslateService fromNativeTranslateService;
    private final ToNativeTranslateService toNativeTranslateService;

    @Autowired
    public TranslateService(FromNativeTranslateService fromNativeTranslateService,
                            ToNativeTranslateService toNativeTranslateService) {
        this.fromNativeTranslateService = fromNativeTranslateService;
        this.toNativeTranslateService = toNativeTranslateService;
    }

    public FromNativeTranslateService handleFromNativeTranslateService(){
        return fromNativeTranslateService;
    }

    public ToNativeTranslateService handleToNativeTranslateService(){
        return toNativeTranslateService;
    }

}
