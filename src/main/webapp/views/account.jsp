<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>

    <link rel="stylesheet" href="../css/general.css">
    <link href="https://cdn.datatables.net/v/dt/dt-1.13.6/fh-3.4.0/sb-1.5.0/sp-2.2.0/datatables.min.css"
          rel="stylesheet">

    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<div class="background-layer">
    <header class="white-block w-80 center-box">
        <div class="left-right-container">
            <div class="inline-flex-box horizontal-center" style="margin: auto 0; align-content: center">
                <a href="/">
                    <div class="simple-btn btn-gray">Главная страница</div>
                </a>
                <div id="blanks-btn" class="simple-btn btn-gray">Анкеты</div>
                <div id="swot-btn" class="simple-btn btn-gray">SWOT</div>
                <div id="products-btn" class="simple-btn btn-gray">Товары/Услуги</div>
            </div>
            <div>
                <a href="/account">
                    <div class="inline-flex-box">
                        <div id="username-placeholder" class="big-text" style="margin-right: 10px">Личный кабинет</div>
                        <img src="../img/account.png" width="75px" style="border-radius: 100px">
                    </div>
                </a>
            </div>
        </div>
    </header>
    <div id="content-placeholder" class="center-box m-t-20px">
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
<script src="../js/modal.js"></script>
<script>
    let authenticatedUser;

    $(document).ready(() => {
        let role = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.roleName}";
        let id = "${sessionScope.SPRING_SECURITY_CONTEXT.authentication.principal.idAccount}";

        $.ajax({
            method: "get",
            url: "/api/accounts/" + id,
            dataType: "json",
            contentType: "application/json",
            success: (data) => {
                $("#username-placeholder").text(data.username)
                authenticatedUser = data;
            }
        })

        $("#blanks-btn").unbind()
        $("#blanks-btn").on("click", function () {
            doRequest("/blanks")
        })

        $("#swot-btn").unbind();
        $("#swot-btn").on("click", function () {
            doRequest("/swot")
        })

        $("#products-btn").unbind();
        $("#products-btn").on("click", function () {
            doRequest("/products")
        })

        function doRequest(url) {
            $.ajax({
                method: "get",
                url: url,
                contentType: "text/html",
                success: (data) => {
                    $("#content-placeholder").html(data)
                }
            })
        }
    })
</script>


