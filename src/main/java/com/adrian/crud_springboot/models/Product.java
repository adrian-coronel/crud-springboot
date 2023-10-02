package com.adrian.crud_springboot.models;

import jakarta.persistence.*;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.Period;

// Será una ENTIDAD y una TABLA
@Entity
@Table
public class Product {
    // Los valores de este ID se generaran de forma AUTOMATICA
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private double price;
    private LocalDate fecha;
    /*Antiguedad será un campo calculado, por ende no Se creará en la tabla
    * por ende será un campo Transitorio. */
    @Transient
    private int antiguedad;

    public Product(){}
    public Product(Long id, String name, double price, LocalDate fecha) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.fecha = fecha;
        this.antiguedad = antiguedad;
    }
    public Product(String name, double price, LocalDate fecha) {
        this.name = name;
        this.price = price;
        this.fecha = fecha;
        this.antiguedad = antiguedad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public int getAntiguedad() {
        // Retorna los años desde que fue creado hasta la actualidad
        return Period.between(this.fecha, LocalDate.now()).getYears();
    }

    public void setAntiguedad(int antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", fecha=" + fecha +
                ", antiguedad=" + antiguedad +
                '}';
    }
}
