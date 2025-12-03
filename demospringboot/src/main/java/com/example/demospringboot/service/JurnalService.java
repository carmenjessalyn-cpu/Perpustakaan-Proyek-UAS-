// UAS OBP - Sistem Perpustakaan
// Jurnal Service
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

import com.example.demospringboot.entity.Jurnal;
import com.example.demospringboot.repository.JurnalRepository;
import java.util.List;

@Service
public class JurnalService {
    // Repository untuk mengakses data jurnal pada database
    @Autowired
    private JurnalRepository jurnalRepository;

    // Method mengambil seluruh data jurnal dari database
    public List<Jurnal> getAllJurnal() {
        return jurnalRepository.findAll();
    }

    // Method menambahkan jurnal baru ke database
    public Jurnal addJurnal(Jurnal obj) {
        return jurnalRepository.save(obj);
    }

    // Method mencari jurnal berdasarkan ID
    public Jurnal getJurnalById(long produkid) {
        return jurnalRepository.findById(produkid).orElse(null);
    }

    // Method memperbarui jurnal berdasarkan ID
    public Jurnal updateJurnal(long produkid, Jurnal obj) {
        obj.setItemId(produkid); // set itemId agar sesuai dengan jurnal yang ingin diperbarui
        return jurnalRepository.save(obj);
    }

    // Method menghapus jurnal berdasarkan ID
    public void deleteJurnal(long produkid) {
        jurnalRepository.deleteById(produkid);
    }

}
