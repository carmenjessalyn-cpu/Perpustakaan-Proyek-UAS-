package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Laptop;
import com.example.demospringboot.repository.LaptopRepository;
import java.util.List;

@Service
public class LaptopService {
    @Autowired
    private LaptopRepository laptopRepository;

    public List<Laptop> getAllLaptop() {
        return laptopRepository.findAll();
    }

    public Laptop addLaptop(Laptop obj) {
        Long id = null;
        obj.setId(id);
        return laptopRepository.save(obj);
    }

    public Laptop getLaptopById(long id) {
        return laptopRepository.findById(id).orElse(null);
    }

    public Laptop updateLaptop(long id, Laptop obj) {
        obj.setId(id);
        return laptopRepository.save(obj);
    }

    public void deleteLaptop(long id) {
        laptopRepository.deleteById(id);
    }

    public Laptop findLaptop(String tipe) {
        return laptopRepository.findByTipe(tipe);
    }
}
