package com.cinema.db.integradatabase.data; 

import jakarta.persistence.Entity; 
import jakarta.persistence.GeneratedValue; 
import jakarta.persistence.GenerationType; 
import jakarta.persistence.Id; 
import jakarta.persistence.Table; 
import lombok.Data;

@Data
@Entity
@Table(name = "filmes")
public class FilmeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer  id;

    private String titulo;
    private String genero;
    private Integer anoLancamento;

    
}
