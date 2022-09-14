package com.example.esdraskhan.dao;

import com.example.esdraskhan.model.Cliente;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ClienteDao {


    public List<Cliente> listar(){
        return Cliente.listAll(Sort.by("nome,email,contato").ascending());
    }

    @Transactional
    public void cadastrar(Cliente cliente) {
        cliente.persist();
    }


    public Cliente buscarPorEmail(String email){
        Cliente cliente= Cliente.findbyEmail(email);
        return cliente;
    }


}
