<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file='head_tpl.jsp'%>
<body>
<div data-role="page" data-id="page">
<%@include file='header_tpl.jsp'%>
<div data-role="content">

	<c:set var="rabbit" value="${requestScope['rabbit']}" />
	<c:if test="${rabbit == null}">
			<a href="login.jsp" data-role="button" data-rel="dialog" data-transition="pop">Login</a>
	</c:if>
	<c:if test="${rabbit != null}">
		Welcome <c:out value="${rabbit.sn}"/>
	</c:if>
</div>
<%@include file='footer_tpl.jsp'%>
</div>
</body>
</html>
