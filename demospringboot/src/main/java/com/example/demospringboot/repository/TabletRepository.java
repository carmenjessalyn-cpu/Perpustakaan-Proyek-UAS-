package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.Tablet;

public interface TabletRepository extends JpaRepository<Tablet, Long> {
    Tablet findByTipe(String tipe);
}