package com.example.esdraskhan.dto;


public class LivrosDto {

    private String isbn;

    private String nome;

    private String anoDePublicacao;

    private String editora;

    private Integer quatidadeExemplares;


    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(String anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public Integer getQuatidadeExemplares() {
        return quatidadeExemplares;
    }

    public void setQuatidadeExemplares(Integer quatidadeExemplares) {
        this.quatidadeExemplares = quatidadeExemplares;
    }

    @Override
    public String toString() {
        return "LivrosDto{" +
                "isbn='" + isbn + '\'' +
                ", nome='" + nome + '\'' +
                ", anoDePublicacao='" + anoDePublicacao + '\'' +
                ", editora='" + editora + '\'' +
                ", quatidadeExemplares=" + quatidadeExemplares +
                '}';
    }
}