<!DOCTYPE html>
<html>
<%@include file='head_tpl.jsp'%>
<body>
<div data-role="page">
	<div data-role="content">
		<form action="/" method="post">
			<div data-role="fieldcontain">
			    <label for="name">Rabbit sn :</label>
			    <input type="text" name="sn" id="sn" value=""  />
			    <label for="password">Rabbit token :</label>
			    <input type="text" name="token" id="token" value="" />
				<button type="submit">Login</button>	   
			</div>
		</form>
	</div>
</div>
</body>
</html>
