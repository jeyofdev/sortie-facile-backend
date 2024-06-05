package com.poec.projet_backend.domain.contact;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.activity.ActivityService;
import jakarta.persistence.*;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping("/all")
    public ResponseEntity<List<Contact>> getAll() {
        List<Contact> contacts = service.getAll();
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contact> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Contact> add(@RequestBody Contact contact) {
        return new ResponseEntity<>(service.add(contact), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Contact> update(@RequestBody Contact contact, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(contact, id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
