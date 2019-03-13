package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.BussinessClient;
import com.decta.requesthandler.domain.IndividualClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.EnterpriseRegisterDocument;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.repository.PersonalDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class CardIssueBussiness implements DocumentHandler {

    private final RequestType REQUEST_TYPE = RequestType.CARD_ISSUE_ENTITY;
    private PersonalDataBase personalDataBase;

    @Autowired
    public CardIssueBussiness(PersonalDataBase personalDataBase) {
        this.personalDataBase = personalDataBase;
    }

    @Override
    public Boolean execute(Request request) {
        PersonIdDocument personIdDocument = request.getPersonIdDocument()
                .orElseThrow(() -> new NoSuchElementException("Individual ID document missing in the request"));

        EnterpriseRegisterDocument enterpriseRegisterDocument = request.getEnterpriseRegisterDocument()
                .orElseThrow(() -> new NoSuchElementException("Enterprise register document missing in the request"));

        IndividualClient individualClient = personalDataBase.getIndividualById(personIdDocument.getPersonId())
                .orElseThrow(() -> new NoSuchElementException("Individual is not found in base"));

        BussinessClient bussinessClient = personalDataBase.getCompanyById(enterpriseRegisterDocument.getRegistrationNumber())
                .orElseThrow(() -> new NoSuchElementException("Company is not found in base"));

        return validatePersonId(personIdDocument, individualClient) &&
                validateEnterpriseRegisterDocument(enterpriseRegisterDocument, bussinessClient);
    }

    @Override
    public RequestType getRequestType() {
        return REQUEST_TYPE;
    }
}
