<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Маркетинговые исследования</title>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/main.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="background-layer">
    <header class="white-block w-80 center-box">
        <div class="left-right-container">
            <div class="inline-flex-box horizontal-center" style="margin: auto 0; align-content: center">
                <a href="/"><div class="simple-btn btn-gray">Главная страница</div></a>
                <a href="/"><div class="simple-btn btn-gray">Поиск анкет</div></a>
            </div>
            <div>
                <a href="/account">
                    <div class="inline-flex-box">
                        <div class="big-text" style="margin-right: 10px">Личный кабинет</div>
                        <img src="../img/account.png" width="75px" style="border-radius: 100px">
                    </div>
                </a>
            </div>
        </div>
    </header>
    <div class="white-block w-80 center-box m-t-20px">
        Общая информация о ресурсе. Красиво оформлено
    </div>
</div>

<jsp:include page="common/message_popup.jsp"/>
</body>
</html>

<script src="../js/modal.js"></script>