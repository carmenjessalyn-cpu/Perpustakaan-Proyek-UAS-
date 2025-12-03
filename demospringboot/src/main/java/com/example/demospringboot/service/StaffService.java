// UAS OBP - Sistem Perpustakaan
// Staff Service
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
import com.example.demospringboot.entity.Staff;
import com.example.demospringboot.repository.StaffRepository;
import java.util.List;

// Service class untuk mengelola data Staff dan mengimplementasikan interface Login
@Service
public class StaffService implements Login {

    // Dependency Injection untuk StaffRepository
    @Autowired
    private StaffRepository staffRepository;

    // Mengambil seluruh data staff dari database
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // Menambahkan data staff baru ke database
    public Staff addStaff(Staff obj) {
        return staffRepository.save(obj);
    }

    // Mengambil data staff berdasarkan ID
    public Staff getStaffById(Integer id) {
        return staffRepository.findById(id).orElse(null);
    }

    // Memperbarui data staff berdasarkan ID
    public Staff updateStaff(Integer id, Staff obj) {
        obj.setId(id); // memastikan update ditujukan untuk entitas dengan ID tersebut
        return staffRepository.save(obj);
    }

    // Menghapus data staff berdasarkan ID
    public void deleteStaff(Integer id) {
        staffRepository.deleteById(id);
    }

    // Mencari staff berdasarkan email (digunakan untuk proses login)
    public Staff findStaff(String email) {
        return staffRepository.findByEmail(email);
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
