package my.api.project.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import my.api.project.config.ApiConfiguration;

@Controller
public class BidController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private RestTemplate restTemplate;
	
	private final String serviceKey = "XQS5rrjNQ5LkkgbPEcLYv1jbbMAOTxTpSV63WxKtTxmM9UtNIXNCYcVaE2qDstc/PravBk2W6X1k+3BaEq6Xfw==";
	
	
	@GetMapping(value="/getApiData.ajax")
	@ResponseBody
	public Map<String, JsonNode> getApiData() {
		Map<String, JsonNode> map = new HashMap<String, JsonNode>();
		
		String url = "http://apis.data.go.kr/1230000/PubDataOpnStdService/getDataSetOpnStdBidPblancInfo";
		
		UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
				.queryParam("serviceKey", serviceKey)
				.queryParam("pageNo", "1")
				.queryParam("numOfRows", "10")
				.queryParam("type", "xml")
				.queryParam("bidNtceBgnDt", "202407010000")
				.queryParam("bidNtceEndDt", "202408062359");

		try {
		ResponseEntity<String> response = restTemplate.getForEntity(uriBuilder.toUriString(), String.class);
		
			if (response.getStatusCode() == HttpStatus.OK) {
				XmlMapper xmlMapper = new XmlMapper();
				JsonNode jsonNode = xmlMapper.readTree(response.getBody().getBytes());
				
				map.put("bidList", jsonNode);
			} else {
				map.put("error", null);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return map;
	}
}
