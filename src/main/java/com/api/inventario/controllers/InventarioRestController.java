package com.api.inventario.controllers;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



import com.api.inventario.models.Inventario;
import com.api.inventario.service.InventarioService;


@RestController
@RequestMapping("api/inventario")
public class InventarioRestController {
  
    
    @Autowired
    private InventarioService service;

    @GetMapping
    public List<Inventario> List(){
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Inventario> inventarioOptional = service.findById(id);
        if (inventarioOptional.isPresent()){
            return ResponseEntity.ok(inventarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Inventario> crear (@RequestBody Inventario unInventario){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unInventario));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Inventario unInventario){
        Optional <Inventario> inventarioOptional = service.findById(id);
        if(inventarioOptional.isPresent()){
            Inventario inventarioexistente = inventarioOptional.get();
            inventarioexistente.setProducto(unInventario.getProducto());
            inventarioexistente.setCantidad(unInventario.getCantidad());
            inventarioexistente.setUbicacion(unInventario.getUbicacion());
            inventarioexistente.setFechaIngreso(unInventario.getFechaIngreso());
            Inventario inventariomodificado = service.save(inventarioexistente);
            return ResponseEntity.ok(inventariomodificado);
        }
        return ResponseEntity.notFound().build();
    }

       @DeleteMapping("/{id}")
   public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Inventario> inventarioOptional = service.findById(id);
        if (inventarioOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
    }
    return ResponseEntity.notFound().build();
}

}



    

