package com.example.demospringboot.entity;

import jakarta.persistence.Entity;

@Entity
public class Tablet extends Produk {
    private byte tipeStylus;

    public Tablet() {
    }

    public Tablet(byte tipeStylus, String tipe,
            String produsen, int ram) {
        super(tipe, produsen, ram);
        this.tipeStylus = tipeStylus;
    }

    public void setTipeStylus(byte tipeStylus) {
        this.tipeStylus = tipeStylus;
    }

    public byte getTipeStylus() {
        return tipeStylus;
    }
}
