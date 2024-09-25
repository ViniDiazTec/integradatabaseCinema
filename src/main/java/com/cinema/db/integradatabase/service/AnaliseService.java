package com.cinema.db.integradatabase.service;

import com.cinema.db.integradatabase.data.AnaliseEntity;
import com.cinema.db.integradatabase.data.AnaliseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnaliseService {

    @Autowired
    private AnaliseRepository analiseRepository;

    // Método para criar uma nova análise
    public AnaliseEntity criarAnalise(AnaliseEntity an) {
        an.setId(null); // Garantir que o ID seja nulo para criar uma nova entrada
        return analiseRepository.save(an); // Usar o método do repositório para salvar
    }

    // Método para atualizar uma análise existente
    public AnaliseEntity atualizarAnalise(Integer anId, AnaliseEntity analiseRequest) {
        // Busca a análise existente pelo ID
        AnaliseEntity an = getAnaliseId(anId);
        if (an != null) {
            // Atualiza os campos desejados
            an.setFilme(analiseRequest.getFilme());
            an.setNota(analiseRequest.getNota());
            an.setComentario(analiseRequest.getComentario());
            // Salva as mudanças no banco
            return analiseRepository.save(an);
        } else {
            return null; // Retorna null se a análise não for encontrada
        }
    }

    // Método para buscar uma análise por ID
    public AnaliseEntity getAnaliseId(Integer anId) {
        return analiseRepository.findById(anId).orElse(null); // Retorna a análise ou null
    }

    // Método para listar todas as análises
    public List<AnaliseEntity> listarTodasAnalises() {
        return analiseRepository.findAll(); // Retorna uma lista de todas as análises
    }

    // Método para deletar uma análise
    public void deletarAnalise(Integer anId) {
        AnaliseEntity an = getAnaliseId(anId);
        if (an != null) {
            analiseRepository.deleteById(anId); // Remove a análise pelo ID
        }
    }
}