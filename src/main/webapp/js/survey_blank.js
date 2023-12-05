{
    let blankCache;

    function loadBlank(idBlank) {
        if (typeof blankCache === "undefined") {
            $.ajax({
                method: "get",
                url: "/api/blanks/" + idBlank,
                contentType: "application/json",
                dataType: "json",
                async: false,
                success: (data) => {
                    blankCache = data;
                    console.log(data);
                },
                error: () => {
                    callMessagePopup("Ошибка", "Не удалось загрузить анкету")
                }
            })
        }

        return blankCache;
    }

    function initBlankSurvey(blank) {
        if (blank.blankStatus.idBlankStatus !== 2) {
            $("#survey-blank-placeholder").append($("<div class='blue-line'>Анкета не опубликована!</div>"))
            $("#send-answer-btn").remove()
            $("#user-info-placeholder").remove()
        } else {
            $("#survey-blank-placeholder").html('')
            $("#blank-name-placeholder").text(blank.name)
            const fieldTypes = loadFieldTypesCache()

            blank.fields.forEach(field => {
                initQuestion(field, fieldTypes)
            })

            $("#send-answer-btn").unbind()
            $("#send-answer-btn").on('click', () => {
                validateAndSave(blank);
            })
        }
    }

    function validateAndSave(blank) {

        const usernameText = $("#username-input").val();
        let isValid = true

        if (usernameText.length < 1 || usernameText.length > 30) {
            $("#invalid-username-lbl").show();
            isValid = false;
        } else {
            $("#invalid-username-lbl").hide();
        }

        let fieldAnswers = [];
        blank.fields.forEach(field => {
            let fieldAnswer = {
                field: {
                    idField: field.idField,
                    fieldType: field.FieldType
                },
                fieldVariantAnswers: []
            }
            if (field.fieldType.idFieldType != 3) {
                if ($("input[name='variant-" + field.idField + "']:checked").length === 0) {
                    isValid = false;
                    $("#answer-block-" + field.idField + " > label").show()
                } else {
                    $("#answer-block-" + field.idField + " > label").hide()

                    Array.from($("input[name='variant-" + field.idField + "']:checked")).forEach(inputVariant => {
                        fieldAnswer.fieldVariantAnswers.push({
                            fieldVariant: {
                                idFieldVariant: $(inputVariant).val()
                            }
                        })
                    })
                }
            } else {
                let textarea = $("#answer-block-" + field.idField + " > textarea");

                if (textarea.val().trim().length < 2
                    || textarea.val().trim().length > 500) {
                    isValid = false;
                    $("#answer-block-" + field.idField + " > label").show();
                } else {
                    $("#answer-block-" + field.idField + " > label").hide();

                    fieldAnswer.answer = textarea.val().trim();
                }
            }

            fieldAnswers.push(fieldAnswer)
        })

        if (!isValid) {
            return;
        }

        const blankAnswer = {
            blank: {
                idBlank: blank.idBlank
            },
            username: usernameText,
            fieldAnswers: fieldAnswers
        }

        $.ajax({
            method: "post",
            url: "/api/blank-answers",
            dataType: "json",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(blankAnswer),
            success: (data) => {
                $("#blank-placeholder").html('');
                callMessagePopup("Спасибо!", "Спасибо за то, что приняли участие в опросе!")
            },
            error: () => {
                callMessagePopup("Ошибка", "Что-то пошло не так! Возможно, вы недавно уже проходили этот опрос!")
            }
        })
    }


    function initQuestion(field) {
        const $questionBlock = $("<div question-type='" + field.fieldType.idFieldType +
            "' question-id='" + field.idField + "'></div>")

        const $questionText = $("<div>" + field.text + "</div>")
        const $answerBlock = $("<div id='answer-block-" + field.idField + "' class='answer-block'></div>")

        switch (field.fieldType.idFieldType) {
            case 1: {
                $answerBlock.append($("<label class='invalid-label'>Нужно выбрать какой-либо вариант</label>"))

                field.fieldVariants.forEach(fieldVariant => {
                    let $oneAnswerBlock = $("<div class='inline-flex-box answer-field-group'></div>")

                    $oneAnswerBlock.append($("<input name='variant-" + field.idField +
                        "' type='radio' value='" + fieldVariant.idFieldVariant + "'>"));
                    $oneAnswerBlock.append($("<label>" + fieldVariant.text + "</label>"))

                    $answerBlock.append($oneAnswerBlock)
                })
                break;
            }
            case 2: {
                $answerBlock.append($("<label class='invalid-label'>Нужно выбрать хотя бы 1 вариант</label>"))
                field.fieldVariants.forEach(fieldVariant => {
                    let $multiAnswerBlock = $("<div class='inline-flex-box answer-field-group'></div>")
                    $multiAnswerBlock.append($("<input name='variant-" + field.idField +
                        "' type='checkbox' value='" + fieldVariant.idFieldVariant + "'>"));
                    $multiAnswerBlock.append($("<label>" + fieldVariant.text + "</label>"))

                    $answerBlock.append($multiAnswerBlock)
                })
                break;
            }
            case 3: {
                $answerBlock.append($("<label class='invalid-label'>Поле должно содержать от 2 до 500 символов</label>"))
                $answerBlock.append($("<textarea></textarea>"))
                break;
            }
        }

        $questionBlock.append($questionText).append($answerBlock).append($("<hr>"));
        $("#survey-blank-placeholder").append($questionBlock);
    }
}