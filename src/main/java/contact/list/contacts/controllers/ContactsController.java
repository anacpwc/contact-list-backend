package contact.list.contacts.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import contact.list.contacts.entities.Contacts;
import contact.list.contacts.service.ContactsService;

@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/contacts")
public class ContactsController {

    @Autowired
    private ContactsService service;

    @GetMapping
    public ResponseEntity<List<Contacts>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/favorites")
    public ResponseEntity<List<Contacts>> getFavorites() {
        return ResponseEntity.ok(service.getFavorites());
    }

    @GetMapping("/category")
    public ResponseEntity<List<Contacts>> getByCategory(@RequestParam String category) {
        return ResponseEntity.ok(service.getByCategory(category));
    }

    @PostMapping
    public ResponseEntity<Contacts> save(@RequestBody Contacts contacts) {
        Contacts saved = service.save(contacts);
        URI location = URI.create("/contacts/" + saved.getId());
        return ResponseEntity.created(location).body(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contacts> update(@PathVariable Long id, @RequestBody Contacts updatedContact) {
        Contacts updated = service.update(id, updatedContact);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/emergency")
    public ResponseEntity<List<Contacts>> getEmergencyContacts() {
        return ResponseEntity.ok(service.getEmergencyContacts());
    }

}
