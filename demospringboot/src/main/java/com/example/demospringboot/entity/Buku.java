// UAS OBP - Sistem Perpustakaan
// Buku Entity
// author: Carmen Jessalyn - 825240096

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

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "buku")
public class Buku extends Item {

    // atribut tambahan dalam entitas buku
    @NotNull
    private String isbn, genre;

    // Constructor 1: inisialisasi variabel buku (ISBN, genre, judul, penulis)
    public Buku(String isbn, String genre, String judul, String penulis) {
        super(judul, penulis);
        this.isbn = isbn;
        this.genre = genre;
    }

    // Constructor 2: constructor kosong untuk JPA
    public Buku() {
    }

    // Public Setter & Getter
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getGenre() {
        return genre;
    }

    // Method untuk menghitung denda keterlambatan pengembalian buku
    @Override
    public int hitungDenda(Date tglKembali, Date tglTempo) {

        // Jika salah satu tanggal null maka tidak terkena denda
        if (tglKembali == null || tglTempo == null)
            return 0;

        // Hitung selisih waktu dalam milidetik (tglKembali - tglTempo)
        long selisih = tglKembali.getTime() - tglTempo.getTime();

        // Tidak terlambat = 0
        if (selisih <= 0)
            return 0;

        // Konversi selisih milidetik ke satuan hari
        long hariTerlambat = selisih / (1000 * 60 * 60 * 24);

        // denda 2000/hari keterlambatan pengembalian
        return (int) hariTerlambat * 2000;
    }
}
