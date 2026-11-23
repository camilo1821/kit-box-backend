package com.kit_box.ecommerce.controller;

import com.kit_box.ecommerce.dto.ProductoDto;
import com.kit_box.ecommerce.model.Producto;
import com.kit_box.ecommerce.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controlador REST para la gestión de productos.
 * <p>
 * Expone los endpoints bajo {@code /api/productos} para crear, leer, actualizar
 * y eliminar productos, con soporte de paginación y búsqueda por nombre.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoController {
    private final ProductoService productoService;

    /**
     * Constructor con inyección de dependencias.
     * @param productoService servicio de productos
     */
    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    /**
     * Crea un nuevo producto.
     * @param productoDto datos del producto a crear
     * @return producto creado
     */
    @PostMapping
    public ProductoDto save(@RequestBody ProductoDto productoDto) {
        Producto producto = productoDto.toEntity();
        return ProductoDto.fromEntity(productoService.save(producto));
    }

    /**
     * Actualiza un producto existente.
     * @param id identificador del producto
     * @param productoActualizado datos actualizados
     * @return producto actualizado
     */
    @PutMapping("/{id}")
    public ProductoDto update(@PathVariable Long id, @RequestBody ProductoDto productoActualizado) {
        Producto producto = productoActualizado.toEntity();
        return ProductoDto.fromEntity(productoService.update(id,producto));
    }

    /**
     * Retorna todos los productos con paginación (12 por página, ordenado por ID).
     * @param pageable parámetros de paginación
     * @return página de productos
     */
    @GetMapping
    public Page<ProductoDto> findAll(@PageableDefault(size = 12,sort="id") Pageable pageable) {
        return productoService.findAll(pageable)
                .map(ProductoDto::fromEntity);
    }

    /**
     * Busca un producto por su identificador.
     * @param id identificador del producto
     * @return Optional con el producto si existe
     */
    @GetMapping("/{id}")
    public Optional<ProductoDto> findById(@PathVariable Long id) {
        return productoService.findById(id)
                .map(ProductoDto::fromEntity);
    }

    /**
     * Busca productos por nombre (insensible a mayúsculas).
     * @param nombre nombre del producto
     * @return lista de productos que coinciden
     */
    @GetMapping("/buscar/{nombre}")
    public List<ProductoDto> findByName(@PathVariable String nombre) {
        return productoService.findByName(nombre)
                .stream()
                .map(ProductoDto::fromEntity)
                .collect(Collectors.toList());
    }

    /**
     * Elimina un producto por su identificador.
     * @param id identificador del producto a eliminar
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
    productoService.delete(id);

    }




}
