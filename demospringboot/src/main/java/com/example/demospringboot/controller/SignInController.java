// UAS OBP - Sistem Perpustakaan
// Sign In Controller 
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

import com.example.demospringboot.entity.Dosen;
import com.example.demospringboot.entity.Mahasiswa;
import com.example.demospringboot.entity.Staff;
import com.example.demospringboot.service.DosenService;
import com.example.demospringboot.service.MahasiswaService;
import com.example.demospringboot.service.StaffService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SignInController {

    @Autowired
    private DosenService dosenService; // servis untuk cari data dosen berdasarkan email

    @Autowired
    private MahasiswaService mahasiswaService; // servis untuk cari data mahasiswa

    @Autowired
    private StaffService staffService; // servis untuk cari data staff

    // MENAMPILKAN HALAMAN SIGN IN
    @GetMapping(value = "/signin")
    public String showSignInPage(HttpServletRequest request) {
        // Kalau staff sudah login → langsung lempar ke halaman staff
        if (request.getSession().getAttribute("logStaff") != null) {
            return "redirect:/managebuku";
        }

        // Kalau dosen/mahasiswa sudah login → langsung lempar ke halaman user
        if (request.getSession().getAttribute("loggedUser") != null) {
            return "redirect:/buku";
        }

        // Kalau belum login → tampilkan halaman signin
        return "signin";
    }

    // PROSES VALIDASI LOGIN
    @PostMapping(value = "/validateSignin")
    public String validateSignIn(@RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "password", required = false) String password,
            HttpServletRequest request,
            Model model) {

        // Cek apakah email & password diisi
        if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
            model.addAttribute("error", "Email dan password harus diisi!");
            return "signin"; // balik lagi ke halaman login
        }

        // 1. Cek login sebagai Staff
        Staff staff = staffService.findStaff(email);

        // Jika email ketemu dan password cocok → staff berhasil login
        if (staff != null && password.equals(staff.getPassword())) {
            request.getSession().setAttribute("logStaff", staff); // simpan user di session
            return "redirect:/managebuku"; // arahkan ke halaman staff
        }

        // 2. Cek login sebagai Dosen
        Dosen dosen = dosenService.findDosen(email);

        if (dosen != null && password.equals(dosen.getPassword())) {
            request.getSession().setAttribute("loggedUser", dosen);
            return "redirect:/buku"; // arahkan ke halaman user biasa
        }

        // 3. Cek login sebagai Mahasiswa
        Mahasiswa mahasiswa = mahasiswaService.findMahasiswa(email);

        if (mahasiswa != null && password.equals(mahasiswa.getPassword())) {
            request.getSession().setAttribute("loggedUser", mahasiswa);
            return "redirect:/buku";
        }

        // Kalau semua pengecekan di atas gagal = login gagal
        model.addAttribute("error", "Email atau password salah!");
        return "signin";
    }

    // LOGOUT UNTUK STAFF
    @GetMapping(value = "/stafflogout")
    public String staffLogout(HttpServletRequest request) {

        // Kalau session staff ada → hapus sessionnya (logout)
        if (request.getSession().getAttribute("logStaff") != null) {
            request.getSession().invalidate();
        }

        return "redirect:/signin"; // kembali ke halaman login
    }

    // LOGOUT UNTUK USER (DOSEN / MAHASISWA)
    @GetMapping(value = "/logout")
    public String userLogout(HttpServletRequest request) {

        // Hapus session user biasa
        if (request.getSession().getAttribute("loggedUser") != null) {
            request.getSession().invalidate();
        }

        return "redirect:/signin";
    }
}
