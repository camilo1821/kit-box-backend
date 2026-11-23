package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Producto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Interfaz de servicio para la gestión de productos.
 * <p>
 * Define las operaciones de negocio para crear, leer, actualizar y eliminar
 * productos, con soporte de paginación y búsqueda por nombre.
 */
public interface ProductoService {

    /**
     * Guarda un nuevo producto en la base de datos.
     * @param producto producto a guardar
     * @return producto guardado con el ID asignado
     */
    Producto save(Producto producto);

    /**
     * Actualiza los datos de un producto existente.
     * @param id identificador del producto a actualizar
     * @param producto datos actualizados
     * @return producto actualizado
     * @throws RuntimeException si no se encuentra el producto
     */
    Producto update(Long id, Producto producto);

    /**
     * Retorna todos los productos con paginación.
     * @param pageable parámetros de paginación
     * @return página de productos
     */
    Page<Producto> findAll(Pageable pageable);

    /**
     * Busca un producto por su identificador.
     * @param id identificador del producto
     * @return Optional con el producto si existe
     */
    Optional<Producto> findById(Long id);

    /**
     * Busca productos por nombre (insensible a mayúsculas).
     * @param nombre nombre del producto
     * @return lista de productos que coinciden
     */
    List<Producto> findByName(String nombre);

    /**
     * Elimina un producto por su identificador.
     * @param id identificador del producto a eliminar
     * @throws RuntimeException si no se encuentra el producto
     */
    void delete(Long id);
}
