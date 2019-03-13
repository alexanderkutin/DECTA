package com.decta.requesthandler.service;

import com.decta.requesthandler.domain.Request;
import com.decta.requesthandler.service.handlers.DocumentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class RequestService {

    private List<DocumentHandler> requestDocumentHandlers;

    @Autowired
    public RequestService(List<DocumentHandler> requestDocumentHandlers) {
        this.requestDocumentHandlers = requestDocumentHandlers;
    }

    private Optional<DocumentHandler> getHandler(Request request) {
        return requestDocumentHandlers
                .stream()
                .filter(handler -> handler.getRequestType().equals(request.getRequestType()))
                .findFirst();
    }

    public ServiceReturnValue handleRequest(Request request) {
        DocumentHandler handler = getHandler(request)
                .orElseThrow(() -> new NoSuchElementException("No handler for such request"));

        if(handler.execute(request)) {
            return ServiceReturnValue.VALID;
        } else {
            return ServiceReturnValue.NOT_VALID;
        }
    }
}
