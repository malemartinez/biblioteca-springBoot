package com.example.bibliotecasofka.Controller;

import com.example.bibliotecasofka.Model.Biblioteca;
import com.example.bibliotecasofka.Services.BibliotecaServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/libros")
public class BibliotecaController {

    @Autowired
    BibliotecaServices bibliotecaServices;

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> findbyId(@PathVariable("id") String id) {
        return new ResponseEntity(bibliotecaServices.obtenerPorId(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Biblioteca>> findAll() {
        return new ResponseEntity(bibliotecaServices.obtenerTodos(), HttpStatus.OK);
    }

    @GetMapping("/disponibilidad")
    public ResponseEntity<String> disponibilidad(@RequestBody Biblioteca Libro){
        if(bibliotecaServices.disponibilidad(Libro.getNombreLibro())){
            return new ResponseEntity("El Libro está disponible para prestamo", HttpStatus.OK);
        }
        return new ResponseEntity(bibliotecaServices.buscarPorNombre(Libro.getNombreLibro()).getFecha(),
                HttpStatus.OK);
    }
    

    @PostMapping("/libros")
    public ResponseEntity<Biblioteca> create(@RequestBody Biblioteca biblioteca) {
        return new ResponseEntity(bibliotecaServices.crear(biblioteca), HttpStatus.CREATED);
    }

    @PutMapping("/libros")
    public ResponseEntity<Biblioteca> update(@RequestBody Biblioteca biblioteca){
        if (biblioteca.getId() != null){
            return new ResponseEntity(bibliotecaServices.modificar(biblioteca),HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
