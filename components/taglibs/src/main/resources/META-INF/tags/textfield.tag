<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="attribute" required="true" %>
<%@ attribute name="entity" required="true" %>
<%@ attribute name="bootstrapClass" required="true" %>
<%@ attribute name="disabled" required="false" %>	

<div class="control-group">
    <label class="control-label" for="${entity}_${attribute}"><spring:message code="${entity}.${attribute}.label" /></label>
    <div class="controls">

        <c:choose>
            <c:when test="${disabled eq 'true'}">
                <form:input type="text"
                            class="${bootstrapClass}"
                            id="${entity}_${attribute}"
                            name="${attribute}"
                            path="${attribute}" 												
                            readonly="true"/>
            </c:when>
            <c:otherwise>
                <form:input type="text"
                            class="${bootstrapClass}"
                            id="${entity}_${attribute}"
                            name="${attribute}"
                            path="${attribute}"/>
            </c:otherwise>
        </c:choose>
    </div>
</div>