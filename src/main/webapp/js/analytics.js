{
    // google.charts.setOnLoadCallback(drawBackgroundColor);

    function drawBackgroundColor(demandArray, supplyArray) {
        console.log(demandArray, supplyArray)
        var data = new google.visualization.DataTable();
        data.addColumn('number', 'X');
        data.addColumn('number', 'Спрос');
        data.addColumn('number', 'Предложение');

        data.addRows(
            demandArray
        );

        var options = {
            hAxis: {
                title: 'Количество товара',
                // visible: true,
                // viewWindowMode: 'pretty',
                format: 'long'
            },
            vAxis: {
                title: 'Цена',
                format: 'long',
                // gridlines: {count: 4}
                // gridlines: {
                //     interval: 10
                // },
                // showTextEvery: 1,
                // format: 'long'

            },
            orientation: 'vertical',
            width: 800,
            height: 500,
            // chartArea: {
            // },
            backgroundColor: '#f1f8e9'
        };

        var chart = new google.visualization.LineChart(document.getElementById('curve_chart'));
        chart.draw(data, options);
    }

    function initDateSelectors(idBlank) {
        initSelectors();
        initSelectListeners(idBlank);
    }

    function redrawChart(idField, data, currentBlank) {
        let labels = [];
        let series = []
        data.forEach((value, key) => {
            labels.push(key);
            series.push(value)
        })

        let organizedData = {
            series: series
        }

        var sum = function (a, b) {
            return a + b
        };

        new Chartist.Pie('.ct-chart-' + idField, organizedData, {
            width: typeof customWidth !== "undefined" ? customWidth : 400,
            height: 300,
            labelInterpolationFnc: function (value, indx) {
                return indx + 1 + ": " + Math.round(value / organizedData.series.reduce(sum) * 100) + '%';
            }
        });

        let $ctChart = $(".ct-chart-" + idField);

        let $ol = $("<ol></ol>")
        labels.forEach(label => {
            const fieldVariant = findFieldVariantById(currentBlank, label);
            $ol.append("<li>" + fieldVariant.text + ": " + series[labels.indexOf(label)] + "</li>")
        })
        $ctChart.after($ol)

    }

    function initSelectListeners(idBlank) {
        let chartNames = [];
        chartNames.push("start");
        chartNames.push("end");

        chartNames.forEach(cn => {
            $("#year-selector-" + cn).unbind()
            $("#year-selector-" + cn).on("change", () => {
                reloadBlankStatistics(idBlank)
            })

            $("#month-selector-" + cn).unbind()
            $("#month-selector-" + cn).on("change", () => {
                reloadBlankStatistics(idBlank)
            })
        })
    }

    function reloadBlankStatistics(idBlank) {
        const blanks = getBlanksCache();

        let currentBlank
        blanks.forEach(blank => {
            if (idBlank === blank.idBlank) {
                currentBlank = blank;
            }
        })

        const startYear = Number($("#year-selector-start").val());
        const endYear = Number($("#year-selector-end").val());
        const startMonth = Number($("#month-selector-start").val());
        const endMonth = Number($("#month-selector-end").val());

        const startDate = new Date(startYear, startMonth, 1)
        const endDate = new Date(endYear, endMonth,
            new Date(endYear, endMonth, 1).daysInMonth());

        calculateStatistics(currentBlank, startDate, endDate)
    }

    Date.prototype.daysInMonth = () => {
        const date = new Date();
        return 32 - new Date(date.getFullYear(), date.getMonth(), 32).getDate();
    };

    function initSelectors() {
        let chartNames = [];
        chartNames.push("start");
        chartNames.push("end");

        chartNames.forEach(ch => {
            $("#month-selector-" + ch).val(new Date().getMonth())
            $("#year-selector-" + ch).html('');
            for (let i = -5; i < 5; i++) {
                const year = new Date().getFullYear() + i;
                if (i === 0) {
                    $("#year-selector-" + ch).append($("<option value='" + year + "' selected>"
                        + year + "</option>"))
                } else {
                    $("#year-selector-" + ch).append($("<option value='" + year + "'>"
                        + year + "</option>"))
                }
            }
        })
    }

    function getOptions(customWidth, data) {
        return {
            width: typeof customWidth !== "undefined" ? customWidth : 600,
            height: 300,
            // axisY: {
            //     type: Chartist.AutoScaleAxis,
            //     onlyInteger: true,
            //     low: 0
            // },
            lineSmooth: false,
            labelInterpolationFnc: function (value) {
                // return value[0]
                return value + ": " + (Math.round(value / data.series.reduce(sum) * 100)) + '%'
            }
        };
    }

    function getResponsiveOptions() {
        var responsiveOptions = [
            ['screen and (min-width: 640px)', {
                chartPadding: 30,
                labelOffset: 100,
                labelDirection: 'explode',
                // labelInterpolationFnc: function(value) {
                //     return value;
                // }
            }],
            ['screen and (min-width: 1024px)', {
                labelOffset: 80,
                chartPadding: 20
            }]
        ];

        return responsiveOptions
    }
}