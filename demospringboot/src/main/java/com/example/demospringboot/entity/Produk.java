package com.example.demospringboot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Produk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 5, max = 20)
    private String tipe;

    @NotNull
    @Size(min = 3, max = 50)
    private String produsen;

    @NotNull
    private int ram;

    public Produk() {
    }

    public Produk(String tipe, String produsen, int ram) {
        this.tipe = tipe;
        this.produsen = produsen;
        this.ram = ram;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public String getProdusen() {
        return produsen;
    }

    public void setProdusen(String produsen) {
        this.produsen = produsen;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

}
