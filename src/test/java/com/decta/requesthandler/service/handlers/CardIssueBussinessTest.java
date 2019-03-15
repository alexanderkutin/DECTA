package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.BussinessClient;
import com.decta.requesthandler.domain.IndividualClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.EnterpriseRegisterDocument;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.repository.PersonalDataBase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CardIssueBussinessTest {

    @Mock
    private PersonalDataBase personalDataBase;

    @Mock
    private PersonIdDocument personIdDocument;

    @Mock
    private EnterpriseRegisterDocument enterpriseRegisterDocument;

    @Mock
    private IndividualClient individualClient;

    @Mock
    private BussinessClient bussinessClient;

    @Spy
    @InjectMocks
    private CardIssueBussiness victim;

    @Captor
    ArgumentCaptor<PersonIdDocument> personIdDocumentArgumentCaptor;

    @Captor
    ArgumentCaptor<EnterpriseRegisterDocument> enterpriseRegisterDocumentArgumentCaptor;

    @Captor
    ArgumentCaptor<IndividualClient> individualClientArgumentCaptor;

    @Captor
    ArgumentCaptor<BussinessClient> bussinessClientArgumentCaptor;

    private final String TEST_ID = "TEST_ID";
    private Request request;

    @Before
    public void setUp() {
        when(personalDataBase.getIndividualById(TEST_ID)).thenReturn(Optional.of(individualClient));
        when(personalDataBase.getCompanyById(TEST_ID)).thenReturn(Optional.of(bussinessClient));
        when(personIdDocument.getPersonId()).thenReturn(TEST_ID);
        when(enterpriseRegisterDocument.getRegistrationNumber()).thenReturn(TEST_ID);
        request = request();
    }

    @Test
    public void shouldReturnTrue() {
        doReturn(true).when((DocumentHandler)victim).validatePersonId(personIdDocument, individualClient);
        doReturn(true).when((DocumentHandler)victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocument, bussinessClient);

        Boolean result = victim.execute(request);
        verify(victim).validatePersonId(personIdDocumentArgumentCaptor.capture(), individualClientArgumentCaptor.capture());
        verify(victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocumentArgumentCaptor.capture(), bussinessClientArgumentCaptor.capture());

        assertThat(personIdDocumentArgumentCaptor.getValue()).isEqualTo(personIdDocument);
        assertThat(individualClientArgumentCaptor.getValue()).isEqualTo(individualClient);
        assertThat(enterpriseRegisterDocumentArgumentCaptor.getValue()).isEqualTo(enterpriseRegisterDocument);
        assertThat(bussinessClientArgumentCaptor.getValue()).isEqualTo(bussinessClient);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse() {
        doReturn(true).when((DocumentHandler)victim).validatePersonId(personIdDocument, individualClient);
        doReturn(false).when((DocumentHandler)victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocument, bussinessClient);

        Boolean result = victim.execute(request);
        verify(victim).validatePersonId(personIdDocumentArgumentCaptor.capture(), individualClientArgumentCaptor.capture());
        verify(victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocumentArgumentCaptor.capture(), bussinessClientArgumentCaptor.capture());

        assertThat(personIdDocumentArgumentCaptor.getValue()).isEqualTo(personIdDocument);
        assertThat(individualClientArgumentCaptor.getValue()).isEqualTo(individualClient);
        assertThat(enterpriseRegisterDocumentArgumentCaptor.getValue()).isEqualTo(enterpriseRegisterDocument);
        assertThat(bussinessClientArgumentCaptor.getValue()).isEqualTo(bussinessClient);
        assertFalse(result);
    }

    private Request request() {
        Request request = new Request();
        request.setPersonIdDocument(personIdDocument);
        request.setEnterpriseRegisterDocument(enterpriseRegisterDocument);
        request.setRequestType(RequestType.CARD_ISSUE_ENTITY);
        request.setCustomerId("1");

        return request;
    }
}