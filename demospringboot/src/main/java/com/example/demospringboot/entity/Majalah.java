// UAS OBP - Sistem Perpustakaan
// Majalah Entity
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
@Table(name = "majalah")
public class Majalah extends Item {

    // atribut tambahan dalam entitas majalah
    @NotNull
    private int nomorEdisi;

    @NotNull
    private String penerbit;

    // Constructor 1: inisialisasi variabel majalah (nomor edisi, penerbit, judul,
    // penulis)
    public Majalah(int nomorEdisi, String penerbit, String judul, String penulis) {
        super(judul, penulis);
        this.nomorEdisi = nomorEdisi;
        this.penerbit = penerbit;
    }

    // Constructor 2: constructor kosong untuk JPA
    public Majalah() {
    }

    // Public Setter & Getter
    public void setNomorEdisi(int nomorEdisi) {
        this.nomorEdisi = nomorEdisi;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getNomorEdisi() {
        return nomorEdisi;
    }

    public String getPenerbit() {
        return penerbit;
    }

    // Method untuk menghitung denda keterlambatan pengembalian majalah
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

        // denda 1500/hari keterlambatan pengembalian
        return (int) hariTerlambat * 1500;
    }

}
