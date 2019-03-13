package com.decta.requesthandler.domain;

import java.util.Objects;

public class BussinessClient {

    private String registrationNumber;
    private String name;
    private String legalAddress;

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLegalAddress() {
        return legalAddress;
    }

    public void setLegalAddress(String legalAddress) {
        this.legalAddress = legalAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BussinessClient that = (BussinessClient) o;
        return Objects.equals(registrationNumber, that.registrationNumber) &&
                Objects.equals(name, that.name) &&
                Objects.equals(legalAddress, that.legalAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationNumber, name, legalAddress);
    }

    @Override
    public String toString() {
        return "BussinessClient{" +
                "registrationNumber='" + registrationNumber + '\'' +
                ", name='" + name + '\'' +
                ", legalAddress='" + legalAddress + '\'' +
                '}';
    }
}
