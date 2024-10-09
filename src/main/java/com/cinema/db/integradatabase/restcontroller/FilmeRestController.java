package com.cinema.db.integradatabase.restcontroller;

import com.cinema.db.integradatabase.data.FilmeEntity;
import com.cinema.db.integradatabase.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/filmes")  // Prefixo 'api' para diferenciar as rotas
public class FilmeRestController {

    @Autowired
    private FilmeService filmeService;

    // Retorna a lista de todos os filmes
    @GetMapping("/listar")
    public ResponseEntity<List<FilmeEntity>> getAllFilmes() {
        return new ResponseEntity<>(filmeService.listarTodos(), HttpStatus.OK);
    }

    // Retorna um filme pelo ID
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FilmeEntity> getFilmeById(@PathVariable Integer id) {
        FilmeEntity filme = filmeService.buscarPorId(id);
        return filme != null ? new ResponseEntity<>(filme, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Adiciona um novo filme
    @PostMapping("/adicionar")
    public ResponseEntity<FilmeEntity> addFilme(@Valid @RequestBody FilmeEntity filme) {
        FilmeEntity novoFilme = filmeService.criarFilme(filme);
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }

    // Atualiza um filme existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FilmeEntity> atualizarFilme(@PathVariable Integer id, @Valid @RequestBody FilmeEntity filme) {
        FilmeEntity filmeAtualizado = filmeService.atualizarFilme(id, filme);
        return filmeAtualizado != null ? new ResponseEntity<>(filmeAtualizado, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /*// Método para deletar um filme
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Integer id) {
        FilmeEntity filme = filmeService.buscarPorId(id);
        if (filme != null) {
            filmeService.deletarFilme(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }*/
}
