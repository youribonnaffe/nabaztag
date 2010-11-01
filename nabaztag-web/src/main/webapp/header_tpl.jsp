<div data-role="header" data-position="fixed">
<c:set var="rabbit" value="${requestScope['rabbit']}" />
<c:if test="${rabbit != null}">
	<h3>Connected with <i><c:out value="${rabbit.sn}"/></i></h3>
</c:if>
<c:if test="${rabbit == null}">
	<h3>Not connected</h3>
</c:if>
<a href="/" data-role="button" data-icon="grid">Home</a>
<c:if test="${rabbit != null}">
<a href="/sign_out" data-role="button" data-icon="delete" rel="external">Sign out</a>
</c:if> 
</div>
