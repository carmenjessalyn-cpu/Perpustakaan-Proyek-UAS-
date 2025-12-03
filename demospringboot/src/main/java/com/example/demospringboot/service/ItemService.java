// UAS OBP - Sistem Perpustakaan
// Item Service
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

package com.example.demospringboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demospringboot.entity.Item;
import com.example.demospringboot.repository.ItemRepository;
import java.util.List;

@Service
public class ItemService {
    // Repository untuk mengakses tabel item di database
    @Autowired
    private ItemRepository itemRepository;

    // Method mengambil seluruh data item dari database
    public List<Item> getAllItem() {
        return itemRepository.findAll();
    }

    // Method menambahkan item baru ke database
    public Item addItem(Item obj) {
        return itemRepository.save(obj);
    }

    // Method mencari item berdasarkan ID
    public Item getItemById(Long itemId) {
        return itemRepository.findById(itemId).orElse(null);
    }

    // Method memperbarui item berdasarkan ID
    public Item updateItem(Long itemId, Item obj) {
        obj.setItemId(itemId); // set ID pada objek sebelum disimpan
        return itemRepository.save(obj);
    }

    // Method menghapus item berdasarkan ID
    public void deleteItem(Long itemId) {
        itemRepository.deleteById(itemId);
    }

}