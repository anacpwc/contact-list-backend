package contact.list.contacts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import contact.list.contacts.entities.Contacts;
import contact.list.contacts.repositories.ContactsRepository;

@Service
public class ContactsService {
    
    @Autowired
    private ContactsRepository repository;

    public List<Contacts> getAll() {
        return repository.findAll();
    }
    
    public Contacts save(Contacts contacts) {
        return repository.save(contacts);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
