// UAS OBP - Sistem Perpustakaan
// Mahasiswa Entity
// author: Anindya Riandani - 825240068

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

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("mahasiswa")
// Bagian penanda kalau objek ini termasuk jenis "mahasiswa"
// dalam pewarisan tabel (single-table inheritance)
public class Mahasiswa extends Anggota {

    // atribut tambahan spesifik class mahasiswa

    @NotNull
    @Digits(integer = 9, fraction = 0)
    // Wajib diisi, maksimal 9 digit angka
    private int nim;

    @NotNull
    // Menyimpan nama dan jurusan
    private String jurusan;

    // Constructor lengkap → dipakai saat ingin membuat mahasiswa baru
    public Mahasiswa(int nim, String jurusan, String fakultas, int id, String noTelp, String nama, String alamat,
            String email, String password) {

        // Memanggil contructor parent class anggota
        super(fakultas, id, noTelp, nama, alamat, email, password);

        // Isi atribut bagian mahasiswa
        this.nim = nim;
        this.jurusan = jurusan;
    }

    // Contructor kosong → dipakai JPA saat bikin objek dari database
    public Mahasiswa() {
    }

    // Setter untuk mengubah jurusan
    public void setNim(int nim) {
        this.nim = nim;
    }

    // Setter untuk mengubah jurusan
    public void setJurusan(String jurusan) {
        this.jurusan = jurusan;
    }

    // Getter untuk mengambil nilai nim
    public int getNim() {
        return nim;
    }

    // Getter untuk mengambil nilai jurusan
    public String getJurusan() {
        return jurusan;
    }

}
