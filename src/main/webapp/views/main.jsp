<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Main Page...</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="icon" type="image/x-icon" href="<c:url value='/img/runcat.png'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>">
	<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
	
	<!-- 달력 라이브러리 css -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/flatpickr/dist/flatpickr.min.css">
	
</head>

<body>
	<div class="container">
		<!-- 회원 -->
	    <div class="top">
			<div class="top-left">
				<span>${loginId}</span>
			</div>
			<div class="top-right">
				<button style="margin: 5px" onclick="location.href='logout'">로그아웃</button>
			</div>
		</div>
		
		<!-- 제목 -->
		<div class="subject">
			<h3>나라장터 입찰공고 리스트</h3>
		</div>
	
		<!-- 필터링 버튼 -->
		<div class="button">
			<div class="button-left">
				<!-- <select name="pagePerNum" style="height:30px">
					<option val="5">5</option>
					<option val="10">10</option>
					<option val="15">15</option>
					<option val="20">20</option>
				</select> -->
			</div>
			<div class="button-right">
				<!-- <button style="margin: 5px" onclick="location.href='write.go'"></button> -->
				<button onclick="sendMail()" style="margin: 5px">Send</button>
			</div>
		</div>
		
		
		<!-- 상세필터링 -->
		<div class="memberM-top">
			<div class="option-top">
				<div class="option-top-head">
   					<input type="radio" name="supervisorType" class="supervisorType" value="all" checked/><label for="supervisorType1">전체</label>&nbsp;&nbsp;
					<input type="radio" name="supervisorType" class="supervisorType" value="fire"/><label for="supervisorType2">소방</label>&nbsp;&nbsp;
   					<input type="radio" name="supervisorType" class="supervisorType" value="electric"/><label for="supervisorType3">전기</label>&nbsp;&nbsp;
   					<input type="radio" name="supervisorType" class="supervisorType" value="communi"/><label for="supervisorType4">통신</label>&nbsp;&nbsp;
   				</div>
   				<div class="option-top-child">
   					<div class="memberM-top-option-skip">
	   					<p>상세검색&nbsp;</p>
	   					<i class="fa-solid fa-caret-down"></i>
	   					<i class="fa-solid fa-caret-up"></i>
	   				</div>
   				</div>
			</div>
			

   			<div class="memberM-top-option">
   				<div class="memberM-top-option-detail">
		   			<div class="memberM-top-option-detail-1">
   						<div class="memberM-top-option-detail-head">
	   						<p>&nbsp;&nbsp;키워드&nbsp;</p>
   						</div>
   						<input type="text" name="memberSearch" id="memberSearch" placeholder="공고명 검색 ..."/>
   					</div>
   					<div class="memberM-top-option-detail-1">
   						<div class="memberM-top-option-detail-head">
	   						<p>&nbsp;&nbsp;기&nbsp;&nbsp;&nbsp;간&nbsp;</p>
   						</div>
   						<div class="datePickerDiv">
	   						<input type="text" id="strDatePicker" placeholder="Select Date...">
	   						<i class="fa-regular fa-calendar"></i>
   						</div>
   						&nbsp;~&nbsp;
   						<div class="datePickerDiv">
	   						<input type="text" id="endDatePicker" placeholder="Select Date...">
	   						<i class="fa-regular fa-calendar"></i>
   						</div>
   						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   						<input type="radio" name="recently" value="all" class="memberStateOption" id="memberStateOption1"/><label for="memberStateOption1">최근 1개월</label>&nbsp;&nbsp;
   						
   					</div>

   					<div class="memberM-top-option-detail-warning">
   						<div class="memberM-top-option-detail-head">
	   						<p>&nbsp;&nbsp;제&nbsp;&nbsp;&nbsp;한&nbsp;</p>
   						</div>
   						<input type="checkbox" name="limit" id="dateLimit" value="dateLimit"/>마감공고 제외&nbsp;&nbsp;&nbsp;
   						<input type="checkbox" name="limit" id="regionLimit" value="regionLimit"/>투잘 제한 제외&nbsp;&nbsp;&nbsp;
   						<input type="checkbox" name="limit" id="togetherLimit" value="togetherLimit"/>공동협정 제외
   					</div>  
   					<div class="memberM-top-option-detail-memberState">
   						<div class="memberM-top-option-detail-head">
	   						<p>&nbsp;&nbsp;정&nbsp;&nbsp;&nbsp;렬&nbsp;</p>
   						</div>
   						<input type="radio" name="memberStateOption" value="basic" class="memberStateOption" id="memberStateOption1" checked/><label for="memberStateOption1">공고일순</label>&nbsp;&nbsp;
						<input type="radio" name="memberStateOption" value="end" class="memberStateOption" id="memberStateOption2"/><label for="memberStateOption2">마감일순</label>&nbsp;&nbsp;
   						<input type="radio" name="memberStateOption" value="high" class="memberStateOption" id="memberStateOption3"/><label for="memberStateOption3">고가순</label>&nbsp;&nbsp;
   						<input type="radio" name="memberStateOption" value="low" class="memberStateOption" id="memberStateOption4"/><label for="memberStateOption4">저가순</label>&nbsp;&nbsp;
   					</div>
   					<!-- 버튼 영역 -->
	   				<div class="option-btn">
	   					<div id="optionBtn1">검색</div>
	   					<div id="optionBtn2"><p>초기화&nbsp;</p><i class="fa-solid fa-rotate-left"></i></div>
	   				</div>
   				</div>
   			</div>
   		</div>
		
	
		<!-- <h3>입찰공고 검색 결과 <p id="listCnt"></p>건</h3>
		
		<hr/> -->
		
		
		<div id="container">
			<div class="list">
				<div class="list-left">
					<div class="list-left-1">용역</div>
					<div class="list-left-2">일반</div>
				</div>
				<div class="list-right">
					<div class="list-right-1">
						<p>[20240806728-00]</p>
						<p class="p-bold">주택건설공사 감리자(소방) 모집공고[군포대야미 B3블럭]</p>
					</div>
					<div class="list-right-2">
						<div class="list-right-2-1">
							<p class="p-bold">공고일시</p>
							<p>2024/08/07 14:58</p>
						</div>
						<div class="list-right-2-2">
							<p class="p-bold">마감일시</p>
							<p>2024/08/19 14:00</p>
						</div>
						<div class="list-right-2-3">
							<p class="p-bold">개찰일시</p>
							<p>2024/08/19 16:00</p>
						</div>
					</div>
					<div class="list-right-3">
						<div class="list-right-3-1">
							<p class="p-bold">공고기관</p>
							<p>경기도 군포시</p>
						</div>
						<div class="list-right-3-2">
							<p class="p-bold">수요기관</p>
							<p>경기도 군포시</p>
						</div>
					</div>
				</div>
			</div>
			<hr/>
		</div>
		
		<div class="table">
			<table>
				<thead>
					<!-- <tr>
						<th>No</th>
						<th>공고명</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>등록일</th>
						<th><input type="checkbox" name="all" /></th>
					</tr> -->
				</thead>
				<tbody id="bidList">
					<c:if test="${list.size() < 1}">
						<tr>
							<td colspan="7">작성된 게시글이 없습니다.</td>
						</tr>
					</c:if>
					<c:forEach items="${list}" var="item">
						<tr class="bbs_item">
							<td class="bbs_idx">${item.idx}</td>
							<td>${item.subject}</td>
							<td>${item.user_name}</td>
							<td>${item.bHit}</td>
							<td class="reg_date_toDate">${item.reg_date}</td>
							<td><input type="checkbox" name="del" class="delCheck" value="${item.idx}"/></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	
	<!-- 달력 라이브러리 -->
	<script src="https://cdn.jsdelivr.net/npm/flatpickr"></script>
