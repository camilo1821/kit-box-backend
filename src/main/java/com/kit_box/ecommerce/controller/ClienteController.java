
package com.kit_box.ecommerce.controller;

import com.kit_box.ecommerce.dto.ClienteDto;
import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kit_box.ecommerce.dto.ClienteDto.fromEntity;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

  private final ClienteService clienteService;

  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  // REGISTRAR CLIENTE
  /*@PostMapping
  public ResponseEntity<Cliente> save( Cliente cliente) {

   return ResponseEntity.ok().body(clienteService.save(cliente));
  }*/

  @PostMapping
  public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto clienteDto) {
    Cliente cliente = clienteDto.toEntity();
    Cliente clienteGuardado = clienteService.save(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(fromEntity(clienteGuardado));
  }

  // ACTUALIZAR CLIENTE
  @PutMapping("/{id}")
  public ResponseEntity<ClienteDto> update(
      @PathVariable Long id,
      @RequestBody ClienteDto clienteDto) {

    Cliente cliente = clienteDto.toEntity();

    Cliente clienteActualizado = clienteService.update(id, cliente);

    if (clienteActualizado == null) {
      return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(fromEntity(clienteActualizado));
  }

  // CONSULTAR TODOS LOS CLIENTES
  @GetMapping
  public ResponseEntity<List<ClienteDto>> findAll() {

    List<ClienteDto> clientes = clienteService.findAll()
        .stream()
        .map(ClienteDto::fromEntity)
        .toList();

    return ResponseEntity.ok(clientes);
  }

  // BUSCAR CLIENTE POR ID
  @GetMapping("/{id}")
  public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {

    return clienteService.findById(id)
        .map(cliente -> ResponseEntity.ok(fromEntity(cliente)))
        .orElse(ResponseEntity.notFound().build());
  }

  // BUSCAR CLIENTE POR CORREO
  @GetMapping("/correo/{correo}")
  public ResponseEntity<ClienteDto> findByEmail(
      @PathVariable String correo) {

    return clienteService.findByEmail(correo)
        .map(cliente -> ResponseEntity.ok(fromEntity(cliente)))
        .orElse(ResponseEntity.notFound().build());
  }

  // ELIMINAR CLIENTE
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    if (clienteService.findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    clienteService.delete(id);

    return ResponseEntity.noContent().build();
  }


}
