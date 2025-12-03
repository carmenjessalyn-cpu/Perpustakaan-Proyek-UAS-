// UAS OBP - Sistem Perpustakaan
// Sign Up Controller 
// author: Keysa Abigail - 825240077

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demospringboot.entity.Dosen;
import com.example.demospringboot.entity.Mahasiswa;

import com.example.demospringboot.service.DosenService;
import com.example.demospringboot.service.MahasiswaService;

@Controller
public class SignUpController {

    @Autowired
    private DosenService dosenService;

    @Autowired
    private MahasiswaService mahasiswaService;

    // HALAMAN PILIH ROLE SIGNUP
    @GetMapping(value = { "/signup", "/signup/" })
    public String showSignUpPage() {
        // Menampilkan halaman utama signup
        // User bisa memilih: daftar sebagai Dosen atau Mahasiswa
        return "signup";
    }

    // FORM SIGNUP DOSEN
    @GetMapping("/signup/dosen")
    public String showSignUpDosenPage(Model model) {
        // Siapkan objek kosong supaya form dosennya bisa terisi otomatis
        model.addAttribute("dosenInfo", new Dosen());
        return "signupdosen";
    }

    // FORM SIGNUP MAHASISWA
    @GetMapping("/signup/mahasiswa")
    public String showSignUpMahasiswaPage(Model model) {
        // Siapkan objek kosong supaya form mahasiswanya bisa jalan
        model.addAttribute("mahasiswaInfo", new Mahasiswa());
        return "signupmahasiswa";
    }

    // PROSES DAFTAR DOSEN
    // (ketika tombol "Daftar" diklik)
    @PostMapping(value = "/signup/dosen/submit", params = { "daftar" })
    public String submitDosenRegistration(@RequestParam("nidn") int nidn,
            @RequestParam("fakultas") String fakultas,
            @RequestParam("nama") String nama,
            @RequestParam("alamat") String alamat,
            @RequestParam("noTelp") String noTelp,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        // Buat objek dosen baru dari data yang diisi user
        Dosen dosen = new Dosen();
        dosen.setNidn(nidn);
        dosen.setFakultas(fakultas);
        dosen.setNama(nama);
        dosen.setAlamat(alamat);
        dosen.setNoTelp(noTelp);
        dosen.setEmail(email);
        dosen.setPassword(password);

        // Kirim data dosen ke service → lalu disimpan ke database
        dosenService.addDosen(dosen);

        // Setelah daftar selesai, arahkan user ke halaman login
        return "redirect:/signin";
    }

    // PROSES DAFTAR MAHASISWA
    // (ketika tombol "Daftar" diklik)
    @PostMapping(value = "/signup/mahasiswa/submit", params = { "daftar" })
    public String submitMahasiswaRegistration(@RequestParam("nim") int nim,
            @RequestParam("fakultas") String fakultas,
            @RequestParam("jurusan") String jurusan,
            @RequestParam("nama") String nama,
            @RequestParam("alamat") String alamat,
            @RequestParam("noTelp") String noTelp,
            @RequestParam("email") String email,
            @RequestParam("password") String password) {

        // Buat objek mahasiswa baru dari inputan form
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNim(nim);
        mahasiswa.setFakultas(fakultas);
        mahasiswa.setJurusan(jurusan);
        mahasiswa.setNama(nama);
        mahasiswa.setAlamat(alamat);
        mahasiswa.setNoTelp(noTelp);
        mahasiswa.setEmail(email);
        mahasiswa.setPassword(password);

        // Simpan data mahasiswa ke database
        mahasiswaService.addMahasiswa(mahasiswa);

        // Setelah selesai → kembali ke halaman signin
        return "redirect:/signin";
    }

    // TOMBOL "BACK" DARI FORM SIGNUP
    @PostMapping(value = { "/signup/submit/", "/signup/submit/{id}" }, params = { "back" })
    public String backToSignIn() {
        // Kalau user mau kembali, arahkan ke halaman login
        return "redirect:/signin";
    }
}
