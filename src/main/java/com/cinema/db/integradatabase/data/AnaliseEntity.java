package com.cinema.db.integradatabase.data;

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
@Table(name = "analises")
public class AnaliseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "filme_id")
    @NotNull(message = "O campo filme é obrigatório")
    private FilmeEntity filme;

    @NotBlank(message = "O comentário não pode estar em branco")
    @Size(min = 10, message = "O comentário deve ter no mínimo 10 caracteres")
    private String comentario;

    @Min(value = 1, message = "A nota deve ser no mínimo 1")
    @Max(value = 10, message = "A nota deve ser no máximo 10")
    private int nota;
}

