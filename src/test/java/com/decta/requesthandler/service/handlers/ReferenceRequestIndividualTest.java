package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.IndividualClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.domain.documents.RegistrationAddressDocument;
import com.decta.requesthandler.repository.PersonalDataBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.in;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ReferenceRequestIndividualTest {
    @Mock
    private PersonalDataBase personalDataBase;

    @Mock
    private PersonIdDocument personIdDocument;

    @Mock
    private RegistrationAddressDocument registrationAddressDocument;

    @Mock
    IndividualClient individualClient;

    @Spy
    @InjectMocks
    private ReferenceRequestIndividual victim;

    @Captor
    ArgumentCaptor<PersonIdDocument> personIdDocumentArgumentCaptor;

    @Captor
    ArgumentCaptor<RegistrationAddressDocument> registrationAddressDocumentArgumentCaptor;

    @Captor
    ArgumentCaptor<IndividualClient> individualClientArgumentCaptor;

    private final String TEST_ID = "TEST_ID";
    private Request request;

    @Before
    public void setUp() {
        when(personalDataBase.getIndividualById(TEST_ID)).thenReturn(Optional.of(individualClient));
        when(personIdDocument.getPersonId()).thenReturn(TEST_ID);
        request = request();
    }

    @Test
    public void shouldReturnTrue() {
        doReturn(true).when((DocumentHandler)victim).validatePersonId(personIdDocument, individualClient);
        doReturn(true).when((DocumentHandler)victim).validateIndividualRegistrationAddress(registrationAddressDocument, individualClient);

        Boolean result = victim.execute(request);
        verify(victim).validatePersonId(personIdDocumentArgumentCaptor.capture(), individualClientArgumentCaptor.capture());
        verify(victim).validateIndividualRegistrationAddress(registrationAddressDocumentArgumentCaptor.capture(), individualClientArgumentCaptor.capture());

        assertThat(personIdDocumentArgumentCaptor.getValue()).isEqualTo(personIdDocument);
        assertThat(registrationAddressDocumentArgumentCaptor.getValue()).isEqualTo(registrationAddressDocument);
        assertThat(individualClientArgumentCaptor.getAllValues()).containsOnly(individualClient);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse() {
        doReturn(true).when((DocumentHandler)victim).validatePersonId(personIdDocument, individualClient);
        doReturn(false).when((DocumentHandler)victim).validateIndividualRegistrationAddress(registrationAddressDocument, individualClient);

        Boolean result = victim.execute(request);
        verify(victim).validatePersonId(personIdDocumentArgumentCaptor.capture(), individualClientArgumentCaptor.capture());
        verify(victim).validateIndividualRegistrationAddress(registrationAddressDocumentArgumentCaptor.capture(), individualClientArgumentCaptor.capture());

        assertThat(personIdDocumentArgumentCaptor.getValue()).isEqualTo(personIdDocument);
        assertThat(registrationAddressDocumentArgumentCaptor.getValue()).isEqualTo(registrationAddressDocument);
        assertThat(individualClientArgumentCaptor.getAllValues()).containsOnly(individualClient);
        assertFalse(result);
    }

    private Request request() {
        Request request = new Request();
        request.setPersonIdDocument(personIdDocument);
        request.setRegistrationAddressDocument(registrationAddressDocument);
        request.setRequestType(RequestType.REFERENCE_REQUEST_INDIVIDUAL);
        request.setCustomerId("1");

        return request;
    }
}