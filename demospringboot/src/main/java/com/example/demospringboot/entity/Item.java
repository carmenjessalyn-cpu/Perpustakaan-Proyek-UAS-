// UAS OBP - Sistem Perpustakaan
// Item Entity
// author: Carmen Jessalyn Tjong Malik - 825240096

// source:
// TM01 - Dasar Pemrograman Java
// TM02 - Class and Objects
// TM03 - Java Library Classes
// TM04 - Inheritance and Polymorphism
// TM05 - Abstract and Interface
// TM09 - Java GUI berbasis Website
// TM10 - Database dan Java Web GUI Part I
// TM11 - Database dan Java Web GUI Part II
// TM12 - Database dan Java Web GUI Part III
// TM13 - Polymorphism Association

package com.example.demospringboot.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {

    // atribut ID unik untuk setiap item (dibuat otomatis oleh database)
    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long itemId;

    // atribut wajib: setiap item harus memiliki judul dan penulis
    @NotNull
    @Size(min = 3, max = 100)
    private String judul, penulis;

    // Constructor 1: inisialisasi judul dan penulis item
    public Item(String judul, String penulis) {
        this.judul = judul;
        this.penulis = penulis;
    }

    // Constructor 2: constructor kosong untuk JPA
    public Item() {
    }

    // Public Setter & Getter
    public void setJudul(String judul) {
        this.judul = judul;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    // Method abstract untuk hitung denda yang akan di implementasikan oleh subclass
    public abstract int hitungDenda(Date tglKembali, Date tglTempo);
}
