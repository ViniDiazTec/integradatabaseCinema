package com.cinema.db.integradatabase.service;

import com.cinema.db.integradatabase.data.FilmeEntity;
import com.cinema.db.integradatabase.data.FilmeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FilmeService {

    @Autowired
    private FilmeRepository filmeRepository;

    // Listar todos os filmes
    public List<FilmeEntity> listarTodos() {
        return filmeRepository.findAll();
    }

    // Buscar um filme por ID
    public FilmeEntity buscarPorId(Integer id) {
        Optional<FilmeEntity> filme = filmeRepository.findById(id);
        return filme.orElse(null);
    }

    // Criar um novo filme
    public FilmeEntity criarFilme(FilmeEntity filme) {
        return filmeRepository.save(filme);
    }

    // Atualizar um filme existente
    public FilmeEntity atualizarFilme(Integer id, FilmeEntity filmeAtualizado) {
        Optional<FilmeEntity> filmeOptional = filmeRepository.findById(id);
        if (filmeOptional.isPresent()) {
            FilmeEntity filme = filmeOptional.get();
            filme.setTitulo(filmeAtualizado.getTitulo());
            filme.setGenero(filmeAtualizado.getGenero());
            filme.setAnoLancamento(filmeAtualizado.getAnoLancamento());
            return filmeRepository.save(filme);
        }
        return null;
    }

    // Deletar um filme por ID
    public boolean deletarFilme(Integer id) {
        if (filmeRepository.existsById(id)) {
            filmeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
