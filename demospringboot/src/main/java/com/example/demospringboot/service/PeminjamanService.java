// UAS OBP - Sistem Perpustakaan
// Peminjaman Service
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

package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.Peminjaman;
import com.example.demospringboot.repository.PeminjamanRepository;

import java.util.List;

@Service
public class PeminjamanService {
    // Repository untuk mengakses data peminjaman pada database
    @Autowired
    private PeminjamanRepository peminjamanRepository;

    // Method mengambil seluruh data peminjaman dari database
    public List<Peminjaman> getAllPeminjaman() {
        return peminjamanRepository.findAll();
    }

    // Method menambahkan data peminjaman baru ke database
    public Peminjaman addPeminjaman(Peminjaman obj) {
        return peminjamanRepository.save(obj);
    }

    // Method mencari peminjaman berdasarkan ID
    public Peminjaman getPeminjamanById(Integer id) {
        return peminjamanRepository.findById(id).orElse(null);
    }

    // Method memperbarui data peminjaman berdasarkan ID
    public Peminjaman updatePeminjaman(Integer id, Peminjaman obj) {
        obj.setIdPeminjaman(id); // set ID agar sesuai dengan peminjaman yang ingin diperbarui
        return peminjamanRepository.save(obj);
    }

    // Method menghapus data peminjaman berdasarkan ID
    public void deletePeminjaman(Integer id) {
        peminjamanRepository.deleteById(id);
    }

    // Method menghitung total denda dari seluruh peminjaman
    public int hitungTotalDenda() {
        List<Peminjaman> listPeminjaman = peminjamanRepository.findAll();
        int totalDenda = 0;

        // akumulasi denda dari setiap peminjaman
        for (Peminjaman p : listPeminjaman) {
            totalDenda += p.hitungDenda();
        }

        return totalDenda;
    }

}