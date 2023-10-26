package lv.spoti.services;

import lv.spoti.models.EmailDetails;

//Interface
public interface IEmailService {
	String sendSimpleMail(EmailDetails details);
}