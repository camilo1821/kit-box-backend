package com.kit_box.ecommerce.service;

import com.kit_box.ecommerce.model.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Producto save(Producto producto);

    Producto update(Long id, Producto producto);

    List<Producto> findAll();

    Optional<Producto> findById(Long id);

    List<Producto> findByName(String nombre);

    void delete(Long id);
}
