# 🎤 sing-a-song 

### 1. 프로젝트 개요

외부 데이터 API를 이용하여 `노래방 선곡 리스트를 저장하는 개인화 서비스`

### 2. 프로젝트 구성

![image](https://github.com/f-lab-edu/sing-a-song/assets/121920173/146fa56d-ea07-4d26-82b5-dc817aa2ae9a)

1. Application 서버와 DB 서버는 별도의 서버로 구성
2. git `push`를 실행하였을 때 `CI`가 실행, `main branch`에 `push`될 때 Docker를 이용하여 자동 배포
3. `좋아요` 기능의 경우, `redis` 서버를 이용하여 구현
4. `nGrinder`를 이용하여 `좋아요` 기능의 `redis` 도입 이전/이후의 성능 비교

### 3. 프로젝트 목표

1.  서버 세팅부터 배포(CI/CD)까지 구축하기
2.  DB 정보 및 social 로그인에 필요한 secret 정보는 `git submodule`로 관리하기
3.  social 로그인 기능 구현
4.  스프링 배치를 이용하여 외부 데이터를 DB에 INSERT하는 JOB과 통계용 데이터를 추출하는 JOB 구현
5.  주요 API를 모아둔 `api`과 `스프링 배치`과 공통적으로 사용하는 코드를 모아둔 `common`을 각 각의 모듈로 분리하여 멀티모듈로 관리하기
6. redis 도입하기

### 4. 주요기능

1. 네이버 social 로그인 기능을 이용하여 회원가입/로그인
2. 스프링 배치를 이용하여 외부 데이터를 조회/추가하는 기능
3. `redis`를 이용한 `좋아요` 기능

### 5. DB 설계

![image](https://github.com/f-lab-edu/sing-a-song/assets/121920173/6d6ef450-1075-426d-b00c-78d9d2fcb3b0)

### 6. 화면 구성

![image](https://github.com/f-lab-edu/sing-a-song/assets/121920173/876c9763-7a70-474e-9c9a-38381e6b06e8)

### 7. 기술적 이슈와 해결 방안

1. 스프링 배치
	: `Tasklet`을 이용하는 방법과 `ItemReader/ItemWriter/ItemProcessor`을 이용하는 방법 중, dummy data 입력 중 발생할 수 있는 오류에 대해 대응하기 위해 `ItemReader/ItemWriter/ItemProcessor`을 이용하는 방법을 사용하기로 하였다. 해당 방법을 사용하여 적당한 크기의 `chunk size`를 지정하여, 배치 실행 중 오류가 발생하면 오류가 발생한 chunk의 데이터만 롤백이 된다.  
	더 자세한 내용과  어떤 `배치 Trigger`를 선택하였는지는 아래의 블로그 링크를 확인해보자.  
	<a href="https://j-jeongeun.github.io/posts/batch">chunk를 이용한 스프링 배치 만들기</a>
2. `좋아요` 기능
	: `좋아요` 기능은 다른 기능들에 비해 잦은 이벤트가 발생할 수 있는 이벤트이다. 현재 서비스에서는 사용자가 테스터 1명이기 때문에 문제가 발생하지는 않겠지만, 사용자가 늘어날수록 해당 기능에 대한 부하가 늘어날 것이라고 예상하였다. 그래서 초반에는 이벤트가 발생할 때마다 `DB INSERT/DELETE`를 실행하였지만, 부하를 줄이기 위해 `Redis`를 도입하였다. 부하테스트를 해본 결과 최대 20배 이상 성능이 좋아진 것을 확인할 수 있었다.  
	`Redis` 도입에 대한 자세한 내용과 테스트 결과는 아래의 블로그 링크를 확인해보자.  
	<a href="https://j-jeongeun.github.io/posts/redis">Redis 도입과 부하 테스트 결과</a>

### 8. 그 외 프로젝트 관련 블로그 글
<a href="https://j-jeongeun.github.io/posts/http_message">http 라이브러리</a><br>
<a href="https://j-jeongeun.github.io/posts/Paging">페이징을 처리하는 2가지 방법</a><br>
<a href="https://j-jeongeun.github.io/posts/social_login_naver">소셜 로그인1 (네이버 편)</a><br>
<a href="https://j-jeongeun.github.io/posts/social_login_kakao">소셜 로그인2 (카카오 편) + 구글</a>
