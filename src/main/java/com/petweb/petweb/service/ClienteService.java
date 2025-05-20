package com.petweb.petweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petweb.petweb.model.Cliente;
import com.petweb.petweb.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    // Metodo para crear clientes C
    public Cliente crearCliente(Cliente cliente) {
        return clienteRepo.save(cliente);
    }

    // Metodo para listar las cliente R
    public List<Cliente> ListarClientes() {
        return clienteRepo.findAll();
    }

    // Actualizar nombre de cliente U
    public void actualizarCliente(Cliente cliente) {
        clienteRepo.save(cliente);
    }

    // Eliminar cliente D
    public void eliminarClientePorId(Integer id) {
        clienteRepo.deleteById(id);
    }

    // Buscar por id.
    public Optional<Cliente> BuscarPorId(Integer id) {
        return clienteRepo.findById(id);
    }

    // Patch
    public Cliente patchCliente(Integer id, Cliente clienteParcial) {
        Optional<Cliente> clienteOpcional = clienteRepo.findById(id);
        if (clienteOpcional.isPresent()) {

            Cliente clienteActualizar = clienteOpcional.get();

            if (clienteParcial.getNombre_cliente() != null) {
                clienteActualizar.setNombre_cliente(clienteParcial.getNombre_cliente());
            }

            if (clienteParcial.getApellido_cliente() != null) {
                clienteActualizar.setApellido_cliente(clienteParcial.getApellido_cliente());
            }

            if (clienteParcial.getRut_cliente() != null) {
                clienteActualizar.setRut_cliente(clienteParcial.getRut_cliente());
            }

            if (clienteParcial.getDireccion_cliente() != null) {
                clienteActualizar.setDireccion_cliente(clienteParcial.getDireccion_cliente());
            }

            if (clienteParcial.getDireccion_cliente() != null) {
                clienteActualizar.setDireccion_cliente(clienteParcial.getDireccion_cliente());
            }

            if (clienteParcial.getCorreo_cliente() != null) {
                clienteActualizar.setCorreo_cliente(clienteParcial.getCorreo_cliente());
            }

            if (clienteParcial.getContrasena_cliente() != null) {
                clienteActualizar.setContrasena_cliente(clienteParcial.getContrasena_cliente());
            }

            if (clienteParcial.getTelefono_cliente() != null) {
                clienteActualizar.setTelefono_cliente(clienteParcial.getTelefono_cliente());
            }

            return clienteRepo.save(clienteActualizar);
        } else {
            return null;
        }
    }

}
