package com.cinema.db.integradatabase.service;

import com.cinema.db.integradatabase.data.AnaliseEntity;
import com.cinema.db.integradatabase.data.AnaliseRepository;
import com.cinema.db.integradatabase.exception.ResourceNotFoundException;
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
        AnaliseEntity an = getAnaliseId(anId); // Busca a análise pelo ID

        an.setFilme(analiseRequest.getFilme());
        an.setNota(analiseRequest.getNota());
        an.setComentario(analiseRequest.getComentario());

        return analiseRepository.save(an); // Salva as mudanças no banco de dados
    }

    // Método para buscar uma análise por ID
    public AnaliseEntity getAnaliseId(Integer anId) {
        return analiseRepository.findById(anId)
                .orElseThrow(() -> new ResourceNotFoundException("Análise não encontrada: " + anId));
    }

    // Método para listar todas as análises
    public List<AnaliseEntity> listarTodasAnalises() {
        return analiseRepository.findAll(); // Retorna uma lista de todas as análises
    }

    // Método para deletar uma análise
    public void deletarAnalise(Integer anId) {
        getAnaliseId(anId); // Verifica se a análise existe
        analiseRepository.deleteById(anId); // Remove a análise pelo ID
    }
}
