package com.example.demospringboot.entity;

import jakarta.persistence.Entity;

@Entity
public class Laptop extends Produk {
    private int storage;

    public Laptop() {
    }

    public Laptop(int storage, String tipe,
            String produsen, int ram) {
        super(tipe, produsen, ram);
        this.storage = storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getStorage() {
        return storage;
    }
}
