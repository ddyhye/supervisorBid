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

import my.api.project.dao.BidDAO;
import my.api.project.dto.BidInfoDTO;

@Service
public class EmailService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private BidDAO bidDao;
	
	
	// html 메일로 전송
	public Map<String, Object> sendHtmlEmail() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		String to = "ddy.hye@gmail.com"; 
		String subject = "새로운 입찰공고 입니다."; 
		String htmlContent = newBidList();
		
		logger.info(htmlContent);
		
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


	private String newBidList() {
		String msg = "";
		
		BidInfoDTO dto = bidDao.newBid();
		
		if (dto == null) {
			msg += "<h3>새로운 입찰 공고가 없습니다.</h3>";
		}
		
		msg += "<h3>새로운 입찰 공고가 n건 있습니다.</h3>";
		msg += "<hr/>";
		msg += "";
		// 입찰공고명
		msg += "<h4>[ "+dto.getBidNtceNm()+" ]</h4>";
		// 금액
		msg += "<p style='font-weight: bold;'>금액 >> </p><p>"+dto.getAsignBdgtAmt()+"</p>";
		// 공고일시 / 입찰일시 / 입찰 마감 일시
		msg += "<p style='font-weight: bold;'>공고일자 >> </p><p>"+dto.getBidNtceDate()+"</p>";
		msg += "<p style='font-weight: bold;'>입찰기간 >> </p><p>"+dto.getBidBeginDate()+" ~ "+dto.getBidClseDate()+"</p>";
		// 공동수급여부
		String str = "";
		if (dto.getCmmnReciptMethdNm().equals("공동수급불허")) {
			str += "N";
		} else {
			str += "Y";
		}
		msg += "<p style='font-weight: bold;'>공동수급여부 >> </p><p>"+str+"</p>";
		// 링크
		msg += "<p style='font-weight: bold;'>링크 >> </p><a href="+dto.getBidNtceUrl()+">"+dto.getBidNtceNm()+"</a>";
		
		return msg;
	}
	

}
