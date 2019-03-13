package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.BussinessClient;
import com.decta.requesthandler.domain.IndividualClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.EnterpriseRegisterDocument;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.domain.documents.RegistrationAddressDocument;

public interface DocumentHandler {
    Boolean execute(Request request);
    RequestType getRequestType();

    default Boolean validatePersonId(PersonIdDocument document, IndividualClient client) {
        return client != null &&
                document != null &&
                document.getFirstName().equals(client.getFirstName()) &&
                document.getLastName().equals(client.getLastName()) &&
                document.getPersonId().equals(client.getPersonalId());
    }

    default Boolean validateIndividualRegistrationAddress(RegistrationAddressDocument document, IndividualClient client) {
        return client != null &&
                document != null &&
                document.getId().equals(client.getPersonalId()) &&
                document.getRegistrationAddress().equals(client.getRegAddress());
    }

    default Boolean validateEntitylRegistrationAddress(RegistrationAddressDocument document, BussinessClient client) {
        return client != null &&
                document != null &&
                document.getId().equals(client.getRegistrationNumber()) &&
                document.getRegistrationAddress().equals(client.getLegalAddress());
    }

    default Boolean validateEnterpriseRegisterDocument(EnterpriseRegisterDocument document, BussinessClient client) {
        return client != null &&
                document != null &&
                document.getRegistrationNumber().equals(client.getRegistrationNumber()) &&
                document.getCompanyName().equals(client.getName()) &&
                document.getRegistrationAddress().equals(client.getLegalAddress());
    }
}
