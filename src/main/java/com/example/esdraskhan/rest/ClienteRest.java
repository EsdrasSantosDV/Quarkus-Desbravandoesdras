package com.example.esdraskhan.rest;

import com.example.esdraskhan.dto.ClienteDto;
import com.example.esdraskhan.services.ClienteService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/cliente")
@RequestScoped
public class ClienteRest {

    @Inject
    ClienteService service;

    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Clientes")
    @APIResponse(responseCode = "200", description = "ClienteDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ClienteDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }



}
