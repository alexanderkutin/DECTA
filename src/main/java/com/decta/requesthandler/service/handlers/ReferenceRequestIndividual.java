package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.IndividualClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.domain.documents.RegistrationAddressDocument;
import com.decta.requesthandler.repository.PersonalDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ReferenceRequestIndividual implements DocumentHandler {

    private final RequestType REQUEST_TYPE = RequestType.REFERENCE_REQUEST_INDIVIDUAL;
    private PersonalDataBase personalDataBase;

    @Autowired
    public ReferenceRequestIndividual(PersonalDataBase personalDataBase) {
        this.personalDataBase = personalDataBase;
    }

    @Override
    public Boolean execute(Request request) {
        PersonIdDocument personIdDocument = request.getPersonIdDocument()
                .orElseThrow(() -> new NoSuchElementException("Individual ID document missing in the request"));

        RegistrationAddressDocument registrationAddressDocument = request.getRegistrationAddressDocument()
                .orElseThrow(() -> new NoSuchElementException("Registration Address document is missing"));

        IndividualClient individualClient = personalDataBase.getIndividualById(personIdDocument.getPersonId())
                .orElseThrow(() -> new NoSuchElementException("Individual is not found in base"));

        return validatePersonId(personIdDocument, individualClient) &&
                validateIndividualRegistrationAddress(registrationAddressDocument, individualClient);
    }

    @Override
    public RequestType getRequestType() {
        return REQUEST_TYPE;
    }
}
