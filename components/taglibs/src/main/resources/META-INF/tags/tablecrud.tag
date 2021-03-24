<%-- 
    Document   : tablecrud
    Created on : May 30, 2013, 10:25:51 AM
    Author     : macorin
--%>

<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="jump"    uri="http://www.macor.in/tags" %>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core"%>

<%@attribute name="entity" required="true" %>
<%@attribute name="icon" required="false" %>
<%@attribute name="span" required="false" %>
<%@attribute name="overrideNew" required="false" %>

<jump:widget mainicon="${icon ne null ? icon : 'icon-tasks'}" maintitle="${entity}.page.title" widgettitle="${entity}.index.page.title" span="${span ne null ? span : 12}">
    <a class="btn btn-primary" type="button" id="buttonNew" href="${contexto}/${entity}/${overrideNew ne null ? overrideNew : 'novo.do'}">
        <spring:message code="global.action.novo" />
    </a><br><hr />

    <table class="table table-striped table-bordered table-condensed" id="tableCrud" data-controller="/${entity}">
        <thead>
            <tr>
                <c:forEach items="${camposTableCrud}" var="campo">
                    <th><spring:message code="${entity}.${campo}.label" /></th>
                </c:forEach>
                <th></th>
            </tr>
        </thead>
        <tbody></tbody>
    </table>
</jump:widget>