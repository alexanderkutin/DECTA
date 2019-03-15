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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class RegisterNewBussinessTest {
    @Mock
    private PersonalDataBase personalDataBase;

    @Mock
    private EnterpriseRegisterDocument enterpriseRegisterDocument;

    @Mock
    private BussinessClient bussinessClient;

    @Spy
    @InjectMocks
    private RegisterNewBussiness victim;

    @Captor
    ArgumentCaptor<EnterpriseRegisterDocument> enterpriseRegisterDocumentArgumentCaptor;

    @Captor
    ArgumentCaptor<BussinessClient> bussinessClientArgumentCaptor;

    private Request request;
    private final String TEST_ID = "TEST_ID";

    @Before
    public void setUp() {
        when(personalDataBase.getCompanyById(TEST_ID)).thenReturn(Optional.of(bussinessClient));
        when(enterpriseRegisterDocument.getRegistrationNumber()).thenReturn(TEST_ID);
        request = request();
    }

    @Test
    public void shouldReturnTrue() {
        doReturn(true).when((DocumentHandler)victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocument, bussinessClient);

        Boolean result = victim.execute(request);
        verify(victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocumentArgumentCaptor.capture(), bussinessClientArgumentCaptor.capture());

        assertThat(enterpriseRegisterDocumentArgumentCaptor.getValue()).isEqualTo(enterpriseRegisterDocument);
        assertThat(bussinessClientArgumentCaptor.getValue()).isEqualTo(bussinessClient);
        assertTrue(result);
    }

    @Test
    public void shouldReturnFalse() {
        doReturn(false).when((DocumentHandler)victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocument, bussinessClient);

        Boolean result = victim.execute(request);
        verify(victim).validateEnterpriseRegisterDocument(enterpriseRegisterDocumentArgumentCaptor.capture(), bussinessClientArgumentCaptor.capture());

        assertThat(enterpriseRegisterDocumentArgumentCaptor.getValue()).isEqualTo(enterpriseRegisterDocument);
        assertThat(bussinessClientArgumentCaptor.getValue()).isEqualTo(bussinessClient);
        assertFalse(result);
    }

    private Request request() {
        Request request = new Request();
        request.setEnterpriseRegisterDocument(enterpriseRegisterDocument);
        request.setRequestType(RequestType.REGISTER_NEW_ENTITY);
        request.setCustomerId("1");

        return request;
    }
}