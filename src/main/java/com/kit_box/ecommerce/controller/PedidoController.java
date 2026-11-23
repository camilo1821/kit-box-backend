package com.kit_box.ecommerce.controller;

import com.kit_box.ecommerce.dto.PedidoDTO;
import com.kit_box.ecommerce.model.Pedido;
import com.kit_box.ecommerce.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controlador REST para la gestión de pedidos.
 * <p>
 * Expone los endpoints bajo {@code /api/pedidos} para crear, leer, actualizar
 * y eliminar pedidos, con soporte de paginación, búsqueda por estado y por fecha.
 */
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    /**
     * Constructor con inyección de dependencias.
     * @param pedidoService servicio de pedidos
     */
    @Autowired
    public PedidoController(PedidoService pedidoService) {this.pedidoService = pedidoService; }

    /**
     * Retorna todos los pedidos con paginación y ordenamiento.
     * @param page número de página (default: 0)
     * @param size tamaño de página (default: 10)
     * @param sortBy campo de ordenamiento (default: "id")
     * @param sortDirection dirección de orden (default: "ASC")
     * @return página de pedidos
     */
    @GetMapping
    public Page<PedidoDTO> getAllPedidos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "ASC") String sortDirection){
            Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
            Pageable pageable = PageRequest.of(page, size, sort);
            Page<Pedido> pagina = pedidoService.findAll(pageable);
            return pagina.map(PedidoDTO::fromEntity);
    }

    /**
     * Busca un pedido por su identificador.
     * @param id identificador del pedido
     * @return pedido encontrado
     */
    @GetMapping("/{id}")
    public PedidoDTO getPedidoById(@PathVariable Long id) {
        return PedidoDTO.fromEntity(pedidoService.findById(id));
    }

    /**
     * Busca pedidos por su estado actual.
     * @param estado estado del pedido
     * @return lista de pedidos con el estado especificado
     */
    @GetMapping("/estado/{estado}")
    public List<PedidoDTO> getPedidosByEstado(@PathVariable String estado) {
        return pedidoService.findByEstado(estado)
                .stream()
                .map(PedidoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Busca pedidos por fecha con paginación.
     * @param fecha fecha y hora del pedido (formato ISO)
     * @param pageable parámetros de paginación
     * @return página de pedidos que coinciden con la fecha
     */
    @GetMapping("/fecha/{fecha}")
    public Page<PedidoDTO> getPedidosByFecha(@PathVariable @DateTimeFormat
        (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha, @PageableDefault(size=12, sort="id") Pageable pageable) {
        return pedidoService.findByFecha(fecha,pageable)
                .map(PedidoDTO::fromEntity);
    }

    /**
     * Crea un nuevo pedido.
     * @param pedidoDTO datos del pedido a crear
     * @return pedido creado
     */
    @PostMapping
    public PedidoDTO createPedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoDTO.toEntity();
        return  PedidoDTO.fromEntity(pedidoService.save(pedido));
    }

    /**
     * Actualiza un pedido existente.
     * @param id identificador del pedido
     * @param pedidoDTO datos actualizados
     * @return pedido actualizado
     */
    @PutMapping("/{id}")
    public PedidoDTO updatePedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoDTO.toEntity();
        return PedidoDTO.fromEntity(pedidoService.update(id, pedido));
    }

    /**
     * Elimina un pedido por su identificador.
     * @param id identificador del pedido a eliminar
     */
    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {pedidoService.delete(id);}

}
