# 환율계산

##### Started
``` bash
#Run
./gradlew bootRun

#Test build 
./gradlew test
```

#### 프로젝트 구조
1. interfaces - domain - application 패키지 구조로 구성  
 - interfaces : 화면 및 클라이언트 뷰에 해당하는 로직 처리  
 - domain : 주요 도메인 로직 기능 구현  
 - application : api 통신 및 서비스로직 구현 


#### API 명세

1. 현재 환율 조회
-  요청
    - GET : /exchange?sourceCountry={value}&targetCountry={value}
- 응답(성공)
 ```json
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 01 Aug 2021 18:03:31 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
"currencyRelation": "KRW/USD",
"rate": 1151.93,
"rateExpression": "1,151.93"
}
```

2. 송금
-  요청
   - POST : /exchange/transfer
 ```json
   - Body : {  
         "source": : "USD",    
         "target": "KRW",
         "rate" : 1153.68,
         "transferMoney" : 100
   }
```

 ```json
HTTP/1.1 200
Content-Type: application/json
Transfer-Encoding: chunked
Date: Sun, 01 Aug 2021 18:03:31 GMT
Keep-Alive: timeout=60
Connection: keep-alive

{
"recipientCountry": "KRW",
"recipientRate": 1151.93,
"recipientRateExpression": "1,151.93"
}
```
