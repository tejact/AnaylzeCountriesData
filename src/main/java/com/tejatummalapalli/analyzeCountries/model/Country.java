package com.tejatummalapalli.analyzeCountries.model;

public class Country {
    String code;
    String name;
    long internetUsers;
    long adultLiteracyRate;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(long internetUsers) {
        this.internetUsers = internetUsers;
    }

    public long getAdultLiteracyRate() {
        return adultLiteracyRate;
    }

    public void setAdultLiteracyRate(long adultLiteracyRate) {
        this.adultLiteracyRate = adultLiteracyRate;
    }
}
