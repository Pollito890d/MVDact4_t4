package com.mvdact4_t4.aplicacion_autenticacion.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mvdact4_t4.aplicacion_autenticacion.dto.TelefonoDTO;
import com.mvdact4_t4.aplicacion_autenticacion.models.Marca;
import com.mvdact4_t4.aplicacion_autenticacion.models.Telefono;
import com.mvdact4_t4.aplicacion_autenticacion.repositories.MarcaRepository;
import com.mvdact4_t4.aplicacion_autenticacion.repositories.TelefonoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/telefonos")
public class TelefonoApiController {

    @Autowired
    private TelefonoRepository telefonoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    // GET Paginado: /api/telefonos?page=0&size=5
    @GetMapping
    public ResponseEntity<Page<TelefonoDTO>> listar(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Page<TelefonoDTO> pagina = telefonoRepository.findAll(PageRequest.of(page, size))
                .map(this::aDTO);
        return ResponseEntity.ok(pagina);
    }

    // GET Individual
    @GetMapping("/{id}")
    public ResponseEntity<TelefonoDTO> obtenerPorId(@PathVariable Long id) {
        return telefonoRepository.findById(id)
                .map(tel -> ResponseEntity.ok(aDTO(tel)))
                .orElse(ResponseEntity.notFound().build());
    }

    // POST Crear (201 Created)
    @PostMapping
    public ResponseEntity<TelefonoDTO> crear(@Valid @RequestBody TelefonoDTO dto) {
        Marca marca = marcaRepository.findById(dto.getMarcaId()).orElse(null);
        if (marca == null) {
            return ResponseEntity.badRequest().build();
        }
        Telefono guardado = telefonoRepository.save(
                new Telefono(dto.getModelo(), dto.getPrecio(), dto.getAlmacenamientoGb(), marca)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(aDTO(guardado));
    }

    // PUT Actualizar (200 OK / 404 Not Found)
    @PutMapping("/{id}")
    public ResponseEntity<TelefonoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody TelefonoDTO dto) {
        return telefonoRepository.findById(id).map(existente -> {
            Marca marca = marcaRepository.findById(dto.getMarcaId()).orElse(null);
            if (marca == null) {
                return ResponseEntity.badRequest().<TelefonoDTO>build();
            }
            existente.setModelo(dto.getModelo());
            existente.setPrecio(dto.getPrecio());
            existente.setAlmacenamientoGb(dto.getAlmacenamientoGb());
            existente.setMarca(marca);
            return ResponseEntity.ok(aDTO(telefonoRepository.save(existente)));
        }).orElse(ResponseEntity.notFound().build());
    }

    // DELETE Eliminar (204 No Content / 404 Not Found)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!telefonoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        telefonoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    private TelefonoDTO aDTO(Telefono t) {
        return new TelefonoDTO(
                t.getId(),
                t.getModelo(),
                t.getPrecio(),
                t.getAlmacenamientoGb(),
                t.getMarca() != null ? t.getMarca().getId() : null,
                t.getMarca() != null ? t.getMarca().getNombre() : "Sin marca"
        );
    }
}
