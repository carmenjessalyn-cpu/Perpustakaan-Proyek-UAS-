// UAS OBP - Sistem Perpustakaan
// Mahasiswa Controller 
// author: Andindya Riandani - 825240068

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

package com.example.demospringboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demospringboot.entity.Mahasiswa;
import com.example.demospringboot.repository.MahasiswaRepository;
import com.example.demospringboot.service.MahasiswaService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MahasiswaController {

    @Autowired
    private MahasiswaRepository mahasiswaRepo;

    @Autowired
    private MahasiswaService mahasiswaService;

    @GetMapping({ "/managemahasiswa", "/managemahasiswa/" })
    public String manageMahasiswaPage(Model model, HttpServletRequest request) {

        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        model.addAttribute("listMahasiswa", mahasiswaService.getAllMahasiswa());
        model.addAttribute("mahasiswaInfo", new Mahasiswa());
        model.addAttribute("mahasiswaRec", null);
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        return "managemahasiswa";
    }

    // Tampilkan data untuk edit
    @GetMapping("/mahasiswa/{id}")
    public String getMahasiswaById(@PathVariable("id") int id, Model model, HttpServletRequest req) {

        model.addAttribute("logStaff", req.getSession().getAttribute("logStaff"));
        model.addAttribute("mahasiswaRec", mahasiswaRepo.findById(id).orElse(null));
        model.addAttribute("mahasiswaInfo", new Mahasiswa());
        model.addAttribute("listMahasiswa", mahasiswaRepo.findAll());

        return "managemahasiswa";
    }

    // TAMBAH MAHASISWA BARU (aksi tombol Add)
    @PostMapping(value = { "/mahasiswa/submit/", "/mahasiswa/submit/{id}" }, params = { "add" })
    public String addMahasiswa(@ModelAttribute("mahasiswaInfo") Mahasiswa m) {
        
        // ID akan di-generate otomatis oleh database
        mahasiswaRepo.save(m);
        
        return "redirect:/managemahasiswa";
    }

    // EDIT MAHASISWA (aksi tombol Edit)
    @PostMapping(value = "/mahasiswa/submit/{id}", params = { "edit" })
    public String editMahasiswa(@PathVariable("id") int id, @ModelAttribute("mahasiswaInfo") Mahasiswa m) {
        
        Mahasiswa old = mahasiswaRepo.findById(id).orElse(null);
        
        if (old != null) {
            // Update field yang diubah (kecuali ID)
            old.setNim(m.getNim());
            old.setNama(m.getNama());
            old.setJurusan(m.getJurusan());
            old.setFakultas(m.getFakultas());
            old.setAlamat(m.getAlamat());
            old.setEmail(m.getEmail());
            old.setNoTelp(m.getNoTelp());
            old.setPassword(m.getPassword());
            
            mahasiswaRepo.save(old);
        }
        
        return "redirect:/managemahasiswa";
    }

    // HAPUS MAHASISWA (aksi tombol Delete)
    @PostMapping(value = "/mahasiswa/submit/{id}", params = { "delete" })
    public String deleteMahasiswa(@PathVariable("id") int id) {
        
        mahasiswaRepo.deleteById(id);
        
        return "redirect:/managemahasiswa";
    }
}