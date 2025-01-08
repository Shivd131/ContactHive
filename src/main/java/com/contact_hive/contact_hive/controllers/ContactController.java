package com.contact_hive.contact_hive.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.contact_hive.contact_hive.entities.Contact;
import com.contact_hive.contact_hive.entities.User;
import com.contact_hive.contact_hive.forms.ContactForm;
import com.contact_hive.contact_hive.forms.ContactSearchForm;
import com.contact_hive.contact_hive.helpers.AppConstants;
import com.contact_hive.contact_hive.helpers.Helper;
import com.contact_hive.contact_hive.helpers.Message;
import com.contact_hive.contact_hive.helpers.MessageType;
import com.contact_hive.contact_hive.services.ContactService;
import com.contact_hive.contact_hive.services.ImageService;
import com.contact_hive.contact_hive.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private UserService userService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/add")
    public String addContactView(Model model) {
        ContactForm contactForm = new ContactForm();
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    @PostMapping("/add")
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm, BindingResult result,
            Authentication authentication, HttpSession session) {

        System.out.println(contactForm);
        // process form data
        // validate data
        if (result.hasErrors()) {
            session.setAttribute("message",
                    Message.builder().content("Please correct the following errors").type(MessageType.red).build());
            return "user/add_contact";
        }
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        // image processing
        // upload image
        String fileURL = imageService.uploadImage(contactForm.getContactImage());

        System.out.println("file info: " + contactForm.getContactImage().getOriginalFilename());
        Contact contact = new Contact();
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setEmail(contactForm.getEmail());
        contact.setFavorite(contactForm.isFavorite());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setName(contactForm.getName());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setPicture(fileURL);
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        contact.setUser(user);

        contactService.save(contact);

        session.setAttribute("message",
                Message.builder().content("Contact added successfully").type(MessageType.green).build());
        return "redirect:/user/contacts/add";
    }

    @GetMapping
    public String viewContacts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Authentication authentication, Model model) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        Page<Contact> pageContact = contactService.getByUser(user, page, size, sortBy, direction);
        model.addAttribute("pageContact", pageContact);
        model.addAttribute("pageSize", AppConstants.PAGE_SIZE);
        model.addAttribute("contactSearchForm", new ContactSearchForm());
        return "user/contacts";
    }

    @GetMapping("/search")
    public String searchHandler(
            @ModelAttribute ContactSearchForm contactSearchForm,
            Model model,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size,
            @RequestParam(value = "sortBy", defaultValue = "name") String sortBy,
            @RequestParam(value = "direction", defaultValue = "asc") String direction,
            Authentication authentication

    ) {
        model.addAttribute("pageSize", size);
        var user = userService.getUserByEmail(Helper.getEmailOfLoggedInUser(authentication));
        String field = contactSearchForm.getField();
        String value = contactSearchForm.getValue();
        Page<Contact> pageContact = null;
        model.addAttribute("field", field);
        model.addAttribute("value", value);

        model.addAttribute(field, value);
        if (field != null) { // Add null check for field
            if (field.equalsIgnoreCase("name")) {
                pageContact = contactService.searchByName(value, size, page, sortBy, direction, user);
            } else if (field.equalsIgnoreCase("email")) {
                pageContact = contactService.searchByEmail(value, size, page, sortBy, direction, user);
            } else if (field.equalsIgnoreCase("phone")) {
                pageContact = contactService.searchByPhone(value, size, page, sortBy, direction, user);
            }
        }
        if (pageContact == null) {
            pageContact = Page.empty();
        }
        model.addAttribute("pageContact", pageContact);
        return "user/search";
    }

    @GetMapping("/delete/{id}")
    public String deleteContact(
            @PathVariable("id") String id) {
        contactService.delete(id);
        return "redirect:/user/contacts";
    }

    @GetMapping("/view/{contactId}")
    public String updateContactFormView(
            @PathVariable("contactId") String contactId,
            Model model) {
        var contact = contactService.getById(contactId);

        ContactForm contactForm = new ContactForm();
        contactForm.setAddress(contact.getAddress());
        contactForm.setDescription(contact.getDescription());
        contactForm.setEmail(contact.getEmail());
        contactForm.setFavorite(contact.isFavorite());
        contactForm.setLinkedInLink(contact.getLinkedInLink());
        contactForm.setName(contact.getName());
        contactForm.setPhoneNumber(contact.getPhoneNumber());
        contactForm.setWebsiteLink(contact.getWebsiteLink());
        contactForm.setPicture(contact.getPicture());
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("contactId", contactId);
        return "user/update_contact";
    }

    @PostMapping("/update/{contactId}")
    public String updateContact(
            @PathVariable("contactId") String contactId,
            @Valid @ModelAttribute ContactForm contactForm,
            BindingResult result,
            HttpSession session) {
        if (result.hasErrors()) {
            session.setAttribute("message",
                    Message.builder().content("Please correct the following errors").type(MessageType.red).build());
            return "user/update_contact";
        }
        var existingContact = contactService.getById(contactId);
        var contact = new Contact();
        contact.setId(contactId);
        contact.setPicture(existingContact.getPicture());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setEmail(contactForm.getEmail());
        contact.setFavorite(contactForm.isFavorite());
        contact.setLinkedInLink(contactForm.getLinkedInLink());
        contact.setName(contactForm.getName());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setWebsiteLink(contactForm.getWebsiteLink());

        // image processing
        // upload image
        if (contactForm.getContactImage() != null && !contactForm.getContactImage().isEmpty()) {
            String imagePublicId = imageService.uploadImage(contactForm.getContactImage());

            contact.setPicture(imagePublicId);
            contactForm.setPicture(imagePublicId);
        }

        contactService.update(contact);
        return "redirect:/user/contacts";
    }
}
