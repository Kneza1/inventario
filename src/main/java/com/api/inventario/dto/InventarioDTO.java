package com.api.inventario.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {

    private Integer idProducto;
    private String nombreProducto;
    private Integer stock;
}
