package com.tecsup.demo.domain.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "curso")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "intCurCodigo")
    private int id;
    @Column(name = "vchCurNombre")
    @NotEmpty
    private String nombre;
    @Column(name = "intCurCreditos")
    @Max(5)
    @Min(0)
    private int creditos;

    public Curso() {
    }

    public Curso(int id, String nombre, int creditos) {
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public void setCreditos(int creditos) {
        this.creditos = creditos;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", creditos=" + creditos +
                '}';
    }
}
