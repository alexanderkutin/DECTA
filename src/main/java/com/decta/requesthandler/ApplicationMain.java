package com.decta.requesthandler;


import com.decta.requesthandler.config.AppConfig;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.EnterpriseRegisterDocument;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.service.RequestService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class ApplicationMain {
    public static void main(String[] args){

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        RequestService requestService = context.getBean(RequestService.class);

        PersonIdDocument idDocOne = new PersonIdDocument();
        idDocOne.setFirstName("Ivan");
        idDocOne.setLastName("Ivanov");
        idDocOne.setPersonId("010203-12345");

        EnterpriseRegisterDocument busDocOne = new EnterpriseRegisterDocument();
        busDocOne.setCompanyName("Company 2");
        busDocOne.setRegistrationAddress("Address 2");
        busDocOne.setRegistrationNumber("45682174879");

        Request requestOne = new Request();
        requestOne.setPersonIdDocument(idDocOne);
        requestOne.setEnterpriseRegisterDocument(busDocOne);
        requestOne.setRequestType(RequestType.CARD_ISSUE_ENTITY);
        requestOne.setCustomerId("1");

        System.out.println(requestService.handleRequest(requestOne));
    }
}
