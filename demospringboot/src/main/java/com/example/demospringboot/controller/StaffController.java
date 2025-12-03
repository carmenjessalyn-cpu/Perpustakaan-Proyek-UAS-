// UAS OBP - Sistem Perpustakaan
// Staff Controller 
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

import com.example.demospringboot.entity.Staff;
import com.example.demospringboot.repository.StaffRepository;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

@Controller
public class StaffController {

    @Autowired
    private StaffRepository staffRepository;

    // HALAMAN UTAMA UNTUK KELOLA STAFF
    @GetMapping({ "/managestaff", "/managestaff/" })
    public String manageStaff(Model model, HttpServletRequest req) {

        // Cek sesi login
        if (req.getSession().getAttribute("logStaff") == null) {
            return "redirect:/signin";
        }

        // Ambil semua data staff
        List<Staff> listStaff = staffRepository.findAll();
        model.addAttribute("listStaff", listStaff);

        // Form tambah staff
        model.addAttribute("staffInfo", new Staff());
        
        // Default form edit kosong
        model.addAttribute("staffRec", null);

        // Sesi staff
        model.addAttribute("logStaff", req.getSession().getAttribute("logStaff"));

        return "managestaff";
    }

    // AMBIL DATA STAFF BERDASARKAN ID untuk form edit
    @GetMapping("/staff/{id}")
    public String getStaffById(@PathVariable("id") Integer id, Model model, HttpServletRequest req) {

        // Cari data staff berdasarkan id
        Staff s = staffRepository.findById(id).orElse(null);

        // Tampilkan daftar semua staff
        model.addAttribute("listStaff", staffRepository.findAll());

        // Data staff untuk form edit
        model.addAttribute("staffRec", s);

        // Objek kosong untuk form tambah
        model.addAttribute("staffInfo", new Staff());

        // Data sesi login
        model.addAttribute("logStaff", req.getSession().getAttribute("logStaff"));

        return "managestaff";
    }

    // TAMBAH STAFF BARU (aksi tombol Add)
    @PostMapping(value = { "/staff/submit/", "/staff/submit/{id}" }, params = { "add" })
    public String addStaff(@ModelAttribute("staffInfo") Staff staffForm) {
        
        // ID akan di-generate otomatis oleh database (auto increment)
        // Tidak perlu set ID manual
        staffRepository.save(staffForm);
        
        return "redirect:/managestaff";
    }

    // EDIT STAFF (aksi tombol Edit)
    @PostMapping(value = "/staff/submit/{id}", params = { "edit" })
    public String editStaff(@PathVariable("id") Integer id, @ModelAttribute("staffInfo") Staff staffForm) {

        // Ambil data lama berdasarkan id
        Staff old = staffRepository.findById(id).orElse(null);

        if (old != null) {
            // Update field yang diubah (kecuali ID)
            old.setNama(staffForm.getNama());
            old.setJabatan(staffForm.getJabatan());
            old.setShift(staffForm.getShift());
            old.setAlamat(staffForm.getAlamat());
            old.setEmail(staffForm.getEmail());
            old.setNoTelp(staffForm.getNoTelp());
            old.setPassword(staffForm.getPassword());
            
            staffRepository.save(old);
        }

        return "redirect:/managestaff";
    }

    // HAPUS STAFF (aksi tombol Delete)
    @PostMapping(value = "/staff/submit/{id}", params = { "delete" })
    public String deleteStaff(@PathVariable("id") Integer id) {
        
        staffRepository.deleteById(id);
        
        return "redirect:/managestaff";
    }
}