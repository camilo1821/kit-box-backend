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

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {this.pedidoService = pedidoService; }

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

    @GetMapping("/{id}")
    public PedidoDTO getPedidoById(@PathVariable Long id) {
        return PedidoDTO.fromEntity(pedidoService.findById(id));
    }

    @GetMapping("/estado/{estado}")
    public List<PedidoDTO> getPedidosByEstado(@PathVariable String estado) {
        return pedidoService.findByEstado(estado)
                .stream()
                .map(PedidoDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/fecha/{fecha}")
    public Page<PedidoDTO> getPedidosByFecha(@PathVariable @DateTimeFormat
        (iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fecha, @PageableDefault(size=12, sort="id") Pageable pageable) {
        return pedidoService.findByFecha(fecha,pageable)
                .map(PedidoDTO::fromEntity);
    }

    @PostMapping
    public PedidoDTO createPedido(@RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoDTO.toEntity();
        return  PedidoDTO.fromEntity(pedidoService.save(pedido));
    }

    @PutMapping("/{id}")
    public PedidoDTO updatePedido(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO){
        Pedido pedido = pedidoDTO.toEntity();
        return PedidoDTO.fromEntity(pedidoService.update(id, pedido));
    }

    @DeleteMapping("/{id}")
    public void deletePedido(@PathVariable Long id) {pedidoService.delete(id);}

}
