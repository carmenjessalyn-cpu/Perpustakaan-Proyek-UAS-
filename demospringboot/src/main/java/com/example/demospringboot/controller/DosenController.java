// UAS OBP - Sistem Perpustakaan
// Dosen Controller
// author: Anindya Riandani - 825240068

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

import com.example.demospringboot.entity.Dosen;
import com.example.demospringboot.repository.DosenRepository;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class DosenController {

    @Autowired
    private DosenRepository dosenRepo;

    // HALAMAN UTAMA MANAGE DOSEN
    @GetMapping({ "/managedosen", "/managedosen/" })
    public String manageDosenPage(Model model, HttpServletRequest request) {

        // Cek login staff
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Data tabel
        model.addAttribute("listDosen", dosenRepo.findAll());

        // Form tambah
        model.addAttribute("dosenInfo", new Dosen());

        // Default form edit kosong
        model.addAttribute("dosenRec", null);

        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        return "managedosen";
    }

    // LOAD DOSEN UNTUK EDIT
    @GetMapping("/dosen/{id}")
    public String ambilDosen(@PathVariable("id") int id, Model model, HttpServletRequest request) {

        Dosen dosen = dosenRepo.findById(id).orElse(null);

        model.addAttribute("listDosen", dosenRepo.findAll());
        model.addAttribute("dosenInfo", new Dosen());
        model.addAttribute("dosenRec", dosen);
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        return "managedosen";
    }

    // TAMBAH DOSEN BARU (aksi tombol Add)
    @PostMapping(value = { "/managedosen/submit/", "/managedosen/submit/{id}" }, params = { "add" })
    public String addDosen(@ModelAttribute("dosenInfo") Dosen dosen) {
        
        // ID akan di-generate otomatis oleh database
        dosenRepo.save(dosen);
        
        return "redirect:/managedosen";
    }

    // EDIT DOSEN (aksi tombol Edit)
    @PostMapping(value = "/managedosen/submit/{id}", params = { "edit" })
    public String editDosen(@PathVariable("id") int id, @ModelAttribute("dosenInfo") Dosen dosen) {
        
        Dosen old = dosenRepo.findById(id).orElse(null);
        
        if (old != null) {
            // Update field yang diubah (kecuali ID)
            old.setNidn(dosen.getNidn());
            old.setNama(dosen.getNama());
            old.setFakultas(dosen.getFakultas());
            old.setAlamat(dosen.getAlamat());
            old.setEmail(dosen.getEmail());
            old.setNoTelp(dosen.getNoTelp());
            old.setPassword(dosen.getPassword());
            
            dosenRepo.save(old);
        }
        
        return "redirect:/managedosen";
    }

    // HAPUS DOSEN (aksi tombol Delete)
    @PostMapping(value = "/managedosen/submit/{id}", params = { "delete" })
    public String deleteDosen(@PathVariable("id") int id) {
        
        dosenRepo.deleteById(id);
        
        return "redirect:/managedosen";
    }
}