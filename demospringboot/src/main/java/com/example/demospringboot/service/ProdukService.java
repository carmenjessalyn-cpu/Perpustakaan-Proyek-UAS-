package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Produk;
import com.example.demospringboot.repository.ProdukRepository;
import java.util.List;

@Service
public class ProdukService {
    @Autowired
    private ProdukRepository produkRepository;

    public List<Produk> getAllProduk() {
        return produkRepository.findAll();
    }

    public Produk addProduk(Produk obj) {
        Long id = null;
        obj.setId(id);
        return produkRepository.save(obj);
    }

    public Produk getProdukById(long id) {
        return produkRepository.findById(id).orElse(null);
    }

    public Produk updateProduk(long id, Produk obj) {
        obj.setId(id);
        return produkRepository.save(obj);
    }

    public void deleteProduk(long id) {
        produkRepository.deleteById(id);
    }

}