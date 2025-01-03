package com.contact_hive.contact_hive.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.contact_hive.contact_hive.entities.Contact;
import com.contact_hive.contact_hive.entities.User;
import com.contact_hive.contact_hive.forms.ContactForm;
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
    public String viewContacts(Authentication authentication, Model model) {
        String username = Helper.getEmailOfLoggedInUser(authentication);
        User user = userService.getUserByEmail(username);

        List<Contact> contacts = contactService.getByUser(user);
        model.addAttribute("contacts", contacts);
        return "user/contacts";
    }

}
