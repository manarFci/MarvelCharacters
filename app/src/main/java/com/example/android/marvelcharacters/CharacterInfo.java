package com.example.android.marvelcharacters;

/**
 * Created by macbook on 7/14/19.
 */

public class CharacterInfo {
    private String mImageURL;
    private String mCharacterName;
    private  String mCharacterDesc;

    public CharacterInfo(String imageURL, String characterName, String characterDesc){

        mImageURL=imageURL;
        mCharacterName=characterName;
        mCharacterDesc=characterDesc;

    }

    public String getmImageURL()
    {
        return mImageURL;

    }

    public String getmCharacterName()
    {
        return mCharacterName;

    }
    public String getmCharacterDesc()
    {
        return mCharacterDesc;

    }
}
