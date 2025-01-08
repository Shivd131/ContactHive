package com.contact_hive.contact_hive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.contact_hive.contact_hive.services.EmailService;

@SpringBootTest
class ContactHiveApplicationTests {
	@Autowired
	private EmailService emailService;

	@Test
	void contextLoads() {
	}

	@Test
	void SendMailTests() {

		emailService.sendEmail("shivdeshpande131@gmail.com", "Email Email ahajhaha", "systum");

	}

}
