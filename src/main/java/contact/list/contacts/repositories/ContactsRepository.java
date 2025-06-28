package contact.list.contacts.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import contact.list.contacts.entities.Contacts;

public interface ContactsRepository extends JpaRepository<Contacts, Long> {
    List<Contacts> findByIsFavoriteTrue();

    List<Contacts> findByCategory(String category);
    
    List<Contacts> findByEmergencyTrue();
}
