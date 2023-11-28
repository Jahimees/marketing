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
<div class="background-layer" style="height: auto">
    <header class="white-block w-80 center-box">
        <div class="left-right-container">
            <div class="inline-flex-box horizontal-center" style="margin: auto 0; align-content: center">
                <a href="/">
                    <div class="simple-btn btn-gray">Главная страница</div>
                </a>
                <a href="/">
                    <div class="simple-btn btn-gray">Поиск анкет</div>
                </a>
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
        <div class="w-60 center-box">
            <div class="blue-line">Сервис маркетинговых исследований</div>
            <div class="big-text">На данном ресурсе специализированные маркетологи имеют возможность
                создавать свои собственные анкеты для проведения исследований. Для того, чтобы стать участником нашей
                программы, отправьте письмо на email: <i>someemail@m.com</i></div>
            <img src="../img/main1.jpg" style="width: 700px" class="center-box">
            <div class="big-text">Пользователи сети смогут проходить ваши анкеты в режиме онлайн! Вам больше не придётся
                часами ходить по улицам и собирать данные. Пусть потребители сами приходят к нам и проходят Ваши анкеты!
                Вся информация будет доступна в Вашем личном кабинете!
            </div>
            <div class="blue-line">
                Более 0 довольных клиентов!
            </div>
            <div class="big-text">
                Потребителям же, мы предлагаем уникальную возможность принять участие в многочисленных исследованиях без
                регистрации и совершенно бесплатно! Просто переходите во вкладку "Поиск анкет" и помогайте своим любимым
                брендам стать лучше!
            </div>
            <img src="../img/main2.gif" style="width: 700px" class="center-box">
            Права не защищены 2023 г. Курсовое проектирование студента БГУИР Антоновича Александра
        </div>
    </div>
</div>

<jsp:include page="common/message_popup.jsp"/>
</body>
</html>

<script src="../js/modal.js"></script>