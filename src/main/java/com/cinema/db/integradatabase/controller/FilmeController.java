package com.cinema.db.integradatabase.controller;

import com.cinema.db.integradatabase.data.FilmeEntity;
import com.cinema.db.integradatabase.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes") // Define o endpoint base para operações relacionadas a filmes
public class FilmeController {

    @Autowired
    private FilmeService filmeService; // Injeta o serviço responsável pela lógica de filmes

    // Método para listar todos os filmes
    @GetMapping("/listar")
    public ResponseEntity<List<FilmeEntity>> getAllFilmes() {
        List<FilmeEntity> filmes = filmeService.listarTodos(); // Obtém a lista de todos os filmes através do serviço
        return new ResponseEntity<>(filmes, HttpStatus.OK); // Retorna a lista com status 200 (OK)
    }

    // Método para pesquisar um filme por ID
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<FilmeEntity> getFilmeById(@PathVariable Integer id) {
        FilmeEntity filme = filmeService.buscarPorId(id); // Busca um filme específico pelo ID
        if (filme != null) {
            return new ResponseEntity<>(filme, HttpStatus.OK); // Se o filme for encontrado, retorna com status 200 (OK)
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se não for encontrado, retorna status 404 (NOT FOUND)
    }

    // Método para adicionar um novo filme
    @PostMapping("/adicionar")
    public ResponseEntity<FilmeEntity> addFilme(@Valid @RequestBody FilmeEntity filme) {
        FilmeEntity novoFilme = filmeService.criarFilme(filme); // Cria um novo filme através do serviço
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED); // Retorna o filme criado com status 201 (CREATED)
    }

    // Método para atualizar um filme existente
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<FilmeEntity> atualizarFilme(@PathVariable Integer id, @Valid @RequestBody FilmeEntity filme) {
        FilmeEntity filmeAtualizado = filmeService.atualizarFilme(id, filme); // Atualiza o filme pelo ID
        if (filmeAtualizado != null) {
            return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK); // Se atualizado, retorna o filme com status 200 (OK)
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se não encontrado, retorna status 404 (NOT FOUND)
    }

    // Método para deletar um filme por ID
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Void> deletarFilme(@PathVariable Integer id) {
        FilmeEntity filme = filmeService.buscarPorId(id); // Busca o filme para verificar se ele existe
        if (filme != null) {
            filmeService.deletarFilme(id); // Se existir, deleta o filme pelo ID
            return new ResponseEntity<>(HttpStatus.OK); // Retorna status 200 (OK) após deletar
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Se não for encontrado, retorna status 404 (NOT FOUND)
    }
}
