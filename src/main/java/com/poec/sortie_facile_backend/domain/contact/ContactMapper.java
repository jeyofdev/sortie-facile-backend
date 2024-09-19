package com.poec.sortie_facile_backend.domain.contact;

import com.poec.sortie_facile_backend.core.interfaces.BaseDomainMapper;
import com.poec.sortie_facile_backend.domain.contact.dto.ContactDTO;
import com.poec.sortie_facile_backend.domain.contact.dto.SaveContactDTO;
import org.springframework.stereotype.Service;

@Service
public class ContactMapper implements BaseDomainMapper<Contact, ContactDTO, SaveContactDTO> {
    @Override
    public ContactDTO mapFromEntity(Contact contact, boolean primaryDataOnly) {
        return new ContactDTO(
                contact.getId(),
                contact.getTitle(),
                contact.getEmail(),
                contact.getMessage(),
                contact.isRead()
        );
    }

    @Override
    public Contact mapToEntity(SaveContactDTO saveContactDTO) {
        Contact contact = new Contact();
        contact.setTitle(saveContactDTO.title());
        contact.setEmail(saveContactDTO.email());
        contact.setMessage(saveContactDTO.message());
        contact.setRead(saveContactDTO.isRead());

        return contact;
    }
}
