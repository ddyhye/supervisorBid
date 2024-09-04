package my.api.project.schedule;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import my.api.project.service.BidService;
import my.api.project.service.EmailService;

@EnableScheduling
@Component
public class ScheduleUtil {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private final BidService bidService;
	private final EmailService emailService;
	
	public ScheduleUtil(BidService bidService, EmailService emailService) {
		this.bidService = bidService;
		this.emailService = emailService;
	}
	
	// crontab 활용
	// 주중(월~금) 9시, 12시, 15시, 18시에 작업을 수행
	@Scheduled(cron = "0 0 9,12,15,18 * * MON-FRI")
	public void performTask() {
		Map<String, Object> map = new HashMap<String, Object>();
		bidService.saveBid(map);
		
		emailService.sendHtmlEmail();
	}

}
