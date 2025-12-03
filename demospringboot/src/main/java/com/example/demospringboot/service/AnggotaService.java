// UAS OBP - Sistem Perpustakaan
// Anggota Service
// author: Anindya Riandani - 825240068

// source:
// TM01 - Dasar Pemrograman Java
// TM02 - Class and Objects
// TM03 - Java Library Classes
// TM04 - Inheritance and Polymorphism
// TM05 - Abstract dan Interface
// TM09 - Java GUI berbasis Website
// TM10 - Database dan Java Web GUI Part I
// TM11 - Database dan Java Web GUI Part II
// TM12 - Database dan Java Web GUI Part III
// TM13 - Polymorphism Association

package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.Interfaces.Login;
import com.example.demospringboot.entity.Anggota;
import com.example.demospringboot.repository.AnggotaRepository;
import java.util.List;

// Service class untuk mengelola data Anggota dan mengimplementasikan interface Login
@Service
public class AnggotaService implements Login {

    // Dependency Injection untuk repository Anggota
    @Autowired
    private AnggotaRepository anggotaRepository;

    // Mengambil seluruh data anggota dari database
    public List<Anggota> getAllAnggota() {
        return anggotaRepository.findAll();
    }

    // Menambahkan data anggota baru ke database
    public Anggota addAnggota(Anggota obj) {
        return anggotaRepository.save(obj);
    }

    // Mengambil data anggota berdasarkan ID
    public Anggota getAnggotaById(Integer id) {
        return anggotaRepository.findById(id).orElse(null);
    }

    // Memperbarui data anggota berdasarkan ID
    public Anggota updateAnggota(Integer id, Anggota obj) {
        obj.setId(id); // Set ID agar update mengarah ke entitas yang benar
        return anggotaRepository.save(obj);
    }

    // Menghapus data anggota berdasarkan ID
    public void deleteAnggota(Integer id) {
        anggotaRepository.deleteById(id);
    }

    // Mengambil data anggota berdasarkan email (misalnya untuk login)
    public Anggota getByEmail(String email) {
        return anggotaRepository.findByEmail(email);
    }

    // Implementasi metode login dari interface Login
    @Override
    public void signIn() {
        // Kosong, dapat diimplementasikan sesuai kebutuhan autentikasi
    }

    @Override
    public void signUp() {
        // Kosong, dapat diimplementasikan sesuai proses registrasi sistem
    }
}
