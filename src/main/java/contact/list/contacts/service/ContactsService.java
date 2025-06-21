package contact.list.contacts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import contact.list.contacts.entities.Contacts;
import contact.list.contacts.repositories.ContactsRepository;

@Service
public class ContactsService {
    
    @Autowired
    private ContactsRepository repository;

    public List<Contacts> getAll() {
        return repository.findAll();
    }
    
    public List<Contacts> getFavorites() {
        return repository.findByIsFavoriteTrue();
    }

    public List<Contacts> getByCategory(String category) {
    return repository.findByCategory(category);
    }
    
    public Contacts save(Contacts contacts) {
        return repository.save(contacts);
    }
    
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Contacts update(Long id, Contacts updatedContact) {
        return repository.findById(id).map(existing -> {
            existing.setName(updatedContact.getName());
            existing.setNumber(updatedContact.getNumber());
            existing.setEmail(updatedContact.getEmail());
            existing.setCategory(updatedContact.getCategory());
            existing.setIsFavorite(updatedContact.getIsFavorite());
            return repository.save(existing);
        }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contato com ID " + id + " não encontrado"));
    }
}
