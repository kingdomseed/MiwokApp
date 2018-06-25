package com.example.android.miwok;

public class Word {

    private String mFrenchTranslation;
    private String mDefaultTranslation;
    private int mImageResourceID;
    private int mSoundFileID;

    public Word(String french, String defaultTranslation, int soundFileID) {
        mFrenchTranslation = french;
        mDefaultTranslation = defaultTranslation;
        mImageResourceID = 0;
        mSoundFileID = soundFileID;
    }

    public Word(String french, String defaultTranslation, int imageResourceID, int soundFileID) {
        mFrenchTranslation = french;
        mDefaultTranslation = defaultTranslation;
        mImageResourceID = imageResourceID;
        mSoundFileID = soundFileID;
    }

    public String getFrenchTranslation() {
        return mFrenchTranslation;
    }

    public String getDefaultTranslation() {
        return mDefaultTranslation;
    }

    public int getmImageResourceID() {
        return mImageResourceID;
    }

    public int getmSoundFileID() {
        return mSoundFileID;
    }
}
