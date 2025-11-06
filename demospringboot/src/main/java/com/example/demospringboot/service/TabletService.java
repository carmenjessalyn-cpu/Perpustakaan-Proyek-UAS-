
package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Tablet;
import com.example.demospringboot.repository.TabletRepository;
import java.util.List;

@Service
public class TabletService {
    @Autowired
    private TabletRepository tabletRepository;

    public List<Tablet> getAllTablet() {
        return tabletRepository.findAll();
    }

    public Tablet addTablet(Tablet obj) {
        Long id = null;
        obj.setId(id);
        return tabletRepository.save(obj);
    }

    public Tablet getTabletById(long id) {
        return tabletRepository.findById(id).orElse(null);
    }

    public Tablet updateTablet(long id, Tablet obj) {
        obj.setId(id);
        return tabletRepository.save(obj);
    }

    public void deleteTablet(long id) {
        tabletRepository.deleteById(id);
    }

    public Tablet findTablet(String tipe) {
        return tabletRepository.findByTipe(tipe);
    }
}
