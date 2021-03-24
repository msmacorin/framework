<%@ taglib prefix="jump"    uri="http://www.macor.in/tags" %>
<%@ taglib prefix='form'    uri='http://www.springframework.org/tags/form'%>
<%@ taglib prefix="spring"  uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"       uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="attribute"          required="true" %>
<%@ attribute name="entity"             required="true" %>
<%@ attribute name="list"               required="true" type="in.macor.commons.iface.IEnums[]" %>
<%@ attribute name="object"             required="true" type="in.macor.commons.iface.IEnums"%>
<%@ attribute name="bootstrapClass"     required="true" %>
<%@ attribute name="disabled"           required="false"%>
<%@ attribute name="nullValue"          required="false"%>

<div class="control-group">
    <label class="control-label" for="${entity}_${attribute}"><spring:message code="${entity}.${attribute}.label" /></label>
    <div class="controls">
        <form:select path="${attribute}" class="${bootstrapClass}" id="${entity}_${attribute}" name="${attribute}" readonly="${disabled eq 'true' ? true : false}">
            <c:if test="${nullValue ne 'false'}">
                <option value=""><spring:message code="global.action.selecione"/></option>
            </c:if>
            <c:forEach items="${list}" var="item">
                <option ${object eq item ? 'selected' : ''} value="${item}">
                    <spring:message code="${item.descricao}" />
                </option>
            </c:forEach>
        </form:select>
    </div>
</div>