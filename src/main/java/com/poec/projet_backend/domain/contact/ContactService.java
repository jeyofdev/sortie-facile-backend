package com.poec.projet_backend.domain.contact;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository repository;

    public List<Contact> getAll() {
        return repository.findAll();
    }

    public Contact getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Contact add(Contact contact) {
        return repository.save(contact);
    }

    public Contact update(Contact contact, Long id) {
        Contact newContact = getById(id);
        newContact.setMessage(contact.getMessage());
        newContact.setEmail(contact.getEmail());
        newContact.setTitle(contact.getTitle());

        return repository.save(newContact);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
