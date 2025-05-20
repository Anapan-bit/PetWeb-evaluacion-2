package com.petweb.petweb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.petweb.petweb.repository.RegionRepository;

import jakarta.transaction.Transactional;

import com.petweb.petweb.model.Region;

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionRepository regionRepo;

    // Metodo para crear regiones C
    public Region crearRegion(Region region) {
        return regionRepo.save(region);
    }

    // Metodo para listar las regiones R
    public List<Region> ListarRegiones() {
        return regionRepo.findAll();
    }

    // Actualizar nombre de region U
    public void actualizarRegion(Region region) {
        regionRepo.save(region);
    }

    // Eliminar Region D
    public void eliminarRegionPorId(Integer id) {
        regionRepo.deleteById(id);
    }

    // Buscar por id.
    public Optional<Region> BuscarPorId(Integer id) {
        return regionRepo.findById(id);
    }

    // Patch
    public Region patchRegion(Integer id, Region regionParcial) {
        Optional<Region> regionOpcional = regionRepo.findById(id);
        if (regionOpcional.isPresent()) {

            Region regionActualizar = regionOpcional.get();

            if (regionParcial.getNombre_region() != null) {
                regionActualizar.setNombre_region(regionParcial.getNombre_region());
            }
            return regionRepo.save(regionActualizar);
        } else {
            return null;
        }
    }
}
