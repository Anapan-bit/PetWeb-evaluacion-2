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

import com.petweb.petweb.model.Cliente;
import com.petweb.petweb.service.ClienteService;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    // Insertar info (post)
    @PostMapping
    public ResponseEntity<Cliente> guardar(@RequestBody Cliente cliente) {
        Cliente clienteNuevo = clienteService.crearCliente(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteNuevo);
    }

    // Mostrar los datos (Get)
    @GetMapping
    public List<Cliente> ListarClientes() {
        return clienteService.ListarClientes();
    }

    // Listar-buscar por id
    @GetMapping("/{id}")
    public Optional<Cliente> BuscarPorId(@PathVariable Integer id) {
        return clienteService.BuscarPorId(id);
    }

    // Actualizar clientes (put)
    @PutMapping("/{id}")
    public void actualizarCliente(@RequestBody Cliente cliente) {
        clienteService.actualizarCliente(cliente);
    }

    // Borrar (delete)
    @DeleteMapping("/{id}")
    public void eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminarClientePorId(id);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cliente> patchCliente(@PathVariable Integer id, @RequestBody Cliente clienteParcial) {
        try {
            Cliente actualizarCliente = clienteService.patchCliente(id, clienteParcial);
            return ResponseEntity.ok(actualizarCliente);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
