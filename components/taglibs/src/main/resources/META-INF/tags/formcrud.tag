<%-- 
    Document   : formcrud
    Created on : May 29, 2013, 1:04:43 PM
    Author     : macorin
--%>

<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jump"    uri="http://www.macor.in/tags" %>

<%@attribute name="mapping" required="true" %>
<%@attribute name="icon" required="false" %>
<%@attribute name="span" required="false" %>
<%@attribute name="title" required="false" %>

<jump:widget mainicon="${icon ne null ? icon : 'icon-tasks'}" maintitle="${mapping}.page.title" widgettitle="${mapping}.crud.page.title" span="${span ne null ? span : 12}">
    <form:form class="form" action="${contexto}/${mapping}/salvar.do" method="post" id="formCrud" modelAttribute="model">
        <jsp:doBody />

        <div class="widget-content">
            <div class="padd">
                <hr />
                <div class="btn-group pull-right">
                    <button type="submit" class="btn btn-primary"><spring:message code="global.action.salvar" /></button>
                    <a href="${contexto}/${mapping}/index.do" class="btn"><spring:message code="global.action.cancelar" /></a>
                </div>
            </div>
        </div>
    </form:form>
</jump:widget>