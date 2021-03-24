<%-- 
    Document   : main
    Created on : May 27, 2013, 9:29:44 PM
    Author     : macorin
--%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>

<%@attribute name="title" required="true"%>
<%@attribute name="icon" required="true"%>

<!--inicio mainbar-->
<div class="mainbar">
    <div class="page-head">
        <h2 class="pull-left"><i class="${icon}"></i> <spring:message code="${title}" /></h2>

        <div class="clearfix"></div>
    </div>

    <div class="matter">
        <div class="container-fluid">
            <jsp:doBody />
        </div>
    </div>
</div>
<!--fim mainbar-->