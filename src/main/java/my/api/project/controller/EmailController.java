package my.api.project.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import my.api.project.service.EmailService;

@Controller
public class EmailController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private EmailService emailService;
	
	
	@GetMapping(value="/sendMail.ajax")
	@ResponseBody
	public Map<String, Object> sendMail() {
		logger.info("이메일 전송 >> ");
		
		String to = "ddy.hye@gmail.com";
		String subject = "Mail Send Test";
		String htmlContent = "<h1>Hello World!!!</h1>";
		
		return emailService.sendHtmlEmail(to, subject, htmlContent);
	}

}
