package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.IndividualClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.repository.PersonalDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class CardIssueIndividual implements DocumentHandler {

    private final RequestType REQUEST_TYPE = RequestType.CARD_ISSUE_INDIVIDUAL;
    private PersonalDataBase personalDataBase;

    @Autowired
    public CardIssueIndividual(PersonalDataBase personalDataBase) {
        this.personalDataBase = personalDataBase;
    }

    @Override
    public Boolean execute(Request request) {
        PersonIdDocument personIdDocument = request.getPersonIdDocument()
                .orElseThrow(() -> new NoSuchElementException("Individual ID document missing in the request"));

        IndividualClient individualClient = personalDataBase.getIndividualById(personIdDocument.getPersonId())
                .orElseThrow(() -> new NoSuchElementException("Individual is not found in base"));

        return validatePersonId(personIdDocument, individualClient);
    }

    @Override
    public RequestType getRequestType() {
        return REQUEST_TYPE;
    }
}
