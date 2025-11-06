package com.example.demospringboot.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demospringboot.entity.Penjualan;
import com.example.demospringboot.entity.Person;
import com.example.demospringboot.entity.Produk;
import com.example.demospringboot.service.PenjualanService;
import com.example.demospringboot.service.PersonService;
import com.example.demospringboot.service.ProdukService;

@Controller
public class PenjualanController {
    @Autowired
    private PenjualanService penjualanService;
    @Autowired
    private PersonService personService;
    @Autowired
    private ProdukService produkService;

    @GetMapping(value = { "/penjualan", "/penjualan/" })
    public String PenjualanPage(Model model) {
        List<Penjualan> penjualanList = penjualanService.getAllPenjualan();
        List<Person> personList = personService.getAllPerson();
        List<Produk> produkList = produkService.getAllProduk();
        model.addAttribute("penjualanList", penjualanList);
        model.addAttribute("penjualanInfo", new Penjualan());
        model.addAttribute("personList", personList);
        model.addAttribute("produkList", produkList);
        return "penjualan.html";
    }

    @GetMapping("/penjualan/{id}")
    public String PenjualanGetRec(Model model, @PathVariable("id") int id) {
        List<Penjualan> penjualanList = penjualanService.getAllPenjualan();
        List<Person> personList = personService.getAllPerson();
        List<Produk> produkList = produkService.getAllProduk();
        Penjualan penjualanRec = penjualanService.getPenjualanById(id);
        model.addAttribute("penjualanList", penjualanList);
        model.addAttribute("penjualanRec", penjualanRec);
        model.addAttribute("personList", personList);
        model.addAttribute("produkList", produkList);
        return "penjualan.html";
    }

    @PostMapping(value = { "/penjualan/submit/", "/penjualan/submit/{id}" }, params = { "add" })
    public String penjualanAdd(@ModelAttribute("penjualanInfo") Penjualan penjualanInfo) {
        penjualanService.addPenjualan(penjualanInfo);
        return "redirect:/penjualan";
    }

    @PostMapping(value = "/penjualan/submit/{id}", params = { "edit" })
    public String penjualanEdit(@ModelAttribute("penjualanInfo") Penjualan penjualanInfo,
            @PathVariable("id") int id) {
        penjualanService.updatePenjualan(id, penjualanInfo);
        return "redirect:/penjualan";
    }

    @PostMapping(value = "/penjualan/submit/{id}", params = { "delete" })
    public String penjualanDelete(@PathVariable("id") int id) {
        penjualanService.deletePenjualan(id);
        return "redirect:/penjualan";
    }

}
