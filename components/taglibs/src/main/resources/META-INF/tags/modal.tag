<%@ tag language="java" pageEncoding="UTF-8"%>

<%@attribute name="id" required="true"%>
<%@attribute name="title" required="true"%>

<div id="${id}" class="modal hide fade" tabindex="-1"
     role="dialog" aria-labelledby="${id}Label" aria-hidden="true">

    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">x</button>
        <h3 id="${id}Label">
            <spring:message code="${title}" />
        </h3>
    </div>

    <jsp:doBody />
</div>