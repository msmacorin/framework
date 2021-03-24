<%@ taglib prefix="jump" uri="http://www.macor.in/tags" %>
<%@ taglib prefix='form' uri='http://www.springframework.org/tags/form' %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="searchId" required="true" description="N�o pode ser o mesmo nome do atributo no Entity" %>
<%@ attribute name="attributeValue" required="true" %>
<%@ attribute name="attributeText" required="true" %>
<%@ attribute name="entity" required="true" %>
<%@ attribute name="entityRel" required="true" %>
<%@ attribute name="bootstrapClass" required="true" %>
<%@ attribute name="positionValue" required="true" %>
<%@ attribute name="positionText" required="true" %>
<%@ attribute name="loadMethod" required="true" %>
<%@ attribute name="fieldColumns" required="true" type="java.lang.String[]" %>


<div id="div${searchId}">
    <div class="control-group input-append">
        <label class="control-label" for="${searchId}"><spring:message code="${entity}.${searchId}.label"/></label>

        <div class="controls">

            <form:hidden id="${searchId}Value" name="${attributeValue}" path="${attributeValue}"/>

            <%--tem que colocar o campo de texto para o spring fazer o bind--%>
            <form:hidden id="${searchId}Text" name="${attributeText}" path="${attributeText}"/>

            <%--é criado outro campo para mostrar o texto para ficar mais facil de setar o name para validação do jquery--%>
            <input type="text" id="${searchId}" name="${searchId}" class="${bootstrapClass}" readonly="true" />

            <a href="#" onclick="javascript:clearSearch();" class="btn"><i class="icon-remove"></i></a>
            <a href="#modal${searchId}" class="btn" data-toggle="modal"><i class="icon-search"></i></a>

        </div>
    </div>

    <div id="modal${searchId}" class="modal hide fade" tabindex="-1" aria-hidden="true" role="dialog"
         style="width: 90%; left: 5%; margin-left: auto; margin-right: auto">
        <div class="modal-header">
            <h3><spring:message code="modal.${searchId}.legend"/></h3>
        </div>
        <div class="modal-body">
            <table cellpadding="0" cellspacing="0" border="0" class="display" id="table${searchId}">
                <thead>
                <tr>
                    <c:forEach items="${fieldColumns}" var="column">
                        <th>
                            <spring:message code="${entityRel}.${column}.label"/>
                        </th>
                    </c:forEach>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <div class="modal-footer">
            <button class="btn" data-dismiss="modal" aria-hidden="true" onclick="$('#modal${searchId}').modal('hide');">
                <spring:message code="global.action.cancelar"/></button>
            <button class="btn btn-primary" id="selecionar${searchId}" data-dismiss="modal"
                    onclick="javascript:selectedDataTableItem();"><spring:message
                    code="global.action.selecionar"/></button>
        </div>
    </div>
</div>

<script type="text/javascript" charset="utf-8">
    var oTable;

    $(document).ready(function () {
        oTable = $('#table${searchId}').dataTable({
            "oLanguage": {
                "sUrl": contexto + "/js/dataTables-pt_BR.txt"
            },
            "bProcessing": true,
            "bServerSide": true,
            "bDestroy": true,
            "bAutoWidth": false,
            "sAjaxSource": contexto + "/${entity}/${loadMethod}.do"
        });

        /* Add a click handler to the rows - this could be used as a callback */
        $("#table${searchId} tbody").click(function (event) {
            $(oTable.fnSettings().aoData).each(function () {
                $(this.nTr).removeClass('row_selected');
            });
            $(event.target.parentNode).addClass('row_selected');
        });

//        para o editar o spring carrega o campo de texto do objeto e aqui mostra o texto no search
        $('#${searchId}').val($('#${searchId}Text').val());
    });

//    seta os valores da linha selecionada no search
    function selectedDataTableItem() {
        var anSelected = fnGetSelected(oTable);
        if (anSelected) {
            $("#${searchId}Value").val(anSelected[0].cells[${positionValue}].textContent);
            $("#${searchId}").val(anSelected[0].cells[${positionText}].textContent);
            $('#modal${searchId}').modal('hide');
        }
    }

//    pega a linha que foi selecionada na table.
    function fnGetSelected(oTableLocal) {
        var aReturn = new Array();
        var aTrs = oTableLocal.fnGetNodes();
        for (var i = 0; i < aTrs.length; i++) {
            if ($(aTrs[i]).hasClass('row_selected')) {
                aReturn.push(aTrs[i]);
            }
        }
        return aReturn;
    }

//    limpa os campos
    function clearSearch() {
        $('#${searchId}Text').val(null);
        $('#${searchId}Value').val(null);
    }
</script>