package com.api.inventario.repository;
import com.api.inventario.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository  extends JpaRepository <Inventario, Integer>{

}
