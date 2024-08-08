<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Main Page...</title>
    <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css'/>" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" /> -->
    <link rel="icon" type="image/x-icon" href="<c:url value='/img/runcat.png'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/common.css'/>">
    <link rel="stylesheet" type="text/css" href="<c:url value='/css/main.css'/>">
</head>

<body>
	<div class="container">
	    <div class="top">
			<div class="top-left">
				<span>${loginId}</span>
			</div>
			<div class="top-right">
				<button style="margin: 5px" onclick="location.href='logout'">로그아웃</button>
			</div>
		</div>
		
		<div class="subject">
			<h3>나라장터 입찰공고 리스트</h3>
		</div>
	
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
				<button style="margin: 5px" onclick="location.href='write.go'">필터링</button>
				<button onclick="delBbs()" style="margin: 5px">삭제</button>
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
</body>

<script>

	// 리스트 불러오기
    fetch('/getApiData.ajax', {
        method: 'GET',
        headers: {
            'Content-type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
		/* var listCnt = document.getElementById('listCnt');
		while (listCnt.firstChild) {
	        listCnt.removeChild(listCnt.firstChild);
	    }
		listCnt.innerHTML = data.cnt; */
		
        drawBidList(data);
    })
    .catch(error => {console.log('Error: ', error);});
	
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
