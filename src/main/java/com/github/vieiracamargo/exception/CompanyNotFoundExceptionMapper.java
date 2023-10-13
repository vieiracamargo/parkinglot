package com.github.vieiracamargo.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class CompanyNotFoundExceptionMapper implements ExceptionMapper<CompanyNotFoundException> {
    @Override
    public Response toResponse(CompanyNotFoundException e) {
        Message message = new Message("id", e.getMessage());
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(message)
                .build();
    }
}

