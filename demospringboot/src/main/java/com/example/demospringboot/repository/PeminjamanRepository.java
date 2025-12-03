// UAS OBP - Sistem Perpustakaan
// Peminjaman Repository
// author: Keysa Abigail - 825240077

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

package com.example.demospringboot.repository;

import com.example.demospringboot.entity.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Integer> {
    Peminjaman findByIdPeminjaman(int idPeminjaman); // mencari peminjaman berdasarkan id peminjaman
}
