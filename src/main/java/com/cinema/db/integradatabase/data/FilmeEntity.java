package com.cinema.db.integradatabase.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "filmes") // Define o nome da tabela no banco de dados
public class FilmeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Integer id;
    
    @OneToMany(mappedBy = "filme", cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Relacionamento com Análise
    private List<AnaliseEntity> analises = new ArrayList<>();  // Inicialize a lista de análises
    
    @NotBlank(message = "O título é obrigatório") // Validação do título
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres") // Tamanho máximo do título
    private String titulo;

    @NotBlank(message = "O gênero é obrigatório") // Validação do gênero
    @Size(max = 50, message = "O gênero deve ter no máximo 50 caracteres") // Tamanho máximo do gênero
    private String genero;

    @Min(value = 1888, message = "Ano de lançamento inválido") // Ano mínimo para o lançamento
    private Integer anoLancamento;

    @Size(max = 1000, message = "A sinopse deve ter no máximo 1000 caracteres") // Tamanho máximo da sinopse
    private String sinopse;
}
