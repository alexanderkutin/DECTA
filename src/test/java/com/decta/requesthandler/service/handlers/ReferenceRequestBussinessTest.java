package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.BussinessClient;
import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.domain.RequestType;
import com.decta.requesthandler.domain.documents.EnterpriseRegisterDocument;
import com.decta.requesthandler.domain.documents.RegistrationAddressDocument;
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
public class ReferenceRequestBussinessTest {
    @Mock
    private PersonalDataBase personalDataBase;

    @Mock
    private EnterpriseRegisterDocument enterpriseRegisterDocument;

    @Mock
    private RegistrationAddressDocument registrationAddressDocument;

    @Mock
    private BussinessClient bussinessClient;

    @Spy
    @InjectMocks
    private ReferenceRequestBussiness victim;

    @Captor
    ArgumentCaptor<EnterpriseRegisterDocument> enterpriseRegisterDocumentArgumentCaptor;

    @Captor
    ArgumentCaptor<RegistrationAddressDocument> registrationAddressDocumentArgumentCaptor;

    @Captor
    ArgumentCaptor<BussinessClient> bussinessClientArgumentCaptor;

    private final String TEST_ID = "TEST_ID";
    private Request request;

    @Before
    public void setUp() {
        when(personalDataBase.getCompanyById(TEST_ID)).thenReturn(Optional.of(bussinessClient));
        when(enterpriseRegisterDocument.getRegistrationNumber()).thenReturn(TEST_ID);
        request = request();
    }

    @Test
    public void shouldReturnTrue() {
        doReturn(true).when((DocumentHandler)victim).validateEntitylRegistrationAddress(registrationAddressDocument, bussinessClient);
        doReturn(true).when((DocumentHandler)victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocument, bussinessClient);

        Boolean result = victim.execute(request);
        verify(victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocumentArgumentCaptor.capture(), bussinessClientArgumentCaptor.capture());
        verify(victim).validateEntitylRegistrationAddress(registrationAddressDocumentArgumentCaptor.capture(), bussinessClientArgumentCaptor.capture());

        assertThat(enterpriseRegisterDocumentArgumentCaptor.getValue()).isEqualTo(enterpriseRegisterDocument);
        assertThat(registrationAddressDocumentArgumentCaptor.getValue()).isEqualTo(registrationAddressDocument);
        assertThat(bussinessClientArgumentCaptor.getAllValues()).containsOnly(bussinessClient);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse() {
        doReturn(true).when((DocumentHandler)victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocument, bussinessClient);
        doReturn(false).when((DocumentHandler)victim).validateEntitylRegistrationAddress(registrationAddressDocument, bussinessClient);

        Boolean result = victim.execute(request);
        verify(victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocumentArgumentCaptor.capture(), bussinessClientArgumentCaptor.capture());
        verify(victim).validateEntitylRegistrationAddress(registrationAddressDocumentArgumentCaptor.capture(), bussinessClientArgumentCaptor.capture());

        assertThat(enterpriseRegisterDocumentArgumentCaptor.getValue()).isEqualTo(enterpriseRegisterDocument);
        assertThat(registrationAddressDocumentArgumentCaptor.getValue()).isEqualTo(registrationAddressDocument);
        assertThat(bussinessClientArgumentCaptor.getAllValues()).containsOnly(bussinessClient);
        assertFalse(result);
    }

    private Request request() {
        Request request = new Request();
        request.setRegistrationAddressDocument(registrationAddressDocument);
        request.setEnterpriseRegisterDocument(enterpriseRegisterDocument);
        request.setRequestType(RequestType.REFERENCE_REQUEST_ENTITY);
        request.setCustomerId("1");

        return request;
    }
}