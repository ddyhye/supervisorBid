package my.api.project.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import my.api.project.config.ApiConfiguration;

@Controller
public class BidController {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired 
	private RestTemplate restTemplate;
	
	// 인코딩 값
	private final String serviceKey = "XQS5rrjNQ5LkkgbPEcLYv1jbbMAOTxTpSV63WxKtTxmM9UtNIXNCYcVaE2qDstc%2FPravBk2W6X1k%2B3BaEq6Xfw%3D%3D";
	// 디코딩 값
	//private final String serviceKey = "XQS5rrjNQ5LkkgbPEcLYv1jbbMAOTxTpSV63WxKtTxmM9UtNIXNCYcVaE2qDstc/PravBk2W6X1k+3BaEq6Xfw==";
	

    @GetMapping(value="/getApiData.ajax")
    @ResponseBody
    public Map<String, JsonNode> getApiData() {
        Map<String, JsonNode> map = new HashMap<>();

        String url = "http://apis.data.go.kr/1230000/PubDataOpnStdService/getDataSetOpnStdBidPblancInfo";

        // 쿼리 파라미터를 수동으로 구성
        String queryString = "?serviceKey=" + serviceKey +
                             "&pageNo=1" +
                             "&numOfRows=10" +
                             "&type=json" +
                             "&bidNtceBgnDt=202407010000" +
                             "&bidNtceEndDt=202407312359";

        String fullUrl = url + queryString;

        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
        	HttpGet request = new HttpGet(fullUrl);
        	try(CloseableHttpResponse response = httpClient.execute(request)) {
        		if (response.getStatusLine().getStatusCode() == 200) {
					String responseBody = EntityUtils.toString(response.getEntity());
					ObjectMapper objectMapper = new ObjectMapper();
	                JsonNode jsonNode = objectMapper.readTree(responseBody);
                    map.put("bidList", jsonNode);
				} else {
					map.put("error", null);
				}
        	}
        } catch (IOException e) {
        	e.printStackTrace();
        }

        return map;
    }
}
