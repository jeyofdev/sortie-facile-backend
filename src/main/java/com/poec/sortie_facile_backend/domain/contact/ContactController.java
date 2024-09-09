package com.poec.projet_backend.domain.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.projet_backend.util.Route.*;

@RestController
@RequestMapping(BASE_URL + CONTACT)
@RequiredArgsConstructor
public class ContactController {

    @Autowired
    private ContactService service;

    @GetMapping(ALL)
    public ResponseEntity<List<ContactDTO>> getAll() {
        List<Contact> contacts = service.getAll();
        List<ContactDTO> contactDTOS = contacts.stream().map(ContactDTO::mapFromEntity).toList();
        return new ResponseEntity<>(contactDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<ContactDTO> getById(@PathVariable Long id) {
        Contact newContact = service.getById(id);
        ContactDTO contactDTO = ContactDTO.mapFromEntity(newContact);
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @PostMapping(ADD)
    public ResponseEntity<ContactDTO> add(@RequestBody ContactDTO contactDTO) {
        Contact newContact = service.add(ContactDTO.mapToEntity(contactDTO));
        ContactDTO newContactDTO = ContactDTO.mapFromEntity(newContact);
        return new ResponseEntity<>(newContactDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ContactDTO> update(@RequestBody Contact contact, @PathVariable Long id) {
        Contact newContact = service.update(contact, id);
        ContactDTO contactDTO = ContactDTO.mapFromEntity(newContact);
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
