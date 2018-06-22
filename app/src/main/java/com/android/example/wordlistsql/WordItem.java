package com.android.example.wordlistsql;

public class WordItem {

    private int mId;
    private String mWord;
    private String mDay;
    private String mHour;

    public WordItem() { }

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getWord() {
        return mWord;
    }

    public void setWord(String mWord) {
        this.mWord = mWord;
    }

    public String getDay() {
        return mDay;
    }

    public void setDay(String mDay) {
        this.mDay = mDay;
    }

    public String getHour() {
        return mHour;
    }

    public void setHour(String mHour) {
        this.mHour = mHour;
    }
}
