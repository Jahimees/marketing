<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="view-blanks-tab" class="white-block w-80 center-box">
    <table id="blanks_table" class="display" style="width: 100%">
        <thead style="background-color: #9595ff; color: white">
        <tr>
            <th>Название</th>
            <th>Статус</th>
            <th>Дата создания</th>
            <th>Продукт</th>
            <th>Открыть</th>
            <th>Редактировать</th>
            <th>Удалить</th>
        </tr>
        </thead>
        <tbody id="blanks_table-tbody">

        </tbody>
    </table>
</div>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script>
    $(document).ready(() => {
        google.charts.load('current', {packages: ['corechart', 'line']});
        initViewTab();
    })
</script>