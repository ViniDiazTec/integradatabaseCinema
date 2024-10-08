$(document).ready(function () {
    $('#analiseForm').on('submit', function (event) {
        event.preventDefault(); // Impede o envio padrão do formulário

        const filmeId = $('#filmeIdAnalise').val();
        const analiseTexto = $('#analise').val();
        const nota = $('#nota').val();

        $.ajax({
            type: 'POST',
            url: '/api/analises/adicionar',
            data: JSON.stringify({
                texto: analiseTexto,
                nota: nota,
                filme: { id: filmeId } // Certifique-se de que está passando a referência do filme
            }),
            contentType: 'application/json',
            success: function (novaAnalise) {
                // Adicionar a nova análise ao container de análises
                $('#analisesContainer').append(`<div>${novaAnalise.comentario} - Nota: ${novaAnalise.nota}</div>`);
                $('#analise').val(''); // Limpa o campo de texto
                $('#nota').val(''); // Limpa o campo de nota
            },
            error: function () {
                alert('Erro ao adicionar análise. Tente novamente.');
            }
        });
    });
});
