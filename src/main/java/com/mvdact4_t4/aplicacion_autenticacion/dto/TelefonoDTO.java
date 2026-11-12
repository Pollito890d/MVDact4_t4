package com.mvdact4_t4.aplicacion_autenticacion.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TelefonoDTO {
    private Long id;

    @NotBlank(message = "El modelo es obligatorio")
    private String modelo;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private Double precio;

    @NotNull(message = "El almacenamiento es obligatorio")
    @Min(value = 16, message = "Mínimo 16 GB")
    private Integer almacenamientoGb;

    @NotNull(message = "El ID de la marca es obligatorio")
    private Long marcaId;

    private String marcaNombre; 

    public TelefonoDTO() {}

    public TelefonoDTO(Long id, String modelo, Double precio, Integer almacenamientoGb, Long marcaId, String marcaNombre) {
        this.id = id;
        this.modelo = modelo;
        this.precio = precio;
        this.almacenamientoGb = almacenamientoGb;
        this.marcaId = marcaId;
        this.marcaNombre = marcaNombre;
    }

    public Long getId() { 
        return id; 
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getModelo() { 
        return modelo; 
    }

    public void setModelo(String modelo) { 
        this.modelo = modelo; 
    }

    public Double getPrecio() { 
        return precio; 
    }

    public void setPrecio(Double precio) { 
        this.precio = precio; 
    }

    public Integer getAlmacenamientoGb() { 
        return almacenamientoGb; 
    }

    public void setAlmacenamientoGb(Integer almacenamientoGb) { 
        this.almacenamientoGb = almacenamientoGb; 
    }

    public Long getMarcaId() { 
        return marcaId; 
    }

    public void setMarcaId(Long marcaId) { 
        this.marcaId = marcaId; 
    }

    public String getMarcaNombre() { 
        return marcaNombre; 
    }
    
    public void setMarcaNombre(String marcaNombre) { 
        this.marcaNombre = marcaNombre; 
    }
}
