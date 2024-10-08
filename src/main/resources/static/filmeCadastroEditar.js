document.getElementById('formCadastroEditar').addEventListener('submit', async function(event) {
    event.preventDefault(); // Impede o envio padrão do formulário

    const filmeId = document.getElementById('filmeId').value;
    const titulo = document.getElementById('titulo').value;
    const genero = document.getElementById('genero').value;
    const anoLancamento = document.getElementById('anoLancamento').value;

    // Validação simples
    if (!titulo || !genero || !anoLancamento) {
        alert('Por favor, preencha todos os campos.');
        return;
    }

    const filme = {
        titulo: titulo,
        genero: genero,
        anoLancamento: anoLancamento
    };

    let url = '/api/filmes/adicionar';  // Atualizar para usar o prefixo 'api'
    let method = 'POST';

    if (filmeId) {  // Se existir ID, estamos atualizando
        url = `/api/filmes/atualizar/${filmeId}`;  // Atualizar para usar o prefixo 'api'
        method = 'PUT';
    }

    try {
        const response = await fetch(url, {
            method: method,
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(filme)
        });

        if (response.ok) {
            alert('Filme salvo com sucesso!');
            window.location.href = '/filmes';
        } else {
            const errorMessage = await response.text();  // Pega a mensagem de erro da resposta
            alert(`Erro ao salvar filme: ${errorMessage}`);
        }
    } catch (error) {
        console.error('Erro:', error);
        alert('Erro ao salvar filme. Por favor, tente novamente.');
    }
});
