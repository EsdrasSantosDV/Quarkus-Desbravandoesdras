//REST
package com.example.esdraskhan.rest;



import com.example.esdraskhan.dto.AutorDto;
import com.example.esdraskhan.dto.LivrosDto;
import com.example.esdraskhan.model.Livros;
import com.example.esdraskhan.services.LivrosService;
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
@Path("/livros")
@RequestScoped
public class LivrosRest {

    @Inject
    LivrosService service;
    @Inject
    Validator validator;

    @GET
    @Operation(summary = "Listar", description = "Retorna uma lista de Livros")
    @APIResponse(responseCode = "200", description = "LivrosDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivrosDto.class))})
    public Response listar()  {
        return Response.status(Response.Status.OK).entity(service.listar()).build();
    }

    @POST
    @Operation(summary = "Cadastrar", description = "Cadastrar um Livro")
    @APIResponse(responseCode = "201", description = "LivrosDto",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = LivrosDto.class))})
    public Response cadastrar(LivrosDto livro) throws Exception {

        Set<ConstraintViolation<LivrosDto>> erros=validator.validate(livro);
        if(erros.isEmpty())
        {
            service.cadastrar(livro);
        }
        else
        {
            List<String> listaErros=erros.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());
            throw new NotFoundException(listaErros.get(0));
        }


        return Response.status(Response.Status.CREATED).build();
    }
    @GET
    @Path("/{isbn}")
    @Operation(
            summary = "Buscar um Livro pelo ISBN",
            description = "Buscar um Autor pelo ISBN"
    )
    @APIResponse(
            responseCode = "200",
            description = "livro",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = LivrosDto.class))
            })
    public Response buscarPorISBN(@PathParam("isbn") String ISBN)
    {
        return Response.status(Response.Status.OK)
                .entity(service.buscarPorISBN(ISBN))
                .build();
    }

    @PUT
    @Path("/{isbn}")
    @Operation(
            summary = "Atualizar um Livro pelo ISBN",
            description = "Atualizar um Livro pelo ISBN"
    )
    @APIResponse(
            responseCode = "200",
            description = "atualizar livro",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = LivrosDto.class))
            })
    public Response atualizar(@PathParam("isbn") String ISBN,LivrosDto todo)
    {
        try {
            return Response.ok(service.atualizar(ISBN,todo)).build();
        } catch (Exception e) {
            if (e instanceof InvalidParameterException) {
                return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", e.getMessage())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{isbn}")
    @Operation(
            summary = "Excluir um Livro pelo ISBN",
            description = "Excluir um Livro pelo ISBN"
    )
    @APIResponse(
            responseCode = "202",
            description = "excluir Livro",
            content ={
                    @Content(mediaType="application/json",
                            schema = @Schema(implementation = LivrosDto.class))
            })
    public Response excluir(@PathParam("isbn") String ISBN){
        service.excluir(ISBN);
        return Response.status(Response.Status.ACCEPTED)
                .build();
    }







}