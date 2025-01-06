package com.contact_hive.contact_hive.services;

import java.util.List;

import org.springframework.data.domain.Page;
import com.contact_hive.contact_hive.entities.Contact;
import com.contact_hive.contact_hive.entities.User;

public interface ContactService {
    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    void delete(String id);

    List<Contact> search(String name, String email, String phoneNumber);

    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction);

    Page<Contact> searchByEmail(String email, int size, int page, String sortBy, String direction, User user);
    Page<Contact> searchByPhone(String phone, int size, int page, String sortBy, String direction, User user);
    Page<Contact> searchByName(String name, int size, int page, String sortBy, String direction, User user);   

}
