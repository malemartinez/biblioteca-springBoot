package com.example.bibliotecasofka.Services;


import com.example.bibliotecasofka.Model.Biblioteca;
import com.example.bibliotecasofka.Repository.RepositoryBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BibliotecaServices {
    @Autowired
    RepositoryBiblioteca repositoryBiblioteca;


    public List<Biblioteca> obtenerTodos() {
        return this.repositoryBiblioteca.findAll();
    }

    public Biblioteca obtenerPorId(String id) {
       return repositoryBiblioteca.findById(id).orElseThrow(() -> new RuntimeException("No encuentro el libro que quieres men"));
    }

    public Biblioteca crear(Biblioteca biblioteca) {
        return this.repositoryBiblioteca.save(biblioteca);
    }

    public Biblioteca modificar(Biblioteca biblioteca) {
      return repositoryBiblioteca.findById(biblioteca.getId()).orElseThrow(() -> new RuntimeException("Libro no encontrado"));
    }

    public Boolean disponibilidad (String nombreLibro){
       var libro = repositoryBiblioteca.findByNombreLibro(nombreLibro);
       return libro.getPrestado();
    }

    public Biblioteca buscarPorNombre (String nombreLibro){
        return repositoryBiblioteca.findByNombreLibro(nombreLibro);
    }

    public void borrar(String id) {
        repositoryBiblioteca.deleteById(id);
    }

}
