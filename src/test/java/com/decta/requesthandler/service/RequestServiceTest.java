package com.decta.requesthandler.service;

import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.service.handlers.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RequestServiceTest {

    @Mock
    private CardIssueIndividual cardIssueIndividual;

    @Mock
    private CardIssueBussiness cardIssueBussiness;

    @Mock
    private ReferenceRequestIndividual referenceRequestIndividual;

    @Mock
    private ReferenceRequestBussiness referenceRequestBussiness;

    @Mock
    private RegisterNewIndividual registerNewIndividual;

    @Mock
    private RegisterNewBussiness registerNewBussiness;

    @Captor
    private ArgumentCaptor<Request> requestCaptor;

    private Request request = request();
    private RequestService victim;

    @Before
    public void setUp() {
        List<DocumentHandler> handlerList = new ArrayList<>();

        handlerList.add(registerNewBussiness);
        handlerList.add(registerNewIndividual);
        handlerList.add(referenceRequestBussiness);
        handlerList.add(referenceRequestIndividual);
        handlerList.add(cardIssueBussiness);
        handlerList.add(cardIssueIndividual);

        victim = new RequestService(handlerList);

        when(cardIssueIndividual.getRequestType()).thenReturn(RequestType.CARD_ISSUE_INDIVIDUAL);
        when(cardIssueBussiness.getRequestType()).thenReturn(RequestType.CARD_ISSUE_ENTITY);
        when(referenceRequestIndividual.getRequestType()).thenReturn(RequestType.REFERENCE_REQUEST_INDIVIDUAL);
        when(referenceRequestBussiness.getRequestType()).thenReturn(RequestType.REFERENCE_REQUEST_ENTITY);
        when(registerNewIndividual.getRequestType()).thenReturn(RequestType.REGISTER_NEW_INDIVIDUAL);
        when(registerNewBussiness.getRequestType()).thenReturn(RequestType.REGISTER_NEW_ENTITY);
    }

    @Test
    public void shouldReturnValid() {
        when(cardIssueIndividual.execute(request)).thenReturn(true);

        ServiceReturnValue result = victim.handleRequest(request);

        verify(cardIssueIndividual).execute(requestCaptor.capture());
        Request captorResult = requestCaptor.getValue();

        assertThat(captorResult).isEqualTo(request);
        assertThat(result).isEqualTo(ServiceReturnValue.VALID);
    }

    @Test
    public void shouldReturnNotValid() {
        when(cardIssueIndividual.execute(request)).thenReturn(false);

        ServiceReturnValue result = victim.handleRequest(request);

        verify(cardIssueIndividual).execute(requestCaptor.capture());
        Request captorResult = requestCaptor.getValue();

        assertThat(captorResult).isEqualTo(request);
        assertThat(result).isEqualTo(ServiceReturnValue.NOT_VALID);
    }

    private Request request() {
        PersonIdDocument idDoc = new PersonIdDocument();
        idDoc.setFirstName("Ivan");
        idDoc.setLastName("Ivanov");
        idDoc.setPersonId("010203-12345");

        Request request = new Request();
        request.setPersonIdDocument(idDoc);
        request.setRequestType(RequestType.CARD_ISSUE_INDIVIDUAL);
        request.setCustomerId("1");

        return request;
    }
}