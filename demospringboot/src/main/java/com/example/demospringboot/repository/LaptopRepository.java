package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Laptop;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    Laptop findByTipe(String tipe);
}