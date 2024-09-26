package com.cinema.db.integradatabase.service;

import com.cinema.db.integradatabase.data.FilmeEntity;
import com.cinema.db.integradatabase.data.FilmeRepository;
import com.cinema.db.integradatabase.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    // Método para criar um novo filme
    public FilmeEntity criarFilme(FilmeEntity filme) {
        filme.setId(null); // Garantir que o ID seja nulo para criar uma nova entrada
        return filmeRepository.save(filme);// Usar o método do repositório para salvar
    }

    // Método para atualizar um filme existente
    public FilmeEntity atualizarFilme(Integer id, FilmeEntity filmeAtualizado) {
        FilmeEntity filme = buscarPorId(id); // Busca o filme pelo ID, lança exceção se não encontrado

        // Atualiza os campos do filme com os dados do request
        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setGenero(filmeAtualizado.getGenero());
        filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());

        // Salva as mudanças no banco de dados
        return filmeRepository.save(filme);
    }

    // Método para buscar um filme por ID
    public FilmeEntity buscarPorId(Integer id) {
        return filmeRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Filme não encontrado " + id));
    }

    // Método para listar todos os filmes
    public List<FilmeEntity> listarTodos() {
        return filmeRepository.findAll();
    }

    // Método para deletar um filme
    public void deletarFilme(Integer id) {
        FilmeEntity filme = buscarPorId(id); // Busca o filme antes de deletar
        filmeRepository.deleteById(filme.getId()); // Remove o filme pelo ID
    }
}
