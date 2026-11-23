package com.kit_box.ecommerce.repository;

import com.kit_box.ecommerce.model.Pedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Repositorio para la entidad {@link Pedido}.
 * <p>
 * Proporciona acceso a la base de datos para operaciones CRUD y consultas
 * personalizadas como búsqueda por estado y por fecha con paginación.
 */
@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    /**
     * Busca pedidos por su estado actual.
     * Utiliza la convención de nombres de Spring Data JPA (findBy + propiedad + palabra clave).
     *
     * @param estado estado del pedido (PENDIENTE, EN_PROCESO, ENVIADO, etc.)
     * @return lista de pedidos con el estado especificado
     */
    List<Pedido> findByEstado(String estado);

    /**
     * Busca pedidos por fecha exacta con soporte de paginación.
     * Usa una consulta JPQL personalizada para filtrar por fecha.
     *
     * @param fecha    fecha y hora del pedido
     * @param pageable parámetros de paginación
     * @return página de pedidos que coinciden con la fecha
     */
    @Query("SELECT p FROM Pedido p WHERE p.fecha_pedido = :fecha")
    Page<Pedido> findByFecha(LocalDateTime fecha, Pageable pageable);

}
