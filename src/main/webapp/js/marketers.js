{

    let accountsCache;

    function initMarketersTab() {
        loadAccounts();
        initCreateMarketerButton();

        setTimeout(fillAccountsTable(), 100);
    }

    function initCreateMarketerButton() {
        $("#create-marketer-btn").unbind();
        $("#create-marketer-btn").on("click", function () {
            $("#createAccountModal").modal('show');

            $("#confirm-create-account-btn").unbind();
            $("#confirm-create-account-btn").on("click", function () {
                validateAndCreateAccount();
            })
        })
    }

    function validateAndCreateAccount() {
        const usernameText = $("#username-input").val();
        const passwordText = $("#password-input").val();
        const repeatPasswordText = $("#repeat-password-input").val();

        let isValid = true;
        if (usernameText.trim().length < 5 || usernameText.trim().length > 30) {
            isValid = false;
            $("#username-invalid").show();
        } else {
            $("#username-invalid").hide();
        }

        if (passwordText.trim().length < 5 || passwordText.trim() > 100) {
            isValid = false;
            $("#password-invalid").show();
        } else {
            $("#password-invalid").hide();
        }

        if (passwordText !== repeatPasswordText) {
            isValid = false;
            $("#repeat-password-invalid").show();
        } else {
            $("#repeat-password-invalid").hide();
        }

        if (isValid) {
            if (!isExistsByUsername(usernameText)) {


                const newAccount = {
                    username: usernameText,
                    password: passwordText
                }
                $("#createAccountModal").modal('hide')
                $.ajax({
                    method: "post",
                    url: "/api/accounts",
                    dataType: "json",
                    contentType: "application/json",
                    data: JSON.stringify(newAccount),
                    async: false,
                    success: (data) => {
                        reloadAccounts();
                        fillAccountsTable();
                        callMessagePopup("Аккаунт создан", "Аккаунт маркетолога успешно создан!")
                    },
                    error: () => {
                        callMessagePopup("Ошибка", "Не удалось создать аккаунт")
                    }
                })
            }

        }
    }

    function isExistsByUsername(username) {
        let flag = false;
        $.ajax({
            method: "get",
            url: "/api/accounts/checkby?username=" + username,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: (data) => {
                if (data) {
                    flag = data;
                    $("#username-exists").show();
                }
            },
            error: () => {
                callMessagePopup("Ошибка", "Что-то пошло не так")
            }
        })

        return flag;
    }

    function loadAccounts() {
        if (typeof accountsCache === "undefined") {
            reloadAccounts();
        }

        return accountsCache;
    }

    function reloadAccounts() {
        $.ajax({
            method: "get",
            url: "/api/accounts?idRole=2",
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: (data) => {
                accountsCache = data;
            },
            error: () => {
                callMessagePopup("Ошибка", "Не удалось загрузить список пользователей")
            }
        })

        return accountsCache;
    }

    function deleteAccountFromCache(idAccount) {
        let index;
        accountsCache.forEach(account => {
            if (account.idAccount = idAccount) {
                index = accountsCache.indexOf(account);
            }
        })

        accountsCache.splice(index, 1);
    }

    function fillAccountsTable() {
        const tableName = "marketers"
        const $dataTable = $("#" + tableName + "_table");

        destroyAndInitDataTable(tableName, $dataTable)

        console.log(accountsCache)
        accountsCache?.forEach(function (account) {
            addRowToMarketersDataTable(account, tableName)
        })
    }

    function addRowToMarketersDataTable(account, tableId) {
        const tableName = tableId ? tableId : "default";

        console.log(account);
        let text = account.accountStatus.name === "Активен" ? "Заблокировать" : "Разблокировать"

        $("#" + tableName + "_table").DataTable().row.add([
            account.username,
            account.accountStatus.name,
            "<div onclick='changeAccountStatus(" + account.idAccount + ")' class='simple-btn btn-gray' onclick=''>" + text + "</div>",
            "<div onclick='callConfirmDeleteAccount(" + account.idAccount + ")' class='simple-btn btn-gray' onclick=''>Удалить</div>",
        ]).draw();
    }

    function changeAccountStatus(idAccount) {
        $.ajax({
            method: "put",
            url: "/api/accounts/" + idAccount,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: (data) => {
                reloadAccounts();
                fillAccountsTable();
            },
            error: () => {
                callMessagePopup("Ошибка", "Не удалось изменить статус пользователя. Попробуйте еще раз")
            }
        })
    }

    function callConfirmDeleteAccount(idAccount) {
        callMessagePopup("Удаление", "Вы действительно хотите удалить данные об аккаунте? Данные не подлежат" +
            " восстановлению!");
        $("#confirm-message-btn").unbind();
        $("#confirm-message-btn").on("click", () => {
            deleteAccount(idAccount);
        })

        $("#decline-message-btn").show();
    }

    function deleteAccount(idAccount) {
        $("#messageModal").hide()
        $.ajax({
            method: "delete",
            url: "/api/accounts/" + idAccount,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: () => {
                callMessagePopup("Аккаунт успешно удален");
                deleteAccountFromCache(idAccount);
                fillAccountsTable();
            },
            error: () => {
                callMessagePopup("Ошибка", "Что-то пошло не так! Не удалось удалить аккаунт")
            }
        })
    }
}