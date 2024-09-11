package com.poec.sortie_facile_backend.domain.contact;

import com.poec.sortie_facile_backend.domain.contact.dto.ContactDTO;
import com.poec.sortie_facile_backend.domain.contact.dto.SaveContactDTO;
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

    @Autowired
    private ContactMapper contactMapper;

    @GetMapping(ALL)
    public ResponseEntity<List<ContactDTO>> getAll() {
        List<Contact> contactList = contactService.findAll();
        List<ContactDTO> contactDTOS = contactList.stream().map(contactMapper::mapFromEntity).toList();

        return new ResponseEntity<>(contactDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<ContactDTO> getById(@PathVariable("id") Long contactId) {
        Contact contact = contactService.findById(contactId);
        ContactDTO contactDTO = contactMapper.mapFromEntity(contact);

        return new ResponseEntity<>(contactDTO, HttpStatus.FOUND);
    }

    @PostMapping(ADD)
    public ResponseEntity<ContactDTO> add(@RequestBody SaveContactDTO saveContactDTO) {
        Contact contact = contactMapper.mapToEntity(saveContactDTO);
        Contact newContact = contactService.add(contact);
        ContactDTO newContactDTO = contactMapper.mapFromEntity(newContact);

        return new ResponseEntity<>(newContactDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ContactDTO> updateById(
            @RequestBody SaveContactDTO saveContactDTO,
            @PathVariable("id") Long contactId
    ) {
        Contact contact = contactMapper.mapToEntity(saveContactDTO);
        Contact updatedContact = contactService.updateById(contact, contactId);
        ContactDTO updatedContactDTO = contactMapper.mapFromEntity(updatedContact);

        return new ResponseEntity<>(updatedContactDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long contactId) {
        contactService.deleteById(contactId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
