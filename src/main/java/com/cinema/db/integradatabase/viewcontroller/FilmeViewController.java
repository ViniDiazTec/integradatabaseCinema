package com.cinema.db.integradatabase.viewcontroller;

import com.cinema.db.integradatabase.data.FilmeEntity;
import com.cinema.db.integradatabase.data.AnaliseEntity;
import com.cinema.db.integradatabase.service.FilmeService;
import com.cinema.db.integradatabase.service.AnaliseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;

@Controller
@RequestMapping("/filmes")
public class FilmeViewController {

    @Autowired
    private FilmeService filmeService;

    @Autowired
    private AnaliseService analiseService;

    // Exibe a lista de filmes
    @GetMapping
    public String listarFilmes(Model model) {
        List<FilmeEntity> filmes = filmeService.listarTodos();
        model.addAttribute("filmes", filmes);
        return "FilmeListar";
    }

    // Exibe a página para cadastrar um novo filme
    @GetMapping("/cadastro")
    public String mostrarCadastro(Model model) {
        model.addAttribute("filme", new FilmeEntity()); // Cria um novo objeto FilmeEntity
        return "FilmeDetalheEditaAnalise"; // Retorna a página para cadastro
    }

    // Salva um novo filme ou atualiza um filme existente
    @PostMapping("/salvar")
    public String salvarFilme(@Valid FilmeEntity filme, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filme", filme);
            return "FilmeDetalheEditaAnalise"; // Retorna para a página de edição em caso de erro
        }

        filmeService.criarFilme(filme);
        model.addAttribute("mensagemSucesso", "Filme salvo com sucesso!");
        return "redirect:/filmes";  // Redireciona para a lista após o salvamento
    }

    // Exibe a página de detalhes para editar um filme
    @GetMapping("/detalhe-editar")
    public String editarFilme(@RequestParam("filmeId") Integer filmeId, Model model) {
        FilmeEntity filme = filmeService.buscarPorId(filmeId);
        if (filme == null) {
            return "redirect:/filmes";
        }
        model.addAttribute("filme", filme);
        return "FilmeDetalheEditaAnalise";
    }

    // Método para salvar uma análise
    @PostMapping("/adicionar-analise")
    public String adicionarAnalise(@RequestParam Integer filmeId, @RequestParam String texto, @RequestParam Integer nota, Model model) {
        // Verifica se o texto possui pelo menos 10 caracteres
        if (texto.length() < 10) {
            model.addAttribute("mensagemErroAnalise", "O comentário deve ter no mínimo 10 caracteres.");
            return "redirect:/filmes/detalhe-editar?filmeId=" + filmeId; // Retorna à página de edição do filme
        }

        // Verifica se a nota está entre 1 e 10
        if (nota < 1 || nota > 10) {
            model.addAttribute("mensagemErroAnalise", "A nota deve estar entre 1 e 10.");
            return "redirect:/filmes/detalhe-editar?filmeId=" + filmeId; // Retorna à página de edição do filme
        }

        // Cria uma nova análise
        AnaliseEntity analise = new AnaliseEntity();
        FilmeEntity filme = filmeService.buscarPorId(filmeId);

        analise.setFilme(filme); // Define o filme relacionado à análise
        analise.setComentario(texto); // Define o texto da análise
        analise.setNota(nota); // Define a nota da análise

        // Salva a análise utilizando o serviço
        analiseService.criarAnalise(analise);

        model.addAttribute("mensagemSucessoAnalise", "Análise salva com sucesso!");
        return "redirect:/filmes/detalhe-editar?filmeId=" + filmeId; // Redireciona para a página de edição do filme
    }

    // Método para excluir um filme

}
