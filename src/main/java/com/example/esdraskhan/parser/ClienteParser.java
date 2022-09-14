package com.example.esdraskhan.parser;

import com.example.esdraskhan.dto.ClienteDto;
import com.example.esdraskhan.model.Cliente;


//CLASSE RESPONSAVEL PELO PARSEAMENTO DOS DTO PARA AS MODELS

public class ClienteParser {

    public static ClienteParser get()
    {
        return new ClienteParser();
    }

    public Cliente entidade(ClienteDto dto)
    {
        Cliente entidade=new Cliente();
        entidade.setEmail(dto.getEmail());
        entidade.setContato(dto.getContato());
        entidade.setNome(dto.getNome());

        return entidade;

    }

    public ClienteDto dto(Cliente entidade)
    {
        ClienteDto dto=new ClienteDto();
        dto.setEmail(entidade.getEmail());
        dto.setContato(entidade.getContato());
        dto.setNome(entidade.getNome());

        return dto;

    }


}
