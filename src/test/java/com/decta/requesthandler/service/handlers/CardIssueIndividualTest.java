package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.IndividualClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.repository.PersonalDataBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CardIssueIndividualTest {

    @Mock
    private PersonalDataBase personalDataBase;

    @Mock
    private PersonIdDocument personIdDocument;

    @Mock
    private IndividualClient individualClient;

    @Spy
    @InjectMocks
    private CardIssueIndividual victim;

    @Captor
    ArgumentCaptor<PersonIdDocument> personIdDocumentArgumentCaptor;

    @Captor
    ArgumentCaptor<IndividualClient> individualClientArgumentCaptor;

    private Request request;
    private final String TEST_ID = "TEST_ID";

    @Before
    public void setUp() {
        when(personalDataBase.getIndividualById(TEST_ID)).thenReturn(Optional.of(individualClient));
        when(personIdDocument.getPersonId()).thenReturn(TEST_ID);
        request = request();
    }

    @Test
    public void shouldReturnTrue() {
        doReturn(true).when((DocumentHandler)victim).validatePersonId(personIdDocument, individualClient);

        Boolean result = victim.execute(request);
        verify(victim).validatePersonId(personIdDocumentArgumentCaptor.capture(), individualClientArgumentCaptor.capture());

        assertThat(personIdDocumentArgumentCaptor.getValue()).isEqualTo(personIdDocument);
        assertThat(individualClientArgumentCaptor.getValue()).isEqualTo(individualClient);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse() {
        doReturn(false).when((DocumentHandler)victim).validatePersonId(personIdDocument, individualClient);

        Boolean result = victim.execute(request);
        verify(victim).validatePersonId(personIdDocumentArgumentCaptor.capture(), individualClientArgumentCaptor.capture());

        assertThat(personIdDocumentArgumentCaptor.getValue()).isEqualTo(personIdDocument);
        assertThat(individualClientArgumentCaptor.getValue()).isEqualTo(individualClient);
        assertFalse(result);
    }

    private Request request() {
        Request request = new Request();
        request.setPersonIdDocument(personIdDocument);
        request.setRequestType(RequestType.CARD_ISSUE_INDIVIDUAL);
        request.setCustomerId("1");

        return request;
    }
}