package com.kit_box.ecommerce.controller;

import com.kit_box.ecommerce.dto.DetallePedidoDTO;
import com.kit_box.ecommerce.service.DetallePedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles-pedido")
@CrossOrigin(origins = "*")
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    // Inyectamos la interfaz; Spring inyectará DetallePedidosServiceJPA automáticamente
    public DetallePedidoController(DetallePedidoService detallePedidoService) {
        this.detallePedidoService = detallePedidoService;
    }

    @GetMapping
    public ResponseEntity<List<DetallePedidoDTO>> listarTodos() {
        return ResponseEntity.ok(detallePedidoService.listarTodos());
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<List<DetallePedidoDTO>> listarPorPedido(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(detallePedidoService.listarPorPedido(pedidoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(detallePedidoService.obtenerPorId(id));
    }

    @PostMapping("/pedido/{pedidoId}")
    public ResponseEntity<DetallePedidoDTO> crearDetalle(
            @PathVariable Long pedidoId,
            @RequestBody DetallePedidoDTO dto) {
        DetallePedidoDTO creado = detallePedidoService.crearDetalle(pedidoId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> actualizarCantidad(
            @PathVariable Long id,
            @RequestBody DetallePedidoDTO dto) {
        return ResponseEntity.ok(detallePedidoService.actualizarCantidad(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        detallePedidoService.eliminarDetalle(id);
        return ResponseEntity.noContent().build();
    }

}
