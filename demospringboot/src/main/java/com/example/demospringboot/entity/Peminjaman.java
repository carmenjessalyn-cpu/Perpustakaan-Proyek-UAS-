// UAS OBP - Sistem Perpustakaan
// Peminjaman Entity
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

package com.example.demospringboot.entity;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Peminjaman {

    // Atribut class peminajamn
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPeminjaman;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tglPinjam;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tglTempo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date tglKembali;

    private String status;

    // Relasi Asosiasi dengan class item, mahasiswa, dosen, staff
    @ManyToOne
    private Item objItem;

    @ManyToOne
    private Mahasiswa objMahasiswa;

    @ManyToOne
    private Dosen objDosen;

    @ManyToOne
    private Staff objStaff;

    // Constructor 1: Inisialisasi variabel
    public Peminjaman(int idPeminjaman, Date tglPinjam, Date tglTempo, Date tglKembali, String status, Item objItem,
            Mahasiswa objMahasiswa, Dosen objDosen, Staff objStaff) {
        this.idPeminjaman = idPeminjaman;
        this.tglPinjam = tglPinjam;
        this.tglTempo = tglTempo;
        this.tglKembali = tglKembali;
        this.status = status;
        this.objMahasiswa = objMahasiswa;
        this.objDosen = objDosen;
        this.objItem = objItem;
        this.objStaff = objStaff;
    }

    // Contructor 2: contstructor kosong untuk keperluan JPA
    public Peminjaman() {
    }

    // Implementasi hitung denda berdasarkan jenis item untuk output
    public int hitungDenda() {
        if (objItem == null)
            return 0; // Kalau item kosong, tidak bisa hitung denda
        return objItem.hitungDenda(tglKembali, tglTempo);
    }

    // public setter & getter
    public int getIdPeminjaman() {
        return idPeminjaman;
    }

    public void setIdPeminjaman(int idPeminjaman) {
        this.idPeminjaman = idPeminjaman;
    }

    public Date getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(Date tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public Date getTglTempo() {
        return tglTempo;
    }

    public void setTglTempo(Date tglTempo) {
        this.tglTempo = tglTempo;
    }

    public Date getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(Date tglKembali) {
        this.tglKembali = tglKembali;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Item getObjItem() {
        return objItem;
    }

    public void setObjItem(Item objItem) {
        this.objItem = objItem;
    }

    public Mahasiswa getObjMahasiswa() {
        return objMahasiswa;
    }

    public void setObjMahasiswa(Mahasiswa objMahasiswa) {
        this.objMahasiswa = objMahasiswa;
    }

    public Dosen getObjDosen() {
        return objDosen;
    }

    public void setObjDosen(Dosen objDosen) {
        this.objDosen = objDosen;
    }

    public Staff getObjStaff() {
        return objStaff;
    }

    public void setObjStaff(Staff objStaff) {
        this.objStaff = objStaff;
    }

    // Ambil judul item tanpa harus cek null di tampilan
    public String getJudulItem() {
        return objItem != null ? objItem.getJudul() : "-";
    }

    // Ambil penulis item
    public String getPenulisItem() {
        return objItem != null ? objItem.getPenulis() : "-";
    }

    // Ambil nama peminjam, bisa mahasiswa atau dosen
    public String getNamaPeminjam() {
        if (objMahasiswa != null)
            return objMahasiswa.getNama();
        if (objDosen != null)
            return objDosen.getNama();
        return "-"; // kalau dua-duanya null
    }
}
