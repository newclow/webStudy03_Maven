<%@page import="java.util.Arrays"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>12/jstlDesc.jsp</title>
</head>
<body>
<h4> JSTL (Jsp Standard Tag Library) </h4>
<pre>
   : 커스텀 태그들을 모아놓은 라이브러리 형태.
     커스텀 태그의 사용방법 : &lt;prefix:tagName attributes... /&gt;
     ** JSTL 사용을 위해 먼저, taglib 로딩
     
     1. Core
          1) EL 변수(속성) 지원 : set, remove
                &lt;c:set var="속성명" value="속성값" scope="영역"/&gt;  <!-- set은 속성을 설정해줄때 사용. -->
                &lt;c:remove var="속성명" scope="영역"/&gt;  <!-- remove는 속성을 삭제할때 사용 , 속성 삭제시 영역을 명시할 것-->
               <c:set var="testAttr" value="테스트" scope="request"></c:set>
               ${requestScope.testAttr }
               <c:remove var="testAttr" scope="session"/> <!-- 지울 영역을 설정해줘야 다른 영역은 건드리지 않고 삭제할 수 있다. -->
               ${requestScope.testAttr }
          2) 제어문
               - 조건문: if(조건식){ 조건 블럭 }
                  <c:if test="${empty requestScope.testAttr }">
                      testAttr이 지워짐
                  </c:if>
                  <c:if test="${not empty requestScope.testAttr }">
                      testAttr이 지워지지 않았음
                  </c:if>
               - 다중조건문: choose when(조건) otherwise
                  <c:choose>
                     <c:when test="${empty requestScope.testAttr }">
                         testAttr이 지워짐
                     </c:when>
                     <c:otherwise>
                        testAttr이 지워지지 않았음
                     </c:otherwise>
                  </c:choose>
               
               - 반복문: forEach forTokens
                     ** LoopTagStatus 객체 : begin, end, step, index, count, first(boolean), last(boolean)
                     for(선언절(var, begin);조건절(end);증감절(step)){ },for(블럭 변수 선언: 반복 대상 집합 객체){}
<%--                      <c:forEach var="idx" begin="1" end="10" step="2" varStatus="lts"> <!-- pageScope의 속성을 정한것. --> --%>
<!--                         변수 선언은 var, 초기값은 1, 끝은 10 , 증감절은 1 -->
<%--                         <c:choose> --%>
<%--                            <c:when test="${lts.first }"> --%>
<%--                               <span style="color: blue;">${idx }</span> --%>
<%--                            </c:when> --%>
<%--                            <c:when test="${lts.count eq 3}"> --%>
<%--                               <span style="color: red;">${idx }</span> --%>
<%--                            </c:when> --%>
<%--                            <c:when test="${lts.last }"> --%>
<%--                               <span style="color: green;">${idx }</span> --%>
<%--                            </c:when> --%>
<%--                            <c:otherwise> --%>
<%--                               <span>${idx }</span> --%>
<%--                            </c:otherwise> --%>
<%--                         </c:choose> --%>
<%--                      </c:forEach> --%>
               for(블럭 변수 선언(var) : 반복 대상 집합 객체(items)){}
               <c:set var="sampleList" value='<%=Arrays.asList("listvalue1", "listvalue2") %>'></c:set>
               <c:forEach items="${sampleList}" var="element" varStatus="lts"> <!-- element라는 이름의 속성이 만들어진것이다. -->
                  ${element  }
               </c:forEach>
               forTokens : ** token 토큰들에 대해서 반복을 수행하기 위해 사용하는것.
                        문장(items), 구분자(delims), 토큰에 대한 레퍼런스 속성(var) 
               <c:forTokens items="1,2,3,4,5" delims="," var="token" varStatus="vs">
                  <c:if test="${vs.first }">
                     <span style="color:red;">${token * 1000}</span>
                  </c:if>
                  <c:if test="${not vs.first }">
                     ${token * 1000}
                  </c:if>
               </c:forTokens> 
               ex)int i = 4;
                  inti=4;
                  select * from member;
                  select*frommember;
                  
                  
                  
                  
          3) URL 재처리(Rewrite) : 서버사이드 경로를 클라이언트사이드 경로로 바꿔준다.
                - 클라이언트 방식의 절대 경로, 쿼리스트링, url rewriting 처리
             <c:url value="/member/memberView.do" var="viewURL">
                <c:param name="who" value="a001"></c:param>
             </c:url> 
             ${viewURL }
          4) 기타 기능 : redirect, import, out      
             <%-- <c:redirect url="/member/memberList.do" context="/webStudy01"></c:redirect> --%>
             <!-- 서버사이드 방식으로 경로를 기술해주면 자기가 알아서 contextPath를 붙일지 말지 결정한다. 또한 다른어플리케이션으로 이동도 가능-->
<%--              <c:import url="https://www.naver.com" var="naver"></c:import> <!-- var="naver"을 입력해주면 자동으로 뜨던게 안뜨므로 el로 따로 호출해줘야댐 --> --%>
<%--              <c:out value="${naver }" escapeXml="false"></c:out> escapeXml="false"로 두면 페이지소스보기를 할때 excape하지않은상태로 출력된다.  --%>
     2. Fmt
     3. Fn 라이브러리
     <c:set var="target" value="ABC123DEF"/>
     <c:set var="search" value="123"/>
     <c:set var=targetArray value='<%=new String[]{"ab", "cd", "ef"} %>' />
     ${fn:substringAfter(target, search) }
     ${fn:substringBefore(target, search) }
     ${fn:join(targetArray, "|") }
     ${fn:containsIgnoreCase(target, "abc") }
</pre>
</body>
</html>

