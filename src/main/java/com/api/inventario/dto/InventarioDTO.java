package com.api.inventario.dto;

import org.springframework.hateoas.RepresentationModel;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO extends RepresentationModel<InventarioDTO> {

    private Integer idProducto;
    private String nombreProducto;
    private Integer stock;

}
