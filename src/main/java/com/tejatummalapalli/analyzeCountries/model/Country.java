package com.tejatummalapalli.analyzeCountries.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Country {
    @Id
    @Column(length = 3)
    String code;
    @Column(length=32)
    String name;
    @Column(columnDefinition = "decimal",precision = 11,scale = 8)
    long internetUsers;
    @Column(columnDefinition = "decimal",precision = 11,scale = 8)
    long adultLiteracyRate;

    //Default Constructor for JPA's
    public Country() {

    }

    public Country(CountryBuilder countryBuilder) {
          this.name = countryBuilder.name;
          this.internetUsers = countryBuilder.internetUsers;
          this.adultLiteracyRate = countryBuilder.adultLiteracyRate;
    }

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

    public static class CountryBuilder {
        String name;
        long internetUsers;
        long adultLiteracyRate;

        public CountryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CountryBuilder withInternetUsers(long internetUsers) {
            this.internetUsers = internetUsers;
            return this;
        }

        public CountryBuilder withAdultLiteracyRate(long adultLiteracyRate) {
            this.adultLiteracyRate = adultLiteracyRate;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }
}
