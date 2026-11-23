package com.kit_box.ecommerce.controller;

import com.kit_box.ecommerce.dto.DetallePedidoDTO;
import com.kit_box.ecommerce.service.DetallePedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador REST para la gestión de detalles de pedido.
 * <p>
 * Expone los endpoints bajo {@code /api/detalles-pedido} para crear, leer,
 * actualizar y eliminar líneas de detalle dentro de un pedido.
 * Cada operación de modificación recalcula automáticamente el total del pedido.
 */
@RestController
@RequestMapping("/api/detalles-pedido")
@CrossOrigin(origins = "*")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    /**
     * Constructor con inyección de dependencias.
     * Spring inyectará automáticamente la implementación DetallePedidoServiceJPA.
     * @param detallePedidoService servicio de detalles de pedido
     */
    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    /**
     * Retorna todos los detalles de pedido del sistema.
     * @return lista de todos los detalles
     */
    @GetMapping
    public ResponseEntity<List<DetallePedidoDTO>> listarTodos() {
        return ResponseEntity.ok(detallePedidoService.listarTodos());
    }

    /**
     * Retorna los detalles de un pedido específico.
     * @param pedidoId identificador del pedido
     * @return lista de detalles del pedido
     */
    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DetallePedidoDTO>> listarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(detallePedidoService.listarPorPedido(pedidoId));
    }

    /**
     * Obtiene un detalle de pedido por su identificador.
     * @param id identificador del detalle
     * @return detalle encontrado
     */
    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(detallePedidoService.obtenerPorId(id));
    }

    /**
     * Crea un nuevo detalle para un pedido.
     * Valida stock y recalcula el total del pedido.
     * @param pedidoId identificador del pedido
     * @param dto datos del detalle a crear
     * @return detalle creado con estado HTTP 201
     */
    @PostMapping("/pedido/{pedidoId}")
    public ResponseEntity<DetallePedidoDTO> crearDetalle(
            @PathVariable Long pedidoId,
            @RequestBody DetallePedidoDTO dto) {
        DetallePedidoDTO creado = detallePedidoService.crearDetalle(pedidoId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /**
     * Actualiza la cantidad de un detalle existente.
     * Valida stock y recalcula el total del pedido.
     * @param id identificador del detalle
     * @param dto datos actualizados con la nueva cantidad
     * @return detalle actualizado
     */
    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> actualizarCantidad(
            @PathVariable Long id,
            @RequestBody DetallePedidoDTO dto) {
        return ResponseEntity.ok(detallePedidoService.actualizarCantidad(id, dto));
    }

    /**
     * Elimina un detalle de pedido y recalcula el total del pedido.
     * @param id identificador del detalle a eliminar
     * @return 204 No Content
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        detallePedidoService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }

}
