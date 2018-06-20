package com.example.android.miwok;

public class Word {

    private String mFrenchTranslation;
    private String mDefaultTranslation;
    private int mImageResourceID;

    public Word(String french, String defaultTranslation)
    {
        mFrenchTranslation = french;
        mDefaultTranslation = defaultTranslation;
        mImageResourceID = 0;
    }

    public Word(String french, String defaultTranslation, int imageResourceID)
    {
        mFrenchTranslation = french;
        mDefaultTranslation = defaultTranslation;
        mImageResourceID = imageResourceID;
    }

    public String getFrenchTranslation() {
        return mFrenchTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getmImageResourceID()
    {
        return mImageResourceID;
    }
}
