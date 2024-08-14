package my.api.project.service;

import java.util.HashMap;
import java.util.List;
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
		
		if (!htmlContent.equals("X")) {
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
		} else {
			map.put("msg", "새로운 입찰공고 없음...");
		}
		
		
		return map;
	}


	private String newBidList() {
		String msg = "";
		
		//BidInfoDTO dto = bidDao.newBid();
		List<BidInfoDTO> list = bidDao.newBidList();
		int cnt = bidDao.newBidCnt();
		
		if (list.size() == 0) {
			return "X";
		}
		
		msg += "<h3>새로운 입찰 공고가 "+cnt+"건 있습니다.</h3>";
		msg += "<br/>";
		msg += "<hr/>";
		msg += "<br/>";
		
		for (BidInfoDTO dto : list) {
			// 입찰공고명
			msg += "<h4>[ "+dto.getBidNtceNm()+" ]</h4>";
			// 금액
			String formatNum = String.format("%,d", dto.getAsignBdgtAmt());
			msg += "<p style='font-weight: bold;'>금액 >> <span>"+formatNum+" 원</span></p>";
			// 공고일시 / 입찰일시 / 입찰 마감 일시
			msg += "<p style='font-weight: bold;'>공고일자 >> <span>"+dto.getBidNtceDate()+"</span></p>";
			msg += "<p style='font-weight: bold;'>입찰기간 >> <span>"+dto.getBidBeginDate()+" ~ "+dto.getBidClseDate()+"</span></p>";
			// 공동수급여부
			String str = "";
			if (dto.getCmmnReciptMethdNm().equals("공동수급불허")) {
				str += "N";
			} else {
				str += "Y";
			}
			msg += "<p style='font-weight: bold;'>공동수급여부 >> <span>"+str+"</span></p>";
			// 링크
			msg += "<p style='font-weight: bold;'>링크 >> <span><a href="+dto.getBidNtceUrl()+">"+dto.getBidNtceNm()+"</a></span></p>";
			msg += "<br/>";
			msg += "<hr/>";
			msg += "<br/>";
		}
		
		
		return msg;
	}
	

}
