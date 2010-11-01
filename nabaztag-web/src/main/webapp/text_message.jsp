<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<!DOCTYPE html>
<html>
<%@include file='head_tpl.jsp'%>
<body>
<div data-role="page">
<%@include file='header_tpl.jsp'%>
<div data-role="content">
	<form action="/send_text_message" method="post">
		<div data-role="fieldcontain">
			<label for="textarea">Text to send:</label>
			<textarea cols="40" rows="8" name="text_to_speech" id="text_to_speech"></textarea>
		</div>
		<button type="submit">Submit</button>
	</form> 
</div>
<%@include file='footer_tpl.jsp'%>
</div>
</body>
</html>
