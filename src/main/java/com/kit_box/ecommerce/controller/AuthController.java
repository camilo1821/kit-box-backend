package com.kit_box.ecommerce.controller;


import com.kit_box.ecommerce.dto.AuthRequestDTO;
import com.kit_box.ecommerce.dto.AuthResponseDTO;
import com.kit_box.ecommerce.dto.ClienteDto;
import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.model.Rol;
import com.kit_box.ecommerce.repository.ClienteRepository;
import com.kit_box.ecommerce.security.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtUtil jwtUtil;


    public AuthController(ClienteRepository clienteRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserDetailsService userDetailsService, JwtUtil jwtUtil) {
        this.clienteRepository = clienteRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/register")
    public String register(@RequestBody ClienteDto  clienteDto) {
        Cliente cliente = clienteDto.toEntity();
        //cliente.setCorreo(requestDTO.getUsername());
        cliente.setContrasenia(passwordEncoder.encode(clienteDto.getContrasenia()));
        cliente.setRol(Rol.USER);
        clienteRepository.save(cliente);
        return "Usuario Registrado";

    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody AuthRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtUtil.generateToken(userDetails);
        return new AuthResponseDTO(token);
    }
}
