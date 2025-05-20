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

import com.petweb.petweb.model.Estado;
import com.petweb.petweb.service.EstadoService;

@RestController
@RequestMapping("/api/v1/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

        //Insertar info (post)
    @PostMapping
    public ResponseEntity<Estado> guardar(@RequestBody Estado estado){
        Estado estadoNuevo = estadoService.crearEstado(estado);
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoNuevo);
    }

    //Mostrar los datos (Get)
    @GetMapping
    public List<Estado> ListarEstados(){
        return estadoService.ListarEstados();
    }

    //Listar-buscar por id
    @GetMapping("/{id}")
    public Optional<Estado> BuscarPorId(@PathVariable Integer id){
            return estadoService.BuscarPorId(id);
    }
        
    // Actualizar estados (put)
    @PutMapping("/{id}")
    public void actualizarEstado(@RequestBody Estado estado){
        estadoService.actualizarEstado(estado);
    }

    //Borrar (delete)
    @DeleteMapping("/{id}")
    public void eliminarEstado(@PathVariable Integer id){
        estadoService.eliminarEstadoPorId(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Estado> patchEstado(@PathVariable Integer id, @RequestBody Estado estadoParcial) {
        try{
            Estado actualizarEstado = estadoService.patchEstado(id, estadoParcial);
            return ResponseEntity.ok(actualizarEstado);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
        }

}
