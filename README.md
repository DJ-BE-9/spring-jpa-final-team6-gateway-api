## 기술요구사항

# 회원관리

1. [o] 회원가입 시 계정정보를 입력해서 저장합니다.
   1-2. [o] 계정정보 (id, email, password)
   1-3. [o] 등록된 사용자 로그인 및 인증

# 프로젝트

1. [ ] 사용자는 Project를 생성할 수 있습니다.
   1-2. [ ] Project 관리자
   1-3. [ ] Project는 프로젝트 이름, 상태(활성, 휴면, 종료)를 가집니다

2. [ ] Project 관리자는 멤버를 등록할 수 있습니다.
   2-1. [ ] Project 멤버는 회원관리에서 가입한 회원만 가능합니다.
   2-2. [ ] Project 멤버는 자신이 속한 Project 목록만 확인할 수 있습니다.

3. [ ] Project 멤버는 Task 의 목록 및 내용을 확인 할 수 있습니다.
4. [ ] Project 멤버는 Project 에 Tag, MileStone 을 등록, 수정, 삭제 할 수 있습니다.
5. [ ] Project 에 등록한 Tag, MileStone 을 Task에 설정 할 수 있습니다.
   5-1. [ ] Task 에 1개이상의 Tag 를 설정할 수 있습니다
   5-2. [ ] Task 에 한개의 MileStone 을 설정할 수 있습니다.
   5-3. [ ] Tag, MileStone 는 프로젝트 멤버에 의해 삭제될 수 있으며 설정된 Task 는 Tag, MileStone이 제거됩니다.
6. [ ] Project 멤버는 Task 에 Comment 를 생성할 수 있습니다.
7. [ ] Comment 를 생성한 사용자는 Comment 를 수정, 삭제 할 수 있습니다.

# 기능

1. [ ] gateway 는 모든 서비스 요청을 받으며 프레젠테이션 기능을 담당합니다.
   1-2. [o] TemplateEngine(Thymeleaf) 사용하여 화면을 표시합니다.
   1-3. [o] 데이터는 AccountApi, TaskApi 를 RestTempate으로 호출하여 받아 옵니다.
   1-4. [ ] 화면정보를 표시할때 AccountApi, TaskApi 를 조합해서 제공할 수 있어야 합니다.

2. [o] gateway 는 사용자의 인증을 담당합니다.
   2-2. [o] 인증 세션은 gateway 서버에서 redis 를 사용하여 관리합니다.
   2-3. [o] 인증 데이터는 Account-Api 를 사용합니다.

3. [o] AccountApi 는 멤버의 정보를 관리합니다.
4. [ ] ProjectApi 는 Project, Task, Comment, Tag 를 관리 합니다.

# Account-api

1. [o] 인증처리는 GateWay 에 위임합니다.
2. [ ] (RestApi)회원 정보를 제공합니다.
3. [ ] (RestApi)회원의 상태(가입,탈퇴,휴면)를 관리(cud)합니다.

# Task-api

1. [ ] 인증처리는 GateWay 에 위임합니다.
2. [ ] (RestApi) Project, Task, Comment, Tag, MileStone 정보를 관리하는 API 를 제공합니다.

# 결과
1. [ ] Test 코드
2. [ ] ERD
3. [ ] 소스코드
4. [ ] REST-API 실행 요청 및 결과 문서
