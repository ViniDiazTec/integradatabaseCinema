package com.cinema.db.integradatabase.controller;

import com.cinema.db.integradatabase.data.AnaliseEntity;
import com.cinema.db.integradatabase.service.AnaliseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/analises")
public class AnaliseController {

    @Autowired
    private AnaliseService analiseService;

    @GetMapping("/listar")
    public ResponseEntity<List<AnaliseEntity>> getAllAnalises() {
        List<AnaliseEntity> analises = analiseService.listarTodasAnalises();
        return new ResponseEntity<>(analises, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<AnaliseEntity> getAnaliseById(@PathVariable Integer id) {
        AnaliseEntity analise = analiseService.getAnaliseId(id);
        if (analise != null) {
            return new ResponseEntity<>(analise, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<AnaliseEntity> addAnalise(@RequestBody AnaliseEntity analise) {
        AnaliseEntity novaAnalise = analiseService.criarAnalise(analise);
        return new ResponseEntity<>(novaAnalise, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<AnaliseEntity> atualizarAnalise(@PathVariable Integer id, @RequestBody AnaliseEntity analise) {
        AnaliseEntity analiseAtualizada = analiseService.atualizarAnalise(id, analise);
        if (analiseAtualizada != null) {
            return new ResponseEntity<>(analiseAtualizada, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarAnalise(@PathVariable Integer id) {
        AnaliseEntity analise = analiseService.getAnaliseId(id);
        if (analise != null) {
            analiseService.deletarAnalise(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
