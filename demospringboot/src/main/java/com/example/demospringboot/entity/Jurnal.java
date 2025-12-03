// UAS OBP - Sistem Perpustakaan
// Jurnal Entity
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

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "jurnal")
public class Jurnal extends Item {

    // atribut tambahan dalam entitas jurnal
    @NotNull
    private int volume, edisi;

    // Constructor 1: Inisialisasi variabel
    public Jurnal(int volume, int edisi, String judul, String penulis) {
        super(judul, penulis);
        this.volume = volume;
        this.edisi = edisi;
    }

    // Constructor 2: constructor kosong untuk JPA
    public Jurnal() {
    }

    // Public Setter & Getter
    public void setVolume(int volume) {
        this.volume = volume;
    }

    public void setEdisi(int edisi) {
        this.edisi = edisi;
    }

    public int getVolume() {
        return volume;
    }

    public int getEdisi() {
        return edisi;
    }

    // Method untuk menghitung denda keterlambatan pengembalian jurnal
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

        // denda 3000/hari keterlambatan pengembalian
        return (int) hariTerlambat * 3000;
    }

}
