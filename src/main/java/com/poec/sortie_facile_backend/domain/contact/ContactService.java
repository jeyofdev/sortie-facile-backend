package com.poec.sortie_facile_backend.domain.contact;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactService extends AbstractDomainService<Contact> {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        super(contactRepository, "contact");
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact updateById(Contact contact, Long contactId) {
        Contact newContact = findById(contactId);

        newContact.setMessage(contact.getMessage());
        newContact.setEmail(contact.getEmail());
        newContact.setTitle(contact.getTitle());
        newContact.setRead(contact.isRead());

        return contactRepository.save(newContact);
    }
}
