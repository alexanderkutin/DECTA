package com.decta.requesthandler.service.handlers;

import com.decta.requesthandler.domain.BussinessClient;
import com.decta.requesthandler.domain.IndividualClient;
import com.decta.requesthandler.domain.documents.EnterpriseRegisterDocument;
import com.decta.requesthandler.domain.documents.PersonIdDocument;
import com.decta.requesthandler.domain.documents.RegistrationAddressDocument;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DocumentHandlerTest {
    @Mock
    private PersonIdDocument personIdDocument;

    @Mock
    private RegistrationAddressDocument registrationAddressDocument;

    @Mock
    private EnterpriseRegisterDocument enterpriseRegisterDocument;

    @Mock
    private IndividualClient individualClient;

    @Mock
    private BussinessClient bussinessClient;

    @Spy
    private DocumentHandler victim;

    private final String TEST_NAME = "TEST_NAME";
    private final String TEST_SURNAME = "TEST_SURNAME";
    private final String TEST_ID = "TEST_ID";

    @Test
    public void setPersonIdDocumentTrueTest() {
        when(personIdDocument.getFirstName()).thenReturn(TEST_NAME);
        when(personIdDocument.getLastName()).thenReturn(TEST_SURNAME);
        when(personIdDocument.getPersonId()).thenReturn(TEST_ID);

        when(individualClient.getFirstName()).thenReturn(TEST_NAME);
        when(individualClient.getLastName()).thenReturn(TEST_SURNAME);
        when(individualClient.getPersonalId()).thenReturn(TEST_ID);

        Boolean result = victim.validatePersonId(personIdDocument, individualClient);

        assertTrue(result);
    }

    @Test
    public void setPersonIdDocumentFalseTestOne() {
        Boolean result = victim.validatePersonId(null, individualClient);

        assertFalse(result);
    }

    @Test
    public void setPersonIdDocumentFalseTestTwo() {
        when(personIdDocument.getFirstName()).thenReturn(TEST_NAME);
        when(personIdDocument.getLastName()).thenReturn(TEST_SURNAME);
        when(personIdDocument.getPersonId()).thenReturn(TEST_ID);

        when(individualClient.getFirstName()).thenReturn(TEST_NAME);
        when(individualClient.getLastName()).thenReturn("OTHER_SURNAME");
        when(individualClient.getPersonalId()).thenReturn(TEST_ID);

        Boolean result = victim.validatePersonId(personIdDocument, individualClient);

        assertFalse(result);
    }
}