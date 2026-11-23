package com.kit_box.ecommerce.repository;


import com.kit_box.ecommerce.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
 * Repositorio para la entidad {@link Producto}.
 * <p>
 * Proporciona acceso a la base de datos para operaciones CRUD y búsqueda
 * de productos por nombre (insensible a mayúsculas/minúsculas).
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto,Long> {

    /**
     * Busca productos por nombre, sin distinguir mayúsculas de minúsculas.
     * @param nombre nombre del producto a buscar
     * @return lista de productos que coinciden con el nombre
     */
    List<Producto> findByNombreIgnoreCase(String nombre);
}
