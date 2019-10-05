package com.portfolio.adinput;

public class TitleData {

    private String title;
    private String adex;
    private String youtubeid;
    private String email;
    private String longex;
    private String precautions;
    private String next;

    public TitleData(String title, String adex, String youtubeid, String email, String longex, String precautions, String next) {
        this.title = title;
        this.adex = adex;
        this.youtubeid = youtubeid;
        this.email = email;
        this.longex = longex;
        this.precautions = precautions;
        this.next = next;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAdex() {
        return this.adex;
    }

    public String getYoutubeid() {
        return this.youtubeid;
    }

    public String getEmail() {
        return this.email;
    }

    public String getLongex() {
        return this.longex;
    }

    public String getPrecautions() {
        return this.precautions;
    }

    public String getNext() {
        return this.next;
    }
}
