package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.BussinessClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.EnterpriseRegisterDocument;
import com.decta.requesthandler.domain.documents.RegistrationAddressDocument;
import com.decta.requesthandler.repository.PersonalDataBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class ReferenceRequestBussiness implements DocumentHandler {

    private final RequestType REQUEST_TYPE = RequestType.REFERENCE_REQUEST_ENTITY;
    private PersonalDataBase personalDataBase;

    @Autowired
    public ReferenceRequestBussiness(PersonalDataBase personalDataBase) {
        this.personalDataBase = personalDataBase;
    }

    @Override
    public Boolean execute(Request request) {
        EnterpriseRegisterDocument enterpriseRegisterDocument = request.getEnterpriseRegisterDocument()
                .orElseThrow(() -> new NoSuchElementException("Enterprise register document missing in the request"));

        RegistrationAddressDocument registrationAddressDocument = request.getRegistrationAddressDocument()
                .orElseThrow(() -> new NoSuchElementException("Registration Address document is missing"));

        BussinessClient bussinessClient = personalDataBase.getCompanyById(enterpriseRegisterDocument.getRegistrationNumber())
                .orElseThrow(() -> new NoSuchElementException("Company is not found in base"));

        return validateEnterpriseRegisterDocument(enterpriseRegisterDocument, bussinessClient) &&
                validateEntitylRegistrationAddress(registrationAddressDocument, bussinessClient);
    }

    @Override
    public RequestType getRequestType() {
        return REQUEST_TYPE;
    }
}
