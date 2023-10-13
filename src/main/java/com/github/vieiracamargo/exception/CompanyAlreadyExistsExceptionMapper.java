package com.github.vieiracamargo.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CompanyAlreadyExistsExceptionMapper implements ExceptionMapper<CompanyAlreadyExistsException> {
    @Override
    public Response toResponse(CompanyAlreadyExistsException e) {
        Message message = new Message("cpnj", e.getMessage());
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(message)
                .build();
    }
}

