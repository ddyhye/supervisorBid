package my.api.project.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import my.api.project.dao.BidDAO;
import my.api.project.dto.BidInfoDTO;

@Service
public class BidService {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	
	@Autowired
	private BidDAO bidDao;
	
	
	
	
	// 나라장터 api 인증키 (인코딩)
	private final String serviceKey = "XQS5rrjNQ5LkkgbPEcLYv1jbbMAOTxTpSV63WxKtTxmM9UtNIXNCYcVaE2qDstc%2FPravBk2W6X1k%2B3BaEq6Xfw%3D%3D";
	

	// api 값 저장
	public Map<String, Object> saveBid(Map<String, Object> map) {
        // 요청 endpoint
        String url = "http://apis.data.go.kr/1230000/PubDataOpnStdService/getDataSetOpnStdBidPblancInfo";

        // 오늘 날짜 (End)
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        String formattedNow = now.format(dateTimeFormatter);
        
        // 1달 이전 날짜 (Start)
        LocalDateTime monthAgo = LocalDateTime.now().minus(30, ChronoUnit.DAYS);
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyyMMdd'0000'");
        String formattedMonthAgo = monthAgo.format(dateTimeFormatter2);
        
        
        // 공고 수
        int cnt = 0;
        // 요청 페이지
        int pageNo = 1;
        int numOfRows = 100;
        // 다음 페이지가 있는지?
        boolean hasNextPage = true;
        
        
        // 입찰 정보를 담을 DTO
        List<BidInfoDTO> bidList = new ArrayList<BidInfoDTO>();
        
        while(hasNextPage) {
        	// 쿼리 파라미터를 수동으로 구성
        	String queryString = "?serviceKey="+serviceKey
        			+"&pageNo="+pageNo
        			+"&numOfRows="+numOfRows
        			+"&type=json"
        			+"&bidNtceBgnDt=202408060000"
        			+"&bidNtceEndDt="+formattedNow;
        	
        	
        	String fullUrl = url + queryString;
        	
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
        						
        						if ((bidInfo.getBsnsDivNm().contains("용역")||bidInfo.getBsnsDivNm().contains("민간"))
        								&& (bidInfo.getBidNtceNm().contains("소방") || bidInfo.getBidNtceNm().contains("전기") || bidInfo.getBidNtceNm().contains("통신"))
        								&& bidInfo.getBidNtceNm().contains("감리")) {
        							insertBid(bidInfo);
        							
        							cnt++;
        							bidList.add(bidInfo);
								}
        					}
        				}
        				
        				if (itemsNode.size() == numOfRows) {
        					logger.info("페이지 번호 >> "+pageNo+"  행의 수 >> "+itemsNode.size());
        					pageNo++;
        				} else {
        					hasNextPage = false;
        				}
					} else {
						hasNextPage = false;
					}
        		}
        	} catch (IOException e) {
        		e.printStackTrace();
        		hasNextPage = false;
        	}
        }
        
        map.put("cnt", cnt);
        map.put("bidList", bidList);

		return map;
	}


	private void insertBid(BidInfoDTO bidInfo) {
		bidDao.insertBid(bidInfo);
	}


	
	
	
	
	// 입찰공고 리스트 불러오기
	public Map<String, Object> bidList(Map<String, Object> map) {
		List<BidInfoDTO> bidList = bidDao.bidList();
		int cnt = bidDao.bidListCnt();
		
		map.put("cnt", cnt);
        map.put("bidList", bidList);
		
		return map;
	}
	
	
	

}
