package my.api.project.service;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	// html 메일로 전송
	public Map<String, Object> sendHtmlEmail(String to, String subject, String htmlContent) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(htmlContent, true);
			helper.setFrom("my.test0721@gmail.com");
			
			mailSender.send(message);
			
			map.put("msg", "success!!");
		} catch (MessagingException e) {
			e.printStackTrace();
			map.put("msg", "fail!!");
		}
		
		return map;
	}
	

}
