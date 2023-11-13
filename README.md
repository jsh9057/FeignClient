# FeignClient

- Feign 이란 선언적으로 사용할 수 있는 Client
- (=Feign is a declatative web service client)
- client ?
    - 외부 컴포넌트와 통신 하는데 있어서 여러 클라이언트가 있음
    - ex) rest templete

### Connection / Read Timeout

- 외부 서버와 통신시 타임아웃 설정이 가능하다.

### Feign Interceptor

- 외부로 요청이 나가기전에 공통적으로 처리해야하는 부분이 있다면
  Interceptor 를 재정의하여 처리 가능하다.
- ex) 로깅, 헤더설정

### Feign CustomLogger

- Request / Response 등 운영을 하기위한 적절한 Log를 남길 수 있다.

### Feign ErrorDecoder

- 요청에 대해 정상 응답이 아닌 경우 핸들링이 가능하다.