</body>

<script>
	//option 펼침/닫힘
	$('.memberM-top-option-skip').on('click', function(){
		var isToggled = $(this).data('toggled');
		
		// $() 특정 요소 명시
	    var $caretDown = $(this).find('.fa-caret-down');
	    var $caretUp = $(this).find('.fa-caret-up');
	    var $details = $('.memberM-top-option-detail');
	
	    if (isToggled) {
	        $caretDown.addClass('active');
	        $caretUp.addClass('active');
	        $details.addClass('active');
	    } else {
	        $caretDown.removeClass('active');
	        $caretUp.removeClass('active');
	        $details.removeClass('active');
	    }
	    
	    $(this).data('toggled', !isToggled);
		
	});
	
	
	// 옵션 - 기간 선택
	document.addEventListener('DOMContentLoaded', function() {
		// 시작 날짜
		flatpickr('#strDatePicker', {
            dateFormat: 'Y-m-d',  
            allowInput: true
        });
		// 종료 날짜
		flatpickr('#endDatePicker', {
            dateFormat: 'Y-m-d',  
            allowInput: true
        });
	});
	
	
	// 입찰공고 데이터베이스에 저장하기
	fetch('/getApiData.ajax', {
        method: 'GET',
        headers: {
            'Content-type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {		
        drawBidList(data);
    })
    .catch(error => {console.log('Error: ', error);});
	

	// 리스트 불러오기 (필터링 X)
	/*
	fetch('/bidList.ajax', {
		method: 'GET',
		headers: {
			'Content-type': 'application/json'
		}
	})
    .then(response => response.json())
    .then(data => {
    	drawBidList(data);
    })
    .catch(error => {console.log('Error: ',error);});*/
    
    
    function sendMail() {
    	sendMail();
    }
    
 	// 메일 전송
	function sendMail() {
		fetch('/sendMail.ajax', {
			method: 'GET',
			headers: {
				'Content-type': 'application/json'
			}
		})
		.then(response => response.json())
		.then(data => {
			console.log(data.msg);
		})
		.catch(error => {console.log('Error: ',error);});
	}
    
    // 필터링하여 리스트 불러오기
	 document.getElementById('optionBtn1').addEventListener('click', function() {
		 // 필터링
		 //filterBidList();
		 
		 // 메일 전송
		 //sendMail();
	 });
	 
	function filterBidList() {
		var supervisorType = document.querySelector('input[name="supervisorType"]:checked').value;
		var keyword = document.getElementById('memberSearch').value;
		var strDate = document.getElementById('strDatePicker').value;
		var endDate = document.getElementById('endDatePicker').value;
		var dateLimit = document.getElementById('dateLimit').checked;
		var regionLimit = document.getElementById('regionLimit').checked;
		var togetherLimit = document.getElementById('togetherLimit').checked;
		var array = document.querySelector('input[name="memberStateOption"]:checked').value;
		
		if ((strDate === '' && endDate !== '') || (strDate !== '' && endDate === '')) {
			alert("시작 기간과 종료 기간 모두 설정해주세요!");
			return; 
		}
		 
		const data = {
			supervisorType: supervisorType,
			keyword: keyword,
			strDate: strDate,
			endDate: endDate,
			dateLimit: dateLimit,
			regionLimit: regionLimit,
			togetherLimit: togetherLimit,
			array: array
		}
		 
		// 서버로 필터링 값 전송
		fetch('/filterBidList.ajax', {
			method: 'POST',
			headers: {
				'Content-type': 'application/json'
			},
			body: JSON.stringify(data)
		})
		.then(response => response.json())
		.then(data => {
			drawBidList(data);
		})
		.catch(error => {console.log('Error: ', error);});
	}	
    
	
	// 리스트 그리기
	function drawBidList(data) {
		// 요소 비우기
		var container = document.getElementById('container');
		while (container.firstChild) {
			container.removeChild(container.firstChild);
		}
		
		var content = '';
		
		content += '<h3 class="row-div">입찰공고 검색 결과 <p id="listCnt">'+data.cnt+'</p>건</h3>';
		content += '<hr/>';
		
		if (!data.bidList || data.bidList.length == 0) {
			content += '<p>입찰공고 검색 결과가 없습니다...</p>';
		}
		for (item of data.bidList) {
			content += '<div class="list"><div class="list-left">';
			content += '<div class="list-left-1">'+item.bsnsDivNm+'</div>';
			content += '<div class="list-left-2">'+item.bidNtceSttusNm+'</div></div>';
			content += '<div class="list-right">';
			content += '<div class="list-right-1">';
			content += '<p>['+item.bidNtceNo+']</p>';
			content += '<p class="p-bold">'+item.bidNtceNm+'</p></div>';
			content += '<div class="list-right-2">';
			content += '<div class="list-right-2-1">';
			content += '<p class="p-bold">공고일시</p>';
			content += '<p>'+item.bidNtceDate+' '+item.bidNtceBgn+'</p></div>';
			content += '<div class="list-right-2-2">';
			content += '<p class="p-bold">마감일시</p>';
			content += '<p>'+item.bidClseDate+' '+item.bidClseTm+'</p></div>';
			content += '<div class="list-right-2-3">';
			content += '<p class="p-bold">개찰일시</p>';
			content += '<p>'+item.bidBeginDate+' '+item.bidBeginTm+'</p></div></div>';
			content += '<div class="list-right-3">';
			content += '<div class="list-right-3-1">';
			content += '<p class="p-bold">공고기관</p>';
			content += '<p>'+item.ntceInsttNm+'</p></div>';
			content += '<div class="list-right-3-2">';
			content += '<p class="p-bold">수요기관</p>';
			content += '<p>'+item.dmndInsttNm+'</p></div>';
			content += '</div>';
			content += '</div></div><hr/>';
		}
		
		container.innerHTML = content;
	}
	
	
	
	
	
</script>

</html>
