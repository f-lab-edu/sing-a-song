# 🎤 sing-a-song 

### 1. 프로젝트 개요
외부 데이터 API를 이용하여 노래방 선곡 리스트를 저장하는 개인화 서비스

---
### 2. Skills
<img alt="image" src="https://github.com/f-lab-edu/sing-a-song/assets/121920173/7ca3e0bd-78b3-4708-8ee9-37ff1ce0356e">

---
### 3. 기능 정의

**해당 서비스는 회원가입/로그인 된 회원만이 사용할 수 있다.**

1. 소셜 로그인을 통한 회원 가입 및 로그인 (네이버)

2. 내 선곡 리스트
    1. 선곡 리스트 생성 / 수정 / 삭제
    2. 선곡 리스트 공유하기 기능
    
    **(* 선곡 리스트 내에 brand(금영/TJ)가 다른 같은 곡은 추가 할 수 있지만, 동일 brand/노래는 중복 추가 할 수 없다.)**
   
3. 다른 사용자의 선곡 리스트
    1. 선곡 리스트 내 노래 좋아요 기능
        1. 좋아요 시, 내 목록의 동일 노래에는 모두 좋아요 표시
    2. 선곡 리스트 공유하기 기능

4. 노래방 API를 통한 곡 검색 후, 선곡 리스트에 저장 / 삭제

5. 구분별 / 좋아요 순 별 랭킹 제공
    1. 구분별 Best30 : 남/여, 10/20/30/40/50/60대 별 순
    2. 좋아요 Best30 순 : 월간/주간
---
### 4. DB 설계
![image](https://github.com/f-lab-edu/sing-a-song/assets/121920173/6d6ef450-1075-426d-b00c-78d9d2fcb3b0)

---
### 5. 화면구성
![image](https://github.com/f-lab-edu/sing-a-song/assets/121920173/876c9763-7a70-474e-9c9a-38381e6b06e8)

---
### 6. 후기
프로젝트 완료 후, update 예정

---
### 7. 프로젝트 관련 블로그 글
<a href="https://j-jeongeun.github.io/posts/social_login_naver">소셜 로그인1 (네이버 편)</a><br>
<a href="https://j-jeongeun.github.io/posts/social_login_kakao">소셜 로그인2 (카카오 편) + 구글</a><br>
<a href="https://j-jeongeun.github.io/posts/http_message">http 라이브러리</a><br>
<a href="https://j-jeongeun.github.io/posts/batch">배치 만들기</a><br>
<a href="https://j-jeongeun.github.io/posts/redis">Redis</a>
