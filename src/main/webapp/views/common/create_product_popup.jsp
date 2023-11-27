<%@ page contentType="text/html;charset=UTF-8" %>

<div class="modal fade" id="createProductModal" tabindex="-1" aria-labelledby="createProductModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createProductModalLabel">Создание продукта</h5>
                <div class="close close-button" data-bs-dismiss="modal" aria-label="Закрыть">
                    <span aria-hidden="true">X</span>
                </div>
            </div>
            <div class="modal-body">
                <label for="product-name-input">Название</label>
                <label id="name-product-invalid" class="invalid-label" for="product-name-input">Название должно иметь 1-30 символов</label>
                <input id="product-name-input">
                <label for="product-type-select">Тип</label>
                <select id="product-type-select">
                </select>
                <label for="about-product-input">О продукте</label>
                <label id="about-invalid" class="invalid-label" for="about-product-input">Описание не должно быть более 300 символов</label>
                <textarea id="about-product-input"></textarea>
            </div>
            <div class="modal-footer">
                <button id="confirm-create-product-btn" class="btn btn-primary" data-bs-dismiss="modal">
                    Создать
                </button>
                <button class="btn btn-secondary" data-bs-dismiss="modal">
                    Отменить
                </button>
            </div>
        </div>
    </div>
</div>
