package my.api.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@GetMapping(value="/")
	public String main() {
		logger.info("입찰공고 리스트 페이지...");
		
		return "main";
	}

}
