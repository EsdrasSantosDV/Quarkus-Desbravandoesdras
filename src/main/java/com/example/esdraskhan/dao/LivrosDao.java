package com.example.esdraskhan.dao;

import com.example.esdraskhan.model.Autor;
import com.example.esdraskhan.model.Livros;
import com.example.esdraskhan.model.Livros;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@RequestScoped
public class LivrosDao {

    public List<Livros> listar(){
        return Livros.listAll(Sort.by("isbn,nome,ano_publicacao,editora,quantidade_exemplares").ascending());
    }


    @Transactional
    public void cadastrar(Livros livros) {
        System.out.println("Dao"+livros.toString());
        livros.persist();
    }


    public Livros buscarPorISBN(String ISBN){
        Livros livro= Livros.findbyISBN(ISBN);
        return livro;
    }

    @Transactional
    public void excluirPorISBN(String ISBN)
    {
        Livros livro=Livros.findbyISBN(ISBN);
        if(livro.isPersistent()){
            // delete it
            livro.delete();
        }
    }

    @Transactional
    public Optional<Livros> atualizar(Livros livro) {
        Livros encontrar= Livros.findbyISBN(livro.getIsbn());
        if(encontrar==null)
        {
            return Optional.empty();
        }
        encontrar.setNome(livro.getNome());
        encontrar.setAnoDePublicacao(livro.getAnoDePublicacao());
        encontrar.setEditora(livro.getEditora());
        encontrar.setQuatidadeExemplares(livro.getQuatidadeExemplares());
        return Optional.of(encontrar);
    }



}
