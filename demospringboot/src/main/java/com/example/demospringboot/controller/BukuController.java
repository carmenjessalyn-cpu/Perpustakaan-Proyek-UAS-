// UAS OBP - Sistem Perpustakaan
// Buku Controller 
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

package com.example.demospringboot.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demospringboot.entity.Buku;
import com.example.demospringboot.service.BukuService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BukuController {

    @Autowired
    private BukuService bukuService;

    // Mapping untuk menampilkan katalog buku (tampilan yang keluar apabila yang
    // signin adalah mahasiswa/dosen)
    @GetMapping(value = { "/buku", "/buku/" })
    public String bukuPage(Model model, HttpServletRequest request) {
        // Cek apakah user sudah login
        Object loggedUser = request.getSession().getAttribute("loggedUser");
        // logika apabila user belum login maka akan kembali ke halaman signin
        if (loggedUser == null) {
            return "redirect:/signin";
        }

        // mengambil semua data buku dari database
        List<Buku> listBuku = bukuService.getAllBuku();

        // mengirim data buku dan user yang login ke html
        model.addAttribute("listBuku", listBuku);
        model.addAttribute("loggedUser", loggedUser);

        // mengarahkan kembali ke halaman katalog buku
        return "buku";
    }

    // Mapping untuk menampilkan halaman manajemen buku (khusus staff)
    @GetMapping(value = { "/managebuku", "/managebuku/" })
    public String manageBukuPage(Model model, HttpServletRequest request) {

        // Cek apakah staff sudah login
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Mengambil seluruh data buku dari database
        List<Buku> listBuku = bukuService.getAllBuku();

        // Mengirim daftar buku dan objek kosong untuk form
        model.addAttribute("listBuku", listBuku);
        model.addAttribute("bukuInfo", new Buku());

        // Mengirim data staff yang sedang login
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        // Mengarahkan ke halaman manajemen buku
        return "managebuku";
    }

    // Mapping untuk mengambil 1 record buku berdasarkan ID (untuk form edit)
    @GetMapping("/buku/{id}")
    public String bukuGetRec(Model model, @PathVariable("id") long id, HttpServletRequest request) {

        // Cek apakah staff sudah login
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Mengambil seluruh daftar buku
        List<Buku> listBuku = bukuService.getAllBuku();

        // Mengambil data buku sesuai ID yang dipilih
        Buku bukuRec = bukuService.getBukuById(id);

        // Mengirim data list dan buku yang akan diedit ke view
        model.addAttribute("listBuku", listBuku);
        model.addAttribute("bukuRec", bukuRec);
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        return "managebuku";
    }

    // Mapping untuk menambahkan buku baru (aksi tombol Add)
    @PostMapping(value = { "/buku/submit/", "/buku/submit/{id}" }, params = { "add" })
    public String bukuAdd(@ModelAttribute("bukuInfo") Buku bukuInfo) {

        // Menyimpan data buku baru ke database
        bukuService.addBuku(bukuInfo);

        // Kembali ke halaman manajemen buku
        return "redirect:/managebuku";
    }

    // Mapping untuk mengedit buku (aksi tombol Edit)
    @PostMapping(value = "/buku/submit/{id}", params = { "edit" })
    public String bukuEdit(@ModelAttribute("bukuInfo") Buku bukuInfo, @PathVariable("id") long id) {

        // Memperbarui data buku berdasarkan ID
        bukuService.updateBuku(id, bukuInfo);

        // Kembali ke halaman manajemen buku
        return "redirect:/managebuku";
    }

    // Mapping untuk menghapus buku (aksi tombol Delete)
    @PostMapping(value = "/buku/submit/{id}", params = { "delete" })
    public String bukuDelete(@PathVariable("id") long id) {

        // Menghapus buku dari database berdasarkan ID
        bukuService.deleteBuku(id);

        // Kembali ke halaman manajemen buku
        return "redirect:/managebuku";
    }

}
