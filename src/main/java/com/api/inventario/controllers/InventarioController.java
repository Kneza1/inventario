package com.api.inventario.controllers;

import com.api.inventario.dto.InventarioDTO;
import com.api.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/inventario")
public class InventarioController {
  
    @Autowired
    private InventarioService inventarioService;

    @PostMapping
    public ResponseEntity<InventarioDTO> crear(@RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(inventarioService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> listar() {
        return ResponseEntity.ok(inventarioService.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> buscar(@PathVariable Integer id) {
        return ResponseEntity.ok(inventarioService.buscar(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> actualizar(@PathVariable Integer id, @RequestBody InventarioDTO dto) {
        return ResponseEntity.ok(inventarioService.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        inventarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    } 

}



    

