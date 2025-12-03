// UAS OBP - Sistem Perpustakaan
// Dosen Service
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
import com.example.demospringboot.entity.Dosen;
import com.example.demospringboot.repository.DosenRepository;
import java.util.List;

// Service class untuk mengelola data Dosen dan mengimplementasikan interface Login
@Service
public class DosenService implements Login {

    // Dependency Injection untuk DosenRepository
    @Autowired
    private DosenRepository dosenRepository;

    // Mengambil seluruh data dosen dari database
    public List<Dosen> getAllDosen() {
        return dosenRepository.findAll();
    }

    // Menambahkan data dosen baru ke database
    public Dosen addDosen(Dosen obj) {
        return dosenRepository.save(obj);
    }

    // Mengambil data dosen berdasarkan ID
    public Dosen getDosenById(Integer id) {
        return dosenRepository.findById(id).orElse(null);
    }

    // Memperbarui data dosen berdasarkan ID
    public Dosen updateDosen(Integer id, Dosen obj) {
        obj.setId(id); // memastikan update dilakukan pada entitas yang tepat
        return dosenRepository.save(obj);
    }

    // Menghapus data dosen berdasarkan ID
    public void deleteDosen(Integer id) {
        dosenRepository.deleteById(id);
    }

    // Mencari dosen berdasarkan email (umumnya digunakan untuk proses login)
    public Dosen findDosen(String email) {
        return dosenRepository.findByEmail(email);
    }

    // Implementasi metode login dari interface Login
    @Override
    public void signIn() {
        System.out.println("berhasil login");
    }

    // Implementasi metode registrasi dari interface Login
    @Override
    public void signUp() {
        System.out.println("berhasil registrasi");
    }
}
