// UAS OBP - Sistem Perpustakaan
// Mahasiswa Service
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
import com.example.demospringboot.entity.Mahasiswa;
import com.example.demospringboot.repository.MahasiswaRepository;
import java.util.List;

// Service class untuk mengelola data Mahasiswa serta mengimplementasikan interface Login
@Service
public class MahasiswaService implements Login {

    // Dependency Injection untuk MahasiswaRepository
    @Autowired
    private MahasiswaRepository mahasiswaRepository;

    // Mengambil seluruh data mahasiswa dari database
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepository.findAll();
    }

    // Menambahkan data mahasiswa baru ke database
    public Mahasiswa addMahasiswa(Mahasiswa obj) {
        return mahasiswaRepository.save(obj);
    }

    // Mengambil data mahasiswa berdasarkan ID
    public Mahasiswa getMahasiswaById(Integer id) {
        return mahasiswaRepository.findById(id).orElse(null);
    }

    // Memperbarui data mahasiswa berdasarkan ID
    public Mahasiswa updateMahasiswa(Integer id, Mahasiswa obj) {
        obj.setId(id); // memastikan update mengarah ke entitas yang sesuai
        return mahasiswaRepository.save(obj);
    }

    // Menghapus data mahasiswa berdasarkan ID
    public void deleteMahasiswa(Integer id) {
        mahasiswaRepository.deleteById(id);
    }

    // Mencari data mahasiswa berdasarkan email (biasanya untuk login)
    public Mahasiswa findMahasiswa(String email) {
        return mahasiswaRepository.findByEmail(email);
    }

    // Implementasi method signIn dari interface Login
    @Override
    public void signIn() {
        System.out.println("berhasil login");
    }

    // Implementasi method signUp dari interface Login
    @Override
    public void signUp() {
        System.out.println("berhasil registrasi");
    }
}
