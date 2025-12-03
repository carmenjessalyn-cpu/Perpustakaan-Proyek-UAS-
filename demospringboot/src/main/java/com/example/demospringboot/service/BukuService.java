// UAS OBP - Sistem Perpustakaan
// Buku Service
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

import com.example.demospringboot.entity.Buku;
import com.example.demospringboot.repository.BukuRepository;

import java.util.List;

@Service
public class BukuService {

    // Repository untuk mengakses tabel buku pada database
    @Autowired
    private BukuRepository bukuRepository;

    // Method mengambil seluruh data buku dari database
    public List<Buku> getAllBuku() {
        return bukuRepository.findAll();
    }

    // Method menambahkan data buku baru ke database
    public Buku addBuku(Buku obj) {
        return bukuRepository.save(obj);
    }

    // Method mencari buku berdasarkan ID
    public Buku getBukuById(long id) {
        return bukuRepository.findById(id).orElse(null);
    }

    // Method memperbarui data buku berdasarkan ID
    public Buku updateBuku(long id, Buku obj) {
        obj.setItemId(id); // set ID agar sesuai dengan data yang diubah
        return bukuRepository.save(obj);
    }

    // Method menghapus buku berdasarkan ID
    public void deleteBuku(long id) {
        bukuRepository.deleteById(id);
    }
}
