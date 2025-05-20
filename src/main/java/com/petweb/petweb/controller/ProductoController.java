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

import com.petweb.petweb.model.Producto;
import com.petweb.petweb.service.ProductoService;

@RestController
@RequestMapping("/api/v1/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    //Insertar info (post)
    @PostMapping
    public ResponseEntity<Producto> guardar(@RequestBody Producto producto){
    Producto productoNuevo = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productoNuevo);
    }

    //Mostrar los datos (Get)
    @GetMapping
    public List<Producto> ListarProductos(){
        return productoService.ListarProductos();
    }

    //Listar-buscar por id
        @GetMapping("/{id}")
        public Optional<Producto> BuscarPorId(@PathVariable Integer id){
            return productoService.BuscarPorId(id);
        }
        
    // Actualizar productos (put)
        @PutMapping("/{id}")
            public void actualizarProducto(@RequestBody Producto producto){
                productoService.actualizarProducto(producto);
            }

    //Borrar (delete)
    @DeleteMapping("/{id}")
        public void eliminarProducto(@PathVariable Integer id){
            productoService.eliminarProductoPorId(id);
        }

    @PatchMapping("/{id}")
    public ResponseEntity<Producto> patchProducto(@PathVariable Integer id, @RequestBody Producto ProductoParcial) {
        try{
            Producto actualizarProducto = productoService.patchProducto(id, ProductoParcial);
            return ResponseEntity.ok(actualizarProducto);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
        }


}
