package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.HandPhone;
import com.example.demospringboot.repository.HandPhoneRepository;
import java.util.List;

@Service
public class HandPhoneService {
    @Autowired
    private HandPhoneRepository handPhoneRepository;

    public List<HandPhone> getAllhHandPhones() {
        return handPhoneRepository.findAll();
    }

    public HandPhone addHandPhone(HandPhone obj) {
        Long id = null;
        obj.setId(id);
        return handPhoneRepository.save(obj);
    }

    public HandPhone getHandPhoneById(long id) {
        return handPhoneRepository.findById(id).orElse(null);
    }

    public HandPhone updateHandPhone(long id, HandPhone obj) {
        obj.setId(id);
        return handPhoneRepository.save(obj);
    }

    public void deleteHandPhone(long id) {
        handPhoneRepository.deleteById(id);
    }

    public HandPhone findHandPhone(String tipe) {
        return handPhoneRepository.findByTipe(tipe);
    }
}
