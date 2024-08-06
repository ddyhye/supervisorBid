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
	<div class="top">
	
	</div>
	<div class="center">
		<p>Hello world!!!</p>
	</div>
	<div class="bottom">
	
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