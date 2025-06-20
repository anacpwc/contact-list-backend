package contact.list.contacts.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import contact.list.contacts.entities.Contacts;
import contact.list.contacts.service.ContactsService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("contacts")
public class ContactsController {

    @Autowired
    private ContactsService service;

    @GetMapping
    public ResponseEntity<List<Contacts>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<Contacts> save(@RequestBody Contacts contacts){
        return ResponseEntity.created(null)
                             .body(service.save(contacts));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
