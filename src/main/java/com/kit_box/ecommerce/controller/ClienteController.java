
package com.kit_box.ecommerce.controller;

import com.kit_box.ecommerce.dto.ClienteDto;
import com.kit_box.ecommerce.model.Cliente;
import com.kit_box.ecommerce.service.ClienteService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kit_box.ecommerce.dto.ClienteDto.fromEntity;

/**
 * Controlador REST para la gestión de clientes.
 * <p>
 * Expone los endpoints bajo {@code /api/cliente} para crear, leer, actualizar
 * y eliminar clientes, así como buscarlos por ID o correo electrónico.
 */
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

  private final ClienteService clienteService;

  /**
   * Constructor con inyección de dependencias.
   * @param clienteService servicio de clientes
   */
  public ClienteController(ClienteService clienteService) {
    this.clienteService = clienteService;
  }

  /**
   * Registra un nuevo cliente.
   * @param clienteDto datos del cliente a registrar
   * @return cliente creado con estado HTTP 201
   */
  @PostMapping
  public ResponseEntity<ClienteDto> save(@RequestBody ClienteDto clienteDto) {
    Cliente cliente = clienteDto.toEntity();
    Cliente clienteGuardado = clienteService.save(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(fromEntity(clienteGuardado));
  }

  /**
   * Actualiza los datos de un cliente existente.
   * @param id identificador del cliente
   * @param clienteDto datos actualizados
   * @return cliente actualizado o 404 si no se encontró
   */
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

  /**
   * Retorna todos los clientes registrados.
   * @return lista de clientes
   */
  @GetMapping
  public ResponseEntity<List<ClienteDto>> findAll() {

    List<ClienteDto> clientes = clienteService.findAll()
        .stream()
        .map(ClienteDto::fromEntity)
        .toList();

    return ResponseEntity.ok(clientes);
  }

  /**
   * Busca un cliente por su identificador.
   * @param id identificador del cliente
   * @return cliente encontrado o 404
   */
  @GetMapping("/{id}")
  public ResponseEntity<ClienteDto> findById(@PathVariable Long id) {

    return clienteService.findById(id)
        .map(cliente -> ResponseEntity.ok(fromEntity(cliente)))
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Busca un cliente por su correo electrónico.
   * @param correo correo electrónico del cliente
   * @return cliente encontrado o 404
   */
  @GetMapping("/correo/{correo}")
  public ResponseEntity<ClienteDto> findByEmail(
      @PathVariable String correo) {

    return clienteService.findByEmail(correo)
        .map(cliente -> ResponseEntity.ok(fromEntity(cliente)))
        .orElse(ResponseEntity.notFound().build());
  }

  /**
   * Elimina un cliente por su identificador.
   * @param id identificador del cliente
   * @return 204 No Content si se eliminó, 404 si no se encontró
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {

    if (clienteService.findById(id).isEmpty()) {
      return ResponseEntity.notFound().build();
    }

    clienteService.delete(id);

    return ResponseEntity.noContent().build();
  }


}
