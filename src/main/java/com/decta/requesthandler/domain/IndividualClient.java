package com.decta.requesthandler.domain;

import java.util.Objects;

public class IndividualClient {
    private String personalId;
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String regAddress;

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getRegAddress() {
        return regAddress;
    }

    public void setRegAddress(String regAddress) {
        this.regAddress = regAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IndividualClient that = (IndividualClient) o;
        return Objects.equals(personalId, that.personalId) &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(dateOfBirth, that.dateOfBirth) &&
                Objects.equals(regAddress, that.regAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personalId, firstName, lastName, dateOfBirth, regAddress);
    }

    @Override
    public String toString() {
        return "IndividualClient{" +
                "personalId='" + personalId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", regAddress='" + regAddress + '\'' +
                '}';
    }
}
