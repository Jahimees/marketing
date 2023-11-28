<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div class="blue-line">Создание анкеты</div>
    <div class="inline-flex-box">
        <label for="blank-name-input" class="m-r-10px">Название</label>
        <input id="blank-name-input">
        <label for="product-select" class="m-r-10px" style="white-space: nowrap">Товар/услуга</label>
        <select id="product-select">

        </select>
        <%--        TODO LOAD--%>
        <div style="margin-left: 20px">
            <div>Статус анкеты *</div>
            <div id="blank-status-radiogroup">
                <%--            <label for="development-status">В разработке</label>--%>
                <%--            <input id="development-status" value="1" name="status" type="radio" checked>--%>
                <%--            <label for="published-status">Опубликована</label>--%>
                <%--            <input id="published-status" value="2" name="status" type="radio">--%>
                <%--            <label for="closed-status">Закрыта</label>--%>
                <%--            <input id="closed-status" value="3" name="status" type="radio">--%>
            </div>
        </div>
    </div>
    <div>
    </div>
    <div id="question-container">

    </div>
    <div class="simple-btn btn-gray" id="add-question-btn">Добавить вопрос</div>

    <div id="save-blank-btn" class="simple-btn btn-gray">Сохранить</div>
</div>

<script>
    $(document).ready(() => {
        initCreateTab();
    })
</script>