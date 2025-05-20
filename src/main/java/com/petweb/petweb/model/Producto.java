package com.petweb.petweb.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String nombre_producto;

    @Column(nullable = false, length = 7)
    private Integer precio_producto;

    @Column(nullable = false, length = 255)
    private String descripcion_producto;

    @ManyToMany
    @JoinTable(name = "categorias", joinColumns = @JoinColumn(name = "producto_id"), inverseJoinColumns = @JoinColumn(name = "categoria_id"))
    private List<Categoria> categorias;

    @JsonManagedReference(value = "producto-existencias")
    @OneToMany(mappedBy = "producto")
    private List<Existencias> existencias;

    @JsonManagedReference(value = "producto-carrito")
    @OneToMany(mappedBy = "producto")
    private List<Carrito> carritos;

}
