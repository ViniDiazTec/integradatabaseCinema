package com.cinema.db.integradatabase.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "analises") // Define o nome da tabela no banco de dados
public class AnaliseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Geração automática do ID
    private Integer id;

    @ManyToOne // Relacionamento muitos-para-um com FilmeEntity
    @JoinColumn(name = "filme_id") // Define a coluna da chave estrangeira
    @NotNull(message = "O campo filme é obrigatório") // Validação do campo filme
    private FilmeEntity filme;

    @NotBlank(message = "O comentário não pode estar em branco") // Validação do comentário
    @Size(min = 10, message = "O comentário deve ter no mínimo 10 caracteres") // Tamanho mínimo do comentário
    @Column(columnDefinition = "TEXT") // Especifica que o tipo da coluna é TEXT
    private String comentario;

    @Min(value = 1, message = "A nota deve ser no mínimo 1") // Validação do valor mínimo da nota
    @Max(value = 10, message = "A nota deve ser no máximo 10") // Validação do valor máximo da nota
    private int nota;
}