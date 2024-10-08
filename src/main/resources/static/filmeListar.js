async function carregarFilmes() {
    try {
        const response = await fetch('/api/filmes/listar'); // Use o prefixo API
        if (!response.ok) throw new Error('Erro ao carregar filmes');

        const filmes = await response.json();
        const tabelaFilmes = document.getElementById('filmesTabela'); // Referência ao tbody

        // Limpa a tabela antes de adicionar novos filmes
        tabelaFilmes.innerHTML = '';

        filmes.forEach(filme => {
            const row = tabelaFilmes.insertRow();

            const tituloCell = row.insertCell(0);
            const generoCell = row.insertCell(1);
            const anoCell = row.insertCell(2);
            const acoesCell = row.insertCell(3);

            tituloCell.textContent = filme.titulo;
            generoCell.textContent = filme.genero;
            anoCell.textContent = filme.anoLancamento;

            // Ações de editar e deletar
            acoesCell.innerHTML = `
                <a href="/filmes/cadastro?filmeId=${filme.id}" class="btn btn-warning btn-sm">Editar</a>
                <button onclick="deletarFilme(${filme.id})" class="btn btn-danger btn-sm">Excluir</button>
            `;
        });
    } catch (error) {
        console.error('Erro ao carregar filmes:', error);
        alert('Erro ao carregar filmes. Verifique o console para mais detalhes.');
    }
}

// Chama a função para carregar os filmes ao carregar a página
window.onload = carregarFilmes;
