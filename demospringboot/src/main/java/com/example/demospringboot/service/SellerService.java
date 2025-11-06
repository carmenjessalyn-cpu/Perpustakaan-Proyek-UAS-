package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Seller;
import com.example.demospringboot.repository.SellerRepository;
import java.util.List;

@Service
public class SellerService {
    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getAllSeller() {
        return sellerRepository.findAll();
    }

    public Seller addSeller(Seller obj) {
        Long id = null;
        obj.setId(id);
        return sellerRepository.save(obj);
    }

    public Seller getSellerById(long id) {
        return sellerRepository.findById(id).orElse(null);
    }

    public Seller updateSeller(long id, Seller obj) {
        obj.setId(id);
        return sellerRepository.save(obj);
    }

    public void deleteSeller(long id) {
        sellerRepository.deleteById(id);
    }

    public Seller findSeller(String kode) {
        return sellerRepository.findByKode(kode);
    }

}
