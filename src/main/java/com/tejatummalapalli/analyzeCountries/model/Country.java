package com.tejatummalapalli.analyzeCountries.model;

import javax.persistence.*;

@Entity(name="Country")
public class Country {
    @Id
    String code;

    @Column
    String name;
    @Column
    Double internetUsers;
    @Column
    Double adultLiteracyRate;

    //Default Constructor for JPA's
    public Country() {

    }

    //Builder Desgin pattern for creating a Country.
    public Country(CountryBuilder countryBuilder) {
          this.name = countryBuilder.name;
          this.code = countryBuilder.code;
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

    public Double getInternetUsers() {
        return internetUsers;
    }

    public void setInternetUsers(Double internetUsers) {
        this.internetUsers = internetUsers;
    }

    public Double getAdultLiteracyRate() {
        return adultLiteracyRate;
    }

    public void setAdultLiteracyRate(Double adultLiteracyRate) {
        this.adultLiteracyRate = adultLiteracyRate;
    }

    @Override
    public String toString() {
        return "Country{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", internetUsers=" + internetUsers +
                ", adultLiteracyRate=" + adultLiteracyRate +
                '}';
    }

    public static class CountryBuilder {
        String name;
        Double internetUsers;
        Double adultLiteracyRate;
        String code;

        public CountryBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public CountryBuilder withCode(String countryCode) {
            this.code = countryCode;
            return this;
        }

        public CountryBuilder withInternetUsers(Double internetUsers) {
            this.internetUsers = internetUsers;
            return this;
        }

        public CountryBuilder withAdultLiteracyRate(Double adultLiteracyRate) {
            this.adultLiteracyRate = adultLiteracyRate;
            return this;
        }

        public Country build() {
            return new Country(this);
        }
    }
}
