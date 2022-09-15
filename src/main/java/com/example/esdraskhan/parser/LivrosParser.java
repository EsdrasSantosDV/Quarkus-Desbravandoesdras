package com.example.esdraskhan.parser;

import com.example.esdraskhan.dto.LivrosDto;
import com.example.esdraskhan.model.Livros;


//CLASSE RESPONSAVEL PELO PARSEAMENTO DOS DTO PARA AS MODELS

public class LivrosParser {

    public static LivrosParser get()
    {
        return new LivrosParser();
    }
//"isbn,nome,anoDePublicacao,editora, quantidadeExemplares"
    public Livros entidade(LivrosDto dto)
    {

        Livros entidade=new Livros();
        entidade.setAnoDePublicacao(dto.getAnoDePublicacao());
        entidade.setNome(dto.getNome());
        entidade.setIsbn(dto.getIsbn());
        entidade.setEditora(dto.getEditora());
        entidade.setQuatidadeExemplares(dto.getQuatidadeExemplares());

        return entidade;
    }

    public LivrosDto dto(Livros entidade)
    {
        LivrosDto dto=new LivrosDto();
        dto.setAnoDePublicacao(entidade.getAnoDePublicacao());
        dto.setNome(entidade.getNome());
        dto.setIsbn(entidade.getIsbn());
        dto.setEditora(entidade.getEditora());
        dto.setQuatidadeExemplares(entidade.getQuatidadeExemplares());
        return dto;
    }


}
