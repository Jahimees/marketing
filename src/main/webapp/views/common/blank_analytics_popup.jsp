<%@ page contentType="text/html;charset=UTF-8" %>

<div class="modal fade" id="blankAnalyticsModal" tabindex="-1" aria-labelledby="blankAnalyticsModalLabel"
     aria-hidden="true">
    <div class="modal-dialog big-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="blankAnalyticsModalLabel">Информация об анкете</h5>
                <div class="close close-button" data-bs-dismiss="modal" aria-label="Закрыть">
                    <span aria-hidden="true">X</span>
                </div>
            </div>
            <div class="content">
                <div style="margin-left: 15px" class="medium-text">По умолчанию, данные выводятся за весь период существования анкеты!</div>

                <div class="inline-flex-box">
                    <div id="select-box-new-clients" class="flex-box-neutral">
                        <div data-tooltip="Начальная дата" class="inline-flex-box margin-0-10px">
                            <select id="month-selector-start" class="w-150px">
                                <option value="0">Январь</option>
                                <option value="1">Февраль</option>
                                <option value="2">Март</option>
                                <option value="3">Апрель</option>
                                <option value="4">Май</option>
                                <option value="5">Июнь</option>
                                <option value="6">Июль</option>
                                <option value="7">Август</option>
                                <option value="8">Сентябрь</option>
                                <option value="9">Октябрь</option>
                                <option value="10">Ноябрь</option>
                                <option value="11">Декабрь</option>
                            </select>
                            <select id="year-selector-start" class="w-150px">
                            </select>
                        </div>
                        <div data-tooltip="Конечная дата" class="inline-flex-box margin-0-10px">
                            <select id="month-selector-end" class="w-150px">
                                <option value="0">Январь</option>
                                <option value="1">Февраль</option>
                                <option value="2">Март</option>
                                <option value="3">Апрель</option>
                                <option value="4">Май</option>
                                <option value="5">Июнь</option>
                                <option value="6">Июль</option>
                                <option value="7">Август</option>
                                <option value="8">Сентябрь</option>
                                <option value="9">Октябрь</option>
                                <option value="10">Ноябрь</option>
                                <option value="11">Декабрь</option>
                            </select>
                            <select id="year-selector-end" class="w-150px">
                            </select>
                        </div>
                    </div>
                    <div id="reload-statistics-btn" class="simple-btn btn-gray">Сбросить</div>
                </div>
            </div>
            <div id="blank-analytics-placeholder" class="modal-body">

            </div>
            <div id="supply-demand-chart-box">
                <div class="ct-chart-supply-demand">

                </div>
            </div>
            <div class="modal-footer">
                <button class="btn btn-primary" data-bs-dismiss="modal">
                    Закрыть
                </button>
            </div>
        </div>
    </div>
</div>


<script src="../../js/chartist/chartist.min.js"></script>
<script src="../../js/analytics.js"></script>
<script>
</script>