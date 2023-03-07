package com.hsc.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hsc.email.model.EmailRequest;
import com.hsc.email.model.EmailResponse;
import com.hsc.email.service.EmailService;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	private EmailService emailService;

	@PostMapping("/email")
	public ResponseEntity<?> email(@RequestBody EmailRequest emailRequest) {

		boolean result = this.emailService.sendEmail(emailRequest.getTo(), emailRequest.getSubject(), emailRequest.getMessage());
		if(result) {
			return ResponseEntity.ok(new EmailResponse("Email Sent successfully") );
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new EmailResponse("Email is not sent"));
		}
	}

}
