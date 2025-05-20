package com.petweb.petweb.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petweb.petweb.model.Boleta;
import com.petweb.petweb.repository.BoletaRepository;
import com.petweb.petweb.repository.ClienteRepository;
import com.petweb.petweb.repository.EstadoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BoletaService {

    @Autowired
    private BoletaRepository boletaRepo;
    @Autowired
    private ClienteRepository clienteRepo;
    @Autowired
    private EstadoRepository estadoRepo;

    // Metodo para crear boletas C
    public Boleta crearBoleta(Boleta boleta) {
        return boletaRepo.save(boleta);
    }

    // Metodo para listar las boletas R
    public List<Boleta> ListarBoletas() {
        return boletaRepo.findAll();
    }

    // Actualizar boleta U
    public Boleta actualizarBoleta(Boleta boletaEntrada) {
        Boleta boletaExistente = boletaRepo.findById(boletaEntrada.getId())
                .orElseThrow(() -> new RuntimeException("No se encontró boleta con el ID indicado"));

        boletaExistente.setCuponDescuento(boletaEntrada.getCuponDescuento());
        boletaExistente.setFechaEmision(boletaEntrada.getFechaEmision());
        boletaExistente.setGastoEnvio(boletaEntrada.getGastoEnvio());

        if (boletaEntrada.getCliente() != null && boletaEntrada.getCliente().getId() != null) {
            boletaExistente.setCliente(clienteRepo.getReferenceById(boletaEntrada.getCliente().getId()));
        }

        if (boletaEntrada.getEstado() != null && boletaEntrada.getEstado().getId() != null) {
            boletaExistente.setEstado(estadoRepo.getReferenceById(boletaEntrada.getEstado().getId()));
        }

        return boletaRepo.save(boletaExistente);
    }

    // Eliminar boleta D
    public void eliminarBoletaPorId(Integer id) {
        boletaRepo.deleteById(id);
    }

    // Buscar por id.
    public Optional<Boleta> BuscarPorId(Integer id) {
        return boletaRepo.findById(id);
    }

    // Patch
    public Boleta patchBoleta(Integer id, Boleta boletaParcial) {
        Optional<Boleta> boletaOpcional = boletaRepo.findById(id);
        if (boletaOpcional.isPresent()) {

            Boleta boletaActualizar = boletaOpcional.get();

            if (boletaParcial.getCuponDescuento() != null) {
                boletaActualizar.setCuponDescuento(boletaParcial.getCuponDescuento());
            }

            if (boletaParcial.getFechaEmision() != null) {
                boletaActualizar.setFechaEmision(boletaParcial.getFechaEmision());
            }

            if (boletaParcial.getGastoEnvio() != null) {
                boletaActualizar.setGastoEnvio(boletaParcial.getGastoEnvio());
            }

            return boletaRepo.save(boletaActualizar);
        } else {
            return null;
        }
    }

    // Query buscar x fecha
    public List<Boleta> findByFechaEmision(LocalDate fecha) {
        return boletaRepo.findByFechaEmision(fecha);
    }

    // Query buscar x id cliente
    public List<Boleta> findByClienteId(Integer cliente) {
        return boletaRepo.findByClienteId(cliente);
    }
}
