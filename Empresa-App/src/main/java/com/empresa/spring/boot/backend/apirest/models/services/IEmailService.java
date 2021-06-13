package com.empresa.spring.boot.backend.apirest.models.services;

import com.empresa.spring.boot.backend.apirest.models.entity.Email;

public interface IEmailService {

	 public void sendEmail(Email mail);
}
