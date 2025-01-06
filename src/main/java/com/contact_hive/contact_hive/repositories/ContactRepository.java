package com.contact_hive.contact_hive.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contact_hive.contact_hive.entities.Contact;
import com.contact_hive.contact_hive.entities.User;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    List<Contact> findByUser(User user);

    @Query("SELECT c FROM Contact c WHERE c.user.id = :userId")
    List<Contact> findByUserId(@Param("userId") String userId);

    // pagination
    Page<Contact> findByUser(User user, Pageable pageable);

    Page<Contact> findByUserAndNameContaining(User user, String name, Pageable pageable);
    Page<Contact> findByUserAndEmailContaining( User user, String email, Pageable pageable);
    Page<Contact> findByUserAndPhoneNumberContaining( User user, String phoneNumber, Pageable pageable);

}
