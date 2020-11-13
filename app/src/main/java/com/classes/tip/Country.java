package com.classes.tip;

public class Country {
    private String countryName;
    private Taxi taxi;
    private Restaurant rest;
    private String currency;
    private boolean serviceCharge;

    public Country(String countryName, String currency, double low_rest_tip, double high_rest_tip,
                   double low_taxi_tip, double high_taxi_tip, boolean serviceCharge) {
        this.countryName = countryName;
        this.taxi = new Taxi(low_taxi_tip, high_taxi_tip);
        this.rest = new Restaurant(low_rest_tip, high_rest_tip);
        this.currency = currency;
        this.serviceCharge = serviceCharge;
    }

    public Country(String countryName, String currency, double taxi_tip, double rest_tip, boolean serviceCharge) {
        this.countryName = countryName;
        this.taxi = new Taxi(taxi_tip);
        this.rest = new Restaurant(rest_tip);
        this.currency = currency;
        this.serviceCharge = serviceCharge;
    }

    public boolean isServiceCharge() {
        return serviceCharge;
    }

    public String getCurrency() {
        return currency;
    }

    public Taxi getTaxi() {
        return this.taxi;
    }

    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    public Restaurant getRest() {
        return rest;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}