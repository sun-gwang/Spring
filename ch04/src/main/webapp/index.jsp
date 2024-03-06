<!--
    날짜 : 2024/03/05
    이름 : 김선광
    내용 : Spring MVC 실습하기

    프로젝트 생성
    - Maven archetypes : maven-archetype-webapp
    - GroupId : kr.co.ch04
    - ArtifactId : ch04

    Spring MVC 라이브러리
    - spring-context 6.1.4
    =>https://mvnrepository.com/artifact/org.springframework/spring-context/6.1.4
    - spring-webmvc 6.1.4
    =>https://mvnrepository.com/artifact/org.springframework/spring-webmvc/6.1.4
    - jakarta.servlet-api 6.0.0
    =>https://mvnrepository.com/artifact/jakarta.servlet/jakarta.servlet-api/6.0.0
    - jakarta.servlet.jsp.jstl-api 3.0.0
    =>https://mvnrepository.com/artifact/jakarta.servlet.jsp.jstl/jakarta.servlet.jsp.jstl-api/3.0.0
    - jakarta.servlet.jsp.jstl 3.0.1
    =>https://mvnrepository.com/artifact/org.glassfish.web/jakarta.servlet.jsp.jstl


    데이터베이스
    https://mvnrepository.com/artifact/org.springframework/spring-jdbc/6.1.4
    https://mvnrepository.com/artifact/com.mysql/mysql-connector-j/8.3.0
    https://mvnrepository.com/artifact/org.apache.commons/commons-dbcp2/2.11.0

    WebApplicationInitializer
    - 스프링 웹 애플리케이션 초기화를 위한 인터페이스
    - DispatcherServlet을 생성하고 컨텍스트 등록
    - 웹 애플리케이션 컨텍스트(컨테이너)를 설정하고 필요한 서블릿, 필터, 리스너 등 설정

    WebMvcConfigurer
    - 스프링 웹 MVC 구성 컴포넌트 설정을 위한 인터페이스
    - ViewResolver 설정 및 ResourceHandler 설정 등 애플리케이션 전반에 걸친 자원 설정

    @EnableWebMvc
    - 스프링 MVC를 구성하고 MVC 관련 기능을 활성화하는 어노테이션

---------------------------------------------------------------------
Tomcat 관련 설정
---------------------------------------------------------------------
톰캣 버전
- Java-17, Spring-core 6.x은 반드시 톰캣은 apache-tomcat-10.1.19으로 해야됨!!!!

톰캣 실행 시 "Address localhost:1099 is already in use" 뜨면 아래 명령 CMD 관리자 모드에서 실행 후 PC 재부팅
    - netsh int ipv4 set dynamicport tcp start=49152 num=16383
    - netsh int ipv4 set dynamicport udp start=49152 num=16383
    - 또는 그냥 1099를 1199로 변경 후 실행

톰캣 로그 한글이 깨짐 설정
VM options: -Duser.language=en -Duser.region=us

톰캣 정적 자원(html, jsp 등) 수정시 자동 리로드 설정
On 'Update' action: update classes and resources 선택(이건 Redeploy 하는게 더 나을듯 싶음...)
On frame deactivation: update classes and resources 선택

* 만약 Update classes and resources 메뉴가 없으면 현재 설정중인 WAS에 deploy 되어있는 war Artifact가 exploded 인지 확인
  exploded가 아닌 war 로만 되어 있는 경우 Update classes and resources 항목이 나타나지 않음
-->
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
    <h2>ch04.Spring MVC</h2>

    <h4>MVC 기본</h4>
    <a href="/ch04/hello">hello</a>
    <a href="/ch04/welcome">welcome</a>
    <a href="/ch04/greeting">greeting</a>

    <h4>user1 실습</h4>
    <a href="/ch04/user1/list">user1 목록</a>
    <a href="/ch04/user1/register">user1 등록</a>

    <h4>user2 실습</h4>
    <a href="/ch04/user2/list">user2 목록</a>
    <a href="/ch04/user2/register">user2 등록</a>

    <h4>user3 실습</h4>
    <a href="/ch04/user3/list">user3 목록</a>
    <a href="/ch04/user3/register">user3 등록</a>

    <h4>user4 실습</h4>
    <a href="/ch04/user4/list">user4 목록</a>
    <a href="/ch04/user4/register">user4 등록</a>

    <h4>user5 실습</h4>
    <a href="/ch04/user5/list">user5 목록</a>
    <a href="/ch04/user5/register">user5 등록</a>


</body>
</html>
