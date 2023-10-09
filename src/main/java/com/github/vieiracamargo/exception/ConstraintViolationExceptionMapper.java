package com.github.vieiracamargo.exception;

import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        List<Message> errors = e.getConstraintViolations().stream().map(
                        constraintViolation -> new Message(
                                extractFieldName(constraintViolation.getPropertyPath().toString()),
                                constraintViolation.getMessage()))
                .toList();
        return Response.status(Response.Status.BAD_REQUEST).entity(new ApiError(errors)).build();
    }

    private String extractFieldName(String propertyPath) {
        String[] pathElements = propertyPath.split("\\.");
        if (pathElements.length > 0) {
            return pathElements[pathElements.length - 1];
        }
        return propertyPath;
    }

}
