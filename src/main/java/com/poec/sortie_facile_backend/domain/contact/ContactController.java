package com.poec.sortie_facile_backend.domain.contact;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + CONTACT)
@RequiredArgsConstructor
public class ContactController {

    @Autowired
    private ContactService contactService;

    @GetMapping(ALL)
    public ResponseEntity<List<ContactDTO>> getAll() {
        List<Contact> contacts = contactService.findAll();
        List<ContactDTO> contactDTOS = contacts.stream().map(ContactDTO::mapFromEntity).toList();
        return new ResponseEntity<>(contactDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<ContactDTO> getById(@PathVariable Long id) {
        Contact newContact = contactService.findById(id);
        ContactDTO contactDTO = ContactDTO.mapFromEntity(newContact);
        return new ResponseEntity<>(contactDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD)
    public ResponseEntity<ContactDTO> add(@RequestBody ContactDTO contactDTO) {
        Contact newContact = contactService.add(ContactDTO.mapToEntity(contactDTO));
        ContactDTO newContactDTO = ContactDTO.mapFromEntity(newContact);
        return new ResponseEntity<>(newContactDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ContactDTO> update(@RequestBody Contact contact, @PathVariable Long id) {
        Contact newContact = contactService.updateById(contact, id);
        ContactDTO contactDTO = ContactDTO.mapFromEntity(newContact);
        return new ResponseEntity<>(contactDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        contactService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
