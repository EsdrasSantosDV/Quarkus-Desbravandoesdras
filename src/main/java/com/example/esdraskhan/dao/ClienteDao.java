package com.example.esdraskhan.dao;

import com.example.esdraskhan.model.Cliente;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Traced
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
    @Transactional
    public void excluirPorEmail(String email)
    {
        Cliente cliente= Cliente.findbyEmail(email);
        if(cliente.isPersistent()){
            // delete it
            cliente.delete();
        }
    }


    @Transactional
    public Optional<Cliente> atualizar(Cliente cliente) {
       Cliente encontrar=Cliente.findbyEmail(cliente.getEmail());

       if(encontrar==null)
       {
           return Optional.empty();
       }
       encontrar.setNome(cliente.getNome());
       encontrar.setContato(cliente.getContato());
       return Optional.of(encontrar);
    }
}
