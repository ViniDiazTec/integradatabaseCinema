$(document).ready(function () {
    $('#analiseForm').on('submit', function (event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        const filmeId = $('input[name="filmeId"]').val();
        const analiseTexto = $('textarea[name="comentario"]').val(); // Nome correto
        const nota = $('input[name="nota"]').val();

        // Verificação de campos vazios
        if (!analiseTexto || !nota) {
            alert('Por favor, preencha todos os campos.');
            return; // Sai da função se campos estiverem vazios
        }

        const analiseData = {
            filme: { id: filmeId },
            comentario: analiseTexto,
            nota: nota
        };

        $.ajax({
            type: 'POST',
            url: '/api/analises/adicionar',
            contentType: 'application/json',
            data: JSON.stringify(analiseData),
            success: function (novaAnalise) {
                const analisesContainer = $('.list-group');
                const novaAnaliseItem = `
                    <div class="list-group-item analise-item">
                        <p class="analise-preview">${novaAnalise.comentario}</p>
                        <small class="text-muted">Nota: ${novaAnalise.nota}/10</small>
                    </div>`;
                analisesContainer.append(novaAnaliseItem); // Adiciona a nova análise
                $('textarea[name="comentario"]').val(''); // Limpa o campo de texto
                $('input[name="nota"]').val(''); // Limpa o campo de nota
            },
            error: function (xhr) {
                alert('Erro ao adicionar análise: ' + xhr.responseText); // Mensagem de erro
            }
        });
    });
});
