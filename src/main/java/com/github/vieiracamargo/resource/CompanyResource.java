package com.github.vieiracamargo.resource;

import com.github.vieiracamargo.service.CompanyService;
import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;

@ApplicationScoped
@Path("api/v1/parkinglot/company")
public class CompanyResource {
    @Inject
    CompanyService companyService;


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerCompany(@Valid CompanyInput registration, @Context UriInfo uriInfo){
        CompanyOutput companyOutput = companyService.registerCompany(registration);
        URI uri = uriInfo.getAbsolutePathBuilder().path(companyOutput.id().toString()).build();
        return Response.created(uri).entity(companyOutput).build();
    }
}
