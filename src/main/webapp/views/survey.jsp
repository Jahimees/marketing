<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Маркетинговые исследования. Опрос</title>
    <link rel="stylesheet" href="../css/general.css">
    <link rel="stylesheet" href="../css/main.css">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

</head>
<body>
<div class="background-layer" style="height: auto">
    <jsp:include page="common/main_header.jsp"/>
    <div id="blank-placeholder" class="white-block w-80 center-box m-t-20px">
        <div id="blank-name-placeholder" class="blue-line"></div>
        <div id="user-info-placeholder" class="w-60 center-box">
            <label for="username-input">Введите Ваше имя</label>
            <label id="invalid-username-lbl" class="invalid-label" for="username-input">Имя должно содержать от 1 до 30 символов</label>
            <input id="username-input">
        </div>
        <hr>
        <div id="survey-blank-placeholder" class="w-60 center-box">
        </div>
        <div id="send-answer-btn" class="w-60 center-box simple-btn btn-gray">Отправить ответ</div>
    </div>
</div>

<jsp:include page="common/message_popup.jsp"/>
</body>
</html>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous"></script>
<script src="../js/blank.js"></script>
<script src="../js/survey_blank.js"></script>
<script src="../js/modal.js"></script>

<script>
    $(document).ready(() => {
        const blank = loadBlank(${idBlank})
        initBlankSurvey(blank);
    })
</script>