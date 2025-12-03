// UAS OBP - Sistem Perpustakaan
// Jurnal Controller 
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

import com.example.demospringboot.entity.Jurnal;
import com.example.demospringboot.service.JurnalService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class JurnalController {

    @Autowired
    private JurnalService jurnalService;

    // Mapping untuk menampilkan katalog jurnal (khusus mahasiswa/dosen)
    @GetMapping(value = { "/jurnal", "/jurnal/" })
    public String jurnalPage(Model model, HttpServletRequest request) {

        // Cek apakah user sudah login
        Object loggedUser = request.getSession().getAttribute("loggedUser");
        if (loggedUser == null) {
            // Jika belum login, arahkan ke halaman signin
            return "redirect:/signin";
        }

        // Mengambil seluruh data jurnal dari database
        List<Jurnal> listJurnal = jurnalService.getAllJurnal();

        // Mengirim data jurnal dan user yang sedang login ke view
        model.addAttribute("listJurnal", listJurnal);
        model.addAttribute("loggedUser", loggedUser);

        // Mengarahkan ke halaman katalog jurnal
        return "jurnal";
    }

    // Mapping untuk menampilkan halaman manajemen jurnal (khusus staff)
    @GetMapping(value = { "/managejurnal", "/managejurnal/" })
    public String manageJurnalPage(Model model, HttpServletRequest request) {

        // Cek apakah staff sudah login
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Mengambil seluruh data jurnal
        List<Jurnal> listJurnal = jurnalService.getAllJurnal();

        // Menyiapkan list jurnal dan form kosong untuk tambah data
        model.addAttribute("listJurnal", listJurnal);
        model.addAttribute("jurnalInfo", new Jurnal());

        // Mengirim data staff yang sedang login
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        // Mengarahkan ke halaman manajemen jurnal
        return "managejurnal";
    }

    // Mapping untuk mengambil 1 data jurnal berdasarkan ID (untuk form edit)
    @GetMapping("/jurnal/{id}")
    public String jurnalGetRec(Model model, @PathVariable("id") long id, HttpServletRequest request) {

        // Cek apakah staff sudah login
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Mengambil semua data jurnal dan jurnal yang dipilih untuk diedit
        List<Jurnal> listJurnal = jurnalService.getAllJurnal();
        Jurnal jurnalRec = jurnalService.getJurnalById(id);

        // Mengirim data ke view (list + data edit)
        model.addAttribute("listJurnal", listJurnal);
        model.addAttribute("jurnalRec", jurnalRec);
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        return "managejurnal";
    }

    // Mapping untuk menambahkan jurnal baru (aksi tombol Add)
    @PostMapping(value = { "/jurnal/submit/", "/jurnal/submit/{id}" }, params = { "add" })
    public String jurnalAdd(@ModelAttribute("jurnalInfo") Jurnal jurnalInfo) {

        // Menyimpan data jurnal baru ke database
        jurnalService.addJurnal(jurnalInfo);

        // Kembali ke halaman manajemen jurnal
        return "redirect:/managejurnal";
    }

    // Mapping untuk mengedit jurnal (aksi tombol Edit)
    @PostMapping(value = "/jurnal/submit/{id}", params = { "edit" })
    public String jurnalEdit(@ModelAttribute("jurnalInfo") Jurnal jurnalInfo, @PathVariable("id") long id) {

        // Memperbarui data jurnal sesuai ID
        jurnalService.updateJurnal(id, jurnalInfo);

        // Kembali ke halaman manajemen jurnal
        return "redirect:/managejurnal";
    }

    // Mapping untuk menghapus jurnal (aksi tombol Delete)
    @PostMapping(value = "/jurnal/submit/{id}", params = { "delete" })
    public String jurnalDelete(@PathVariable("id") long id) {

        // Menghapus jurnal berdasarkan ID
        jurnalService.deleteJurnal(id);

        // Kembali ke halaman manajemen jurnal
        return "redirect:/managejurnal";
    }
}
