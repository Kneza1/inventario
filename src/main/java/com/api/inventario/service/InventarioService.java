package com.api.inventario.service;
import java.util.List;
import java.util.Optional;
import com.api.inventario.models.Inventario;
public interface InventarioService {

    List<Inventario> findByAll();

    Optional<Inventario> findById(Long id);

    Inventario save(Inventario unInventario);

    void deleteById(Long id);
}
