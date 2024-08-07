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
	    <div class="top-div">
			<div class="top-left-div">
				<span>${loginId}</span>
			</div>
			<div class="top-right-div">
				<button style="margin: 5px" onclick="location.href='logout'">로그아웃</button>
			</div>
		</div>
		
		<div class="subject-div">
			<h3>나라장터 입찰 공고 리스트</h3>
		</div>
	
		<div class="button-div">
			<div class="button-left-div">
				<!-- <select name="pagePerNum" style="height:30px">
					<option val="5">5</option>
					<option val="10">10</option>
					<option val="15">15</option>
					<option val="20">20</option>
				</select> -->
			</div>
			<div class="button-right-div">
				<button style="margin: 5px" onclick="location.href='write.go'">필터링</button>
				<button onclick="delBbs()" style="margin: 5px">삭제</button>
			</div>
		</div>
	
		<div class="table-div">
			<table>
				<thead>
					<tr>
						<th>No</th>
						<th>제목</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>등록일</th>
						<th><input type="checkbox" name="all" /></th>
					</tr>
				</thead>
				<tbody id="bbsList">
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
				<tbody id="page">				
				</tbody>
			</table>
		</div>
	</div>
</body>

<script>
    fetch('/getApiData.ajax', {
        method: 'GET',
        headers: {
            'Content-type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
    })
    .catch(error => {console.log('Error: ', error);});
</script>

</html>
