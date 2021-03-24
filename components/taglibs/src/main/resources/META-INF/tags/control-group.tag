<%@ tag language="java" pageEncoding="UTF-8"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ attribute name="entity" required="true"%>
<%@ attribute name="attribute" required="true"%>

<div class="control-group">
	<label class="control-label"><spring:message
			code="${entity}.${attribute}.label" /></label>
	<div class="controls">
		<jsp:doBody />
	</div>
</div>