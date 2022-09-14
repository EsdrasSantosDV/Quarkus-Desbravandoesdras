//service
package com.example.esdraskhan.services;

import com.example.esdraskhan.dao.AutorDao;


import com.example.esdraskhan.model.Autor;
import com.example.esdraskhan.dto.AutorDto;
import com.example.esdraskhan.parser.AutorParser;


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
public class AutorService {
    @Inject
    AutorDao dao;

    public List<AutorDto> listar(){
        return dao.listar().stream().map(AutorParser.get()::dto).collect(Collectors.toList());
    }

    @Transactional(rollbackOn = Exception.class)
    public void cadastrar(@Valid AutorDto autorDto) {

        System.out.println(autorDto.toString());
        Autor autor = AutorParser.get().entidade(autorDto);
        dao.cadastrar(autor);
    }
    @Transactional
    public void excluir(String ISNI) {
        //VALIDAR SE O ID E VALIDO
        if(dao.buscarPorISNI(ISNI)==null)
        {
            throw new NotFoundException();
        }
        dao.excluirPorISNI(ISNI);
    }
    public AutorDto buscarPorISNI(String ISNI)
    {
        Autor autor=dao.buscarPorISNI(ISNI);
        if(autor==null)
        {
            throw new NotFoundException();
        }
        AutorDto autorDto=AutorParser.get().dto(autor);
        return autorDto;
    }
    @Transactional
    public AutorDto atualizar(String ISNI, AutorDto todo) {
        todo.setIsni(ISNI);
        Autor autor=AutorParser.get().entidade(todo);
        AutorDto dto;
        return dto= AutorParser.get().dto(dao.atualizar(autor).orElseThrow(() -> new InvalidParameterException("Autor nao Encontrado")));
    }
}