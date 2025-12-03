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

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotNull;

@Entity
@DiscriminatorValue("staff")
// Class turunan dari User, yang khusus mewakili data staff
public class Staff extends User {

    // Atribut tambahan yang hanya dimiliki staff
    @NotNull
    private String jabatan, shift;

    // Constructor 1: dipakai saat ingin membuat objek staff baru langsung dengan
    // semua datanya
    public Staff(String jabatan, String shift, int id, String noTelp, String nama, String alamat, String email,
            String password) {
        // Mengakses atribut dari parent class
        super(id, noTelp, nama, alamat, email, password);

        // Lalu mengisi atribut khusus staff
        this.jabatan = jabatan;
        this.shift = shift;
    }

    // Constructor 2: dibutuhkan oleh JPA untuk membuat objek dari database
    public Staff() {
    }

    // Public setter & getter untuk staff
    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public String getJabatan() {
        return jabatan;
    }

    public String getShift() {
        return shift;
    }
}
