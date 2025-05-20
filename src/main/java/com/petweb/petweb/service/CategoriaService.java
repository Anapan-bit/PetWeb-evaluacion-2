package com.petweb.petweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petweb.petweb.model.Categoria;
import com.petweb.petweb.repository.CategoriaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepo;

    // Metodo para crear categoria C
    public Categoria crearCategoria(Categoria categoria) {
        return categoriaRepo.save(categoria);
    }

    // Metodo para listar las categorias R
    public List<Categoria> ListarCategorias() {
        return categoriaRepo.findAll();
    }

    // Actualizar nombre de categoria U
    public void actualizarCategoria(Categoria categoria) {
        categoriaRepo.save(categoria);
    }

    // Eliminar categoria D
    public void eliminarCategoriaPorId(Integer id) {
        categoriaRepo.deleteById(id);
    }

    // Buscar por id.
    public Optional<Categoria> BuscarPorId(Integer id) {
        return categoriaRepo.findById(id);
    }

    // Patch
    public Categoria patchCategoria(Integer id, Categoria categoriaParcial) {
        Optional<Categoria> categoriaOpcional = categoriaRepo.findById(id);
        if (categoriaOpcional.isPresent()) {

            Categoria categoriaActualizar = categoriaOpcional.get();

            if (categoriaParcial.getNombre_categoria() != null) {
                categoriaActualizar.setNombre_categoria(categoriaParcial.getNombre_categoria());
            }
            return categoriaRepo.save(categoriaActualizar);
        } else {
            return null;
        }
    }
}
