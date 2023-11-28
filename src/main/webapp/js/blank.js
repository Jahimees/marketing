{
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

    }

    let questionIds = [];

    function createNewQuestion() {
        questionCounter++;
        questionIds.push(questionCounter);
        const fieldTypes = loadFieldTypesCache();

        const questionBlock = $("<div id='question-" + questionCounter + "' q-number='" + questionCounter + "'></div>")

        const deleteBtn = $("<div class='simple-btn btn-gray' style='margin: 0 0 0 auto;' onclick='dropQuestion(" + questionCounter + ")'>X</div>")

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

        const addAnswerBtn = $("<div class='simple-btn btn-gray'>Добавить ответ</div>");
        questionBlock
            .append(deleteBtn)
            .append(questionLabel)
            .append(textArea)
            .append(fieldTypeLabel)
            .append(fieldTypeBlock)
            .append(addAnswerBtn)
            .append($("<hr>"));
        $("#question-container").append(questionBlock);

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
}