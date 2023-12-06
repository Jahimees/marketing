{
    let swotCache;
    let idAcc;

    function initSwotTab(idAccount) {
        idAcc =idAccount;
        loadSwot(idAccount);
        showAllSwots(swotCache);

        $("#create-swot-btn").unbind()
        $("#create-swot-btn").on("click", () => {
            $("#strong").val('')
            $("#weak").val('')
            $("#opportunity").val('')
            $("#threat").val('');
            $("#swot-id").val('');
            $("#swot-name-input").val('')
            $("#swotModal").modal('show');
        })

        $("#confirm-save-swot-btn").unbind()
        $("#confirm-save-swot-btn").on("click", () => {
            const weak = $("#weak").val();
            const strong = $("#strong").val();
            const opportunity = $("#opportunity").val();
            const threat = $("#threat").val();
            const name = $("#swot-name-input").val();

            let isValid = true;
            if (name.trim().length < 1 || name.trim().length > 30) {
                isValid = false;
                $("#swot-name-invalid-lbl").show();
            } else {
                $("#swot-name-invalid-lbl").hide();
            }

            if (isValid) {
                let swotId = $("#swot-id").val();

                let url;
                let method;

                console.log(swotId)
                if (swotId.trim() !== '') {
                    method = "put"
                    url = "/api/swot/" + swotId
                } else {
                    method = "post"
                    url = "/api/swot"
                }

                let newSwot = {
                    account: {
                        idAccount: idAccount
                    },
                    name: name,
                    weakness: weak.trim(),
                    strength: strong.trim(),
                    opportunity: opportunity.trim(),
                    threat: threat.trim()
                }

                console.log(JSON.stringify(newSwot))
                $.ajax({
                    method: method,
                    url: url,
                    dataType: "json",
                    contentType: "application/json",
                    async: false,
                    data: JSON.stringify(newSwot),
                    success: (data) => {
                        $("#swotModal").modal('hide');
                        callMessagePopup("Сохранено", "Данные сохранены");
                        swotCache = loadSwot(idAccount);
                        showAllSwots(swotCache)
                    },
                    error: () => {
                        callMessagePopup("Ошибка", "Не удалось сохранить данные")
                    }
                })
            }
        })
    }

    function loadSwot(idAccount){
        $.ajax({
            method: "get",
            url: "/api/swot?idAccount=" + idAccount,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: (data) => {
                swotCache = data
            },
            error: () => {
                callMessagePopup("Ошибка", "Невозможно загрузить данные swot анализа");
            }
        })

        return swotCache;
    }

    function showAllSwots(swotCache) {
        $("#swot-container").html('');

        swotCache.forEach(swot => {
            let div = $("<div style='display: inline-flex; margin: 0 20px 10px 0'><div onclick='openSwot("+ swot.idSwot +")' class='btn-gray simple-btn'>"+swot.name+"" +
                "</div><div style='cursor:pointer;' onclick='callConfirmDeleteSwot("+swot.idSwot+")'>X</div></div>")
            $("#swot-container").append(div);
        })
    }

    function callConfirmDeleteSwot(idSwot) {
        callMessagePopup("Удаление", "Вы действительно хотите удалить swot?");
        $("#decline-message-btn").show();
        $("#confirm-message-btn").on("click", function () {
            deleteSwot(idSwot)
        });
    }

    function deleteSwot(idSwot) {
        $.ajax({
            method: "delete",
            url: "/api/swot/" + idSwot,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: () => {
                swotCache = loadSwot(idAcc);
                showAllSwots(swotCache);
            },
            error: () => {
                console.log("ERROR cant delete swot")
            }
        })
    }

    function findSwot(idSwot) {
        let currentSwot;
        swotCache.forEach(swot => {
            if (swot.idSwot === idSwot) {
                currentSwot = swot;
            }
        })

        return currentSwot;
    }

    function openSwot(idSwot) {
        $("#swot-id").val(idSwot);

        let currentSwot = findSwot(idSwot);

        console.log("WEWW")
        $("#weak").val(currentSwot.weakness);
        $("#strong").val(currentSwot.strength);
        $("#opportunity").val(currentSwot.opportunity);
        $("#threat").val(currentSwot.threat);
        $("#swot-name-input").val(currentSwot.name);

        $("#swotModal").modal('show');
    }
}