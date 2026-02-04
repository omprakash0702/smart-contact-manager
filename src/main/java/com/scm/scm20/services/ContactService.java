package com.scm.scm20.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.scm.scm20.entities.Contact;
import com.scm.scm20.entities.User;

public interface ContactService {

    // =========================
    // CRUD
    // =========================
    Contact save(Contact contact);

    Contact update(Contact contact);

    List<Contact> getAll();

    Contact getById(String id);

    void delete(String id);

    // =========================
    // SEARCH
    // =========================
    Page<Contact> searchByName(
            String nameKeyword,
            int size,
            int page,
            String sortBy,
            String order,
            User user
    );

    Page<Contact> searchByEmail(
            String emailKeyword,
            int size,
            int page,
            String sortBy,
            String order,
            User user
    );

    Page<Contact> searchByPhoneNumber(
            String phoneNumberKeyword,
            int size,
            int page,
            String sortBy,
            String order,
            User user
    );

    // =========================
    // USER CONTACTS
    // =========================
    List<Contact> getByUserId(String userId);

    Page<Contact> getByUser(
            User user,
            int page,
            int size,
            String sortField,
            String sortDirection
    );

    // =========================
    // DASHBOARD COUNTS (NEW)
    // =========================
    long countByUser(User user);

    long countFavoritesByUser(User user);
    // ⭐ toggle favorite
    Contact toggleFavorite(String contactId, User user);
    // ⭐ favorite contacts list
    Page<Contact> getFavoriteContacts(
            User user,
            int page,
            int size,
            String sortBy,
            String direction
    );


}
