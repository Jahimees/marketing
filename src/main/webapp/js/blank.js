{
    //TODO грузить для  просмотра + волшебные методы, которые всё заполняют
    let createTabCache;
    let viewTabCache;
    let templatesTabCache;

    function initBlankTab() {
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
    }

    let questionCounter;
    let blankStatusesCache;
    let fieldTypesCache;

    function initCreateTab() {
        questionCounter = 0;
        $("#question-container").html('');
        $("#blank-name-input").val('');

        initBlankStatusRadio()
        initProductSelect();

        $("#save-blank-btn").unbind();
        $("#save-blank-btn").on('click', function () {
            saveBlank();
        })

        $("#add-question-btn").unbind();
        $("#add-question-btn").on('click', function () {
            createNewQuestion();
        })
    }

    function saveBlank() {
        if ($("div[q-number]").length === 0) {
            callMessagePopup("Ошибка", "Добавьте хотя бы один вопрос!")
            return;
        }

        //fields
        const fields = []
        let isInvalidQuestion = false;
        let isInvalidVariant = false;
        let isNoOneVariant = false;
        Array.from($("div[q-number]")).forEach(field => {
            if (typeof field !== "undefined") {
                const qNumber = $(field).attr('q-number');

                const fieldText = $(field).children('textarea').val();

                if (fieldText.trim().length < 1 || fieldText.trim().length > 500) {
                    isInvalidQuestion = true;
                }

                const idFieldType = $("input[name='type-" + qNumber + "']:checked").val();

                //Дадаяда
                let variants = []
                if (+idFieldType !== 3) {

                    if (($("input[name='answer-" + qNumber + "']")).length <= 1) {
                        isNoOneVariant = true;
                    }

                    Array.from($("input[name='answer-" + qNumber + "']")).forEach(variant => {
                        if (typeof variant !== "undefined") {
                            const variantText = $(variant).val().trim();
                            if (variantText.length < 1 || variantText.length > 100) {
                                isInvalidVariant = true;
                            } else {
                                variants.push({
                                    text: variantText
                                });
                            }
                        }
                    })
                }

                let newField = {
                    text: fieldText,
                    fieldType: {
                        idFieldType: idFieldType
                    },
                    fieldVariants: variants
                }

                fields.push(newField);
            }
        })

        if (isInvalidQuestion) {
            callMessagePopup("Ошибка", "Вопросы должны содержать от 1 до 500 символов!")
            return;
        }

        if (isNoOneVariant) {
            callMessagePopup("Ошибка", "У вопроса с типом ответа 'Одиночный выбор' или 'Множественный выбор'" +
                " должно быть как минимум 2 ответа");
            return;
        }

        if (isInvalidVariant) {
            callMessagePopup("Ошибка", "Варианты ответа должны содержать от 1 до 100 символов!")
            return;
        }

        //blank
        const blankName = $("#blank-name-input").val();
        if (blankName.length < 1 || blankName.length > 30) {

            callMessagePopup("Ошибка", "Название анкеты должно содержать от 1 до 30 символов!");
            return;
        }

        const blankStatusId = $("input[name='status']:checked").val();
        const productId = $("#product-select").val();
        const accountId = authenticatedUser.idAccount;

        const newBlank = {
            name: blankName,
            blankStatus: {
                idBlankStatus: blankStatusId
            },
            product: {
                idProduct: productId
            },
            account: {
                idAccount: accountId
            },
            fields: fields
        }

        $.ajax({
            method: "post",
            url: "/api/blanks",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(newBlank),
            success: (data) => {
                callMessagePopup("Анкета сохранена", "Анкета успешно сохранена!")
                $("#blank-name-input").val('');
                $("#question-container").html('');
            },
            error: () => {
                callMessagePopup("Ошибка", "Не удалось сохранить анкету!")
            }
        })
    }

    let questionIds = [];

    function createNewQuestion() {
        if ($("div[q-number]").length >= 10) {
            callMessagePopup("Ошибка", "Невозможно создать более 10 вопросов в анкете!");
            return
        }

        questionCounter++;
        questionIds.push(questionCounter);
        const fieldTypes = loadFieldTypesCache();

        const questionBlock = $("<div id='question-" + questionCounter + "' q-number='" + questionCounter + "'></div>")

        const deleteBtn = $("<div class='delete-btn' style='margin: 0 0 0 auto;' onclick='dropQuestion(" + questionCounter + ")'>X</div>")

        const questionLabel = $("<div>Текст вопроса *</div>");
        const fieldTypeBlock = $("<div style='width: fit-content' id='field-type-radiogroup-" + questionCounter + "'></div>")
        const textArea = $("<textarea></textarea>");

        const fieldTypeLabel = $("<div>Тип поля *</div>");

        fieldTypes.forEach(fieldType => {
            const fieldTypeDivContainer = $("<div class='inline-flex-box' '></div>")

            let radio = $("<input id='radio-" + questionCounter + "-" + fieldType.idFieldType + "' " +
                "type='radio' value='" + fieldType.idFieldType + "' name='type-" + questionCounter + "'>");

            if (fieldType.idFieldType === 1) {
                radio.attr('checked', '');
            }

            let label = $("<label for='radio-" + questionCounter + "-" + fieldType.idFieldType + "'>" + fieldType.name + "</label>")
            fieldTypeDivContainer.append(radio).append(label);
            fieldTypeBlock.append(fieldTypeDivContainer);
        })


        const answersBlock = $("<div style='display: inline-grid; margin-top: 30px; margin-bottom: 30px;' " +
            "id='answers-group-" + questionCounter + "'></div>")

        const addAnswerBtn = $("<div onclick='addAnswer(" + questionCounter + ")' class='simple-btn btn-gray'>Добавить ответ</div>");
        questionBlock
            .append(deleteBtn)
            .append(questionLabel)
            .append(textArea)
            .append(fieldTypeLabel)
            .append(fieldTypeBlock)
            .append(answersBlock)
            .append(addAnswerBtn)
            .append($("<hr>"));
        $("#question-container").append(questionBlock);

        $("input[name=type-" + questionCounter + "]").unbind()
        $("input[name=type-" + questionCounter + "]").on('change', (obj) => {
            fieldTypes.forEach(fieldType => {
                if (fieldType.idFieldType === +obj.currentTarget.value) {
                    addAnswerBtn.remove();
                    switch (fieldType.name) {
                        case 'Одиночный выбор': {
                        }
                        case 'Множественный выбор': {
                            answersBlock.after(addAnswerBtn)
                            break;
                        }
                        case 'Текстовый ответ': {
                            answersBlock.html('')
                        }
                    }
                }
            })
        })
    }

    function addAnswer(questionId) {
        if ($("input[name='answer-" + questionId + "'").length >= 10) {
            callMessagePopup("Ошибка", "Нельзя создать более 10 ответов на вопрос!");
            return
        }

        const answersBlock = $("#answers-group-" + questionId);
        const answerBlock = $("<div class='inline-flex-box'></div>")

        const newInput = $("<input name='answer-" + questionId + "'>")
        const removeAnswer = $("<div onclick='dropAnswer(this)' class='delete-btn'>X</div>")
        answerBlock.append(newInput).append(removeAnswer);
        answersBlock.append(answerBlock);
    }

    function dropAnswer(answerBlock) {
        answerBlock.parentElement.remove();
    }

    function dropQuestion(questionId) {
        let index;
        questionIds.forEach(id => {
            if (id === questionId) {
                index = questionIds.indexOf(id);
            }
        })

        questionIds.splice(index, 1);

        $("#question-" + questionId).remove();
    }

    function loadBlankStatusesCache() {
        if (typeof blankStatusesCache === "undefined") {
            $.ajax({
                method: "get",
                url: "/api/blank-status",
                contentType: "application/json",
                dataType: "json",
                async: false,
                success: (data) => {
                    blankStatusesCache = data;
                },
                error: () => {
                    callMessagePopup("Ошибка", "Невозможно загрузить статусы анкеты")
                }
            })
        }

        return blankStatusesCache;
    }

    function loadFieldTypesCache() {
        if (typeof fieldTypesCache === "undefined") {
            $.ajax({
                method: "get",
                url: "/api/field-types",
                contentType: "application/json",
                dataType: "json",
                async: false,
                success: (data) => {
                    fieldTypesCache = data;
                },
                error: () => {
                    callMessagePopup("Ошибка", "Невозможно загрузить типы полей!")
                }
            })
        }

        return fieldTypesCache;
    }

    function initBlankStatusRadio() {
        let blankStatuses = loadBlankStatusesCache();

        $("#blank-status-radiogroup").html('')
        $("#blank-status-select").html('');
        blankStatuses.forEach(blankStatus => {
            let radio;
            let div = $("<div style='padding: 0 10px; display: inline-flex'></div>");
            if (blankStatus.idBlankStatus === 1) {
                radio = $("<input id='status-" + blankStatus.idBlankStatus + "' type='radio' name='status' " +
                    "value='" + blankStatus.idBlankStatus + "' checked>")
            } else {
                radio = $("<input id='status-" + blankStatus.idBlankStatus + "' type='radio' name='status' " +
                    "value='" + blankStatus.idBlankStatus + "'>")
            }

            const label = $("<label for='status-" + blankStatus.idBlankStatus + "' style='white-space: nowrap'>" + blankStatus.name + "</label>")
            div.append(radio).append(label);

            $("#blank-status-radiogroup").append(div)

            const option = $("<option value='" + blankStatus.idBlankStatus + "'>" + blankStatus.name + "</option>")
            $("#blank-status-select").append(option);
        })
    }

    function initProductSelect() {
        let products = loadProducts();
        const $productSelect = $("#product-select");

        $productSelect.html('');
        $productSelect.append($("<option value='0'>Не привязывать к товару</option>"))
        products.forEach(product => {
            let option = $("<option value='" + product.idProduct + "'>" + product.name + "</option>");
            $productSelect.append(option);
        })
    }

    let blanksCache;

    ///////////VIEW TAB
    function initViewTab() {
        reloadBlanks();
        fillBlanksDataTable();
    }

    function loadBlanks() {
        if (typeof blanksCache === "undefined") {
            blanksCache = reloadBlanks();
        }

        return blanksCache;
    }

    function deleteBlankFromCache(idBlank) {
        let index;
        blanksCache.forEach(blank => {
            if (blank.idBlank === idBlank) {
                index = blanksCache.indexOf(blank);
            }
        })

        blanksCache.splice(index, 1);
    }

    function reloadBlanks() {
        $.ajax({
            method: "get",
            url: "/api/blanks?idAccount=" + authenticatedUser.idAccount,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: (data) => {
                blanksCache = data;
            },
            error: () => {
                callMessagePopup("Ошибка", "Невозможно загрузить данные по анкетам")
            }
        })

        return blanksCache;
    }

    function fillBlanksDataTable() {
        const tableName = "blanks"
        const $dataTable = $("#" + tableName + "_table");

        destroyAndInitDataTable(tableName, $dataTable)

        blanksCache?.forEach(function (blank) {
            addRowToBlanksDataTable(blank, tableName)
        })
    }

    function addRowToBlanksDataTable(blank, tableId) {
        const tableName = tableId ? tableId : "default";

        $("#" + tableName + "_table").DataTable().row.add([
            blank.name,
            blank.blankStatus?.name,
            new Date(blank.creationDate).toLocaleDateString('ru'),
            typeof blank.product === "undefined" || blank.product === null ? "Не привязан" : blank.product.name,
            "<div onclick='openEditBlankTab(" + blank.idBlank + ")' class='simple-btn btn-gray' onclick=''>Подробнее</div>",
            "<div onclick='callConfirmDeleteBlank(" + blank.idBlank + ")' class='simple-btn btn-gray' onclick=''>Удалить</div>",
        ]).draw();
    }

    function openEditBlankTab(idBlank) {
        $.ajax({
            method: "get",
            url: "/blanks/edit/" + idBlank,
            contentType: "html/text",
            success: (data) => {
                $("#blank-working-space").html(data)
            },
            error: () => {
                callMessagePopup("Ошибка", "Что-то пошло не так. Невозможно загрузить редактирование анкеты")
            }
        })
    }

    function callConfirmDeleteBlank(idBlank) {
        callMessagePopup("Удаление", "Вы действительно хотите удалить анкету? Это удалит все результаты проведенных исследований!");
        $("#decline-message-btn").show();
        $("#confirm-message-btn").on("click", function () {
            deleteBlank(idBlank)
        });
    }

    function deleteBlank(idBlank) {
        $.ajax({
            method: "delete",
            url: "/api/blanks/" + idBlank,
            dataType: "json",
            contentType: "application/json",
            success: () => {
                $("#messageModal").modal('hide');
                deleteBlankFromCache(idBlank);
                fillBlanksDataTable();
            },
            error: () => {
                $("#messageModal").modal('hide');
                callMessagePopup("Ошибка", "Не удалось удалить объект!")
            }
        })
    }
}