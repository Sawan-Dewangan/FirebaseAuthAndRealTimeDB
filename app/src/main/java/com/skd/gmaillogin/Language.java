package com.skd.gmaillogin;

public class Language {
    String lId;
    String lname;
    String lLevel;

    public Language(){

    }

    public Language(String lId, String lname, String lLevel) {
        this.lId = lId;
        this.lname = lname;
        this.lLevel = lLevel;
    }

    public String getlId() {
        return lId;
    }

    public String getLname() {
        return lname;
    }

    public String getlLevel() {
        return lLevel;
    }
}
