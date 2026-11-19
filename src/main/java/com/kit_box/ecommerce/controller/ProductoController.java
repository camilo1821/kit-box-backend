package com.kit_box.ecommerce.controller;

import com.kit_box.ecommerce.dto.ProductoDto;
import com.kit_box.ecommerce.model.Producto;
import com.kit_box.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @PostMapping
    public ProductoDto save(@RequestBody ProductoDto productoDto) {
        Producto producto = productoDto.toEntity();
        return ProductoDto.fromEntity(productoService.save(producto));
    }

    @PutMapping("/{id}")
    public ProductoDto update(@PathVariable Long id, @RequestBody ProductoDto productoActualizado) {
        Producto producto = productoActualizado.toEntity();
        return ProductoDto.fromEntity(productoService.update(id,producto));
    }

    @GetMapping
    public List<ProductoDto> findAll(@PageableDefault(size = 12,sort="id") Pageable pageable) {
        return productoService.findAll(pageable)
                .getContent()
                .Stream()
                .map(ProductoDto::fromEntity)
                .collect(Collectors.toList());
    }


    public Optional<ProductoDto> findById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ProductoDto::fromEntity);
    }


    /*public List<Producto> findByName(String nombre) {
        return productoRepository.findByNombreIgnoreCase(nombre);
    } */

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    productoService.delete(id);

    }




}
