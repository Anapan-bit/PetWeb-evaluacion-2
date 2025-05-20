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

import com.petweb.petweb.model.Bodega;
import com.petweb.petweb.service.BodegaService;

@RestController
@RequestMapping("/api/v1/bodegas")
public class BodegaController {

    @Autowired
    private BodegaService bodegaService;

    // Insertar info (post)
    @PostMapping
    public ResponseEntity<Bodega> guardar(@RequestBody Bodega bodega) {
        Bodega bodegaNueva = bodegaService.crearBodega(bodega);
        return ResponseEntity.status(HttpStatus.CREATED).body(bodegaNueva);
    }

    // Mostrar los datos (Get)
    @GetMapping
    public List<Bodega> ListarBodegas() {
        return bodegaService.ListarBodegas();
    }

    // Listar-buscar por id
    @GetMapping("/{id}")
    public Optional<Bodega> BuscarPorId(@PathVariable Integer id) {
        return bodegaService.BuscarPorId(id);
    }

    // Actualizar bodega (put)
    @PutMapping("/{id}")
    public void actualizarBodega(@RequestBody Bodega bodega) {
        bodegaService.actualizarBodega(bodega);
    }

    // Borrar (delete)
    @DeleteMapping("/{id}")
    public void eliminarBodega(@PathVariable Integer id) {
        bodegaService.eliminarBodegaPorId(id);
    }

    // Actualizar datos (patch)
    @PatchMapping("/{id}")
    public ResponseEntity<Bodega> patchBodega(@PathVariable Integer id, @RequestBody Bodega bodegaParcial) {
        try {
            Bodega actualizarBodega = bodegaService.patchBodega(id, bodegaParcial);
            return ResponseEntity.ok(actualizarBodega);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}