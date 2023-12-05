<%@ page contentType="text/html;charset=UTF-8" %>

<div class="modal fade" id="productDetailsModal" tabindex="-1" aria-labelledby="productDetailsModalLabel"
     aria-hidden="true">
    <div class="modal-dialog big-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="productDetailsModalLabel">Информация о продукте</h5>
                <div class="close close-button" data-bs-dismiss="modal" aria-label="Закрыть">
                    <span aria-hidden="true">X</span>
                </div>
            </div>
            <div id="product-details-placeholder" class="modal-body">
                <div class="row m-b-20px">
                    <div class="col-4">
                        <label for="product-name-placeholder">Название</label>
                        <input id="product-name-placeholder">
                    </div>
                    <div class="col-4">
                        <label for="product-type-group">Тип</label>
                        <div id="product-type-group" style="display: table-caption">
                        </div>
                    </div>
                </div>
                <div>
                    <div class="row">
                        <div class="col-4">
                            <label for="month-input">Отчётный месяц</label>
                            <label id="month-invalid-lbl" class="invalid-label">Формат указания месяца: MM.yyyy</label>
                            <input id="month-input">
                        </div>

                        <div class="col-4">
                            <label for="sell-count-input">Количество продаж</label>
                            <label id="sell-count-invalid-lbl" class="invalid-label">
                                Должно содержать целое число или число с плавающей точкой</label>
                            <input id="sell-count-input">
                        </div>

                        <div class="col-4">
                            <label for="price-input">Средняя цена</label>
                            <label id="price-invalid-lbl" class="invalid-label" for="price-input">
                                Должно быть указано число с плавающей точкой</label>
                            <input id="price-input">
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-4">
                            <label for="production-count-input">Производство</label>
                            <label id="production-count-invalid-lbl" class="invalid-label" for="production-count-input">
                                Должно быть указано целое число или число с плавающей точкой</label>
                            <input id="production-count-input">
                        </div>

                        <div class="col-4">
                            <label for="surplus-input">Остаток</label>
                            <label id="surplus-invalid-lbl" class="invalid-label" for="surplus-input">
                                Должно быть указано целое число или число с плавающей точкой</label>
                            <input id="surplus-input">
                        </div>

                        <div class="col-3" style="margin: 25px 15px;">
                            <div id="save-product-info-btn" class="simple-btn btn-gray">Сохранить</div>
                        </div>
                    </div>
                </div>
                <table id="product_infos_table" class="display" style="width: 100%">
                    <thead style="background-color: #9595ff; color: white">
                    <tr>
                        <th>Дата заполнения</th>
                        <th>Отчётный месяц</th>
                        <th>Количество продаж</th>
                        <th>Средняя цена (у.е.)</th>
                        <th>Производство (у.е.)</th>
                        <th>Остаток (у.е.)</th>
                        <th>Удалить</th>
                    </tr>
                    </thead>
                    <tbody id="product_infos_table-tbody">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="confirm-save-swot-btn" class="btn btn-primary">
                    Создать
                </button>
                <button class="btn btn-primary" data-bs-dismiss="modal">
                    Закрыть
                </button>
            </div>
        </div>
    </div>
</div>
