{
    let productsCache;
    let productTypesCache;

    function initButtons() {
        const $productNameInput = $("#product-name-input")
        const $productNameInvalid = $("#name-product-invalid")
        const $aboutProductInput = $("#about-product-input");
        const $aboutInvalid = $("#about-invalid");
        const $productTypeSelect = $("#product-type-select");

        $("#create-product-btn").unbind()
        $("#create-product-btn").on("click", function () {
            // callMessagePopup("Как", "Дела");
            $("#createProductModal").modal('show');

            $("#product-type-select").html('');
            productTypesCache.forEach(productType => {
                let option = $("<option value='" + productType.idProductType + "'>" + productType.name + "</option>")
                $("#product-type-select").append(option);
            })
        })

        $("#confirm-create-product-btn").on("click", function () {
            let name = $productNameInput.text();
            let about = $aboutProductInput.val();
            let flag = true;

            if (name.length < 1 || name.length > 30) {
                $productNameInvalid.show()
                flag = false;
            } else {
                $productNameInvalid.hide();
            }

            if (about.length > 300) {
                $aboutInvalid.show();
                flag = false;
            } else {
                $aboutInvalid.hide();
            }

            if (flag) {
                let newProductObj = {
                    name: name,
                    about: about,
                    productType: {
                        idProductType: $productTypeSelect.val()
                    },
                    account: authenticatedUser
                }

                createProduct(newProductObj)
            }
        })
    }

    // TODO ты остановился тута. не тестилося.
    function createProduct(newProductObj) {
        $.ajax({
            method: "post",
            url: "/api/products",
            contentType: "application/json",
            dataType: "json",
            async: false,
            data: JSON.stringify(newProductObj),
            success: (data) => {
                $("#createProductModal").hide()
                pushProductToCache(data);
                fillProductsTable()
                callMessagePopup("Сохранено", "Новый продукт успешно создан")
            },
            error: () => {
                callMessagePopup("Ошибка сохранения", "Не удалось создать новый объект продукта");
            }
        })
    }

    function loadProducts() {
        if (typeof productsCache === "undefined") {
            reloadProducts();
        }

        return productsCache;
    }

    function reloadProducts() {
        $.ajax({
            method: "get",
            url: "/api/products?idAccount=" + authenticatedUser.idAccount,
            dataType: "json",
            async: false,
            contentType: "application/json",
            success: (data) => {
                productsCache = data;
            },
            error: () => {
                callMessagePopup("Ошибка", "Невозможно загрузить спрачовник ваших продуктов")
            }
        })

        return productsCache;
    }

    function pushProductToCache(newProduct) {
        productsCache.push(newProduct);
    }

    function loadProductTypes() {
        if (typeof productTypesCache === "undefined") {
            $.ajax({
                method: "get",
                url: "/api/product-types",
                contentType: "application/json",
                dataType: "json",
                success: (data) => {
                    productTypesCache = data;
                },
                error: () => {
                    callMessagePopup("Ошибка", "Невозможно загрузить данные о типах продуктов")
                }
            })
        }
    }

    function fillProductsTable() {
        const tableName = "products"
        const $dataTable = $("#" + tableName + "_table");

        destroyAndInitDataTable(tableName, $dataTable)

        console.log(productsCache)
        productsCache?.forEach(function (product) {
            addRowToProductsDataTable(product, tableName)
        })
    }

    function addRowToProductsDataTable(product, tableId) {
        const tableName = tableId ? tableId : "default";

        $("#" + tableName + "_table").DataTable().row.add([
            product.name,
            product.productType.name,
            product.about,
            new Date(product.creationDate).toLocaleDateString('ru'),
            "<div class='simple-btn btn-gray' onclick=''>Подробнее</div>",
        ]).draw();
    }
}