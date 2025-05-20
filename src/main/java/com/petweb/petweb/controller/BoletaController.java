package com.petweb.petweb.controller;

import java.time.LocalDate;
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

import com.petweb.petweb.model.Boleta;
import com.petweb.petweb.service.BoletaService;

@RestController
@RequestMapping("/api/v1/boletas")
public class BoletaController {

    @Autowired
    private BoletaService boletaService;

    // Insertar info (post)
    @PostMapping
    public ResponseEntity<Boleta> guardar(@RequestBody Boleta boleta) {
        Boleta boletaNueva = boletaService.crearBoleta(boleta);
        return ResponseEntity.status(HttpStatus.CREATED).body(boletaNueva);
    }

    // Mostrar los datos (Get)
    @GetMapping
    public List<Boleta> ListarBoletas() {
        return boletaService.ListarBoletas();
    }

    // Listar-buscar por id
    @GetMapping("/{id}")
    public Optional<Boleta> BuscarPorId(@PathVariable Integer id) {
        return boletaService.BuscarPorId(id);
    }

    // Query buscar x fecha de emision
    @GetMapping("/buscarFecha/{fecha}") // año-mes-dia
    public List<Boleta> findByFechaEmision(@PathVariable LocalDate fecha) {
        return boletaService.findByFechaEmision(fecha);
    }

    // Query buscar x
    @GetMapping("/buscarCliente/{cliente}")
    public List<Boleta> findByClienteId(@PathVariable Integer cliente) {
        return boletaService.findByClienteId(cliente);
    }

    // Actualizar boleta (put)
    @PutMapping("/{id}")
    public void actualizarBoleta(@RequestBody Boleta boleta) {
        boletaService.actualizarBoleta(boleta);
    }

    // Borrar (delete)
    @DeleteMapping("/{id}")
    public void eliminarBoleta(@PathVariable Integer id) {
        boletaService.eliminarBoletaPorId(id);
    }

    // Actualizar datos (patch)
    @PatchMapping("/{id}")
    public ResponseEntity<Boleta> patchBoleta(@PathVariable Integer id, @RequestBody Boleta boletaParcial) {
        try {
            Boleta actualizarBoleta = boletaService.patchBoleta(id, boletaParcial);
            return ResponseEntity.ok(actualizarBoleta);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
