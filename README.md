## PLAN
```json
{
  "language" : "java",
  "framework" : {
    "0" : "spring",
    "1" : "spring-boot",
    "2" : "spring-security"
  },
  "database" : {
    "0" : "mysql",
    "1" : "redis"
  },
  "library" : {
    "0" : "JPA",
    "1" : "queryDsl"
  },
  "test" : {
    "0" : "Junit5"
  }
}
```
### User - Service
> * 회원가입
> * 회원탈퇴
> * 회원정보 수정 
### User - Service Refactoring
> * 인터페이스 분리 
> * 트랜잭션 분리 
> * 유틸 클래스 추상화
### User Service Test Code
> * JUnit5
### User - Spring Security
> * 로그인 
> * 로그아웃
### Admin User - Service
> * 관리자 회원가입
> * 관리자 회원탈퇴
> * 관리자 정보 수정
### Admin User - Service Refactoring
> * 인터페이스 분리
> * 트랜잭션 분리
> * 유틸 클래스 추상화
### Admin User Service Test Code
> * JUnit5
### Admin User - Spring Security
> * 관리자 로그인
> * 관리자 로그아웃


