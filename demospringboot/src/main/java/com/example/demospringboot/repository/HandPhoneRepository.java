package com.example.demospringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demospringboot.entity.HandPhone;

public interface HandPhoneRepository extends JpaRepository<HandPhone, Long> {
    HandPhone findByTipe(String tipe);
}