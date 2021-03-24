<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="attribute" required="true" %>
<%@ attribute name="entity" required="true" %>
<%@ attribute name="dateFormat" required="false" %>
<%@ attribute name="disabled" required="false" %>

<div class="control-group">
    <label class="control-label" for="${entity}_${attribute}"><spring:message
            code="${entity}.${attribute}.label"/></label>

    <div id="datetimepicker1" class="input-append">
        <c:choose>
            <c:when test="${disabled eq 'true'}">
                <form:input type="text"
                            data-format="${dateFormat ne null ? dateFormat : 'dd/MM/yyyy'}"
                            id="${entity}_${attribute}"
                            name="${attribute}"
                            path="${attribute}"
                            readonly="true"/>
            </c:when>
            <c:otherwise>
                <form:input type="text"
                            data-format="${dateFormat ne null ? dateFormat : 'dd/MM/yyyy'}"
                            id="${entity}_${attribute}"
                            name="${attribute}"
                            path="${attribute}"/>
            <span class="add-on">
                <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
            </span>
            </c:otherwise>
        </c:choose>
    </div>
</div>
