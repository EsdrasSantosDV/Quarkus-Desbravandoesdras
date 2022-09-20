//service
package com.example.esdraskhan.services;

import com.example.esdraskhan.dao.AutorDao;
import com.example.esdraskhan.dao.LivrosDao;
import com.example.esdraskhan.dto.AutorDto;
import com.example.esdraskhan.dto.LivrosDto;
import com.example.esdraskhan.model.Autor;
import com.example.esdraskhan.model.Livros;
import com.example.esdraskhan.parser.AutorParser;
import com.example.esdraskhan.parser.LivrosParser;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;


//NADA DE PERSISTENCIA
//VALIDAÇÃO SE ENCONTRA AQUI
@RequestScoped
@Traced
public class LivrosService {
    @Inject
    LivrosDao dao;

    public List<LivrosDto> listar(){
        return dao.listar().stream().map(LivrosParser.get()::dto).collect(Collectors.toList());
    }

    @Transactional(rollbackOn = Exception.class)
    public void cadastrar(@Valid LivrosDto livrosDto) {
        Livros livros = LivrosParser.get().entidade(livrosDto);
        dao.cadastrar(livros);
    }


    @Transactional
    public void excluir(String ISBN) {
        //VALIDAR SE O ID E VALIDO
        if(dao.buscarPorISBN(ISBN)==null)
        {
            throw new NotFoundException();
        }
        dao.excluirPorISBN(ISBN);
    }


    public LivrosDto buscarPorISBN(String ISBN)
    {
        Livros livros=dao.buscarPorISBN(ISBN);
        if(livros==null)
        {
            throw new NotFoundException();
        }
        LivrosDto autorDto=LivrosParser.get().dto(livros);
        return autorDto;
    }
    @Transactional
    public LivrosDto atualizar(String ISBN, LivrosDto todo) {
        todo.setIsbn(ISBN);
        Livros autor=LivrosParser.get().entidade(todo);
        LivrosDto dto;
        return dto=LivrosParser.get().dto(dao.atualizar(autor).orElseThrow(() -> new InvalidParameterException("Livro nao Encontrado")));
    }
}