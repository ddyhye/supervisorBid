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
import my.api.project.dto.FilterDTO;

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
        // 이전에 가져온 날짜 가져오기
        String getLastDate = bidDao.getLastDate();
        
        // 마지막으로 가져온 날짜 업데이트
        // 테스트. 임시막음
    	bidDao.updateLastGetDate(formattedNow);
        
        logger.info("가져온 시간 ~ 현재 시간 >> "+getLastDate+" ~ "+formattedNow);
        
        
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
        			+"&bidNtceBgnDt="+getLastDate
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
        						
        						// 나라장터에서 제공하는 것이 아니라, 특정회사에서 올리는,,,, 문서 구조가 엉망인 것 찾아내기,,,
        					    // 먼저 itemNode 내부에서 "E012407751" 값이 포함되어 있는지 확인
        					    //if (itemNode.toString().contains("500kV HVDC 동해안변환소 소방설비공사")) {

        						//logger.info(itemNode.toString());
        						
        						BidInfoDTO bidInfo = objectMapper.treeToValue(itemNode, BidInfoDTO.class);
        						
        						// 건설사업관리가 아니라,, 투찰제한업종에서 필터링하자.
        						/*
        						if (
        								((bidInfo.getBsnsDivNm().contains("용역")||bidInfo.getBsnsDivNm().contains("민간"))
        									&& !bidInfo.getCntrctCnclsMthdNm().equals("수의계약")
        									&& (bidInfo.getPrtcptPsblRgnNm().equals("") || bidInfo.getPrtcptPsblRgnNm().equals("경기도"))
        								)
        								&& 
        								(bidInfo.getBidprcPsblIndstrytyNm().contains("감리")
        									|| (((bidInfo.getBidNtceNm().contains("소방") || bidInfo.getBidNtceNm().contains("전기") || bidInfo.getBidNtceNm().contains("통신")) && bidInfo.getBidNtceNm().contains("감리"))
        										|| bidInfo.getBidNtceNm().contains("건설사업관리"))
        								)
        							) {
        							insertBid(bidInfo);
        							
        							cnt++;
        							bidList.add(bidInfo);
								}
								*/
        						/*
        						if (bidInfo.getPrtcptPsblRgnNm().equals("") || bidInfo.getPrtcptPsblRgnNm().equals("경기도")) {
        							if (
        								(
        								 (bidInfo.getBsnsDivNm().contains("용역")||bidInfo.getBsnsDivNm().contains("민간"))
        										&& !bidInfo.getCntrctCnclsMthdNm().equals("수의계약")
        								)
        								&& 
        								(
        								 ((bidInfo.getBidprcPsblIndstrytyNm().contains("소방") || bidInfo.getBidprcPsblIndstrytyNm().contains("전기") || bidInfo.getBidprcPsblIndstrytyNm().contains("통신") || bidInfo.getBidprcPsblIndstrytyNm().contains("종합")) && bidInfo.getBidprcPsblIndstrytyNm().contains("감리"))
        										|| (((bidInfo.getBidNtceNm().contains("소방") || bidInfo.getBidNtceNm().contains("전기") || bidInfo.getBidNtceNm().contains("통신")) && bidInfo.getBidNtceNm().contains("감리"))
        								 )
        								)
        							) {
        								
        								String tooLong = bidInfo.getBidprcPsblIndstrytyNm();
        								if (tooLong.length() > 255) {  // 만약 길이가 255자를 초과한다면
        									tooLong = tooLong.substring(0, 255);  // 255자까지만 잘라냄
        									
        									bidInfo.setBidprcPsblIndstrytyNm(tooLong);
        								}
        								
    									insertBid(bidInfo);
    									
    									cnt++;
    									bidList.add(bidInfo);
        								
									}
        						}*/
        						
        						
        						/* 이게 정답. 근데 테스트해야해서 임시로 막아놓음*/
        						if (bidInfo.getPrtcptPsblRgnNm().equals("") || bidInfo.getPrtcptPsblRgnNm().contains("경기도")) {
        							if (bidInfo.getBsnsDivNm().contains("용역")||bidInfo.getBsnsDivNm().contains("민간")) {
        								if (!bidInfo.getCntrctCnclsMthdNm().equals("수의계약")) {
											if (((bidInfo.getBidprcPsblIndstrytyNm().contains("소방") || bidInfo.getBidprcPsblIndstrytyNm().contains("전기") || bidInfo.getBidprcPsblIndstrytyNm().contains("통신") || bidInfo.getBidprcPsblIndstrytyNm().contains("종합")) && bidInfo.getBidprcPsblIndstrytyNm().contains("감리"))
												|| ((bidInfo.getBidNtceNm().contains("소방") || bidInfo.getBidNtceNm().contains("전기") || bidInfo.getBidNtceNm().contains("통신")) && bidInfo.getBidNtceNm().contains("감리"))) {
												
												String tooLong = bidInfo.getBidprcPsblIndstrytyNm();
		        								if (tooLong.length() > 255) {  // 만약 길이가 255자를 초과한다면
		        									tooLong = tooLong.substring(0, 255);  // 255자까지만 잘라냄
		        									
		        									bidInfo.setBidprcPsblIndstrytyNm(tooLong);
		        								}
		        								
		    									insertBid(bidInfo);
		    									
		    									cnt++;
		    									bidList.add(bidInfo);
											}
										}
									}
        						}
        						
        						/*
        						if (bidInfo.getBidNtceNo().contains("E012407751")) {
        							String tooLong = bidInfo.getBidprcPsblIndstrytyNm();
    								if (tooLong.length() > 255) {  // 만약 길이가 255자를 초과한다면
    									tooLong = tooLong.substring(0, 255);  // 255자까지만 잘라냄
    									
    									bidInfo.setBidprcPsblIndstrytyNm(tooLong);
    								}
    								
									insertBid(bidInfo);
									
									cnt++;
									bidList.add(bidInfo);
								}*/
        					}
        					 
        					    
        					// 이거 삭제해야함. if문 테스트.    
        					//}
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
	// DB에 값 넣기
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


	
	// 필터링한 입찰공고 리스트 불러오기
	public Map<String, Object> filterBidList(Map<String, Object> map, FilterDTO data) {
		List<BidInfoDTO> bidList = bidDao.filterBidList(data);
		int cnt = bidDao.filterBidListCnt(data);
		
		map.put("cnt", cnt);
        map.put("bidList", bidList);
		
		return map;
	}
	
	
	

}
