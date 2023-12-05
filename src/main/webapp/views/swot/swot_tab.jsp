<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="inline-flex-box w-100">
    <div id="swot-working-space" class="white-block w-80 center-box">
        <div id="create-swot-btn" class="simple-btn btn-gray">Создать</div>
        <div style="margin: 10px 10px 10px 0; padding-bottom: 10px; display: inline-block" id="swot-container"></div>
    </div>
</div>

<jsp:include page="../common/swot_analysis_popup.jsp"/>

<script>
    $(document).ready(() => {
        initSwotTab(${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idAccount})
    })
</script>