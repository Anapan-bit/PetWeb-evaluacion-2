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

import com.petweb.petweb.model.Comuna;
import com.petweb.petweb.service.ComunaService;

@RestController
@RequestMapping("/api/v1/comunas")
public class ComunaController {

    @Autowired
    private ComunaService comunaService;

    // Insertar info (post)
    @PostMapping
    public ResponseEntity<Comuna> guardar(@RequestBody Comuna comuna) {
        Comuna comunaNueva = comunaService.crearComuna(comuna);
        return ResponseEntity.status(HttpStatus.CREATED).body(comunaNueva);
    }

    // Mostrar los datos (Get)
    @GetMapping
    public List<Comuna> ListarComunas() {
        return comunaService.ListarComunas();
    }

    // Listar-buscar por id
    @GetMapping("/{id}")
    public Optional<Comuna> BuscarPorId(@PathVariable Integer id) {
        return comunaService.BuscarPorId(id);
    }

    // Actualizar comunas (put)
    @PutMapping("/{id}")
    public void actualizarComuna(@RequestBody Comuna comuna) {
        comunaService.actualizarComuna(comuna);
    }

    // Borrar (delete)
    @DeleteMapping("/{id}")
    public void eliminarComuna(@PathVariable Integer id) {
        comunaService.eliminarComunaPorId(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Comuna> patchComuna(@PathVariable Integer id, @RequestBody Comuna comunaParcial) {
        try {
            Comuna actualizarComuna = comunaService.patchComuna(id, comunaParcial);
            return ResponseEntity.ok(actualizarComuna);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
