package com.cinema.db.integradatabase.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "filmes")
public class FilmeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 100, message = "O título deve ter no máximo 100 caracteres")
    private String titulo;

    @NotBlank(message = "O gênero é obrigatório")
    @Size(max = 50, message = "O gênero deve ter no máximo 50 caracteres")
    private String genero;

    @Min(value = 1888, message = "Ano de lançamento inválido") // o primeiro filme do mundo foi la~çado em 1888 
    private Integer anoLancamento;

}
