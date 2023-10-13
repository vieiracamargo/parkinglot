package com.github.vieiracamargo.resource;

import com.github.vieiracamargo.dto.input.CompanyInput;
import com.github.vieiracamargo.dto.output.CompanyOutput;
import com.github.vieiracamargo.service.CompanyService;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;

import java.net.URI;
import java.util.List;

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

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCompanpyById(@PathParam("id") Long id){
        CompanyOutput response = companyService.findCompanyById(id);
        return Response.ok(response).build();
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(
            @QueryParam("startPage") @DefaultValue("0") int startPage,
            @QueryParam("size") @DefaultValue("10") int size,
            @QueryParam("column") @DefaultValue("id") String column,
            @QueryParam("sortDirection") @DefaultValue("Ascending") Sort.Direction direction
    ){
        List<CompanyOutput> allCompanies = companyService.findAll(startPage, size, column, direction);
        return Response.ok(allCompanies).build();
    }
}
