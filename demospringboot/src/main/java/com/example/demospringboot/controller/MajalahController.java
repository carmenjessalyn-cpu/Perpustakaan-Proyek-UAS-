// UAS OBP - Sistem Perpustakaan
// Majalah Controller 
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
// Spring Boot Tutorial for Beginners [2025] - Programming with Mosh (https://youtu.be/gJrjgg1KVL4?si=y64Bx_72OMCwoX2z)
// Java Full Course for free ☕ (2025) - Bro Code (https://youtu.be/xTtL8E4LzTQ?si=6kw2WMCirHQrMCVc)

package com.example.demospringboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demospringboot.entity.Majalah;
import com.example.demospringboot.service.MajalahService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MajalahController {

    @Autowired
    private MajalahService majalahService;

    // Mapping untuk menampilkan katalog majalah (khusus user: dosen/mahasiswa)
    @GetMapping(value = { "/majalah", "/majalah/" })
    public String majalahPage(Model model, HttpServletRequest request) {

        // Cek apakah user sudah login
        Object loggedUser = request.getSession().getAttribute("loggedUser");
        if (loggedUser == null) {
            // Jika belum login, arahkan ke halaman signin
            return "redirect:/signin";
        }

        // Mengambil seluruh data majalah dari database
        List<Majalah> listMajalah = majalahService.getAllMajalah();

        // Mengirim data majalah dan user yang sedang login ke view
        model.addAttribute("listMajalah", listMajalah);
        model.addAttribute("loggedUser", loggedUser);

        // Mengarahkan ke halaman katalog majalah
        return "majalah";
    }

    // Mapping untuk menampilkan halaman manajemen majalah (khusus staff)
    @GetMapping(value = { "/managemajalah", "/managemajalah/" })
    public String manageMajalahPage(Model model, HttpServletRequest request) {

        // Cek apakah staff sudah login
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Mengambil seluruh data majalah
        List<Majalah> listMajalah = majalahService.getAllMajalah();

        // Menyiapkan list dan form kosong untuk tambah data
        model.addAttribute("listMajalah", listMajalah);
        model.addAttribute("majalahInfo", new Majalah());

        // Mengirim data staff yang sedang login
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        // Mengarahkan ke halaman manajemen majalah
        return "managemajalah";
    }

    // Mapping untuk mengambil 1 data majalah berdasarkan ID (untuk form edit)
    @GetMapping("/majalah/{id}")
    public String majalahGetRec(Model model, @PathVariable("id") long id, HttpServletRequest request) {

        // Hanya staff yang boleh mengakses halaman ini
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Mengambil seluruh data majalah (untuk list)
        List<Majalah> listMajalah = majalahService.getAllMajalah();

        // Mengambil data majalah berdasarkan ID
        Majalah majalahRec = majalahService.getMajalahById(id);

        // Mengirim data list + data edit + staff login
        model.addAttribute("listMajalah", listMajalah);
        model.addAttribute("majalahRec", majalahRec);
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        return "managemajalah";
    }

    // Mapping untuk menambahkan majalah baru (aksi tombol Add)
    @PostMapping(value = { "/majalah/submit/", "/majalah/submit/{id}" }, params = { "add" })
    public String majalahAdd(@ModelAttribute("majalahInfo") Majalah majalahInfo) {

        // Menyimpan data majalah baru ke database
        majalahService.addMajalah(majalahInfo);

        // Kembali ke halaman manajemen majalah
        return "redirect:/managemajalah";
    }

    // Mapping untuk mengedit majalah (aksi tombol Edit)
    @PostMapping(value = "/majalah/submit/{id}", params = { "edit" })
    public String majalahEdit(@ModelAttribute("majalahInfo") Majalah majalahInfo,
            @PathVariable("id") long id) {

        // Memperbarui data majalah berdasarkan ID
        majalahService.updateMajalah(id, majalahInfo);

        // Kembali ke halaman manajemen majalah
        return "redirect:/managemajalah";
    }

    // Mapping untuk menghapus majalah (aksi tombol Delete)
    @PostMapping(value = "/majalah/submit/{id}", params = { "delete" })
    public String majalahDelete(@PathVariable("id") long id) {

        // Menghapus data majalah berdasarkan ID
        majalahService.deleteMajalah(id);

        // Kembali ke halaman manajemen majalah
        return "redirect:/managemajalah";
    }
}
