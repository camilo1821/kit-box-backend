package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Producto;
import com.kit_box.ecommerce.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceJPA implements ProductoService{
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoServiceJPA(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

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
    public List<Producto> findAll() {
        return productoRepository.findAll();
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
        }productoRepository.deleteById(id);

    }
}
