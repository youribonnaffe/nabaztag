<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file='head_tpl.jsp'%>
<body>
<div data-role="page">
<%@include file='header_tpl.jsp'%>
<div data-role="content">
	<form action="/ears_control" method="post">
			    <div data-role="fieldcontain">
			    <label for="left_position">Left ear:</label><input type="range" name="left_position" id="left_position" value="0" min="0" max="16" />
			    </div>
			    <div data-role="fieldcontain">
			    <label for="right_position">Right ear:</label>
			    <input type="range" name="right_position" id="right_position" value="0" min="0" max="16" />
			    </div>
		<button type="submit">Submit</button>
	</form> 
</div>
<%@include file='footer_tpl.jsp'%>
</div>
</body>
</html>
