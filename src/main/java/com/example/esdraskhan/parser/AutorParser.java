package com.example.esdraskhan.parser;

import com.example.esdraskhan.model.Autor;
import com.example.esdraskhan.dto.AutorDto;

public class AutorParser {

    public static AutorParser get(){
        return  new AutorParser();
    }

    public Autor entidade(AutorDto dto)
    {
        Autor entidade=new Autor();
        entidade.setIsni(dto.getIsni());
        entidade.setBiografia(dto.getBiografia());
        entidade.setDataNascimento(dto.getDataNascimento());
        entidade.setNome(dto.getNome());
        entidade.setEmail(dto.getEmail());
        return entidade;
    }

    public AutorDto dto(Autor entidade){
        AutorDto dto = new AutorDto();
        dto.setDataNascimento(entidade.getDataNascimento());
        dto.setBiografia(entidade.getBiografia());
        dto.setIsni(entidade.getIsni());
        dto.setNome(entidade.getNome());
        dto.setEmail(entidade.getEmail());
        return dto;
    }
}
