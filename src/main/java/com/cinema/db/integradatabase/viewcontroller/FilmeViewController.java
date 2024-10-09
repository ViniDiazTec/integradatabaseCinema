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
        model.addAttribute("filme", new FilmeEntity());
        return "FilmeCadastro";
    }

    // Salva um novo filme ou atualiza um filme existente
    @PostMapping("/salvar")
    public String salvarFilme(@Valid FilmeEntity filme, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("filme", filme);
            return "FilmeDetalheEditaAnalise";
        }

        if (filme.getId() == null) {
            filmeService.criarFilme(filme);
            model.addAttribute("mensagemSucesso", "Filme salvo com sucesso!");
        } else {
            filmeService.atualizarFilme(filme.getId(), filme);
            model.addAttribute("mensagemSucesso", "Filme atualizado com sucesso!");
        }

        return "redirect:/filmes";
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
    public String adicionarAnalise(@RequestParam Integer filmeId, @RequestParam String comentario, @RequestParam Integer nota, Model model) {
        if (comentario.length() < 10) {
            model.addAttribute("mensagemErroAnalise", "O comentário deve ter no mínimo 10 caracteres.");
            return "redirect:/filmes/detalhe-editar?filmeId=" + filmeId;
        }

        if (nota < 1 || nota > 10) {
            model.addAttribute("mensagemErroAnalise", "A nota deve estar entre 1 e 10.");
            return "redirect:/filmes/detalhe-editar?filmeId=" + filmeId;
        }

        AnaliseEntity analise = new AnaliseEntity();
        FilmeEntity filme = filmeService.buscarPorId(filmeId);
        analise.setFilme(filme);
        analise.setComentario(comentario);
        analise.setNota(nota);

        analiseService.criarAnalise(analise);
        model.addAttribute("mensagemSucessoAnalise", "Análise salva com sucesso!");
        return "redirect:/filmes/detalhe-editar?filmeId=" + filmeId;
    }

    // Método para excluir um filme existente
    @PostMapping("/excluir")
    public String excluirFilme(@RequestParam("filmeId") Integer filmeId, Model model) {
        try {
            filmeService.excluirFilme(filmeId);
            model.addAttribute("mensagemSucesso", "Filme excluído com sucesso!");
        } catch (Exception e) {
            model.addAttribute("mensagemErro", "Erro ao excluir o filme: " + e.getMessage());
        }
        return "redirect:/filmes";
    }
}
