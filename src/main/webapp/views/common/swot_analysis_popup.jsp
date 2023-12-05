<%@ page contentType="text/html;charset=UTF-8" %>

<div class="modal fade" id="swotModal" tabindex="-1" aria-labelledby="swotModalLabel"
     aria-hidden="true">
    <div class="modal-dialog big-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="swotModalLabel">SWOT анализ</h5>
                <div class="close close-button" data-bs-dismiss="modal" aria-label="Закрыть">
                    <span aria-hidden="true">X</span>
                </div>
            </div>
            <div class="content">
                <label class="swot-label" style="margin: 0 0 0 150px">Название</label>
                <label id="swot-name-invalid-lbl" class="invalid-label">Название должно быть от 1 до 30 символов</label>
                <input id="swot-name-input" style="width: 300px; margin: 0 0 10px 150px !important;">
                <div style="display: grid; justify-content: center">
                    <div class="inline-flex-box">
                        <div>
                            <input id="swot-id" hidden>
                            <label class="swot-label">Сильные стороны</label>
                            <textarea id="strong" class="swot-textarea"></textarea>
                        </div>
                        <div>
                            <label class="swot-label">Слабые стороны</label>
                            <textarea id="weak" class="swot-textarea"></textarea>
                        </div>
                    </div>
                    <div class="inline-flex-box">
                        <div>
                            <label class="swot-label">Угрозы</label>
                            <textarea id="threat" class="swot-textarea"></textarea>
                        </div>
                        <div>
                            <label class="swot-label">Возможности</label>
                            <textarea id="opportunity" class="swot-textarea"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
                <button id="confirm-save-swot-btn" class="btn btn-primary">
                    Сохранить
                </button>
                <button class="btn btn-primary" data-bs-dismiss="modal">
                    Закрыть
                </button>
            </div>
        </div>
    </div>
</div>
