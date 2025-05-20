package com.petweb.petweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petweb.petweb.model.Estado;
import com.petweb.petweb.repository.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepo;

    // Metodo para crear estados C
    public Estado crearEstado(Estado estado) {
        return estadoRepo.save(estado);
    }

    // Metodo para listar los estados R
    public List<Estado> ListarEstados() {
        return estadoRepo.findAll();
    }

    // Actualizar nombre de estado U
    public void actualizarEstado(Estado estado) {
        estadoRepo.save(estado);
    }

    // Eliminar estado D
    public void eliminarEstadoPorId(Integer id) {
        estadoRepo.deleteById(id);
    }

    // Buscar por id.
    public Optional<Estado> BuscarPorId(Integer id) {
        return estadoRepo.findById(id);

    }

    // Patch
    public Estado patchEstado(Integer id, Estado estadoParcial) {
        Optional<Estado> estadoOpcional = estadoRepo.findById(id);
        if (estadoOpcional.isPresent()) {

            Estado estadoActualizar = estadoOpcional.get();

            if (estadoParcial.getEstado_compra() != null) {
                estadoActualizar.setEstado_compra(estadoParcial.getEstado_compra());
            }
            return estadoRepo.save(estadoActualizar);
        } else {
            return null;
        }
    }
}