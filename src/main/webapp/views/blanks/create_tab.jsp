<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <div class="blue-line">Создание анкеты</div>
    <div class="inline-flex-box">
        <label for="blank-name-input" class="m-r-10px">Название</label>
        <input id="blank-name-input">
        <label for="product-select" class="m-r-10px" style="white-space: nowrap">Товар/услуга</label>
        <select id="product-select">

        </select>
        <div style="margin-left: 20px">
            <div>Статус анкеты *</div>
            <div id="blank-status-radiogroup">
            </div>
        </div>
    </div>
    <div>
    </div>
    <div id="question-container">

    </div>
    <div class="simple-btn btn-gray inline-block" id="add-question-btn">Добавить вопрос</div>

    <div id="save-blank-btn" class="simple-btn btn-gray inline-block">Сохранить</div>
</div>
