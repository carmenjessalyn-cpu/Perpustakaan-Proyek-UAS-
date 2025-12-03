// UAS OBP - Sistem Perpustakaan
// User Service
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demospringboot.entity.User;
import com.example.demospringboot.repository.UserRepository;

// Service class untuk mengelola data User dalam sistem
@Service
public class UserService {

    // Dependency Injection untuk UserRepository
    @Autowired
    private UserRepository userRepository;

    // Mengambil seluruh data user dari database
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Menambahkan user baru ke database
    public User addUser(User obj) {
        return userRepository.save(obj);
    }

    // Mengambil data user berdasarkan ID
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    // Memperbarui data user berdasarkan ID
    public User updateUser(Integer id, User obj) {
        obj.setId(id); // memastikan update ditujukan ke user dengan ID tertentu
        return userRepository.save(obj);
    }

    // Menghapus user berdasarkan ID
    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }

    // Mengecek apakah user dengan email tertentu sudah ada di database
    public boolean findUserEmail(String email) {
        return userRepository.findByEmail(email) != null;
    }
}
