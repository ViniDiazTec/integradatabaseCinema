package com.cinema.db.integradatabase.restcontroller;

import com.cinema.db.integradatabase.data.AnaliseEntity;
import com.cinema.db.integradatabase.service.AnaliseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/analises") // Prefixo 'api' para diferenciar as rotas
public class AnaliseRestController {

    @Autowired
    private AnaliseService analiseService; // Injeta o serviço de análise para manipulação dos dados

    // Retorna a lista de todas as análises
    @GetMapping("/listar")
    public ResponseEntity<List<AnaliseEntity>> getAllAnalises() {
        List<AnaliseEntity> analises = analiseService.listarTodasAnalises();
        return new ResponseEntity<>(analises, HttpStatus.OK);
    }

    // Retorna uma análise pelo ID
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<AnaliseEntity> getAnaliseById(@PathVariable Integer id) {
        AnaliseEntity analise = analiseService.getAnaliseId(id);
        return analise != null ? new ResponseEntity<>(analise, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Adiciona uma nova análise
    @PostMapping("/adicionar")
    public ResponseEntity<AnaliseEntity> addAnalise(@Valid @RequestBody AnaliseEntity analise) {
        AnaliseEntity novaAnalise = analiseService.criarAnalise(analise);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaAnalise);
    }

    // Atualiza uma análise existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<?> atualizarAnalise(@PathVariable Integer id, @Valid @RequestBody AnaliseEntity analise) {
        AnaliseEntity analiseAtualizada = analiseService.atualizarAnalise(id, analise);
        return analiseAtualizada != null ? ResponseEntity.ok(analiseAtualizada) : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Análise não encontrada");
    }

    // Deleta uma análise pelo ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarAnalise(@PathVariable Integer id) {
        if (analiseService.getAnaliseId(id) != null) {
            analiseService.deletarAnalise(id);
            return ResponseEntity.ok("Análise deletada com sucesso");
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Análise não encontrada");
    }
}
