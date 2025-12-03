// UAS OBP - Sistem Perpustakaan
// Dosen Entity
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
@DiscriminatorValue("dosen")
public class Dosen extends Anggota {

    // Data khusus untuk dosen: NIDN (nomor induk dosen nasional)
    // Haeus ada (NotNull) dan harus berupa angka maksimal 9 digit
    @NotNull
    @Digits(integer = 9, fraction = 0)
    private int nidn;

    // Constructor lengkap untuk membuat objek dosen dari data input
    // Mengisi: NIDN, fakultas, dan data user (nama, alamat, email, dll)
    public Dosen(int nidn, String fakultas, int id, String noTelp, String nama, String alamat, String email,
            String password) {

        // Memanggil constructor milik class anggota (class induknya)
        super(fakultas, id, noTelp, nama, alamat, email, password);
        // Menyimpan nilai NIDN ke atribut
        this.nidn = nidn;
    }

    // Constructor kosong untuk kebutuhan JPA membuat objek otomatis
    public Dosen() {
    }

    // Mengubah nilai NIDN dose
    public void setNidn(int nidn) {
        this.nidn = nidn;
    }

    // Mengambil nilai NIDN untuk ditampilkan atau dipakai proses lain
    public int getNidn() {
        return nidn;
    }
}
