package com.cinema.db.integradatabase.controller;

import com.cinema.db.integradatabase.data.AnaliseEntity;
import com.cinema.db.integradatabase.service.AnaliseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analises") // Define o endpoint base para esse controlador
public class AnaliseController {

    @Autowired
    private AnaliseService analiseService; // Injeta o serviço de análise para manipulação dos dados

    // Método para listar todas as análises
    @GetMapping("/listar")
    public ResponseEntity<List<AnaliseEntity>> getAllAnalises() {
        List<AnaliseEntity> analises = analiseService.listarTodasAnalises(); // Chama o serviço para listar as análises
        return new ResponseEntity<>(analises, HttpStatus.OK); // Retorna a lista de análises com status 200 OK
    }

    // Método para buscar uma análise por ID
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<AnaliseEntity> getAnaliseById(@PathVariable Integer id) {
        AnaliseEntity analise = analiseService.getAnaliseId(id); // Chama o serviço para buscar uma análise pelo ID
        if (analise != null) {
            return new ResponseEntity<>(analise, HttpStatus.OK); // Se encontrada, retorna a análise com status 200 OK
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se não encontrada, retorna 404 NOT FOUND
    }

    // Método para adicionar uma nova análise
    @PostMapping("/adicionar")
    public ResponseEntity<AnaliseEntity> addAnalise(@Valid @RequestBody AnaliseEntity analise) {
        AnaliseEntity novaAnalise = analiseService.criarAnalise(analise); // Chama o serviço para criar uma nova análise
        return new ResponseEntity<>(novaAnalise, HttpStatus.CREATED); // Retorna a nova análise criada com status 201 CREATED
    }

    // Método para atualizar uma análise existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AnaliseEntity> atualizarAnalise(@PathVariable Integer id, @Valid @RequestBody AnaliseEntity analise) {
        AnaliseEntity analiseAtualizada = analiseService.atualizarAnalise(id, analise); // Chama o serviço para atualizar a análise
        if (analiseAtualizada != null) {
            return new ResponseEntity<>(analiseAtualizada, HttpStatus.OK); // Se a análise foi atualizada, retorna status 200 OK
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se a análise não foi encontrada, retorna 404 NOT FOUND
    }

    // Método para deletar uma análise
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAnalise(@PathVariable Integer id) {
        AnaliseEntity analise = analiseService.getAnaliseId(id); // Verifica se a análise existe antes de deletar
        if (analise != null) {
            analiseService.deletarAnalise(id); // Chama o serviço para deletar a análise pelo ID
            return new ResponseEntity<>(HttpStatus.OK); // Se deletada, retorna status 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se não encontrada, retorna 404 NOT FOUND
        }
    }
}
