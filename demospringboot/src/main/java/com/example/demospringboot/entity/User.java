// UAS OBP - Sistem Perpustakaan
// Peminjaman Entity
// author: Anindya RIandani - 825240068

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

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
// Class user adalah induk dari semua jenis user (Mahasiswa, Dosesn, Staff)
// Strategy JOINED → tiap subclass punya tabel sendiri, tetapi masih terhubung
// lewat tabel user
// Discriminator dipakai untuk menandai jenis user di database
// (mahasiswa/dosen/staff)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
public class User {

    // ID utama user, dibuat otomatis oleh database
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    // Nama user (boleh diakses subclass karena protected)
    @NotNull
    @Size(min = 3, max = 50)
    protected String nama;

    // Alamat user
    @NotNull
    @Size(min = 3, max = 50)
    private String alamat;

    // Email harus valid dan tidak boleh kosong
    @Email
    @NotNull
    @Size(min = 10, max = 50)
    private String email;

    // Password minimal 8 karakter
    @NotNull
    @Size(min = 8)
    private String password;

    // Nomor telepon harus 11-12 digit
    @NotNull
    @Size(min = 11, max = 12)
    private String noTelp;

    // Constructor kosong → dibutuhkan oleh JPA
    public User() {
    }

    // Constructor lengkap → untuk emmbuat object User sekaligus mengisi datanya
    public User(int id, String noTelp, String nama, String alamat, String email, String password) {
        this.id = id;
        this.noTelp = noTelp;
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
        this.password = password;
    }

    // Setter: mengubah nilai atribut
    public void setId(int id) {
        this.id = id;
    }

    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter: mengambil data dari atribut
    public int getId() {
        return id;
    }

    public String getNoTelp() {
        return noTelp;
    }

    public String getNama() {
        return nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

}
