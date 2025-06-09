package com.api.inventario.service;

import com.api.inventario.models.Inventario;
import com.api.inventario.dto.InventarioDTO;
import com.api.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;
    private InventarioDTO toDTO(Inventario inventario) {
        return new InventarioDTO(
            inventario.getIdProducto(),
            inventario.getNombreProducto(),
            inventario.getStock()
        );
    }

    private Inventario toEntity(InventarioDTO dto) {
        Inventario inventario = new Inventario();
        inventario.setIdProducto(dto.getIdProducto());
        inventario.setNombreProducto(dto.getNombreProducto());
        inventario.setStock(dto.getStock());
        return inventario;
    } 

    public InventarioDTO crear (InventarioDTO dto) {
        Inventario inventario = toEntity(dto);
        return toDTO(inventarioRepository.save(inventario));
    }

    public List<InventarioDTO> listar() {
        return inventarioRepository.findAll().stream()
            .map(this::toDTO)
            .collect(Collectors.toList());
    }

    public InventarioDTO buscar(Integer id) {
        Inventario inventario = inventarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        return toDTO(inventario);
    }

    public InventarioDTO actualizar(Integer id, InventarioDTO dto) {
        Inventario existente = inventarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existente.setNombreProducto(dto.getNombreProducto());
        existente.setStock(dto.getStock());
        return toDTO(inventarioRepository.save(existente));
    }

    public void eliminar(Integer id) {
        inventarioRepository.deleteById(id);
    }
}
