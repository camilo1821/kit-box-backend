
package com.kit_box.ecommerce.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un cliente del sistema de e-commerce.
 * <p>
 * Cada cliente puede realizar múltiples pedidos, los cuales se gestionan
 * a través de la relación OneToMany con la entidad {@link Pedido}.
 * <p>
 * Mapeada a la tabla {@code cliente} en la base de datos MySQL.
 */
@Entity
@Table(name = "cliente")
public class Cliente {

  /** Identificador único del cliente, generado automáticamente. */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /** Nombre del cliente (obligatorio, máximo 100 caracteres). */
  @Column(nullable = false, length = 100)
  private String nombre;

  /** Apellido del cliente (opcional, máximo 100 caracteres). */
  @Column(nullable = true, length = 100)
  private String apellido;

  /**
   * Correo electrónico del cliente (obligatorio y único, máximo 150 caracteres).
   * Se utiliza como identificador único para el login.
   */
  @Column(nullable = false, unique = true, length = 150)
  private String correo;

  /** Número de teléfono del cliente (opcional, máximo 20 caracteres). */
  @Column(length = 20)
  private String telefono;

  /** Contraseña del cliente para autenticación (opcional, máximo 255 caracteres). */
  @Column(nullable = true, length = 255)
  private String contrasenia;

  /** Rol del cliente en el sistema (ej: "USER", "ADMIN"), máximo 50 caracteres. */
  @Column(nullable = false, length = 50)
  private String rol;

  /** Dirección de entrega del cliente (opcional, máximo 255 caracteres). */
  @Column(length = 255)
  private String direccion;

  /** Fecha y hora de registro del cliente, establecida automáticamente al crearlo. */
  @CreationTimestamp
  @Column(name = "fecha_registro", nullable = false, updatable = false)
  private LocalDateTime fechaRegistro;

  /**
   * Lista de pedidos realizados por este cliente.
   * Se ignora en la serialización JSON para evitar referencias circulares.
   */
  @JsonIgnore
  @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
  private List<Pedido> pedidos = new ArrayList<>();

  /** Constructor por defecto requerido por JPA. */
  public Cliente() {
  }

  /**
   * Constructor con todos los campos excepto id y fechaRegistro.
   *
   * @param nombre     nombre del cliente
   * @param apellido   apellido del cliente
   * @param correo     correo electrónico único
   * @param telefono   número de teléfono
   * @param contrasenia contraseña para autenticación
   * @param rol        rol en el sistema (USER, ADMIN)
   * @param direccion  dirección de entrega
   */
  public Cliente(String nombre, String apellido, String correo,
                 String telefono, String contrasenia, String rol,
                 String direccion) {

    this.nombre = nombre;
    this.apellido = apellido;
    this.correo = correo;
    this.telefono = telefono;
    this.contrasenia = contrasenia;
    this.rol = rol;
    this.direccion = direccion;
  }

  // ------------------------------------------------------------------
  // GETTERS Y SETTERS
  // ------------------------------------------------------------------


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }

  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }

  public String getRol() {
    return rol;
  }

  public void setRol(String rol) {
    this.rol = rol;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public LocalDateTime getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(LocalDateTime fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public List<Pedido> getPedidos() {
    return pedidos;
  }

  public void setPedidos(List<Pedido> pedidos) {
    this.pedidos = pedidos;
  }
}