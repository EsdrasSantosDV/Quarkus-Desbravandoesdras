package com.example.esdraskhan.dao;

import com.example.esdraskhan.model.Cliente;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class ClienteDao {


    public List<Cliente> listar(){
        return Cliente.listAll(Sort.by("nome,email,contato").ascending());
    }







}
