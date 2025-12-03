// UAS OBP - Sistem Perpustakaan
// Peminjaman Controller 
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

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demospringboot.entity.Item;
import com.example.demospringboot.entity.Mahasiswa;
import com.example.demospringboot.entity.Peminjaman;
import com.example.demospringboot.entity.Anggota;
import com.example.demospringboot.entity.Dosen;
import com.example.demospringboot.service.DosenService;
import com.example.demospringboot.service.MahasiswaService;
import com.example.demospringboot.service.ItemService;
import com.example.demospringboot.service.PeminjamanService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

@Controller
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService; 

    @Autowired
    private DosenService dosenService; 

    @Autowired
    private MahasiswaService mahasiswaService; 

    @Autowired
    private ItemService itemService; 

    // Mapping halaman utama pengelolaan peminjaman (staff)
    @GetMapping({ "/managepeminjaman", "/managepeminjaman/" })
    public String managePeminjamanPage(Model model, HttpServletRequest request) {

        // Cek apakah staff sudah login
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Ambil seluruh data peminjaman
        List<Peminjaman> listPeminjaman = peminjamanService.getAllPeminjaman();

        // Ambil seluruh dosen & mahasiswa
        List<Dosen> dosenList = dosenService.getAllDosen();
        List<Mahasiswa> mahasiswaList = mahasiswaService.getAllMahasiswa();

        // Gabungkan menjadi list "anggota" (untuk pilihan peminjam)
        List<Anggota> anggotaList = new ArrayList<>();
        anggotaList.addAll(mahasiswaList);
        anggotaList.addAll(dosenList);

        // Ambil daftar seluruh item
        List<Item> itemList = itemService.getAllItem();

        // Kirim ke view
        model.addAttribute("listPeminjaman", listPeminjaman);
        model.addAttribute("peminjamanInfo", new Peminjaman()); // form kosong
        model.addAttribute("anggotaList", anggotaList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        return "managepeminjaman";
    }

    // Mapping untuk mengambil data peminjaman berdasarkan ID (untuk edit)
    @GetMapping("/peminjaman/{id}")
    public String peminjamanGetRec(Model model, @PathVariable("id") int id, HttpServletRequest request) {

        // Cek login staff
        if (request.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Ambil data peminjaman yang akan diedit
        Peminjaman peminjamanRec = peminjamanService.getPeminjamanById(id);

        // Ambil ulang list data lain untuk dipakai di halaman edit
        List<Peminjaman> listPeminjaman = peminjamanService.getAllPeminjaman();
        List<Dosen> dosenList = dosenService.getAllDosen();
        List<Mahasiswa> mahasiswaList = mahasiswaService.getAllMahasiswa();
        List<Item> itemList = itemService.getAllItem();

        List<Anggota> anggotaList = new ArrayList<>();
        anggotaList.addAll(mahasiswaList);
        anggotaList.addAll(dosenList);

        // Kirim data 
        model.addAttribute("listPeminjaman", listPeminjaman);
        model.addAttribute("peminjamanInfo", peminjamanRec);
        model.addAttribute("peminjamanRec", peminjamanRec);
        model.addAttribute("anggotaList", anggotaList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("logStaff", request.getSession().getAttribute("logStaff"));

        return "managepeminjaman.html";
    }

    // Mapping untuk menambahkan data peminjaman baru
    @PostMapping("/peminjaman/submit")
    public String peminjamanAdd(
            @ModelAttribute("peminjamanInfo") Peminjaman peminjamanInfo,
            @RequestParam("id") int id, // ID anggota (mahasiswa/dosen)
            @RequestParam("itemId") Long itemId) // ID item yang dipinjam
    {

        // Cari item berdasarkan ID
        Item item = itemService.getItemById(itemId);

        if (item == null) {
            return "redirect:/managepeminjaman?error=itemNotFound";
        }

        // PERUBAHAN: setItem menjadi setObjItem
        peminjamanInfo.setObjItem(item);

        // Tentukan apakah peminjamnya mahasiswa atau dosen
        Mahasiswa mahasiswa = mahasiswaService.getMahasiswaById(id);

        if (mahasiswa != null) {
            // PERUBAHAN: setMahasiswa menjadi setObjMahasiswa
            peminjamanInfo.setObjMahasiswa(mahasiswa);
            peminjamanInfo.setObjDosen(null);
        } else {
            Dosen dosen = dosenService.getDosenById(id);
            if (dosen != null) {
                // PERUBAHAN: setDosen menjadi setObjDosen
                peminjamanInfo.setObjDosen(dosen);
                peminjamanInfo.setObjMahasiswa(null);
            } else {
                return "redirect:/managepeminjaman?error=anggotaNotFound";
            }
        }

        // Simpan data peminjaman
        peminjamanService.addPeminjaman(peminjamanInfo);
        return "redirect:/managepeminjaman";
    }

    // Mapping untuk mengedit data peminjaman
    @PostMapping(value = "/peminjaman/submit/{id}", params = "edit")
    public String peminjamanEdit(
            @ModelAttribute("peminjamanInfo") Peminjaman peminjamanInfo,
            @PathVariable("id") int id,
            @RequestParam("itemId") Long itemId,
            @RequestParam(value = "anggotaId", required = false) Integer anggotaId) {

        // Update item jika diganti
        Item item = itemService.getItemById(itemId);
        if (item != null) {
            // PERUBAHAN: setItem menjadi setObjItem
            peminjamanInfo.setObjItem(item);
        }

        // Jika peminjam diganti
        if (anggotaId != null && anggotaId > 0) {
            Mahasiswa mahasiswa = mahasiswaService.getMahasiswaById(anggotaId);
            if (mahasiswa != null) {
                // PERUBAHAN: setMahasiswa menjadi setObjMahasiswa
                peminjamanInfo.setObjMahasiswa(mahasiswa);
                peminjamanInfo.setObjDosen(null);
            } else {
                Dosen dosen = dosenService.getDosenById(anggotaId);
                if (dosen != null) {
                    // PERUBAHAN: setDosen menjadi setObjDosen
                    peminjamanInfo.setObjDosen(dosen);
                    peminjamanInfo.setObjMahasiswa(null);
                }
            }
        }

        // Simpan perubahan
        peminjamanService.updatePeminjaman(id, peminjamanInfo);
        return "redirect:/managepeminjaman";
    }

    // Mapping untuk menghapus peminjaman
    @PostMapping(value = "/peminjaman/submit/{id}", params = "delete")
    public String peminjamanDelete(@PathVariable("id") int id) {

        peminjamanService.deletePeminjaman(id);
        return "redirect:/managepeminjaman";
    }
}