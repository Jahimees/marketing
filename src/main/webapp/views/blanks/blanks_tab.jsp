<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="inline-flex-box w-100">
    <div class="white-block w-8" style="padding: 10px 0 !important;">
        <div id="create-blank-btn" class="menu-btn btn-gray">
            Создать
        </div>
        <div id="view-blanks-btn" class="menu-btn btn-gray">
            Просмотр
        </div>
        <div id="templates-btn" class="menu-btn btn-gray">
            Шаблоны
        </div>
    </div>
    <div id="blank-working-space" class="white-block w-80" style="margin-left: 0.8%">
        Рабочая область
    </div>
</div>

<link href="https://cdn.datatables.net/v/dt/dt-1.13.6/fh-3.4.0/sb-1.5.0/sp-2.2.0/datatables.min.css"
      rel="stylesheet">
<script src="https://cdn.datatables.net/v/dt/dt-1.13.6/fh-3.4.0/sb-1.5.0/sp-2.2.0/datatables.min.js"></script>

<script>
    $(document).ready(() => {
        let createTabCache;
        let viewTabCache;
        let templatesTabCache;

        $("#create-blank-btn").unbind()
        $("#create-blank-btn").on("click", function () {
            if (typeof createTabCache === "undefined") {
                $.ajax({
                    method: "get",
                    url: "/blanks/create",
                    contentType: "text/html",
                    async: false,
                    success: (data) => {
                        createTabCache = data;
                    }
                })
            }
            $("#blank-working-space").html(createTabCache)
        })

        $("#view-blanks-btn").unbind()
        $("#view-blanks-btn").on("click", function () {
            if (typeof viewTabCache === "undefined") {
                $.ajax({
                    method: "get",
                    url: "/blanks/view",
                    contentType: "text/html",
                    async: false,
                    success: (data) => {
                        viewTabCache = data;
                    }
                })
            }

            $("#blank-working-space").html(viewTabCache);
        })

        $("#templates-btn").unbind();
        $("#templates-btn").on("click", function () {
            if (typeof templatesTabCache === "undefined") {
                $.ajax({
                    method: "get",
                    url: "/blanks/templates",
                    contentType: "text/html",
                    async: false,
                    success: (data) => {
                        templatesTabCache = data;
                    }
                })
            }

            $("#blank-working-space").html(templatesTabCache);
        })
    })
</script>