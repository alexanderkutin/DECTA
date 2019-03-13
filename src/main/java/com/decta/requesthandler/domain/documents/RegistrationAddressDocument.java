package com.decta.requesthandler.domain.documents;

import java.util.Objects;

public class RegistrationAddressDocument {
    private String id;
    private String registrationAddress;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegistrationAddress() {
        return registrationAddress;
    }

    public void setRegistrationAddress(String companyRegistrationAddress) {
        this.registrationAddress = companyRegistrationAddress;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistrationAddressDocument that = (RegistrationAddressDocument) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(registrationAddress, that.registrationAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, registrationAddress);
    }

    @Override
    public String toString() {
        return "RegistrationAddressDocument{" +
                "id='" + id + '\'' +
                ", Registration Address='" + registrationAddress + '\'' +
                '}';
    }
}
