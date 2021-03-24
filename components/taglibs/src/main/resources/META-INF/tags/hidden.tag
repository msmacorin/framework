<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="attribute" required="true" %>
<%@ attribute name="entity" required="true" %>

<form:input type="hidden"
            id="${entity}_${attribute}"
            name="${attribute}"
            path="${attribute}"/>