package com.decta.requesthandler.domain.documents;

import java.util.Objects;

public class EnterpriseRegisterDocument {
    private String companyName;
    private String registrationNumber;
    private String registrationAddress;

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String registrationAddress) {
        this.registrationAddress = registrationAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EnterpriseRegisterDocument that = (EnterpriseRegisterDocument) o;
        return Objects.equals(companyName, that.companyName) &&
                Objects.equals(registrationNumber, that.registrationNumber) &&
                Objects.equals(registrationAddress, that.registrationAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyName, registrationNumber, registrationAddress);
    }

    @Override
    public String toString() {
        return "EnterpriseRegisterDocument{" +
                "companyName='" + companyName + '\'' +
                ", registrationNumber='" + registrationNumber + '\'' +
                ", registrationAddress='" + registrationAddress + '\'' +
                '}';
    }
}
