package com.petweb.petweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petweb.petweb.model.Bodega;
import com.petweb.petweb.repository.BodegaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BodegaService {

    @Autowired
    private BodegaRepository bodegaRepo;

    // Metodo para crear bodega C
    public Bodega crearBodega(Bodega bodega) {
        return bodegaRepo.save(bodega);
    }

    // Metodo para listar las bodegas R
    public List<Bodega> ListarBodegas() {
        return bodegaRepo.findAll();
    }

    // Actualizar nombre de bodegas U
    public void actualizarBodega(Bodega bodega) {
        bodegaRepo.save(bodega);
    }

    // Eliminar bodega D
    public void eliminarBodegaPorId(Integer id) {
        bodegaRepo.deleteById(id);
    }

    // Buscar por id.
    public Optional<Bodega> BuscarPorId(Integer id) {
        return bodegaRepo.findById(id);
    }

    // Patch
    public Bodega patchBodega(Integer id, Bodega bodegaParcial) {
        Optional<Bodega> bodegaOpcional = bodegaRepo.findById(id);
        if (bodegaOpcional.isPresent()) {

            Bodega bodegaActualizar = bodegaOpcional.get();

            if (bodegaParcial.getDireccion_bodega() != null) {
                bodegaActualizar.setDireccion_bodega(bodegaParcial.getDireccion_bodega());
            }

            if (bodegaParcial.getNombre_bodega() != null) {
                bodegaActualizar.setNombre_bodega(bodegaParcial.getNombre_bodega());
            }

            if (bodegaParcial.getCorreo_bodega() != null) {
                bodegaActualizar.setCorreo_bodega(bodegaParcial.getCorreo_bodega());
            }
            return bodegaRepo.save(bodegaActualizar);
        } else {
            return null;
        }
    }
}
