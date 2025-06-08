package com.api.inventario.repository;
import com.api.inventario.models.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface InventarioRepository  extends JpaRepository <Inventario, Long>{

}
