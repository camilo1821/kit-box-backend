package com.kit_box.ecommerce.repository;


import com.kit_box.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {
    List<Producto> findByNombreIgnoreCase(String nombre);
}
