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
            $("#createProductModal").modal('show');

            $("#product-type-select").html('');
            productTypesCache.forEach(productType => {
                let option = $("<option value='" + productType.idProductType + "'>" + productType.name + "</option>")
                $("#product-type-select").append(option);
            })
        })

        $("#confirm-create-product-btn").unbind()
        $("#confirm-create-product-btn").on("click", function () {
            let name = $productNameInput.val();
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
                    account: {
                        idAccount: authenticatedUser.idAccount
                    }
                }

                createProduct(newProductObj)
            }
        })
    }

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
                reloadProducts()
                fillProductsTable()
                callMessagePopup("Сохранено", "Новый продукт успешно создан")
            },
            error: () => {
                callMessagePopup("Ошибка сохранения", "Не удалось создать новый объект продукта");
            }
        })
    }

    function loadProducts() {
        let products
        if (typeof productsCache === "undefined") {
            products = reloadProducts();
        } else {
            products = productsCache
        }

        return products;
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
                callMessagePopup("Ошибка", "Невозможно загрузить справочник ваших продуктов")
            }
        })

        return productsCache;
    }

    function loadProductTypes() {
        if (typeof productTypesCache === "undefined") {
            $.ajax({
                method: "get",
                url: "/api/product-types",
                contentType: "application/json",
                dataType: "json",
                async: false,
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
            "<div onclick='callProductDetailsPopup(" + product.idProduct + ")' class='simple-btn btn-gray'>Подробнее</div>",
            "<div onclick='callConfirmDeleteProduct(" + product.idProduct + ")' class='simple-btn btn-gray' onclick=''>Удалить</div>",
        ]).draw();
    }

    function callConfirmDeleteProduct(idProduct) {
        callMessagePopup("Удаление", "Вы действительно хотите удалить товар?");
        $("#decline-message-btn").show();
        $("#confirm-message-btn").on("click", function () {
            deleteProduct(idProduct)
        });
    }

    function deleteProduct(idProduct) {
        $("#messageModal").hide();
        $.ajax({
            method: "delete",
            url: "/api/products/" + idProduct,
            contentType: "application/json",
            async: false,
            dataType: "json",
            success: (data) => {

                callMessagePopup("Удален", "Товар успешно удален!")
                reloadProducts()
                fillProductsTable()
            },
            error: () => {
                callMessagePopup("Ошибка", "Невозможно удалить товар!")
            }
        })
    }

    ///////////PRODUCT INFO POPUP

    let lastIdProduct

    function callProductDetailsPopup(idProduct) {
        let currentProduct
        productsCache.forEach(product => {
            if (product.idProduct === idProduct) {
                currentProduct = product;
            }
        })

        lastIdProduct = idProduct;

        const productInfoList = loadProductInfo(idProduct);
        fillProductInfoDataTable(productInfoList);

        $("#productDetailsModal").modal('show');

        $("#product-name-placeholder").val(currentProduct.name);

        loadProductTypes();
        $("#product-type-group").html('');
        productTypesCache.forEach(productType => {
            let label = $("<label>" + productType.name + "</label>")
            let radio;
            let divContainer = $("<div class='inline-flex-box'></div>")

            if (productType.idProductType === currentProduct.productType.idProductType) {
                radio = $("<input type='radio' name='product-type' value='" + productType.idProductType + "' checked>")
            } else {
                radio = $("<input type='radio' name='product-type' value='" + productType.idProductType + "'>")
            }

            divContainer.append(radio).append(label)
            $("#product-type-group").append(divContainer);
        })

        $("#save-product-info-btn").unbind();
        $("#save-product-info-btn").on("click", () => {
            validateAndSaveProductInfo(idProduct);
        });
    }

    function validateAndSaveProductInfo(idProduct) {
        const monthText = $("#month-input").val();
        const sellCountText = $("#sell-count-input").val();
        const priceText = $("#price-input").val();
        const productionCountText = $("#production-count-input").val();
        const surplusText = $("#surplus-input").val();

        let monthRegexp = /^(0[1-9]|1[0-2])\.20\d{2}$/

        let isValid = true

        if (!monthText.match(monthRegexp)) {
            $("#month-invalid-lbl").show();
            isValid = false
        } else {
            $("#month-invalid-lbl").hide();
        }

        if (sellCountText.trim() === '' || isNaN(Number(sellCountText))) {
            isValid = false;
            $("#sell-count-invalid-lbl").show();
        } else {
            $("#sell-count-invalid-lbl").hide();
        }

        if (priceText.trim() === '' || isNaN(Number(priceText))) {
            isValid = false;
            $("#price-invalid-lbl").show();
        } else {
            $("#price-invalid-lbl").hide();
        }

        if (productionCountText.trim() === '' || isNaN(Number(productionCountText))) {
            isValid = false;
            $("#production-count-invalid-lbl").show();
        } else {
            $("#production-count-invalid-lbl").hide();
        }

        if (surplusText.trim() === '' || isNaN(Number(surplusText))) {
            isValid = false;
            $("#surplus-invalid-lbl").show();
        } else {
            $("#surplus-invalid-lbl").hide();
        }

        if (isValid) {
            let month = monthText.split(".");

            let newProductInfo = {
                product: {
                    idProduct: idProduct
                },
                month: new Date(month[0] + ".02." + month[1]),
                sellCount: sellCountText,
                price: priceText,
                productionCount: productionCountText,
                surplus: surplusText
            }

            $.ajax({
                method: "post",
                url: "/api/product-info",
                dataType: "json",
                contentType: "application/json",
                async: false,
                data: JSON.stringify(newProductInfo),
                success: (data) => {
                    let productInfoList = loadProductInfo(idProduct);
                    fillProductInfoDataTable(productInfoList);
                },
                error: () => {
                    $("#productDetailsModal").modal('hide');
                    callMessagePopup("Ошибка", "Не удалось сохранить данные о продукте. Возможно, Вы пытаетесь " +
                        "создать данные об отчётном периоде, который уже существует.")
                }
            })
        }
    }

    function loadProductInfo(idProduct) {
        let productInfoList;
        $.ajax({
            method: "get",
            url: "/api/product-info?idProduct=" + idProduct,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: (data) => {
                productInfoList = data
            },
            error: () => {
                $("#productDetailsModal").modal('hide');
                callMessagePopup("Ошибка", "Не удалось загрузить информацию о товарах");
            }
        })

        return productInfoList;
    }

    function fillProductInfoDataTable(productInfoList) {
        const tableName = "product_infos"
        const $dataTable = $("#" + tableName + "_table");

        destroyAndInitDataTable(tableName, $dataTable)

        productInfoList?.forEach(function (productInfo) {
            addRowToProductInfoDataTable(productInfo, tableName)
        })
    }

    const monthOptions = {
        year: 'numeric',
        month: 'numeric',
    };

    function addRowToProductInfoDataTable(productInfo, tableId) {
        const tableName = tableId ? tableId : "default";

        $("#" + tableName + "_table").DataTable().row.add([
            new Date(productInfo.fillingDate).toLocaleDateString('ru'),
            new Date(productInfo.month).toLocaleDateString('ru', monthOptions),
            productInfo.sellCount,
            productInfo.price,
            productInfo.productionCount,
            productInfo.surplus,
            "<div onclick='callConfirmDeleteProductInfo(" + productInfo.idProductInfo + ", " + lastIdProduct + ")' class='simple-btn btn-gray'>X</div>",
        ]).draw();
    }

    function callConfirmDeleteProductInfo(idProductInfo, idProduct) {
        callMessagePopup("Удаление", "Вы действительно хотите удалить данные о продукте?")
        $("#confirm-message-btn").unbind();
        $("#confirm-message-btn").on("click", () => {
            deleteProductInfo(idProductInfo, idProduct)
        });

        $("#decline-message-btn").show();
    }

    function deleteProductInfo(idProductInfo, idProduct) {
        let productInfoList
        $.ajax({
            method: "delete",
            url: "/api/product-info/" + idProductInfo,
            dataType: "json",
            contentType: "application/json",
            async: false,
            success: (data) => {
                productInfoList = loadProductInfo(idProduct);
                fillProductInfoDataTable(productInfoList);
            },
            error: () => {
                $("#messageModal").modal('hide')
                callMessagePopup("Ошибка", "Не удалось удалить информацию о товаре")
            }
        })
    }
}