package com.cinema.db.integradatabase.controller;

import com.cinema.db.integradatabase.data.FilmeEntity;
import com.cinema.db.integradatabase.service.FilmeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    @Autowired
    private FilmeService filmeService;

    @GetMapping("/listar")
    public ResponseEntity<List<FilmeEntity>> getAllFilmes() {
        List<FilmeEntity> filmes = filmeService.listarTodos();
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }

    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FilmeEntity> getFilmeById(@PathVariable Integer id) {
        FilmeEntity filme = filmeService.buscarPorId(id);
        if (filme != null) {
            return new ResponseEntity<>(filme, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/adicionar")
    public ResponseEntity<FilmeEntity> addFilme(@RequestBody FilmeEntity filme) {
        FilmeEntity novoFilme = filmeService.criarFilme(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FilmeEntity> atualizarFilme(@PathVariable Integer id, @RequestBody FilmeEntity filme) {
        FilmeEntity filmeAtualizado = filmeService.atualizarFilme(id, filme);
        if (filmeAtualizado != null) {
            return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Integer id) {
        boolean isDeleted = filmeService.deletarFilme(id);
        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
