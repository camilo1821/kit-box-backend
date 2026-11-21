
package com.kit_box.ecommerce.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 100)
  private String nombre;

  @Column(nullable = true, length = 100)
  private String apellido;

  @Column(nullable = false, unique = true, length = 150)
  private String correo;

  @Column(length = 20)
  private String telefono;

  @Column(nullable = true, length = 255)
  private String contrasenia;

  @Column(nullable = false, length = 50)
  private String rol;

  @Column(length = 255)
  private String direccion;

  @CreationTimestamp
  @Column(name = "fecha_registro", nullable = false, updatable = false)
  private LocalDateTime fechaRegistro;

  @JsonIgnore
  @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
  private List<Pedido> pedidos = new ArrayList<>();

  public Cliente() {
  }

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

  /*public Cliente(String nombre, String apellido, String correo, String telefono, String rol, String direccion) {
    this.nombre = nombre;
    this.apellido = apellido;
    this.correo = correo;
    this.telefono = telefono;
    this.rol = rol;
    this.direccion = direccion;

  }*/

  // GETTERS Y SETTERS


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