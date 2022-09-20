package com.example.esdraskhan.dao;

import com.example.esdraskhan.model.Autor;
import io.quarkus.panache.common.Sort;
import org.eclipse.microprofile.opentracing.Traced;

import javax.enterprise.context.RequestScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequestScoped
@Traced
public class AutorDao {


    public List<Autor> listar(){
        return Autor.listAll(Sort.by("nome, email, dataNascimento, biografia").ascending());
    }


    @Transactional
    public void cadastrar(Autor autor) {
        System.out.println(autor.toString());
        autor.persist();
    }




    public Autor buscarPorISNI(String ISNI){
        Autor autor= Autor.findbyISNI(ISNI);
        return autor;
    }
    @Transactional
    public void excluirPorISNI(String ISNI)
    {
        Autor autor= Autor.findbyISNI(ISNI);
        if(autor.isPersistent()){
            // delete it
            autor.delete();
        }
    }


    @Transactional
    public Optional<Autor> atualizar(Autor autor) {
        Autor encontrar=Autor.findbyISNI(autor.getIsni());
        if(encontrar==null)
        {
            return Optional.empty();
        }
        encontrar.setNome(autor.getNome());
        encontrar.setEmail(autor.getEmail());
        encontrar.setBiografia(autor.getBiografia());
        encontrar.setDataNascimento(autor.getDataNascimento());
        return Optional.of(encontrar);
    }
}

