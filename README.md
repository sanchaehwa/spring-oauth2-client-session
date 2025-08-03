## 🛡️ 2025 [OAuth 2.0] Client Session 
> Spring Security’s OAuth2 Client capabilities to implement social login via Kakao and Google, managing authenticated user information through session-based authentication
### 진행 날짜
~ 2025.08.03

### Spring Security OAuth2 로그인 흐름
<img width="1647" height="1122" alt="LoginFlow" src="https://github.com/user-attachments/assets/7724bfff-fd2a-4c80-b5ba-20a63e0c4d49" />

---

### 실습 환경
- Java: 17  
- Spring Boot: 3.4.7  
- Spring Security  
- OAuth2 Client  
- Spring Data JPA (MySQL)  
- Lombok  
- Gradle

### 주요 구현 내용
- /oauth2/authorization/kakao, /oauth2/authorization/google 경로를 통해 소셜 로그인 시작
- 	로그인 성공 후, Spring Security의 OAuth2LoginAuthenticationFilter에서 Authorization Code를 처리하고 Access Token 요청
-  Access Token을 통해 외부 서비스(Kakao, Google)에서 사용자 정보 조회
-  인증된 사용자 정보는 Spring Security 세션에 저장되어 로그인 상태 유지
-  사용자 정보 기반으로 권한(ROLE_USER 등)을 부여하여 인가 처리
