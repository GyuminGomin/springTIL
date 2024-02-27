# 스프링 프레임 워크

프레임워크 - 뼈대나 근간을 이루는 코드들의 묶음(구조)

## Spring의 주요 특징

- POJO(Plain Old Java Object) 기반의 구성
    - http://www.martinfowler.com/bliki/POJO.html
    - 단순히 과거로의 회구가 아니라 EJB(Enterprise Java Bean)를 넘어서자는 의미
    - 프레임워크 등에 종속된 무거운 객체를 만들게 된 것에(EJB) 대한 반발해서 사용하게 된 개념(용어)
    - 일반적인 Java코드를 이용해 객체를 구성하는 방식을 그대로 스프링에서 사용 가능
        - 장점 : 코드를 개발할 때 개발자가 특정한 라이브러리나 컨테이너의 기술에 종속적이지 않다는 것을 의미
    - JAVA BEAN
        - 개발도구에서 가시적으로 조작이 가능하고 재사용이 가능한 소프트웨어 컴포넌트
        - 디폴트 생성자 : 자바빈 파라미터가 없는 디폴트 생성자를 갖고 있어야 함, 툴이나 프레임워크에서 리플렉션을 이용해 오브젝트를 생성하기 때문에 필요 (리플렉션 : java.reflect.class)
        - 프로퍼티 : 자바빈이 노출하는 이름을 가진 속성을 프로퍼티라 함. 프로퍼티는 set으로 시작하는 수정자 메소드(setter)와 getter르 이용해 수정 또는 조회 가능
        - 일반적으로 흔히 이야기하는 자바 빈은 모두 POJO라 볼 수 있다.
    
- 의존성 주입(DI- Dependency Injection)을 통한 객체 간의 관계 구성, IoC(Inversion of Control, 제어의 역전)
    - Ioc 제어의 역전 : 메소드나 객체의 호출작업을 개발자가 결정하는 것이 아니라, 외부에서 결정되는 것을 의미
    - 프레임워크에 제어의 권한을 넘김으로써 클라이언트 코드가 신경 써야 할 것을 줄이는 전략, 이것을 제어가 역전되었다 라고 함
    - 개발자가 필요한 기능을 구현하여 프레임워크에 '끼워 놓았다' 하면 프레임워크에서는 그 기능이 필요할 때마다 개발자가 작성한 기능을 호출하여 사용


- AOP(Aspect Oriented Programming) 지원
    - 좋은 개발환경의 중요 원칙 : 개발자는 반복적인 코드를 줄이고 핵심 비즈니스 로직에만 집중할 수 있어야 함
    - 대부분의 시스템이 공통으로 가지는 보안이나 로그, 트랜잭션과 같은 비즈니스 로직은 아니지만 반드시 처리가 필요한 부분을 횡단 관심사(Cross-concern)라고 하며, 이러한 관심사를 분리해서 관리하는 것이 가능하게 지원하는 것
    - 핵심 비즈니스 로직에만 집중해서 코드를 개발
    - 프로젝트 마다 다른 관심사를 적용할 때 코드의 수정 최소화
    - 원하는 관심사의 유지보수가 수월한 코드를 구성

- Transaction 처리
    - DB를 이용할 때 하나의 처리로 반응하게 하는 작업

- 편리한 MVC 구조

- WAS에 종속적이지 않는 개발 환경

### REST(Representational State Transfer)
- 간단한 의미로는, 웹 상의 자료를 HTTP위에서 SOAP이나 쿠키를 통한 세션 트랜잭킹 같은 별도의 전송 계층 없이 전송하기 위한 아주 간단한 인터페이스를 말한다.

- REST방식은 특정한 URI는 반드시 그에 상응하는 데이터 자체라는 것을 의미
    - 네트워크 상에서 Client와 Server 사이의 통신 방식 중 하나
    - URI는 하나의 고유한 리소스(Resource)를 대표하도록 설계된다는 개념
    - 다양한 기기에서 공통으로 데이터를 처리할 수 있는 규칙을 만들려는 시도가 REST 방식

- REST 방식으로 제공되는 외부 연결 URL를 REST API라고 하고, REST 방식의 서비스 제공이 가능한 것을 RESTful 하다 라고 표현

### REST API란?
- API는 클라이언트의 요청을 받아서 서버에게 가져다 준다.
- RESTful API
    - GET : 데이터를 조회 (Select)
    - POST : 데이터를 생성 (Create)
    - PUT : 데이터를 업데이트 할 때 (Update)
    - PATCH : PUT과 달리 Entity 데이터의 전체가 아닌 부분의 값을 업데이트 할 때 사용 (Update)
    - DELETE : 데이터를 삭제 (Delete)

### REST 설계 규칙
- 밑줄은 사용하지 않는다. (하이픈(-)을 사용)
- URI 경로에는 소문자가 적합하다.
- 행위를 포함하지 않는다. - 행위는 전송방식으로 결정
- 파일확장자는 URI에 포함하지 않는다.
- 리소스 간에는 연관 관계가 있는 경우

### 전처리 후처리

- 종류 : Filter, AOP, intercepter

- Filter
    - request
- AOP
    - httprequest
- intercepter
    - 웹 애플리케이션 내에서 특정한 URI 호출을 말 그대로 '가로채는' 역할
    - URI 요청에 따라 dispatcherServlet에 의해 호출되는 메소드
    - AOP의 Advice와 차이는 전달받는 파라미터 차이
    - business

- 전체적인 흐름
    - Filter -> intercepter -> AOP -> AOP -> intercepter -> Filter

- Filter와 interceptor의 큰 차이
    - Filter는 배포서술자에 기술(빈 사용 불가), interceptor는 빈 사용 가능
