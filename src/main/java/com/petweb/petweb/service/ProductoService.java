package com.petweb.petweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petweb.petweb.model.Producto;
import com.petweb.petweb.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepo;

    // Metodo para crear producto C
    public Producto crearProducto(Producto producto) {
        return productoRepo.save(producto);
    }

    // Metodo para listar las productos R
    public List<Producto> ListarProductos() {
        return productoRepo.findAll();
    }

    // Actualizar nombre de producto U
    public void actualizarProducto(Producto producto) {
        productoRepo.save(producto);
    }

    // Eliminar producto D
    public void eliminarProductoPorId(Integer id) {
        productoRepo.deleteById(id);
    }

    // Buscar por id.
    public Optional<Producto> BuscarPorId(Integer id) {
        return productoRepo.findById(id);
    }

    // Patch
    public Producto patchProducto(Integer id, Producto productoParcial) {
        Optional<Producto> productoOpcional = productoRepo.findById(id);
        if (productoOpcional.isPresent()) {

            Producto productoActualizar = productoOpcional.get();

            if (productoParcial.getNombre_producto() != null) {
                productoActualizar.setNombre_producto(productoParcial.getNombre_producto());
            }

            if (productoParcial.getPrecio_producto() != null) {
                productoActualizar.setPrecio_producto(productoParcial.getPrecio_producto());
            }

            if (productoParcial.getDescripcion_producto() != null) {
                productoActualizar.setDescripcion_producto(productoParcial.getDescripcion_producto());
            }

            return productoRepo.save(productoActualizar);
        } else {
            return null;
        }
    }

}
