package com.example.android.miwok;

public class Word {

    private String mFrenchTranslation;
    private String mDefaultTranslation;

    public Word(String french, String defaultTranslation)
    {
        mFrenchTranslation = french;
        mDefaultTranslation = defaultTranslation;
    }

    public String getFrenchTranslation() {
        return mFrenchTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }
}
