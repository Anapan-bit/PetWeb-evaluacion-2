package com.petweb.petweb.controller;

import java.util.List;
import java.util.Map;
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

import com.petweb.petweb.model.Carrito;
import com.petweb.petweb.service.CarritoService;

@RestController
@RequestMapping("/api/v1/carritos")
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    // Insertar info (post)
    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Carrito carrito) {
        try {
            Carrito carritoNuevo = carritoService.crearCarrito(carrito);
            return ResponseEntity.status(HttpStatus.CREATED).body(carritoNuevo);
        } catch (IllegalArgumentException e) {
            // Esto devolverá un código 400 con el mensaje personalizado
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    // Mostrar los datos (Get)
    @GetMapping
    public List<Carrito> ListarCarrito() {
        return carritoService.ListarCarrito();
    }

    // Listar-buscar por id
    @GetMapping("/{id}")
    public Optional<Carrito> BuscarPorId(@PathVariable Integer id) {
        return carritoService.BuscarPorId(id);
    }

    // Actualizar carrito (put)
    @PutMapping("/{id}")
    public void actualizarCarrito(@RequestBody Carrito carrito) {
        carritoService.actualizarCarrito(carrito);
    }

    // Borrar (delete)
    @DeleteMapping("/{id}")
    public void eliminarCarrito(@PathVariable Integer id) {
        carritoService.eliminarCarritoPorId(id);
    }

    // Actualizar datos (patch)
    @PatchMapping("/{id}")
    public ResponseEntity<Carrito> patchCarrito(@PathVariable Integer id, @RequestBody Carrito carritoParcial) {
        try {
            Carrito actualizarCarrito = carritoService.patchCarrito(id, carritoParcial);
            return ResponseEntity.ok(actualizarCarrito);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
