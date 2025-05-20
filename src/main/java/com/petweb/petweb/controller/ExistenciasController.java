package com.petweb.petweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petweb.petweb.model.Existencias;
import com.petweb.petweb.service.ExistenciasService;

@RestController
@RequestMapping("/api/v1/existencias")
public class ExistenciasController {

    @Autowired
    private ExistenciasService existenciasService;

        //Insertar info (post)
    @PostMapping
    public ResponseEntity<Existencias> guardar(@RequestBody Existencias existencias){
        Existencias existenciasNueva = existenciasService.crearExistencias(existencias);
        return ResponseEntity.status(HttpStatus.CREATED).body(existenciasNueva);
    }

    //Mostrar los datos (Get)
    @GetMapping
    public List<Existencias> ListarExistencias(){
        return existenciasService.ListarExistencias();
    }

    //Listar-buscar por id
        @GetMapping("/{id}")
        public Optional<Existencias> BuscarPorId(@PathVariable Integer id){
            return existenciasService.BuscarPorId(id);
        }
        
    // Actualizar existencias (put)
        @PutMapping("/{id}")
            public void actualizarExistencias(@RequestBody Existencias existencias){
                existenciasService.actualizarExistencias(existencias);
            }

    //Borrar (delete)
    @DeleteMapping("/{id}")
        public void eliminarExistencias(@PathVariable Integer id){
            existenciasService.eliminarExistenciasPorId(id);
        }

        @PatchMapping("/{id}")
    public ResponseEntity<Existencias> patchExistencias(@PathVariable Integer id, @RequestBody Existencias existenciasParcial) {
        try{
            Existencias actualizarExistencias = existenciasService.patchExistencias(id, existenciasParcial);
            return ResponseEntity.ok(actualizarExistencias);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
        }

}
