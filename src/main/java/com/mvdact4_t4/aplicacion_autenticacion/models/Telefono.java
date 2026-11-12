package com.mvdact4_t4.aplicacion_autenticacion.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "telefonos")
public class Telefono {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String modelo;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer almacenamientoGb;

    
    @ManyToOne(fetch = FetchType.EAGER)// ManyToOne: La llave foránea marca_id conecta con la tabla marcas
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    public Telefono() {}

    public Telefono(String modelo, Double precio, Integer almacenamientoGb, Marca marca) {
        this.modelo = modelo;
        this.precio = precio;
        this.almacenamientoGb = almacenamientoGb;
        this.marca = marca;
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

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

}
