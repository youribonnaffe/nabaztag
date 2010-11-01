<div data-role="footer" data-inline="false" data-id="footer" data-position="fixed">
		<div data-role="navbar">
		<c:set var="rabbit" value="${requestScope['rabbit']}" />
		<c:if test="${rabbit != null}">
			<ul>
				<li><a href="text_message.jsp" id="test">Text message</a></li>
				<li><a href="ears_control.jsp">Ears control</a></li>
			</ul>
		</c:if>
		</div>
</div>