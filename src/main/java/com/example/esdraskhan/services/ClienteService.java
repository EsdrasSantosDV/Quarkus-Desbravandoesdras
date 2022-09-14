package com.example.esdraskhan.services;

import com.example.esdraskhan.dao.ClienteDao;
import com.example.esdraskhan.dto.ClienteDto;
import com.example.esdraskhan.parser.ClienteParser;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;


//NADA DE PERSISTENCIA
//VALIDAÇÃO SE ENCONTRA AQUI
@RequestScoped
public class ClienteService {
    @Inject
    ClienteDao dao;

    public List<ClienteDto> listar(){
        return dao.listar().stream().map(ClienteParser.get()::dto).collect(Collectors.toList());
    }




}
