package my.api.project.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import my.api.project.config.ApiConfiguration;
import my.api.project.dto.BidInfoDTO;
import my.api.project.dto.FilterDTO;
import my.api.project.service.BidService;

@Controller
public class BidController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private BidService bidService;
	
	// 인코딩 값
	//private final String serviceKey = "XQS5rrjNQ5LkkgbPEcLYv1jbbMAOTxTpSV63WxKtTxmM9UtNIXNCYcVaE2qDstc%2FPravBk2W6X1k%2B3BaEq6Xfw%3D%3D";
	// 디코딩 값
	//private final String serviceKey = "XQS5rrjNQ5LkkgbPEcLYv1jbbMAOTxTpSV63WxKtTxmM9UtNIXNCYcVaE2qDstc/PravBk2W6X1k+3BaEq6Xfw==";
	

	
	// api 값을 디비에 저장
    @GetMapping(value="/getApiData.ajax")
    @ResponseBody
    public Map<String, Object> getApiData() {
        Map<String, Object> map = new HashMap<String, Object>();

        return bidService.saveBid(map);
    }
    
    
    
    
    
    
    // 리스트 불러오기
    @GetMapping(value="bidList.ajax")
    @ResponseBody
    public Map<String, Object> bidList() {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	return bidService.bidList(map);
    }
    
    
    
    
    
    
    // 필터링
    @PostMapping(value="/filterBidList.ajax")
    @ResponseBody
    public Map<String, Object> filterBidList(@RequestBody FilterDTO data) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	
    	logger.info("필터링 정보 >> {}",data);
    	logger.info(data.getSupervisorType());
    	logger.info(data.getArray());
    	
    	return bidService.filterBidList(map, data);
    }
    
}
