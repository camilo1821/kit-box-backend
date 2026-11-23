package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Producto;
import com.kit_box.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementación del servicio de productos usando Spring Data JPA.
 * <p>
 * Delega las operaciones al {@link ProductoRepository} y aplica la lógica
 * de negocio para actualizar campos específicos y validar existencia.
 */
@Service
public class ProductoServiceJPA implements ProductoService{
    private final ProductoRepository productoRepository;

    /**
     * Constructor con inyección de dependencias.
     * @param productoRepository repositorio de productos
     */
    @Autowired
    public ProductoServiceJPA(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    /**
     * Actualiza un producto copiando los campos del producto actualizado
     * sobre el producto existente en la base de datos.
     * Esto preserva el ID y cualquier otra relación no especificada.
     */
    @Override
    public Producto update(Long id, Producto productoActualizado) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No se encontró producto con id "+id));
        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setCategoria(productoActualizado.getCategoria());
        productoExistente.setStock(productoActualizado.getStock());
        productoExistente.setImagen(productoActualizado.getImagen());

        return productoRepository.save(productoExistente);
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findByName(String nombre) {
        return productoRepository.findByNombreIgnoreCase(nombre);
    }

    @Override
    public void delete(Long id) {
        if(!productoRepository.existsById(id)){
            throw new RuntimeException("Producto no encontrado con id "+ id);
        }
        productoRepository.deleteById(id);
    }
}
