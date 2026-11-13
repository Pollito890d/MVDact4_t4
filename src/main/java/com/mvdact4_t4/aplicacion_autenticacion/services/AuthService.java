package com.mvdact4_t4.aplicacion_autenticacion.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mvdact4_t4.aplicacion_autenticacion.dto.AuthResponse;
import com.mvdact4_t4.aplicacion_autenticacion.dto.LoginRequest;
import com.mvdact4_t4.aplicacion_autenticacion.dto.RegistroRequest;
import com.mvdact4_t4.aplicacion_autenticacion.entities.Usuario;
import com.mvdact4_t4.aplicacion_autenticacion.repositories.UsuarioRepository;
import com.mvdact4_t4.aplicacion_autenticacion.security.JwtService;

@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthResponse registrar(RegistroRequest request) {
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El correo ya está registrado");
        }
        Usuario usuario = new Usuario(
                request.getNombre(),
                request.getEmail(),
                passwordEncoder.encode(request.getPassword())
        );
        usuarioRepository.save(usuario);
        return new AuthResponse(jwtService.generateToken(usuario));
    }

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail()).orElseThrow();
        return new AuthResponse(jwtService.generateToken(usuario));
    }
}
