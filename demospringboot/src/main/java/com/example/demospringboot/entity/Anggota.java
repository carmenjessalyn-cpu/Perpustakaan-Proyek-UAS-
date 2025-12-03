// UAS OBP - Sistem Perpustakaan
// Anggota Entity
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
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorValue("Anggota")
public class Anggota extends User {

    // Setiap anggota (mahasiswa atau dosen) wajib punya data fakultas
    @NotNull
    private String fakultas;

    // Bagian ini berisi constructor seperti mengisi nama, alamat, email, dan
    // fakultas
    public Anggota(String fakultas, int id, String noTelp, String nama, String alamat, String email, String password) {
        super(id, noTelp, nama, alamat, email, password);
        this.fakultas = fakultas;
    }

    public Anggota() {
    }

    // Mengubah data fakultas milik anggoa
    public void setFakultas(String fakultas) {
        this.fakultas = fakultas;
    }

    // Mengambil data fakultas untuk ditampilkan atau dipakai proses lain
    public String getFakultas() {
        return fakultas;
    }
}
