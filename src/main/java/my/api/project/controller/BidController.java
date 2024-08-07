package my.api.project.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import my.api.project.config.ApiConfiguration;
import my.api.project.dto.BidInfoDTO;

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
    public Map<String, Object> getApiData() {
        Map<String, Object> map = new HashMap<String, Object>();

        String url = "http://apis.data.go.kr/1230000/PubDataOpnStdService/getDataSetOpnStdBidPblancInfo";

        // 쿼리 파라미터를 수동으로 구성
        String queryString = "?serviceKey=" + serviceKey +
                             "&pageNo=1" +
                             "&numOfRows=10" +
                             "&type=json" +
                             "&bidNtceBgnDt=202407010000" +
                             "&bidNtceEndDt=202407312359";

        String fullUrl = url + queryString;
        
        // 입찰 정보를 담을 DTO
        List<BidInfoDTO> bidList = new ArrayList<BidInfoDTO>();

        // CloseableHttpClient 를 생성(객체)하여 HTTP 요청 보낼 준비 
        try(CloseableHttpClient httpClient = HttpClients.createDefault()) {
        	//HTTP GET 요청 생성)
        	HttpGet request = new HttpGet(fullUrl);
        	
        	// 생성된 HttpGet 요청을 httpClient 객체를 사용하여 실행 --> CloseableHttpResponse 객체로 받기
        	try(CloseableHttpResponse response = httpClient.execute(request)) {
        		// 응답 http 상태 코드, 200(==OK)
        		if (response.getStatusLine().getStatusCode() == 200) {
        			// 응답을 문자열로 변환하여 저장
					String responseBody = EntityUtils.toString(response.getEntity());
					// JSON 파싱을 위한 ObjectMapper
					ObjectMapper objectMapper = new ObjectMapper();
					
					// JSON 문자열을 파싱하여 JSON 트리 구조의 최상위 루트를 나타내는 JsonNode 객체를 반환
					JsonNode rootNode = objectMapper.readTree(responseBody);
					// rootNode에서 하위 노드를 찾음.
	                JsonNode itemsNode = rootNode.path("response").path("body").path("items");
	                // 하위 노드가 배열인지 확인
	                if (itemsNode.isArray()) {
	                    // 각 요소를 순회
	                	for (JsonNode itemNode : itemsNode) {
	                		BidInfoDTO bidInfo = objectMapper.treeToValue(itemNode, BidInfoDTO.class);
	                        bidList.add(bidInfo);
	                    }
	                }
	                map.put("bidList", bidList);
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
