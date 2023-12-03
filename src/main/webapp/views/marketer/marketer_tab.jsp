<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="inline-flex-box w-100">
    <div id="product-working-space" class="white-block w-80 center-box">
        <div id="create-marketer-btn" class="simple-btn btn-gray m-b-20px">Создать</div>
        <table id="marketers_table" class="display" style="width: 100%">
            <thead style="background-color: #9595ff; color: white">
            <tr>
                <th>Имя пользователя</th>
                <th>Активен/Заблокирован</th>
                <th>Изменить статус</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <tbody id="marketers_table-tbody">

            </tbody>
        </table>
    </div>
</div>
<jsp:include page="../common/create_account_popup.jsp"/>

<script src="../../js/datatables.js"></script>
<script src="../../js/marketers.js"></script>
<script>


    $(document).ready(() => {
        initMarketersTab();
    })
</script>