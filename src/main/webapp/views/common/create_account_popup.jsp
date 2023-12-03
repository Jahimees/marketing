<%@ page contentType="text/html;charset=UTF-8" %>

<div class="modal fade" id="createAccountModal" tabindex="-1" aria-labelledby="createAccountModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createAccountModalLabel">Создание аккаунта маркетолога</h5>
                <div class="close close-button" data-bs-dismiss="modal" aria-label="Закрыть">
                    <span aria-hidden="true">X</span>
                </div>
            </div>
            <div class="modal-body">
                <label for="username-input">Имя пользователя</label>
                <label id="username-exists" class="invalid-label" for="username-input">Пользователь с таким именем существует</label>
                <label id="username-invalid" class="invalid-label" for="username-input">Имя пользователя должно иметь 5-30 символов</label>
                <input id="username-input">
                <label for="password-input">Пароль</label>
                <label id="password-invalid" class="invalid-label" for="password-input">Пароль должен иметь 5-100 символов</label>
                <input id="password-input" type="password">
                <label for="repeat-password-input">Повтор пароля</label>
                <label id="repeat-password-invalid" class="invalid-label" for="repeat-password-input">Пароли не совпадают</label>
                <input id="repeat-password-input" type="password">
            </div>
            <div class="modal-footer">
                <button id="confirm-create-account-btn" class="btn btn-primary">
                    Создать
                </button>
                <button class="btn btn-secondary" data-bs-dismiss="modal">
                    Отменить
                </button>
            </div>
        </div>
    </div>
</div>
