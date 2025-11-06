package com.example.demospringboot.entity;

import jakarta.persistence.Entity;

@Entity
public class HandPhone extends Produk {
    private String gen;

    public HandPhone() {
    }

    public HandPhone(String gen, String tipe,
            String produsen, int ram) {
        super(tipe, produsen, ram);
        this.gen = gen;
    }

    public void setGen(String gen) {
        this.gen = gen;
    }

    public String getGen() {
        return gen;
    }
}
