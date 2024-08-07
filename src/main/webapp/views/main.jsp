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
    
    
    
    /*
    
    const url = "http://apis.data.go.kr/1230000/PubDataOpnStdService/getDataSetOpnStdBidPblancInfo";
    const serviceKey = "XQS5rrjNQ5LkkgbPEcLYv1jbbMAOTxTpSV63WxKtTxmM9UtNIXNCYcVaE2qDstc/PravBk2W6X1k+3BaEq6Xfw=="; // 인코딩된 서비스 키

    const params = new URLSearchParams({
        serviceKey: serviceKey,
        pageNo: "1",
        numOfRows: "10",
        type: "xml",
        bidNtceBgnDt: "202401010000",
        bidNtceEndDt: "202401312359"
    });

    fetch(`${url}?${params.toString()}`, {
        method: 'GET',
        headers: {
            'Content-type': 'application/json'
        }
    })
    .then(response => response.text()) // XML 응답을 텍스트로 받음
    .then(str => {
        const parser = new DOMParser();
        const xml = parser.parseFromString(str, "application/xml");
        return xmlToJson(xml); // XML을 JSON으로 변환
    })
    .then(data => {
        console.log(data);
    })
    .catch(error => {
        console.log('Error: ', error);
    });

    // XML을 JSON으로 변환하는 함수
    function xmlToJson(xml) {
        let obj = {}; // 변경된 부분: const를 let으로 변경
        if (xml.nodeType === 1) { // element
            if (xml.attributes.length > 0) {
                obj["@attributes"] = {};
                for (let j = 0; j < xml.attributes.length; j++) {
                    let attribute = xml.attributes.item(j); // 변경된 부분: const를 let으로 변경
                    obj["@attributes"][attribute.nodeName] = attribute.nodeValue;
                }
            }
        } else if (xml.nodeType === 3) { // text
            obj = xml.nodeValue;
        }

        // child nodes
        if (xml.hasChildNodes()) {
            for(let i = 0; i < xml.childNodes.length; i++) {
                let item = xml.childNodes.item(i); // 변경된 부분: const를 let으로 변경
                let nodeName = item.nodeName; // 변경된 부분: const를 let으로 변경
                if (typeof(obj[nodeName]) === "undefined") {
                    obj[nodeName] = xmlToJson(item);
                } else {
                    if (typeof(obj[nodeName].push) === "undefined") {
                        let old = obj[nodeName]; // 변경된 부분: const를 let으로 변경
                        obj[nodeName] = [];
                        obj[nodeName].push(old);
                    }
                    obj[nodeName].push(xmlToJson(item));
                }
            }
        }
        return obj;
    }
    
    */
</script>

</html>
