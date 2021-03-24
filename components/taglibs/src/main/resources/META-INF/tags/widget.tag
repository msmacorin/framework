<%-- 
    Document   : formcrud
    Created on : May 27, 2013, 9:12:35 PM
    Author     : macorin
--%>

<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form"    uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="jump"    uri="http://www.macor.in/tags" %>

<%@attribute name="mainicon" required="true" %>
<%@attribute name="maintitle" required="true" %>
<%@attribute name="span" required="true" %>

<%@attribute name="widgettitle" required="true" %>

<jump:main title="${maintitle}" icon="${mainicon}">
    <div class="row-fluid">
        <div class="span${span}">

            <!--inicio widget-->
            <div class="widget">
                <div class="widget-head">
                    <div class="pull-left"><spring:message code="${widgettitle}" /></div>

                    <div class="clearfix"></div>
                </div>
                <div class="widget-content">
                    <div class="padd">
                        <jsp:doBody />
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!--fim widget-->
</jump:main>