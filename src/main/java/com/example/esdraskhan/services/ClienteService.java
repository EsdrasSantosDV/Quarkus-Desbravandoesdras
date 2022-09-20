package com.example.esdraskhan.services;

import com.example.esdraskhan.dao.ClienteDao;
import com.example.esdraskhan.dto.ClienteDto;
import com.example.esdraskhan.model.Cliente;
import com.example.esdraskhan.parser.ClienteParser;
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
public class ClienteService {
    @Inject
    ClienteDao dao;

    public List<ClienteDto> listar(){
        return dao.listar().stream().map(ClienteParser.get()::dto).collect(Collectors.toList());
    }

    @Transactional(rollbackOn = Exception.class)
    public void cadastrar(@Valid ClienteDto clientedto) {
        Cliente cliente = ClienteParser.get().entidade(clientedto);
        dao.cadastrar(cliente);


    }
    @Transactional
    public void excluir(String email) {
        //VALIDAR SE O ID E VALIDO
        if(dao.buscarPorEmail(email)==null)
        {
            throw new NotFoundException();
        }
        dao.excluirPorEmail(email);
    }
     public ClienteDto buscarPorEmail(String email)
    {
        Cliente cliente=dao.buscarPorEmail(email);
        if(cliente==null)
        {
            throw new NotFoundException();
        }
        ClienteDto clientedto=ClienteParser.get().dto(cliente);
        return clientedto;
    }
    @Transactional
    public ClienteDto atualizar(String email, ClienteDto todo) {
        todo.setEmail(email);
        Cliente cliente=ClienteParser.get().entidade(todo);
        ClienteDto dto;
        return dto= ClienteParser.get().dto(dao.atualizar(cliente).orElseThrow(() -> new InvalidParameterException("Cliente nao Encontrado")));
    }
}
