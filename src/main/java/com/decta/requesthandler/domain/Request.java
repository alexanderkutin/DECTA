package com.decta.requesthandler.domain;

import com.decta.requesthandler.domain.documents.EnterpriseRegisterDocument;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.domain.documents.RegistrationAddressDocument;

import java.util.Objects;
import java.util.Optional;

public class Request {
    private RequestType requestType;
    private String customerId;

    private PersonIdDocument personIdDocument;
    private RegistrationAddressDocument registrationAddressDocument;
    private EnterpriseRegisterDocument enterpriseRegisterDocument;

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Optional<PersonIdDocument> getPersonIdDocument() {
        return Optional.ofNullable(personIdDocument);
    }

    public void setPersonIdDocument(PersonIdDocument personIdDocument) {
        this.personIdDocument = personIdDocument;
    }

    public Optional<RegistrationAddressDocument> getRegistrationAddressDocument() {
        return Optional.ofNullable(registrationAddressDocument);
    }

    public void setRegistrationAddressDocument(RegistrationAddressDocument registrationAddressDocument) {
        this.registrationAddressDocument = registrationAddressDocument;
    }

    public Optional<EnterpriseRegisterDocument> getEnterpriseRegisterDocument() {
        return Optional.ofNullable(enterpriseRegisterDocument);
    }

    public void setEnterpriseRegisterDocument(EnterpriseRegisterDocument enterpriseRegisterDocument) {
        this.enterpriseRegisterDocument = enterpriseRegisterDocument;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return requestType == request.requestType &&
                Objects.equals(customerId, request.customerId) &&
                Objects.equals(personIdDocument, request.personIdDocument) &&
                Objects.equals(registrationAddressDocument, request.registrationAddressDocument) &&
                Objects.equals(enterpriseRegisterDocument, request.enterpriseRegisterDocument);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestType, customerId, personIdDocument, registrationAddressDocument, enterpriseRegisterDocument);
    }

    @Override
    public String toString() {
        return "Request{" +
                "requestType=" + requestType +
                ", customerId='" + customerId + '\'' +
                ", personIdDocument=" + personIdDocument +
                ", registrationAddressDocument=" + registrationAddressDocument +
                ", enterpriseRegisterDocument=" + enterpriseRegisterDocument +
                '}';
    }
}
