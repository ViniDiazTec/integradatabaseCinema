document.addEventListener('DOMContentLoaded', () => {
    carregarFilmes(); // Carregar filmes ao iniciar a página
});

// Função para carregar filmes
async function carregarFilmes() {
    try {
        const response = await fetch('/api/filmes/listar');
        if (!response.ok) throw new Error('Erro ao carregar filmes');

        const filmes = await response.json();
        const tabelaFilmes = document.getElementById('filmesTabela'); // Referência ao tbody

        // Limpa a tabela antes de adicionar novos filmes
        tabelaFilmes.innerHTML = '';

        filmes.forEach(filme => {
            const row = tabelaFilmes.insertRow();
            row.innerHTML = `
                <td>${filme.titulo}</td>
                <td>${filme.genero}</td>
                <td>${filme.anoLancamento}</td>
                <td>
                    <a href="/filmes/cadastro?filmeId=${filme.id}" class="btn btn-warning btn-sm">Editar</a>
                    <button onclick="deletarFilme(${filme.id})" class="btn btn-danger btn-sm">Excluir</button>
                </td>
            `;
        });
    } catch (error) {
        console.error('Erro ao carregar filmes:', error);
        alert('Erro ao carregar filmes. Verifique o console para mais detalhes.');
    }
}

// Função para deletar filme
async function deletarFilme(filmeId) {
    if (confirm('Você tem certeza que deseja excluir este filme?')) {
        try {
            const response = await fetch(`/api/filmes/deletar/${filmeId}`, {
                method: 'DELETE',
            });

            if (response.ok) {
                alert('Filme excluído com sucesso!');
                carregarFilmes(); // Recarregar a lista de filmes
            } else {
                alert('Erro ao excluir filme.');
            }
        } catch (error) {
            console.error('Erro ao tentar excluir filme:', error);
            alert('Erro ao tentar excluir filme. Verifique o console para mais detalhes.');
        }
    }
}
