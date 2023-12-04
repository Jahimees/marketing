<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    google.charts.load('current', {'packages':['corechart']});
    google.charts.setOnLoadCallback(drawChart);

    function drawChart() {
        var data = google.visualization.arrayToDataTable([
            ['Price', 'Sales', 'Expenses'],
            ['2004',  1000,      400],
            ['2005',  1170,      460],
            ['2006',  660,       1120],
            ['2007',  1030,      540]
        ]);

        var options = {
            title: 'Company Performance',
            curveType: 'function',
            legend: { position: 'bottom' }
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));

        chart.draw(data, options);
    }
</script>

<link rel="stylesheet" href="../../css/chartist/chartist.min.css">
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
    <div id="blank-working-space" class="white-block w-80" style="margin-left: 2%">
        Рабочая область
    </div>
    <div id="curve_chart" style="width: 900px; height: 500px"></div>

</div>

<jsp:include page="../common/blank_analytics_popup.jsp"/>

<link href="https://cdn.datatables.net/v/dt/dt-1.13.6/fh-3.4.0/sb-1.5.0/sp-2.2.0/datatables.min.css"
      rel="stylesheet">
<script src="https://cdn.datatables.net/v/dt/dt-1.13.6/fh-3.4.0/sb-1.5.0/sp-2.2.0/datatables.min.js"></script>
<script src="../../js/chartist/chartist.min.js"></script>
<script src="../../js/datatables.js"></script>
<script src="../../js/blank.js"></script>
<script src="../../js/products.js"></script>
<script>
    $(document).ready(() => {
        initBlankTab();
        $("#view-blanks-btn").click();
    })
</script>
