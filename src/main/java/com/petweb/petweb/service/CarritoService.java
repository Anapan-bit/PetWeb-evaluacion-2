package com.petweb.petweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petweb.petweb.model.Carrito;
import com.petweb.petweb.model.Existencias;
import com.petweb.petweb.repository.CarritoRepository;
import com.petweb.petweb.repository.ExistenciasRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CarritoService {

    @Autowired
    private CarritoRepository carritoRepo;
    @Autowired
    private ExistenciasRepository existenciasRepo;

    // Metodo para crear carrito C
    public Carrito crearCarrito(Carrito carrito) {
        Integer productoId = carrito.getProducto().getId();
        List<Existencias> existencias = existenciasRepo.findByProducto_Id(productoId);
        boolean hayStock = existencias.stream().anyMatch(e -> e.getStock() > 0);
        if (!hayStock) {
            throw new IllegalArgumentException("No hay stock disponible para este producto.");
        }
        return carritoRepo.save(carrito);
    }

    // Metodo para listar los carrito R
    public List<Carrito> ListarCarrito() {
        return carritoRepo.findAll();
    }

    // Actualizar nombre de carrito U
    public void actualizarCarrito(Carrito carrito) {
        carritoRepo.save(carrito);
    }

    // Eliminar carrito D
    public void eliminarCarritoPorId(Integer id) {
        carritoRepo.deleteById(id);
    }

    // Buscar por id.
    public Optional<Carrito> BuscarPorId(Integer id) {
        return carritoRepo.findById(id);
    }

    // Patch
    public Carrito patchCarrito(Integer id, Carrito carritoParcial) {
        Optional<Carrito> carritoOpcional = carritoRepo.findById(id);
        if (carritoOpcional.isPresent()) {

            Carrito carritoActualizar = carritoOpcional.get();

            if (carritoParcial.getCantidad_producto() != null) {
                carritoActualizar.setCantidad_producto(carritoParcial.getCantidad_producto());
            }

            return carritoRepo.save(carritoActualizar);
        } else {
            return null;
        }
    }

}
