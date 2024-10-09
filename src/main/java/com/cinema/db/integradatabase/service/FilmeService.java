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

    @Autowired
    private AnaliseService analiseService; // Injetar o serviço de análises

    // Método para criar um novo filme
    public FilmeEntity criarFilme(FilmeEntity filme) {
        filme.setId(null); // Garantir que o ID seja nulo para criar uma nova entrada
        return filmeRepository.save(filme);
    }

    // Método para atualizar um filme existente
    public FilmeEntity atualizarFilme(Integer id, FilmeEntity filmeAtualizado) {
        FilmeEntity filme = buscarPorId(id); // Busca o filme pelo ID

        filme.setTitulo(filmeAtualizado.getTitulo());
        filme.setGenero(filmeAtualizado.getGenero());
        filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
        filme.setSinopse(filmeAtualizado.getSinopse()); // Atualiza a sinopse

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

    // Método para excluir um filme existente
    public void excluirFilme(Integer id) {
        filmeRepository.deleteById(id); // Chama o método do repositório para excluir
    }
}
