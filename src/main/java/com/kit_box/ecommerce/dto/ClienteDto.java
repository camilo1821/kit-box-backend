
package com.kit_box.ecommerce.dto;

import com.kit_box.ecommerce.model.Cliente;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;

public class ClienteDto {

  private Long id;
  private String nombre;
  private String apellido;
  private String correo;
  private String telefono;
  private String rol;
  private String direccion;
  private LocalDateTime fechaRegistro;

  @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
  private String contrasenia;


  public ClienteDto() {
  }

  public ClienteDto(
          Long id,
          String nombre,
          String apellido,
          String correo,
          String telefono,
          String rol,
          String direccion,
          LocalDateTime fechaRegistro
          ) {
    this.id = id;
    this.nombre = nombre;
    this.apellido = apellido;
    this.correo = correo;
    this.telefono = telefono;
    this.rol = rol;
    this.direccion = direccion;
    this.fechaRegistro = fechaRegistro;

  }

// CONVERTIR DTO A ENTITY

  public static ClienteDto fromEntity(Cliente cliente) {
    return new ClienteDto(
            cliente.getId(),
            cliente.getNombre(),
            cliente.getApellido(),
            cliente.getCorreo(),
            cliente.getTelefono(),
            cliente.getRol(),
            cliente.getDireccion(),
            cliente.getFechaRegistro()

    );
  }

  public Cliente toEntity() {

    return new Cliente(
            this.nombre,
            this.apellido,
            this.correo,
            this.telefono,
            this.contrasenia,
            this.rol,
            this.direccion);
  }


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

  public String getContrasenia() {
    return contrasenia;
  }

  public void setContrasenia(String contrasenia) {
    this.contrasenia = contrasenia;
  }
}
