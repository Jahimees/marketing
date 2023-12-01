<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Маркетинговые исследования. Поиск анкет</title>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/main.css">

    <link rel="stylesheet" href="../css/general.css">
    <link href="https://cdn.datatables.net/v/dt/dt-1.13.6/fh-3.4.0/sb-1.5.0/sp-2.2.0/datatables.min.css"
          rel="stylesheet">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="background-layer" style="height: auto">
    <jsp:include page="common/main_header.jsp"/>

    <div class="white-block w-80 center-box m-t-20px">
        <table id="blanks_table" class="display" style="width: 100%">
            <thead style="background-color: #9595ff; color: white">
            <tr>
                <th>Название</th>
                <th>Статус</th>
                <th>Дата создания</th>
                <th>Продукт</th>
                <th>Открыть</th>
            </tr>
            </thead>
            <tbody id="blanks_table-tbody">

            </tbody>
        </table>
    </div>
</div>

<jsp:include page="common/message_popup.jsp"/>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="https://cdn.datatables.net/v/dt/dt-1.13.6/fh-3.4.0/sb-1.5.0/sp-2.2.0/datatables.min.js"></script>
<script src="../js/datatables.js"></script>
<script src="../js/blank.js"></script>
<script src="../js/modal.js"></script>

<script>
    $(document).ready(() => {
        reloadBlanks();
        fillBlanksDataTable(true);
    })
</script>