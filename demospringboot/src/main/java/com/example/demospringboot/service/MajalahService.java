// UAS OBP - Sistem Perpustakaan
// Majalah Service
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

import com.example.demospringboot.entity.Majalah;
import com.example.demospringboot.repository.MajalahRepository;

import java.util.List;

@Service
public class MajalahService {

    // Repository untuk mengakses data majalah pada database
    @Autowired
    private MajalahRepository majalahRepository;

    // Method mengambil seluruh data majalah dari database
    public List<Majalah> getAllMajalah() {
        return majalahRepository.findAll();
    }

    // Method menambahkan majalah baru ke database
    public Majalah addMajalah(Majalah obj) {
        return majalahRepository.save(obj);
    }

    // Method mencari majalah berdasarkan ID
    public Majalah getMajalahById(long id) {
        return majalahRepository.findById(id).orElse(null);
    }

    // Method memperbarui majalah berdasarkan ID
    public Majalah updateMajalah(long id, Majalah obj) {
        obj.setItemId(id); // set itemId agar sesuai dengan majalah yang akan diperbarui
        return majalahRepository.save(obj);
    }

    // Method menghapus majalah berdasarkan ID
    public void deleteMajalah(long id) {
        majalahRepository.deleteById(id);
    }

}
