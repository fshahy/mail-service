package com.fritz.mailserver.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SampleListener {

	@Autowired
	private JavaMailSender javaMailSender;
	
	@KafkaListener(topics = "user-registration")
	public void sendEmail(ConsumerRecord<String, String> record) {
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("ToSampleUser1@gmail.com");
        mail.setFrom("dev@server.com");
        mail.setReplyTo("no-reply@server.com");
        mail.setSubject("Testing from Spring Boot");
        mail.setText(record.value());

        javaMailSender.send(mail);
	}
	
}
