//REST
package com.example.esdraskhan.rest;


import com.example.esdraskhan.dto.AutorDto;
import com.example.esdraskhan.services.AutorService;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/autores")
@RequestScoped
public class AutorRest {

    @Inject
    AutorService service;

    @Inject
    Validator validator;
    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Autores")
    @APIResponse(responseCode = "200", description = "AutorDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AutorDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }

    @GET
    @Path("/{isni}")
    @Operation(
            summary = "Buscar um Autor pelo ISNI",
            description = "Buscar um Autor pelo ISNI"
    )
    @APIResponse(
            responseCode = "200",
            description = "autor",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = AutorDto.class))
            })
    public Response buscarPorISNI(@PathParam("isni") String ISNI)
    {
        return Response.status(Response.Status.OK)
                .entity(service.buscarPorISNI(ISNI))
                .build();
    }



    @POST
    @Operation(summary = "Cadastrar", description = "Cadastar um Autor")
    @APIResponse(responseCode = "201", description = "AutorDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = AutorDto.class))})
    public Response cadastrar(AutorDto autor) throws Exception {

        System.out.println("Rest"+autor.toString());
        Set<ConstraintViolation<AutorDto>> erros=validator.validate(autor);
        if(erros.isEmpty())
        {
            service.cadastrar(autor);
        }
        else
        {
            List<String> listaErros=erros.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            throw new NotFoundException(listaErros.get(0));
        }
        return Response.status(Response.Status.CREATED).build();
    }

    //ATUALIZANDO RETORNANDO O AUTOR ATUALIZADO
    @PUT
    @Path("/{isni}")
    @Operation(
            summary = "Atualizar um Autor pelo ISNI",
            description = "Atualizar um Autor pelo ISNI"
    )
    @APIResponse(
            responseCode = "200",
            description = "atualizar autor",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = AutorDto.class))
            })
    public Response atualizar(@PathParam("isni") String ISNI,AutorDto todo)
    {
        try {
            return Response.ok(service.atualizar(ISNI,todo)).build();
        } catch (Exception e) {
            if (e instanceof InvalidParameterException) {
                return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", e.getMessage())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{isni}")
    @Operation(
            summary = "Excluir um Autor pelo ISNI",
            description = "Excluir um Autor pelo ISNI"
    )
    @APIResponse(
            responseCode = "202",
            description = "excluir Autor",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = AutorDto.class))
            })
    public Response excluir(@PathParam("isni") String ISNI){
        service.excluir(ISNI);
        return Response.status(Response.Status.ACCEPTED)
                .build();
    }








}