package com.mvdact4_t4.aplicacion_autenticacion.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mvdact4_t4.aplicacion_autenticacion.models.Marca;
import com.mvdact4_t4.aplicacion_autenticacion.models.Telefono;
import com.mvdact4_t4.aplicacion_autenticacion.repositories.MarcaRepository;
import com.mvdact4_t4.aplicacion_autenticacion.repositories.TelefonoRepository;

@Service
public class TelefonoService {

    @Autowired //Una especie de crear una instancia que nos permita acceder a los métodos para actualizar y eliminar
    private TelefonoRepository telefonoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    // Métodos para Marca
    public List<Marca> listarMarcas() {
        return marcaRepository.findAll();
    }

    public Optional<Marca> buscarMarcaPorId(Long id) {
        return marcaRepository.findById(id);
    }

    public Marca guardarMarca(Marca marca) {
        return marcaRepository.save(marca);
    }

    //Leer 
    public List<Telefono> listarTelefonos() {
        return telefonoRepository.findAll();
    }

    //Leer uno por ID
    public Optional<Telefono> buscarTelefonoPorId(Long id) {
        return telefonoRepository.findById(id);
    }

    //Crear
    public Telefono guardarTelefono(Telefono telefono) {
        return telefonoRepository.save(telefono);
    }

    //Actualizar
    public Telefono actualizarTelefono(Long id, Telefono datosActualizados) {
        return telefonoRepository.findById(id).map(telefonoExistente -> {
            telefonoExistente.setModelo(datosActualizados.getModelo());
            telefonoExistente.setPrecio(datosActualizados.getPrecio());
            telefonoExistente.setAlmacenamientoGb(datosActualizados.getAlmacenamientoGb());
            if (datosActualizados.getMarca() != null) {
                telefonoExistente.setMarca(datosActualizados.getMarca());
            }
            return telefonoRepository.save(telefonoExistente);
        }).orElse(null);
    }

    //Eliminar
    public boolean eliminarTelefono(Long id) {
        if (telefonoRepository.existsById(id)) {
            telefonoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}