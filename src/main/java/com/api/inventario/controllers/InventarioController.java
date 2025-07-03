package com.api.inventario.controllers;

import com.api.inventario.dto.InventarioDTO;
import com.api.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo; 
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

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

    @GetMapping("/hateoas/{id}")
    public InventarioDTO obtenerHATEOAS(@PathVariable Integer id) { 
        InventarioDTO dto = inventarioService.buscar(id);
        // Agregar enlaces HATEOAS 
        dto.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(id)).withSelfRel()); 
        dto.add(linkTo(methodOn(InventarioController.class).obtenerTodosHATEOAS()).withRel("todos")); 
        dto.add(linkTo(methodOn(InventarioController.class).eliminar(id)).withRel("eliminar")); 
        return dto;
    }
    @GetMapping("/hateoas") 
    public List<InventarioDTO> obtenerTodosHATEOAS() { 
        List<InventarioDTO> lista = inventarioService.listar(); 
            for (InventarioDTO dto : lista) { 
            dto.add(linkTo(methodOn(InventarioController.class).obtenerHATEOAS(dto.getIdProducto())).withSelfRel()); 
    } 
    return lista; 
} 
}



    

