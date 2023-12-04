<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="inline-flex-box w-100">
    <div id="product-working-space" class="white-block w-80 center-box">
        <div id="create-product-btn" class="simple-btn btn-gray m-b-20px">Создать</div>
        <table id="products_table" class="display" style="width: 100%">
            <thead style="background-color: #9595ff; color: white">
            <tr>
                <th>Название</th>
                <th>Тип</th>
                <th>Описание</th>
                <th>Дата создания</th>
                <th>Подробнее</th>
                <th>Удалить</th>
            </tr>
            </thead>
            <tbody id="products_table-tbody">

            </tbody>
        </table>
    </div>
</div>

<jsp:include page="../common/create_product_popup.jsp"/>
<jsp:include page="../common/product_details_popup.jsp"/>

<script src="../../js/datatables.js"></script>
<script src="../../js/products.js"></script>
<script>
    $(document).ready(() => {
        loadProductTypes();
        loadProducts();
        initButtons();
        setTimeout(fillProductsTable(), 200);
    })
</script>