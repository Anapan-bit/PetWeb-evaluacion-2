package com.petweb.petweb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cliente")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 25, nullable = false)
    private String nombre_cliente;

    @Column(length = 25, nullable = false)
    private String apellido_cliente;

    @Column(unique = true, length = 10, nullable = false)
    private String rut_cliente;

    @Column(length = 50, nullable = false)
    private String direccion_cliente;

    @Column(unique = true, length = 30, nullable = false)
    private String correo_cliente;

    @Column(length = 30, nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String contrasena_cliente;

    @Column(length = 15, nullable = false)
    private Integer telefono_cliente;

    @JsonManagedReference(value = "boleta-cliente")
    @OneToMany(mappedBy = "cliente")
    private List<Boleta> boletas;
}
