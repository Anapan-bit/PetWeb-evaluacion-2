package com.petweb.petweb.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.petweb.petweb.model.Region;
import com.petweb.petweb.service.RegionService;

@RestController
@RequestMapping("/api/v1/regiones")
public class RegionController {

    @Autowired
    private RegionService regionService;

    //Insertar info (post)
@PostMapping
public ResponseEntity<Region> guardar(@RequestBody Region region) {
    Region regionNueva = regionService.crearRegion(region);
    return ResponseEntity.status(HttpStatus.CREATED).body(regionNueva);
}

    //Mostrar los datos (Get)
    @GetMapping
    public List<Region> ListarRegiones(){
        return regionService.ListarRegiones();
    }

    //Listar-buscar por id
        @GetMapping("/{id}")
        public Optional<Region> BuscarPorId(@PathVariable Integer id){
            return regionService.BuscarPorId(id);
        }
        
    // Actualizar regiones (put)
        @PutMapping("/{id}")
            public void actualizarRegion(@RequestBody Region region){
                regionService.actualizarRegion(region);
            }

    //Borrar (delete)
    @DeleteMapping("/{id}")
        public void eliminarRegion(@PathVariable Integer id){
            regionService.eliminarRegionPorId(id);
        }

    //Actualizar datos (patch)
    @PatchMapping("/{id}")
    public ResponseEntity<Region> patchRegion(@PathVariable Integer id, @RequestBody Region regionParcial) {
        try{
            Region actualizarRegion = regionService.patchRegion(id, regionParcial);
            return ResponseEntity.ok(actualizarRegion);
        } catch(RuntimeException e){
            return ResponseEntity.notFound().build();
        }
        }

    }
