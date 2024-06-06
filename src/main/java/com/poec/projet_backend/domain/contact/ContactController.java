package com.poec.projet_backend.domain.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping("/all")
    public ResponseEntity<List<ContactDTO>> getAll() {
        List<Contact> contacts = service.getAll();
        List<ContactDTO> contactDTOS = contacts.stream().map(ContactDTO::mapFromEntity).toList();
        return new ResponseEntity<>(contactDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ContactDTO getById(@PathVariable Long id) {
        Contact newContact = service.getById(id);
        ContactDTO contactDTO = ContactDTO.mapFromEntity(newContact);
        return contactDTO;
    }

    @PostMapping("/add")
    public ContactDTO add(@RequestBody Contact contact) {
        Contact newContact = service.add(contact);
        ContactDTO contactDTO = ContactDTO.mapFromEntity(newContact);
        return contactDTO;
    }

    @PutMapping("update/{id}")
    public ContactDTO update(@RequestBody Contact contact, @PathVariable Long id) {
        Contact newContact = service.update(contact, id);
        ContactDTO contactDTO = ContactDTO.mapFromEntity(newContact);
        return contactDTO;
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
